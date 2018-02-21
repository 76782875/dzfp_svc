package com.rjxx.taxeasy.bizcomm.utils;

import com.alibaba.fastjson.JSON;
import com.rjxx.comm.utils.ApplicationContextUtils;
import com.rjxx.taxeasy.bizcomm.utils.pdf.PdfDocumentGenerator;
import com.rjxx.taxeasy.bizcomm.utils.pdf.TwoDimensionCode;
import com.rjxx.taxeasy.config.RabbitmqUtils;
import com.rjxx.taxeasy.domains.*;
import com.rjxx.taxeasy.service.*;
import com.rjxx.taxeasy.vo.Fpcxvo;
import com.rjxx.taxeasy.vo.messageParams;
import com.rjxx.taxeasy.vo.smsEnvelopes;
import com.rjxx.utils.SignUtils;
import com.rjxx.utils.StringUtils;
import com.rjxx.utils.XmlJaxbUtils;
import org.apache.commons.codec.binary.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2017-05-11.
 */
@Service
public class GeneratePdfService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JylsService jylsService;

    @Autowired
    private KplsService kplsService;

    @Autowired
    private PdfDocumentGenerator pdg;

    @Autowired
    private CszbService cszbService;

    @Autowired
    private DataOperate dc;

    @Autowired
    private SendalEmail se;

    @Autowired
    private MailService mailService;

    @Autowired
    private SaveMessage saveMsg;

    @Autowired
    private GsxxService gsxxService;

    @Autowired
    private YjmbService yjmbService;
    @Autowired
    private JyxxsqService jyxxsqService;
    @Autowired
    private FphxwsjlService fphxwsjlService;

    @Autowired
    private ImputationCardUtil imputationCardUtil;

    @Autowired
    private InvoiceQueryUtil invoiceQueryUtil;


    @Autowired
    private RabbitmqUtils rabbitmqSend;

    @Value("${emailInfoUrl:}")
    private String emailInfoUrl;
    /**
     * 销方省份名称
     */
    private ConcurrentHashMap<Integer, String> xfSfMap = new ConcurrentHashMap<>();

    /**
     * 是否发送短信
     */
    private ConcurrentHashMap<Integer, Boolean> sffsdxMap = new ConcurrentHashMap<>();
    /**
     * @Description: 根据图片地址转换为base64编码字符串
     * @Author:
     * @CreateTime:
     * @return
     */
    public static String getImageStr(String imgFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
    public void generatePdf(int kplsh) {
        Kpls kpls=null;
        Jyls jyls=null;
        Jyxxsq jyxxsq=null;
        int djh=0;
        try {
            logger.info("----生成PDF方法名----generatePdf---"+kplsh);
            kpls = kplsService.findOne(kplsh);
            djh = kpls.getDjh();
            jyls = jylsService.findOne(djh);
            Map jyxxsqMap=new HashMap();
            jyxxsqMap.put("gsdm",kpls.getGsdm());
            jyxxsqMap.put("jylsh",jyls.getJylsh());
            jyxxsqMap.put("sqlsh",jyls.getSqlsh());
            jyxxsq=jyxxsqService.findOneByJylsh(jyxxsqMap);

            int xfid = jyls.getXfid();
            String sfmc = xfSfMap.get(xfid);
            if (StringUtils.isBlank(sfmc)) {
                //获取省份名称
                sfmc = kplsService.findSfmcByXfid(xfid);
                xfSfMap.put(xfid, sfmc);
            }
            Map<String, Object> map = new HashMap<>();
            map.put("SFMC", sfmc);
            map.put("FP_DM", kpls.getFpdm());
            map.put("FP_HM", kpls.getFphm());
            map.put("KPRQ", kpls.getKprq());
            map.put("JQBH", kpls.getSksbm());
            map.put("JYM", kpls.getJym());
            map.put("FP_MW", kpls.getMwq());
            map.put("EWM", kpls.getFpEwm());
            if (pdg.GeneratPDF(map, jyls, kpls)) {
                logger.info("----生成PDF方法名----generatePdf---"+kplsh);
                dc.updatePDFUrl(map, jyls, kpls);// 生成pdf的路径更新入库
                dc.updateFlag(jyls, "91");
                Map parms = new HashMap();
                parms.put("gsdm", kpls.getGsdm());
                Gsxx gsxx = gsxxService.findOneByParams(parms);
                //String url="https://vrapi.fvt.tujia.com/Invoice/CallBack";
                String url = gsxx.getCallbackurl();
                if (!("").equals(url) && url != null) {
                    String returnmessage = "";
                    if (kpls.getFpzldm().equals("12") && kpls.getGsdm().equals("Family")) {
                        returnmessage = this.CreateReturnMessage2(kpls.getKplsh());
                    } else if (kpls.getFpzldm().equals("12") && kpls.getGsdm().equals("mcake")) {
                        returnmessage = this.CreateReturnMessage(kpls.getKplsh());
                    }else if (kpls.getFpzldm().equals("12") && kpls.getGsdm().equals("fwk")) {
                        returnmessage = this.CreateReturnMessage3(kpls.getKplsh());
                    }else {
                        returnmessage = this.CreateReturnMessage(kpls.getKplsh());
                    }
                    //输出调用结果
                    logger.info("回写报文" + returnmessage);
                    if (returnmessage != null && !"".equals(returnmessage)) {
                        if(kpls.getGsdm().equals("afb")){
                            Map returnMap = this.httpPostNoSign(returnmessage, kpls);
                            logger.info("返回报文" + JSON.toJSONString(returnMap));
                        }if(kpls.getGsdm().equals("fwk")){
                            try {
                                String ss = this.netWebService(url, "CallBack", returnmessage, gsxx.getAppKey(), gsxx.getSecretKey());
                                String fwkReturnMessageStr = fwkReturnMessage(kpls);
                                logger.info("----------sap回写报文----------" + fwkReturnMessageStr);
                                String Data = HttpUtils.doPostSoap1_2(gsxx.getSapcallbackurl(), fwkReturnMessageStr, null, "Wendy", "Welcome9");
                                logger.info("----------fwk平台回写返回报文--------" + ss);
                                logger.info("----------sap回写返回报文----------" + Data);
                                Fphxwsjl fphxwsjl = new Fphxwsjl();
                                fphxwsjl.setGsdm("fwk");
                                fphxwsjl.setEnddate(new Date());
                                fphxwsjl.setReturncode("0000");
                                fphxwsjl.setStartdate(new Date());
                                fphxwsjl.setSecretKey("");
                                fphxwsjl.setSign("");
                                fphxwsjl.setWsurl(gsxx.getSapcallbackurl());
                                fphxwsjl.setReturncontent(fwkReturnMessageStr);
                                fphxwsjl.setReturnmessage(Data);
                                fphxwsjlService.save(fphxwsjl);
                            }catch (Exception e){
                                e.printStackTrace();
                                rabbitmqSend.sendMsg("ErrorException_Callback", kpls.getFpzldm(), kpls.getKplsh() + "");
                            }
                        } else{
                            Map returnMap = this.httpPost(returnmessage, kpls);
                            logger.info("返回报文" + JSON.toJSONString(returnMap));
                        }
                    }
                }
                //发送email
                if ("1".equals(jyls.getSffsyj()) && jyls.getGfemail() != null && !"".equals(jyls.getGfemail())) {
                    Kpls ls = new Kpls();
                    ls.setDjh(djh);
                    List<Kpls> listkpls = kplsService.findAllByKpls(ls);
                    Kpls kplsparms=new Kpls();
                    kplsparms.setSerialorder(listkpls.get(0).getSerialorder());
                    kplsparms.setGsdm(listkpls.get(0).getGsdm());
                    List<Kpls> lslist=kplsService.findAllByKpls(kplsparms);
                    List<String> pdfUrlList = new ArrayList<>();
                    boolean f=true;
                    for (Kpls kpls1 : lslist) {
                        if(null==kpls1.getPdfurl()||"".equals(kpls1.getPdfurl())){
                            f=false;
                            break;
                        }
                        pdfUrlList.add(kpls1.getPdfurl());
                    }
                    if(f) {
                        GetYjnr getYjnr = new GetYjnr();
                       /* Map gsxxmap = new HashMap();
                        gsxxmap.put("gsdm", kpls.getGsdm());
                        Gsxx gsxx = gsxxService.findOneByGsdm(gsxxmap);*/
                        Integer yjmbDm = gsxx.getYjmbDm();
                        Yjmb yjmb = yjmbService.findOne(yjmbDm);
                        String yjmbcontent = yjmb.getYjmbNr();
                        String q="";
                        String infoUrl="";
                        List<Fpcxvo> fpcxvos = invoiceQueryUtil.getInvoiceListByDdh(gsxx.getGsdm(), jyls.getDdh());
                        if(fpcxvos !=null && !fpcxvos.isEmpty()){
                            if(fpcxvos.get(0).getTqm()!=null && !fpcxvos.get(0).getTqm().equals("")){
                                q=fpcxvos.get(0).getTqm();
                                infoUrl=emailInfoUrl+"g="+gsxx.getGsdm()+"&q="+q;
                            }else if(fpcxvos.get(0).getKhh()!=null&&!fpcxvos.get(0).getKhh().equals("")){
                                q=fpcxvos.get(0).getKhh();
                                infoUrl=emailInfoUrl+"g="+gsxx.getGsdm()+"&q="+q;
                            }
                        }
                        Map csmap = new HashMap();
                        csmap.put("ddh", jyls.getDdh());
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                        csmap.put("ddrq", sdf.format(jyxxsq.getDdrq()));
                        csmap.put("pdfurls", pdfUrlList);
                        csmap.put("xfmc", jyls.getXfmc());
                        csmap.put("infoUrl",infoUrl);
                        SimpleDateFormat sdfw = new SimpleDateFormat("dd MMMM,yyyy",
                                Locale.ENGLISH);
                        csmap.put("ywdqrq", sdfw.format(new Date()));
                        // 二维码生成部分
                        TwoDimensionCode handler = new TwoDimensionCode();
                        ByteArrayOutputStream output = new ByteArrayOutputStream();
                        handler.encoderQRCode("http://fpj.datarj.com/einv/tq?q="+listkpls.get(0).getSerialorder(), output);// 二维码中数据的来源
                        String imgbase64string = org.apache.commons.codec.binary.Base64.encodeBase64String(output.toByteArray());
                        csmap.put("ewm", "data:image/jpeg;base64,"+imgbase64string);
                        String content = getYjnr.getFpkjYj(csmap, yjmbcontent);
                        try {
                            if(kpls.getGsdm().equals("afb")){
                                String [] to=new String[1];
                                to[0]=kpls.getGfemail();
                                String filePath=(String)map.get("BaseFilePath");
                                mailService.sendAttachmentsMail(to,"电子发票",content,filePath);
                            }else{
                                String gfEmailstr =kpls.getGfemail();// 购方email校验
                                if(gfEmailstr!=null&&!"".equals(gfEmailstr.trim())){
                                    String []gfEmailArray=gfEmailstr.split("，");
                                    for(String gfEmail:gfEmailArray){
                                        se.sendEmail(String.valueOf(kpls.getDjh()), kpls.getGsdm(),gfEmail , "发票开具成功发送邮件", String.valueOf(kpls.getDjh()), content, "电子发票");
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("邮件发送失败" + e.getMessage());
                        }
                    }
                    //发送手机短信
                    if(jyls.getSkpid()!=null){
                        Boolean sffsdx = sffsdxMap.get(jyls.getSkpid());
                        if (sffsdx == null) {
                            Cszb cszb = cszbService.getSpbmbbh(jyls.getGsdm(), jyls.getXfid(), jyls.getSkpid(), "sfktdx");
                            String dxfsFlag = cszb.getCsz();
                            if ("是".equals(dxfsFlag)) {
                                sffsdxMap.put(jyls.getSkpid(), true);
                                sffsdx = true;
                            } else {
                                sffsdxMap.put(jyls.getSkpid(), false);
                                sffsdx = false;
                            }
                        }
                        if (sffsdx) {
                            Cszb zb = cszbService.getSpbmbbh(jyls.getGsdm(), jyls.getXfid(), jyls.getSkpid(), "sfzdfs");
                            String zdfsFlag = zb.getCsz();
                            if ("是".equals(zdfsFlag)) {
                                String sjhm = jyls.getGfsjh();
                                Map<String, String> rep = new HashMap();
                                rep.put("jshj", jyls.getJshj() + "");
                                rep.put("tqm", jyls.getTqm());
                                if (sjhm != null && !"".equals(sjhm)) {
                                    try {
                                        if(jyls.getGsdm().equals("fwk")){
                                          /*  Map messageMap=new HashMap();
                                            messageMap.put("toPhoneNumber",jyls.getGfsjh());
                                            Map messageParams=new HashMap();
                                            messageParams.put("extractcode",jyls.getTqm());
                                            messageMap.put("messageParams",messageParams);
                                            messageMap.put("Messagetype","DigitalInvoiceCode");
                                            Map smsEnvelopesMap=new HashMap();
                                            smsEnvelopesMap.put("smsEnvelopes",messageMap);*/
                                            smsEnvelopes mb=new smsEnvelopes();
                                            mb.setToPhoneNumber(jyls.getGfsjh());
                                            messageParams messageParams=new messageParams();
                                            messageParams.setExtractcode(jyls.getTqm());
                                            mb.setMessageType("DigitalInvoiceCode");
                                            mb.setMessageParams(messageParams);
                                            List mblist=new ArrayList();
                                            mblist.add(mb);
                                            Map smsEnvelopesMap=new HashMap();
                                            smsEnvelopesMap.put("smsEnvelopes",mblist);
                                            logger.info("-----短信模板-------"+JSON.toJSONString(smsEnvelopesMap));
                                            HttpUtils.HttpPost_Basic(gsxx.getMessageurl(),JSON.toJSONString(smsEnvelopesMap));
                                        }else{
                                        saveMsg.saveMessage(jyls.getGsdm(), djh, sjhm, rep, "SMS_34725005", "泰易电子发票");
                                        Map param3 = new HashMap<>();
                                        param3.put("djh", djh);
                                        param3.put("dxzt", '1');
                                        jylsService.updateDxbz(param3);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        System.out.println("发送短信失败" + e.getMessage());
                                    }
                                }
                            } else {
                                Map param1 = new HashMap<>();
                                param1.put("djh", djh);
                                param1.put("dxzt", '2');
                                jylsService.updateDxbz(param1);
                            }
                        } else {
                            Map param2 = new HashMap<>();
                            param2.put("djh", djh);
                            param2.put("dxzt", '2');
                            jylsService.updateDxbz(param2);
                        }
                    }
                }
                dc.saveLog(djh, "91", "0", "正常开具", "", 1, jyls.getXfsh(), jyls.getJylsh());
                //开具成功后写入卡包或发票管家
                imputationCardUtil.invoice2card(jyxxsq,kpls);
            } else {
                dc.updateFlag(jyls, "92");
                logger.info("------1、生成pdf出现异常：---------" + kplsh);

                dc.saveLog(djh, "21", "1", "PdfDocumentGenerator：GeneratPDF", "生成pdf失败,服务异常",
                        1, jyls.getXfsh(), jyls.getJylsh());
                dc.updateKplsFpzt(kpls, "开具成功，但生成pdf失败,服务异常", "05");
            }
        } catch (Exception e) {
            dc.updateFlag(jyls, "92");
            dc.saveLog(djh, "21", "1", "PdfDocumentGenerator：GeneratPDF", "生成pdf失败,服务异常",
                    1, jyls.getXfsh(), jyls.getJylsh());
            dc.updateKplsFpzt(kpls, "开具成功,部分服务异常", "05");
            logger.info("------2、开具成功,部分服务出现异常：-------" + kplsh, e);
            throw new RuntimeException(e);
        }
    }

    public Map httpPostNoSign(String returnmessage, Kpls kpls) {
        Map parms=new HashMap();
        parms.put("gsdm",kpls.getGsdm());
        Gsxx gsxx=gsxxService.findOneByParams(parms);
        //String url="https://vrapi.fvt.tujia.com/Invoice/CallBack";
        String url=gsxx.getCallbackurl();
        String strMessage = "";
        BufferedReader reader = null;
        StringBuffer buffer = new StringBuffer();
        Map resultMap = null;
        if(!("").equals(url)&&url!=null){
            HttpPost httpPost = new HttpPost(url);
            CloseableHttpResponse response = null;
            RequestConfig requestConfig = RequestConfig.custom().
                    setSocketTimeout(120*1000).setConnectionRequestTimeout(120*1000).setConnectTimeout(120*1000).build();
            CloseableHttpClient httpClient = HttpClients.custom()
                    .setDefaultRequestConfig(requestConfig)
                    .build();
            //httpPost.setConfig(requestConfig);
            httpPost.addHeader("Content-Type", "application/json");
            try {
                Map nvps = new HashMap();
                nvps.put("invoiceData", returnmessage);
                StringEntity requestEntity = new StringEntity(JSON.toJSONString(nvps), "utf-8");
                logger.info("-------数据-----"+JSON.toJSONString(nvps));
                httpPost.setEntity(requestEntity);
                response = httpClient.execute(httpPost, new BasicHttpContext());
                if (response.getStatusLine().getStatusCode() != 200) {
                    System.out.println("request url failed, http code=" + response.getStatusLine().getStatusCode()
                            + ", url=" + url);
                    return null;
                }
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    reader = new BufferedReader(new InputStreamReader(entity.getContent(), "utf-8"));
                    while ((strMessage = reader.readLine()) != null) {
                        buffer.append(strMessage);
                    }
                }
                System.out.println("接收返回值:" + buffer.toString());
                resultMap = handerReturnMes(buffer.toString());
                String returnCode=resultMap.get("ReturnCode").toString();
                String ReturnMessage=resultMap.get("ReturnMessage").toString();
                Fphxwsjl fphxwsjl=new Fphxwsjl();
                fphxwsjl.setGsdm(kpls.getGsdm());
                fphxwsjl.setEnddate(new Date());
                fphxwsjl.setReturncode(returnCode);
                fphxwsjl.setStartdate(new Date());
                fphxwsjl.setSecretKey(gsxx.getSecretKey());
                fphxwsjl.setSign("");
                fphxwsjl.setWsurl(gsxx.getCallbackurl());
                fphxwsjl.setReturncontent(returnmessage);
                fphxwsjl.setReturnmessage(ReturnMessage);
                fphxwsjlService.save(fphxwsjl);
            } catch (IOException e) {
                System.out.println("request url=" + url + ", exception, msg=" + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (response != null) try {
                    response.close();
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultMap;
    }

    public static void main(String[] args) {
       /* GeneratePdfService generatePdfService= ApplicationContextUtils.getBean(GeneratePdfService.class);
        generatePdfService.generatePdf( 14688);*/
      String s= getSign("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
               "<Request>\n" +
               "    <ExtractCode>081820888801000000837</ExtractCode>\n" +
               "    <InvoiceItems count=\"1\">\n" +
               "        <InvoiceItem>\n" +
               "            <InvoiceCode>031001700111</InvoiceCode>\n" +
               "            <InvoiceNumber>97369803</InvoiceNumber>\n" +
               "            <InvoiceDate>20170818155605</InvoiceDate>\n" +
               "            <InvoiceStatus>正常发票</InvoiceStatus>\n" +
               "            <Amount>4.27</Amount>\n" +
               "            <TaxAmount>0.73</TaxAmount>\n" +
               "            <PdfUrl>http://test.datarj.com/e-invoice-file/9131000071785090X1/20170818/bc9f7e58-e5f6-46b3-822a-e13e2c04ff19.pdf</PdfUrl>\n" +
               "        </InvoiceItem>\n" +
               "    </InvoiceItems>\n" +
               "</Request>\n","bd79b66f566b5e2de07f1807c56b2469");
        System.out.println(s);

    }
    /**
     * .net webService
     * @param url
     * @param methodName
     * @param QueryData
     * @param AppId
     * @param key
     * @return
     */
    public String netWebService (String url,String methodName,String QueryData,String AppId,String key){
        String result="";
        try {
            logger.info("----------发送的报文------"+QueryData);
            JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
            Client client = dcf.createClient(url);
            String sign= SignUtils.getSign(QueryData,key);
            Object[] objects = client.invoke(methodName, AppId,QueryData,sign);
            //输出调用结果
            result = objects[0].toString();
            logger.info("----------接收返回值------"+result.toString());
            Map resultMap=new HashMap();
            resultMap = handerReturnMes(result.toString());
            String returnCode=resultMap.get("ReturnCode").toString();
            String ReturnMessage=resultMap.get("ReturnMessage").toString();
            Fphxwsjl fphxwsjl=new Fphxwsjl();
            fphxwsjl.setGsdm("fwk");
            fphxwsjl.setEnddate(new Date());
            fphxwsjl.setReturncode(returnCode);
            fphxwsjl.setStartdate(new Date());
            fphxwsjl.setSecretKey(key);
            fphxwsjl.setSign(sign);
            fphxwsjl.setWsurl(url);
            fphxwsjl.setReturncontent(QueryData);
            fphxwsjl.setReturnmessage(ReturnMessage);
            fphxwsjlService.save(fphxwsjl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public String   fwkReturnMessage(Kpls kpls) {
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
        String result="Succeed";
        if(kpls.getFpczlxdm().equals("12")){
            result="CancelSucceed";
        }
        String ss="\n" +
                "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:glob=\"http://sap.com/xi/SAPGlobal20/Global\">\n" +
                "   <soap:Header/>\n" +
                "   <soap:Body>\n" +
                "      <glob:GoldenTaxGoldenTaxCreateRequest_sync>\n" +
                "         <BasicMessageHeader></BasicMessageHeader>\n" +
                "         <GoldenTax>\n" +
                "            <CutInvID>"+kpls.getJylsh()+"</CutInvID>\n" +
                "            <GoldenTaxID>"+kpls.getFphm()+"</GoldenTaxID>\n" +
                "            <GoldenTaxDate>"+sim.format(kpls.getKprq())+"</GoldenTaxDate>\n"+
                "            <GoldenTaxResult>"+result+"</GoldenTaxResult>\n" +
                "            <GoldenTaxCode>"+kpls.getFpdm()+"</GoldenTaxCode>\n" +
                "         </GoldenTax>\n" +
                "      </glob:GoldenTaxGoldenTaxCreateRequest_sync>\n" +
                "   </soap:Body>\n" +
                "</soap:Envelope>";
        return ss;
    }
    public String CreateReturnMessage(Integer kplsh) {

        String Message="";
        Kpls kpls=kplsService.findOne(kplsh);
        Integer djh = kpls.getDjh();
        Map param4 = new HashMap<>();
        param4.put("djh", djh);
        Jyls jyls = jylsService.findJylsByDjh(param4);
        String ddh = jyls.getDdh(); // 查询原交易流水得ddh
        Map params=new HashMap();
        params.put("gsdm",kpls.getGsdm());
        params.put("serialorder",kpls.getSerialorder());
        List<Kpls> kplsList=kplsService.findKplsBySerialOrder(params);
        if(kpls.getFpczlxdm().equals("11")){
            ReturnData returnData=new ReturnData();
            OperationItem operationItem=new OperationItem();
            operationItem.setSerialNumber(kpls.getJylsh());
            operationItem.setOrderNumber(ddh);
            operationItem.setOperationType(kpls.getFpczlxdm());
            InvoiceItems invoiceItems=new InvoiceItems();
            List<InvoiceItem> invoiceItemList=new ArrayList<>();
            boolean f=true;
            for(int i=0;i<kplsList.size();i++){
                Kpls kpls2=kplsList.get(i);
                if(kpls2.getFpztdm().equals("00")||kpls2.getFpztdm().equals("05")){
                    InvoiceItem invoiceItem=new InvoiceItem();
                    if(kpls2.getFpztdm().equals("00")){
                        invoiceItem.setReturnCode("0000");
                        invoiceItem.setInvoiceStatus("正常发票");
                        invoiceItem.setReturnMessage("");
                    }else if(kpls2.getFpztdm().equals("05")){
                        invoiceItem.setReturnCode("9999");
                        invoiceItem.setInvoiceStatus("开具失败");
                        invoiceItem.setReturnMessage(kpls2.getErrorReason());
                    }
                    invoiceItem.setInvoiceCode(kpls2.getFpdm());
                    invoiceItem.setInvoiceNumber(kpls2.getFphm());
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
                    if(kpls2.getKprq()==null){
                        invoiceItem.setInvoiceDate(sdf.format(new Date()));
                    }else{
                        invoiceItem.setInvoiceDate(sdf.format(kpls2.getKprq()));
                    }
                    invoiceItem.setAmount(kpls2.getHjje().toString());
                    invoiceItem.setTaxAmount(kpls2.getHjse().toString());
                    invoiceItem.setPdfUrl(kpls2.getPdfurl());
                    invoiceItemList.add(invoiceItem);
                }else{
                    f=false;
                    break;
                }
            }
            invoiceItems.setCount(invoiceItemList.size());
            invoiceItems.setInvoiceItem(invoiceItemList);
            returnData.setOperationItem(operationItem);
            returnData.setInvoiceItems(invoiceItems);
            if(f){
                Message=XmlJaxbUtils.toXml(returnData);
            }else{
                Message="";
            }
        }else if(kpls.getFpczlxdm().equals("12")){
            ReturnData returnData=new ReturnData();
            OperationItem operationItem=new OperationItem();
            operationItem.setSerialNumber(kpls.getJylsh());
            operationItem.setOrderNumber(ddh);
            operationItem.setOperationType(kpls.getFpczlxdm());
            InvoiceItems invoiceItems=new InvoiceItems();
            List<InvoiceItem> invoiceItemList=new ArrayList<>();

            InvoiceItem invoiceItem=new InvoiceItem();
            if(kpls.getFpztdm().equals("00")){
                invoiceItem.setReturnCode("0000");
                invoiceItem.setInvoiceStatus("正常发票");
                invoiceItem.setReturnMessage("");
            }else if(kpls.getFpztdm().equals("05")){
                invoiceItem.setReturnCode("9999");
                invoiceItem.setInvoiceStatus("开具失败");
                invoiceItem.setReturnMessage(kpls.getErrorReason());
            }
            invoiceItem.setInvoiceCode(kpls.getFpdm());
            invoiceItem.setInvoiceNumber(kpls.getFphm());
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
            if(kpls.getKprq()==null){
                invoiceItem.setInvoiceDate(sdf.format(new Date()));
            }else{
                invoiceItem.setInvoiceDate(sdf.format(kpls.getKprq()));
            }
            invoiceItem.setAmount(kpls.getHjje().toString());
            invoiceItem.setTaxAmount(kpls.getHjse().toString());
            invoiceItem.setPdfUrl(kpls.getPdfurl());
            Kpls parms=new Kpls();
            parms.setFpdm(kpls.getHzyfpdm());
            parms.setFphm(kpls.getHzyfphm());
            Kpls ypkpls = kplsService.findByfphm(parms);
            InvoiceItem ypinvoiceItem=new InvoiceItem();
            if(ypkpls.getFpztdm().equals("02")){
                ypinvoiceItem.setReturnCode("0000");
                ypinvoiceItem.setInvoiceStatus("已红冲");
                ypinvoiceItem.setReturnMessage("");
            }else if(ypkpls.getFpztdm().equals("00")){
                ypinvoiceItem.setReturnCode("0000");
                ypinvoiceItem.setInvoiceStatus("正常发票");
                ypinvoiceItem.setReturnMessage("");
            }
            ypinvoiceItem.setInvoiceCode(ypkpls.getFpdm());
            ypinvoiceItem.setInvoiceNumber(ypkpls.getFphm());
            if(ypkpls.getKprq()==null){
                ypinvoiceItem.setInvoiceDate(sdf.format(new Date()));
            }else{
                ypinvoiceItem.setInvoiceDate(sdf.format(ypkpls.getKprq()));
            }
            ypinvoiceItem.setAmount(ypkpls.getHjje().toString());
            ypinvoiceItem.setTaxAmount(ypkpls.getHjse().toString());
            ypinvoiceItem.setPdfUrl(ypkpls.getPdfurl());
            invoiceItemList.add(invoiceItem);
            invoiceItemList.add(ypinvoiceItem);
            invoiceItems.setCount(invoiceItemList.size());
            invoiceItems.setInvoiceItem(invoiceItemList);
            returnData.setOperationItem(operationItem);
            returnData.setInvoiceItems(invoiceItems);
            Message=XmlJaxbUtils.toXml(returnData);

        }else if(kpls.getFpczlxdm().equals("14")){

            Map parameter =new HashMap();
            parameter.put("fpdm",kpls.getFpdm());
            parameter.put("fphm",kpls.getFphm());
            Jyxxsq jyxxsq=jyxxsqService.findOneByParams(parameter);

            ReturnData returnData=new ReturnData();
            OperationItem operationItem=new OperationItem();
            operationItem.setSerialNumber(jyxxsq.getJylsh());
            operationItem.setOrderNumber(jyxxsq.getDdh());
            operationItem.setOperationType(jyxxsq.getFpczlxdm());
            InvoiceItems invoiceItems=new InvoiceItems();
            List<InvoiceItem> invoiceItemList=new ArrayList<>();

            InvoiceItem invoiceItem=new InvoiceItem();
            if(kpls.getFpztdm().equals("08")){
                invoiceItem.setReturnCode("0000");
                invoiceItem.setInvoiceStatus("已作废");
                invoiceItem.setReturnMessage("");
            }else {
                invoiceItem.setReturnCode("9999");
                invoiceItem.setInvoiceStatus("开具失败");
                invoiceItem.setReturnMessage(kpls.getErrorReason());
            }
            invoiceItem.setInvoiceCode(kpls.getFpdm());
            invoiceItem.setInvoiceNumber(kpls.getFphm());
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");

            if(kpls.getZfrq()==null){
                invoiceItem.setInvoiceDate(sdf.format(new Date()));
            }else{
                invoiceItem.setInvoiceDate(sdf.format(kpls.getZfrq()));
            }
            invoiceItem.setAmount(kpls.getHjje().toString());
            invoiceItem.setTaxAmount(kpls.getHjse().toString());
            invoiceItem.setPdfUrl(kpls.getPdfurl());
            invoiceItemList.add(invoiceItem);

            invoiceItems.setCount(invoiceItemList.size());
            invoiceItems.setInvoiceItem(invoiceItemList);
            returnData.setOperationItem(operationItem);
            returnData.setInvoiceItems(invoiceItems);
            Message=XmlJaxbUtils.toXml(returnData);

        }
        return Message;
    }
    public String CreateReturnMessage3(Integer kplsh) {

        String Message="";
        Kpls kpls=kplsService.findOne(kplsh);
        Integer djh = kpls.getDjh();
        Map param4 = new HashMap<>();
        param4.put("djh", djh);
        Jyls jyls = jylsService.findJylsByDjh(param4);
        String ddh = jyls.getDdh(); // 查询原交易流水得ddh
        Map params=new HashMap();
        params.put("gsdm",kpls.getGsdm());
        params.put("serialorder",kpls.getSerialorder());
        List<Kpls> kplsList=kplsService.findKplsBySerialOrder(params);
        if(kpls.getFpczlxdm().equals("11")){
            ReturnData3 returnData=new ReturnData3();
            OperationItem3 operationItem=new OperationItem3();
            operationItem.setSerialNumber(kpls.getJylsh());
            operationItem.setOrderNumber(ddh);
            operationItem.setOperationType(kpls.getFpczlxdm());
            operationItem.setBuyerID(jyls.getBz());
            InvoiceItems3 invoiceItems=new InvoiceItems3();
            List<InvoiceItem3> invoiceItemList=new ArrayList<>();
            boolean f=true;
            for(int i=0;i<kplsList.size();i++){
                Kpls kpls2=kplsList.get(i);
                if(kpls2.getFpztdm().equals("00")||kpls2.getFpztdm().equals("05")){
                    InvoiceItem3 invoiceItem=new InvoiceItem3();
                    if(kpls2.getFpztdm().equals("00")){
                        invoiceItem.setReturnCode("0000");
                        invoiceItem.setInvoiceStatus("00");
                        invoiceItem.setReturnMessage("");
                    }else if(kpls2.getFpztdm().equals("05")){
                        invoiceItem.setReturnCode("9999");
                        invoiceItem.setInvoiceStatus("05");
                        invoiceItem.setReturnMessage(kpls2.getErrorReason());
                    }
                    invoiceItem.setInvoiceCode(kpls2.getFpdm());
                    invoiceItem.setInvoiceNumber(kpls2.getFphm());
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
                    if(kpls2.getKprq()==null){
                        invoiceItem.setInvoiceDate(sdf.format(new Date()));
                    }else{
                        invoiceItem.setInvoiceDate(sdf.format(kpls2.getKprq()));
                    }
                    invoiceItem.setAmount(kpls2.getHjje().toString());
                    invoiceItem.setTaxAmount(kpls2.getHjse().toString());
                    invoiceItem.setPdfUrl(kpls2.getPdfurl());
                    invoiceItem.setExtractCode(jyls.getTqm());
                    invoiceItemList.add(invoiceItem);
                }else{
                    f=false;
                    break;
                }
            }
            invoiceItems.setCount(invoiceItemList.size());
            invoiceItems.setInvoiceItem(invoiceItemList);
            returnData.setOperationItem(operationItem);
            returnData.setInvoiceItems(invoiceItems);
            if(f){
                Message=XmlJaxbUtils.toXml(returnData);
            }else{
                Message="";
            }
        }else if(kpls.getFpczlxdm().equals("12")){
            ReturnData3 returnData=new ReturnData3();
            OperationItem3 operationItem=new OperationItem3();
            operationItem.setSerialNumber(kpls.getJylsh());
            operationItem.setOrderNumber(ddh);
            operationItem.setOperationType(kpls.getFpczlxdm());
            operationItem.setBuyerID(jyls.getBz());
            InvoiceItems3 invoiceItems=new InvoiceItems3();
            List<InvoiceItem3> invoiceItemList=new ArrayList<>();

            InvoiceItem3 invoiceItem=new InvoiceItem3();
            if(kpls.getFpztdm().equals("00")){
                invoiceItem.setReturnCode("0000");
                invoiceItem.setInvoiceStatus("00");
                invoiceItem.setReturnMessage("");
            }else if(kpls.getFpztdm().equals("05")){
                invoiceItem.setReturnCode("9999");
                invoiceItem.setInvoiceStatus("05");
                invoiceItem.setReturnMessage(kpls.getErrorReason());
            }
            invoiceItem.setInvoiceCode(kpls.getFpdm());
            invoiceItem.setInvoiceNumber(kpls.getFphm());
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
            if(kpls.getKprq()==null){
                invoiceItem.setInvoiceDate(sdf.format(new Date()));
            }else{
                invoiceItem.setInvoiceDate(sdf.format(kpls.getKprq()));
            }
            invoiceItem.setAmount(kpls.getHjje().toString());
            invoiceItem.setTaxAmount(kpls.getHjse().toString());
            invoiceItem.setPdfUrl(kpls.getPdfurl());
            invoiceItem.setExtractCode(jyls.getTqm());
            Kpls parms=new Kpls();
            parms.setFphm(kpls.getHzyfphm());
            parms.setFpdm(kpls.getHzyfpdm());
            Kpls ypkpls=kplsService.findByfphm(parms);
            InvoiceItem3 ypinvoiceItem=new InvoiceItem3();
            if(ypkpls.getFpztdm().equals("02")){
                ypinvoiceItem.setReturnCode("0000");
                ypinvoiceItem.setInvoiceStatus("02");
                ypinvoiceItem.setReturnMessage("");
            }else if(ypkpls.getFpztdm().equals("00")){
                ypinvoiceItem.setReturnCode("0000");
                ypinvoiceItem.setInvoiceStatus("00");
                ypinvoiceItem.setReturnMessage("");
            }
            ypinvoiceItem.setInvoiceCode(ypkpls.getFpdm());
            ypinvoiceItem.setInvoiceNumber(ypkpls.getFphm());
            if(ypkpls.getKprq()==null){
                ypinvoiceItem.setInvoiceDate(sdf.format(new Date()));
            }else{
                ypinvoiceItem.setInvoiceDate(sdf.format(ypkpls.getKprq()));
            }
            ypinvoiceItem.setAmount(ypkpls.getHjje().toString());
            ypinvoiceItem.setTaxAmount(ypkpls.getHjse().toString());
            ypinvoiceItem.setPdfUrl(ypkpls.getPdfurl());
            ypinvoiceItem.setExtractCode(jyls.getTqm());
            invoiceItemList.add(invoiceItem);
            invoiceItemList.add(ypinvoiceItem);
            invoiceItems.setCount(invoiceItemList.size());
            invoiceItems.setInvoiceItem(invoiceItemList);
            returnData.setOperationItem(operationItem);
            returnData.setInvoiceItems(invoiceItems);
            Message=XmlJaxbUtils.toXml(returnData);

        }else if(kpls.getFpczlxdm().equals("14")){

            Map parameter =new HashMap();
            parameter.put("fpdm",kpls.getFpdm());
            parameter.put("fphm",kpls.getFphm());
            Jyxxsq jyxxsq=jyxxsqService.findOneByParams(parameter);

            ReturnData returnData=new ReturnData();
            OperationItem operationItem=new OperationItem();
            operationItem.setSerialNumber(jyxxsq.getJylsh());
            operationItem.setOrderNumber(jyxxsq.getDdh());
            operationItem.setOperationType(jyxxsq.getFpczlxdm());
            InvoiceItems invoiceItems=new InvoiceItems();
            List<InvoiceItem> invoiceItemList=new ArrayList<>();

            InvoiceItem invoiceItem=new InvoiceItem();
            if(kpls.getFpztdm().equals("08")){
                invoiceItem.setReturnCode("0000");
                invoiceItem.setInvoiceStatus("已作废");
                invoiceItem.setReturnMessage("");
            }else {
                invoiceItem.setReturnCode("9999");
                invoiceItem.setInvoiceStatus("开具失败");
                invoiceItem.setReturnMessage(kpls.getErrorReason());
            }
            invoiceItem.setInvoiceCode(kpls.getFpdm());
            invoiceItem.setInvoiceNumber(kpls.getFphm());
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");

            if(kpls.getZfrq()==null){
                invoiceItem.setInvoiceDate(sdf.format(new Date()));
            }else{
                invoiceItem.setInvoiceDate(sdf.format(kpls.getZfrq()));
            }
            invoiceItem.setAmount(kpls.getHjje().toString());
            invoiceItem.setTaxAmount(kpls.getHjse().toString());
            invoiceItem.setPdfUrl(kpls.getPdfurl());
            invoiceItemList.add(invoiceItem);

            invoiceItems.setCount(invoiceItemList.size());
            invoiceItems.setInvoiceItem(invoiceItemList);
            returnData.setOperationItem(operationItem);
            returnData.setInvoiceItems(invoiceItems);
            Message=XmlJaxbUtils.toXml(returnData);

        }
        return Message;
    }
    private static String getSign(String QueryData, String key) {
        String signSourceData = "data=" + QueryData + "&key=" + key;
        String newSign = DigestUtils.md5Hex(signSourceData);
        return newSign;
    }
    public  Map httpPost(String sendMes, Kpls kpls) throws Exception {
        Map parms=new HashMap();
        parms.put("gsdm",kpls.getGsdm());
        Gsxx gsxx=gsxxService.findOneByParams(parms);
        Map resultMap = null;
        try {
            //String url="https://vrapi.fvt.tujia.com/Invoice/CallBack";
            String url = gsxx.getCallbackurl();
            String strMessage = "";
            BufferedReader reader = null;
            StringBuffer buffer = new StringBuffer();

            if (!("").equals(url) && url != null) {
                String Secret = getSign(sendMes, gsxx.getSecretKey());
                logger.info("-----数据------" + sendMes + "-----key------" + gsxx.getSecretKey() + "-----签名-----" + Secret);
                HttpPost httpPost = new HttpPost(url);
                CloseableHttpResponse response = null;
                RequestConfig requestConfig = RequestConfig.custom().
                        setSocketTimeout(120 * 1000).setConnectionRequestTimeout(120 * 1000).setConnectTimeout(120 * 1000).build();
                CloseableHttpClient httpClient = HttpClients.custom()
                        .setDefaultRequestConfig(requestConfig)
                        .build();
                //httpPost.setConfig(requestConfig);
                httpPost.addHeader("Content-Type", "application/json");
                try {
                    Map nvps = new HashMap();
                    nvps.put("invoiceData", sendMes);
                    nvps.put("sign", Secret);
                    StringEntity requestEntity = new StringEntity(JSON.toJSONString(nvps), "utf-8");
                    logger.info("-------数据-----" + JSON.toJSONString(nvps));
                    httpPost.setEntity(requestEntity);
                    response = httpClient.execute(httpPost, new BasicHttpContext());
                    if (response.getStatusLine().getStatusCode() != 200) {
                        System.out.println("request url failed, http code=" + response.getStatusLine().getStatusCode()
                                + ", url=" + url);
                        return null;
                    }
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        reader = new BufferedReader(new InputStreamReader(entity.getContent(), "utf-8"));
                        while ((strMessage = reader.readLine()) != null) {
                            buffer.append(strMessage);
                        }
                    }
                    System.out.println("接收返回值:" + buffer.toString());
                    resultMap = handerReturnMes(buffer.toString());
                    String returnCode = resultMap.get("ReturnCode").toString();
                    String ReturnMessage = resultMap.get("ReturnMessage").toString();
                    Fphxwsjl fphxwsjl = new Fphxwsjl();
                    fphxwsjl.setGsdm(kpls.getGsdm());
                    fphxwsjl.setEnddate(new Date());
                    fphxwsjl.setReturncode(returnCode);
                    fphxwsjl.setStartdate(new Date());
                    fphxwsjl.setSecretKey(gsxx.getSecretKey());
                    fphxwsjl.setSign(Secret);
                    fphxwsjl.setWsurl(gsxx.getCallbackurl());
                    fphxwsjl.setReturncontent(sendMes);
                    fphxwsjl.setReturnmessage(ReturnMessage);
                    fphxwsjlService.save(fphxwsjl);
                } catch (IOException e) {
                    System.out.println("request url=" + url + ", exception, msg=" + e.getMessage());
                    e.printStackTrace();
                } finally {
                    if (response != null) try {
                        response.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            rabbitmqSend.sendMsg("ErrorException_Callback", kpls.getFpzldm(), kpls.getKplsh() + "");
        }
        return resultMap;
    }
    /**
     * 接收返回报文并做后续处理
     *
     * @param returnMes
     *
     * @throws Exception
     */
    public  Map handerReturnMes(String returnMes) throws Exception {

        Document document = DocumentHelper.parseText(returnMes);
        Element root = document.getRootElement();
        List<Element> childElements = root.elements();
        Map resultMap = new HashMap();
        for (Element child : childElements) {
            resultMap.put(child.getName(), child.getText());// 返回结果
        }
        return resultMap;
    }
    public String CreateReturnMessage2(Integer kplsh) {

        String Message="";
        Kpls kpls=kplsService.findOne(kplsh);
        Integer djh = kpls.getDjh();
        logger.info("-----------PDFURL--------------"+kpls.getPdfurl()+"-----kplsh--------"+kpls.getKplsh());
        Map param4 = new HashMap<>();
        param4.put("djh", djh);
        Jyls jyls = jylsService.findJylsByDjh(param4);
        String ddh = jyls.getDdh(); // 查询原交易流水得ddh
        Map params=new HashMap();
        params.put("gsdm",kpls.getGsdm());
        params.put("serialorder",kpls.getSerialorder());
        List<Kpls> kplsList=kplsService.findKplsBySerialOrder(params);
        if(kpls.getFpczlxdm().equals("11")){
            RetrunData2 returnData=new RetrunData2();
            returnData.setExtractCode(jyls.getTqm());
            InvoiceItems2 invoiceItems=new InvoiceItems2();
            List<InvoiceItem2> invoiceItemList=new ArrayList<>();
            boolean f=true;
            for(int i=0;i<kplsList.size();i++){
                Kpls kpls2=kplsList.get(i);
                if(kpls2.getFpztdm().equals("00")||kpls2.getFpztdm().equals("05")){
                    InvoiceItem2 invoiceItem=new InvoiceItem2();
                    if(kpls2.getFpztdm().equals("00")){
                        invoiceItem.setInvoiceStatus("00");
                    }else if(kpls2.getFpztdm().equals("05")){
                        invoiceItem.setInvoiceStatus("05");
                    }
                    invoiceItem.setInvoiceCode(kpls2.getFpdm());
                    invoiceItem.setInvoiceNumber(kpls2.getFphm());
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
                    if(kpls2.getKprq()==null){
                        invoiceItem.setInvoiceDate(sdf.format(new Date()));
                    }else{
                        invoiceItem.setInvoiceDate(sdf.format(kpls2.getKprq()));
                    }
                    invoiceItem.setAmount(kpls2.getHjje().toString());
                    invoiceItem.setTaxAmount(kpls2.getHjse().toString());
                    invoiceItem.setPdfUrl(kpls2.getPdfurl());
                    invoiceItemList.add(invoiceItem);
                }else{
                    f=false;
                    break;
                }
            }
            if(invoiceItemList.size()!=kplsList.size()){
                f=false;
            }
            invoiceItems.setCount(invoiceItemList.size());
            invoiceItems.setInvoiceItem(invoiceItemList);
            returnData.setInvoiceItems(invoiceItems);
            if(f){
                Message=XmlJaxbUtils.toXml(returnData);
            }else{
                Message="";
            }
        }else if(kpls.getFpczlxdm().equals("12")){
            RetrunData2 returnData=new RetrunData2();
            returnData.setExtractCode(jyls.getTqm());
            InvoiceItems2 invoiceItems=new InvoiceItems2();
            List<InvoiceItem2> invoiceItemList=new ArrayList<>();
            boolean f=true;
           // for(int i=0;i<kplsList.size();i++){
                Kpls kpls2=kpls;
                if(kpls2.getFpztdm().equals("00")||kpls2.getFpztdm().equals("05")){
                    InvoiceItem2 invoiceItem=new InvoiceItem2();
                    if(kpls2.getFpztdm().equals("00")){
                        invoiceItem.setInvoiceStatus("02");
                    }else if(kpls2.getFpztdm().equals("05")){
                        invoiceItem.setInvoiceStatus("05");
                    }
                    invoiceItem.setInvoiceCode(kpls2.getFpdm());
                    invoiceItem.setInvoiceNumber(kpls2.getFphm());
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
                    if(kpls2.getKprq()==null){
                        invoiceItem.setInvoiceDate(sdf.format(new Date()));
                    }else{
                        invoiceItem.setInvoiceDate(sdf.format(kpls2.getKprq()));
                    }
                    invoiceItem.setAmount(kpls2.getHjje().toString());
                    invoiceItem.setTaxAmount(kpls2.getHjse().toString());
                    invoiceItem.setPdfUrl(kpls2.getPdfurl());
                    invoiceItemList.add(invoiceItem);
                }else{
                    f=false;
                   // break;
                }
           // }
            /*if(invoiceItemList.size()!=kplsList.size()){
                f=false;
            }*/
            invoiceItems.setCount(invoiceItemList.size());
            invoiceItems.setInvoiceItem(invoiceItemList);
            returnData.setInvoiceItems(invoiceItems);
            if(f){
                Message=XmlJaxbUtils.toXml(returnData);
            }else{
                Message="";
            }

        }else if(kpls.getFpczlxdm().equals("14")){
            Map parameter =new HashMap();
            parameter.put("fpdm",kpls.getFpdm());
            parameter.put("fphm",kpls.getFphm());
            Jyxxsq jyxxsq=jyxxsqService.findOneByParams(parameter);
            RetrunData2 returnData=new RetrunData2();
            returnData.setExtractCode(jyls.getTqm());
            InvoiceItems2 invoiceItems=new InvoiceItems2();
            List<InvoiceItem2> invoiceItemList=new ArrayList<>();

            InvoiceItem2 invoiceItem=new InvoiceItem2();
            if(kpls.getFpztdm().equals("08")){
                invoiceItem.setInvoiceStatus("08");
            }else {
                invoiceItem.setInvoiceStatus("05");
            }
            invoiceItem.setInvoiceCode(kpls.getFpdm());
            invoiceItem.setInvoiceNumber(kpls.getFphm());
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");

            if(kpls.getZfrq()==null){
                invoiceItem.setInvoiceDate(sdf.format(new Date()));
            }else{
                invoiceItem.setInvoiceDate(sdf.format(kpls.getZfrq()));
            }
            invoiceItem.setAmount(kpls.getHjje().toString());
            invoiceItem.setTaxAmount(kpls.getHjse().toString());
            invoiceItem.setPdfUrl(kpls.getPdfurl());
            invoiceItemList.add(invoiceItem);

            invoiceItems.setCount(invoiceItemList.size());
            invoiceItems.setInvoiceItem(invoiceItemList);
            returnData.setInvoiceItems(invoiceItems);
            Message=XmlJaxbUtils.toXml(returnData);
        }
        return Message;
    }
}
