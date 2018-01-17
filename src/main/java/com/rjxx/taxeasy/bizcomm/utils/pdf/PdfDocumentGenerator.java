package com.rjxx.taxeasy.bizcomm.utils.pdf;

import com.rjxx.taxeasy.bizcomm.utils.DataOperate;
import com.rjxx.taxeasy.bizcomm.utils.PasswordConfig;
import com.rjxx.taxeasy.bizcomm.utils.SFtpUtil;
import com.rjxx.taxeasy.domains.*;
import com.rjxx.taxeasy.service.CszbService;
import com.rjxx.taxeasy.service.KppmxxService;
import com.rjxx.utils.*;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Scope("prototype")
public class PdfDocumentGenerator {
    @Autowired
    private KppmxxService kppmxxService;
    @Autowired
    private DataOperate dataOperate;
    @Autowired
    private CszbService cszbService;

    @Value("${pdf.save-path:}")
    private String pdfSavePath;

    @Value("${pdf.server-url:}")
    private String pdfServerUrl;

    private final Logger logger = LoggerFactory.getLogger(PdfDocumentGenerator.class);

    private final HtmlGenerator htmlGenerator;

    public PdfDocumentGenerator() {
        htmlGenerator = new HtmlGenerator();
    }

    /**
     * 使用模板,模板数据,生成pdf
     *
     * @param template   classpath中路径模板路径
     * @param documentVo 模板数据
     * @param outputFile 生成pdf的路径
     * @return
     * @throws
     * @Title: generate
     * @Description: 使用模板, 模板数据, 生成pdf
     * @author
     */
    public Map<String, Object> generate(Map<String, Object> map,
                                        String template, DocumentVo documentVo, String outputFile)
            throws Exception {
        Map<String, Object> variables = new HashMap<String, Object>();

        try {
            variables = documentVo.fillDataMap();
            String htmlContent = htmlGenerator.generate(template,
                    variables);
//            FileUtils.writeStringToFile(new File("C:\\aaa.html"),htmlContent,"UTF-8");
            generate(map, htmlContent, outputFile);
            logger.info("The document [primarykey="
                    + documentVo.findPrimaryKey()
                    + "] is generated successfully,and stored in ["
                    + outputFile + "]");
        } catch (Exception e) {
            String error = "The document [primarykey="
                    + documentVo.findPrimaryKey() + "] is failed to generate";
            logger.error(error);
            throw new Exception(error, e);
        }
        return map;
    }

    public Map<String, Object> generate(Map<String, Object> map,
                                        String htmlContent, String outputFile) throws Exception {
        OutputStream out = null;
        ITextRenderer iTextRenderer = null;
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();
            byte[] contenrBytes = htmlContent.getBytes("UTF-8");
            logger.info("HTML file length = " + contenrBytes.length);
            Document doc = builder.parse(new ByteArrayInputStream(contenrBytes));

            File f = new File(outputFile);
            if (f != null && !f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }

            out = new FileOutputStream(outputFile);
            iTextRenderer = (ITextRenderer) ITextRendererObjectFactory
                    .getObjectPool().borrowObject();// 获取对象池中对象
            //ITextFontResolver fontResolver = iTextRenderer.getFontResolver();
            //fontResolver.addFont("/config/fonts/STKAITI.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            //fontResolver.addFont("/config/fonts/STZhongsong.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

            SharedContext sharedContext = iTextRenderer.getSharedContext();

            // 解决base64图片支持问题
            sharedContext.setReplacedElementFactory(new B64ImgReplacedElementFactory());
            sharedContext.getTextRenderer().setSmoothingThreshold(0);
            // iTextRenderer.setDocumentFromString(strFileContent);
            try {
                iTextRenderer.setDocument(doc, null);
                iTextRenderer.layout();
                iTextRenderer.createPDF(out);

//                FileInputStream fis = new FileInputStream(outputFile);
//                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//                byte[] buffer = new byte[1024];
//                int len = 0;
//                // 将内容读到buffer中，读到末尾为-1
//                while ((len = fis.read(buffer)) != -1) {
//                    // 写到内存缓冲区中，起到保存每次内容的作用
//                    outStream.write(buffer, 0, len);
//                }
//                byte[] data = outStream.toByteArray(); // 取内存中保存的数据
//                fis.close();
//                logger.info("PDF file length = " + data.length);
//                logger.info("开始签名...");
                //String signData = SafeEngine.Sign(data, outputFile,jyls);
                //logger.info(signData);
                //map.put("signData", signData);
                // map.put("outputFile", outputFile);

            } catch (Exception e) {
                ITextRendererObjectFactory.getObjectPool().invalidateObject(iTextRenderer);
                iTextRenderer = null;
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (out != null)
                out.close();
            if (iTextRenderer != null) {
                try {
                    ITextRendererObjectFactory.getObjectPool().returnObject(iTextRenderer);
                } catch (Exception ex) {
                    logger.error("Cannot return object from pool.", ex);
                }
            }
        }
        return map;
    }

    /**
     * 获取购方信息销方信息字体大小
     *
     * @param data
     * @return
     */
    private int getGfxxXfxxFontSize(String data) {
        if (StringUtils.isBlank(data)) {
            return 9;
        }
        try {
            int dataLength = data.getBytes("gbk").length;
            if (dataLength <= 48) {
                return 9;
            } else if (dataLength > 48 && dataLength <= 58) {
                return 8;
            } else if (dataLength > 58 && dataLength <= 70) {
                return 7;
            }
            return 6;
        } catch (Exception e) {
            logger.error("", e);
        }
        return 9;
    }

    /**
     * 获取规格型号字体大小
     *
     * @param ggxh
     * @return
     */
    private int getSpggxhFontSize(String ggxh) {
        if (StringUtils.isBlank(ggxh)) {
            return 9;
        }
        try {
            int dataLength = ggxh.getBytes("gbk").length;
            if (dataLength <= 16) {
                return 9;
            } else if (dataLength > 16 && dataLength <= 18) {
                return 8;
            } else if (dataLength > 18 && dataLength <= 20) {
                return 7;
            } else {
                return 6;
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return 9;
    }

    /**
     * 获取商品名称字体大小
     *
     * @param spmc
     * @return
     */
    private int getSpmcFontSize(String spmc) {
        if (StringUtils.isBlank(spmc)) {
            return 9;
        }
        try {
            int dataLength = spmc.getBytes("gbk").length;
            if (dataLength <= 26) {
                return 9;
            } else if (dataLength > 26 && dataLength <= 30) {
                return 8;
            } else if (dataLength > 30 && dataLength <= 36) {
                return 7;
            } else {
                return 6;
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return 9;
    }
    /**
     * 获取商品数量字体大小
     *
     * @param spsl
     * @return
     */
    private int getSpslFontSize(String spsl) {
        if(spsl.length()>15){
            spsl=spsl.substring(0,14);
        }
        if (StringUtils.isBlank(spsl)) {
            return 9;
        }
        try {
            int dataLength = spsl.getBytes("gbk").length;
            if (dataLength <= 6) {
                return 9;
            } else if (dataLength > 6 && dataLength <= 10) {
                return 8;
            } else if (dataLength > 10 && dataLength <= 12) {
                return 7;
            } else {
                return 6;
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return 9;
    }

    /**
     * 获取商品单价字体大小
     * @param spdj
     * @return
     */
    private int getSpdjFontSize(String spdj) {
        if(spdj.length()>17){
            spdj=spdj.substring(0,16);
        }
        if (StringUtils.isBlank(spdj)) {
            return 9;
        }
        try {
            int dataLength = spdj.getBytes("gbk").length;
            if (dataLength <= 6) {
                return 9;
            } else if (dataLength > 6 && dataLength <= 10) {
                return 8;
            } else if (dataLength > 10 && dataLength <= 12) {
                return 7;
            } else if (dataLength > 12 && dataLength <= 14){
                return 6;
            }else{
                return 5;
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return 9;
    }
    public boolean GeneratPDF(Map<String, Object> map, Jyls jyls, Kpls kpls)
             {
        try {
            long start = System.currentTimeMillis();

            // 模板数据
            String xfsh = kpls.getXfsh();
            String gsdm = kpls.getGsdm();
            Map<String, Object> params = new HashMap<>();
            params.put("xfsh", xfsh);
            params.put("gsdm", gsdm);
            Kppmxx kppmxx = kppmxxService.findOneByParams(params);
            String skr = kpls.getSkr() == null ? "" : kpls.getSkr();
            String kpr = kpls.getKpr();
            String fhr = kpls.getFhr() == null ? "" : kpls.getFhr();
            String xfdz = kpls.getXfdz();
            String xfyh = kpls.getXfyh();
            String xfyhzh = kpls.getXfyhzh();
            String xfdh = kpls.getXfdh();
            if (kppmxx != null) {
                if (!"auto".equals(kppmxx.getSkr())) {
                    skr = kppmxx.getSkr() == null ? "" : kppmxx.getSkr();
                }
                if (!"auto".equals(kppmxx.getKpr())) {
                    kpr = kppmxx.getKpr() == null ? "" : kppmxx.getKpr();
                }
                if (!"auto".equals(kppmxx.getFhr())) {
                    fhr = kppmxx.getFhr() == null ? "" : kppmxx.getFhr();
                }
                if (!"auto".equals(kppmxx.getXfdz())) {
                    xfdz = kppmxx.getXfdz() == null ? "" : kppmxx.getXfdz();
                }
                if (!"auto".equals(kppmxx.getXfdh())) {
                    xfdh = kppmxx.getXfdh() == null ? "" : kppmxx.getXfdh();
                }
                if (!"auto".equals(kppmxx.getXfyh())) {
                    xfyh = kppmxx.getXfyh() == null ? "" : kppmxx.getXfyh();
                }
                if (!"auto".equals(kppmxx.getXfyhzh())) {
                    xfyhzh = kppmxx.getXfyhzh() == null ? "" : kppmxx.getXfyhzh();
                }
            }
            FpPdfInfo in_request = new FpPdfInfo();
            in_request.setSfmc((String) map.get("SFMC"));
            in_request.setGsdm(gsdm);
            in_request.setDdh(jyls.getDdh());
            in_request.setGfdz(kpls.getGfdz() == null ? "" : kpls.getGfdz());
            in_request.setGfyh(kpls.getGfyh() == null ? "" : kpls.getGfyh());
            in_request.setGfyhzh(kpls.getGfyhzh() == null ? "" : kpls.getGfyhzh());
            String gfmc = kpls.getGfmc();
            in_request.setGfmc(gfmc);
            in_request.setGfsh(kpls.getGfsh() == null ? "" : kpls.getGfsh());
            in_request.setGfdh(kpls.getGfdh() == null ? "" : kpls.getGfdh());
            in_request.setKpr(kpr);
            in_request.setSkr(skr);
            in_request.setFhr(fhr);
            // in_request.setRemark(ir.getRemark());
            in_request.setXfdz(xfdz==null?"":xfdz);
            in_request.setXfyh(xfyh==null?"":xfyh);
            in_request.setXfyhzh(xfyhzh==null?"":xfyhzh);
            in_request.setXfmc(kpls.getXfmc());
            in_request.setXfdh(xfdh==null?"":xfdh);

            //处理购方销方字体大小
            in_request.setGfmcSize(getGfxxXfxxFontSize(in_request.getGfmc()));
            in_request.setGfdzdhSize(getGfxxXfxxFontSize(in_request.getGfdz() + "　" + in_request.getGfdh()));
            in_request.setGfyhzhSize(getGfxxXfxxFontSize(in_request.getGfyh() + "　" + in_request.getGfyhzh()));
            in_request.setXfmcSize(getGfxxXfxxFontSize(in_request.getXfmc()));
            in_request.setXfdzdhSize(getGfxxXfxxFontSize(in_request.getXfdz() + "　" + in_request.getXfdh()));
            in_request.setXfyhzhSize(getGfxxXfxxFontSize(in_request.getXfyh() + "　" + in_request.getXfyhzh()));

            in_request.setXfsh(xfsh);
            in_request.setYfpdm((String) map.get("FP_DM"));
            in_request.setYfphm((String) map.get("FP_HM"));
            String bz = kpls.getBz() == null ? "" : kpls.getBz();
            bz = bz.replaceAll("\\n", "<br/>");
//        if ("12".equals(jyls.getFpczlxdm()) || "13".equals(jyls.getFpczlxdm())) {
//            if (StringUtils.isBlank(bz)) {
//                bz = "对应正数发票代码:" + jyls.getYfpdm() + "号码:" + jyls.getYfphm();
//            } else {
//                bz += "<br/>对应正数发票代码:" + jyls.getYfpdm() + "号码:" + jyls.getYfphm();
//            }
//
//        }
            in_request.setBz(bz);
            Date kprq = kpls.getKprq();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            String dateString = formatter.format(kprq);
            in_request.setKprq(ChinaNumber.getYMDNumber(dateString));
            in_request.setJqbh((String) map.get("JQBH"));
            in_request.setJym(ChinaNumber.getWs((String) map.get("JYM")));
            String fpmw = (String) map.get("FP_MW");
            in_request.setFpmw(fpmw);
            if (StringUtils.isNotBlank(fpmw)) {
                fpmw = fpmw.replaceAll("\n", "");
                int lineLength = fpmw.length() / 4;
                in_request.setFpmw1(fpmw.substring(0, lineLength));
                in_request.setFpmw2(fpmw.substring(lineLength, lineLength * 2));
                in_request.setFpmw3(fpmw.substring(lineLength * 2, lineLength * 3));
                in_request.setFpmw4(fpmw.substring(lineLength * 3));
            }
            String imagePath = ResourceLoader.getPath("config/images");
            in_request.setImagePath(imagePath);
            // pdf的存储路径
            String tempPath = pdfSavePath;
            tempPath = tempPath.replaceAll("\\\\", "/");
            String serverUrl = pdfServerUrl;
            if (StringUtils.isNotBlank((String) map.get("pdfUrl"))) {
                String pdfUrl = (String) map.get("pdfUrl");
                if (!pdfUrl.contains(serverUrl)) {
                    int pos = serverUrl.indexOf("/", 10);
                    serverUrl = serverUrl.substring(0, pos + 1);
                    pos = tempPath.lastIndexOf("/", tempPath.length() - 2);
                    tempPath = tempPath.substring(0, pos + 1);
                }
            }
            String outputFile_AbsolutePath = null;
            if(kpls.getGsdm().equals("afb")){
                outputFile_AbsolutePath = xfsh +"/" + UUID.randomUUID().toString()+ ".pdf";
            }else {
                outputFile_AbsolutePath = xfsh + "/"
                        + dateString + "/" + UUID.randomUUID().toString() + ".pdf";
            }
            String outputFile = tempPath + outputFile_AbsolutePath;

            // 发票明细部分
            List<Kpspmx> t_kpspmxes = dataOperate.getPDFSpmx(kpls.getKplsh());
            DecimalFormat df = new DecimalFormat("#0.00");
            DecimalFormat dfsl = new DecimalFormat("#0.00####");
            DecimalFormat dfdj = new DecimalFormat("#0.00##########");

            // 合计金额部分
            String total = df.format(kpls.getJshj());
            String totalAmount = df.format(kpls.getHjje());     //le.getTotalAmount(djh, jyspmxs);// 两位小数已保留
            String totalTaxAmount = df.format(kpls.getHjse());  //le.getTotalTaxAmount(total, totalAmount);

            in_request.setJshjdx(ChinaNumber.getCHSNumber(total));// 中文大写表示
            in_request.setTotalString(total);
            in_request.setTotalAmountString(totalAmount);

            List<FpPdfMxInfo> pdfMxList = new ArrayList<>();
            //免税标志
            boolean freeDutyFlag = false;
            // 商品明细信息 已处理数据小于1的情况
            if (!"af".equals(gsdm)) {
                for (int i = 0; i < t_kpspmxes.size(); i++) {
                    Kpspmx t_kpspmx = t_kpspmxes.get(i);
                    String s = String.valueOf(i + 1);
                    String sl = LeviedSeparate.getTaxRate(t_kpspmx.getSpsl());//t_kpspmx.getSpsl() == null ? 0 : t_kpspmx.getSpsl();
                    Double sps = t_kpspmx.getSps();
                    //数量
                    String xmsl = "";
                    if (sps != null && sps != 0) {
                        xmsl = dfsl.format(sps);
                    }
                    //单价
                    Double dj = t_kpspmx.getSpdj();
                    String xmdj = "";
                    if (dj != null && dj != 0) {
                        xmdj = dfdj.format(dj);
                    }
                    FpPdfMxInfo fpPdfMxInfo = new FpPdfMxInfo(t_kpspmx.getSpmc(),//商品名称
                            t_kpspmx.getSpggxh() == null ? "" : t_kpspmx.getSpggxh(),//规格型号
                            t_kpspmx.getSpdw() == null ? "" : t_kpspmx.getSpdw(),//商品单位
                            xmsl,//商品数量
                            xmdj,
                            df.format(t_kpspmx.getSpje()),
                            sl,
                            df.format(t_kpspmx.getSpse()),
                            s
                    );
                    //处理优惠政策:免费
                    if ("1".equals(t_kpspmx.getYhzcbs()) && "1".equals(t_kpspmx.getLslbz()) && "免税".equals(t_kpspmx.getYhzcmc())) {
                        fpPdfMxInfo.setSl("免税");
                        fpPdfMxInfo.setSe("***");
                        freeDutyFlag = true;
                    }else if("1".equals(t_kpspmx.getYhzcbs()) && "2".equals(t_kpspmx.getLslbz()) && "不征税".equals(t_kpspmx.getYhzcmc())){
                        fpPdfMxInfo.setSl("不征税");
                        fpPdfMxInfo.setSe("***");
                        freeDutyFlag = true;
                    }
                    //处理商品名称字体大小
                    fpPdfMxInfo.setSpmcSize(getSpmcFontSize(fpPdfMxInfo.getSpmc()));
                    //处理规格型号字体大小
                    fpPdfMxInfo.setSpggxhSize(getSpggxhFontSize(fpPdfMxInfo.getSpggxh()));
                    //处理商品数量字体大小
                    fpPdfMxInfo.setSpslSize(getSpslFontSize(xmsl));
                    //处理商品单价字体大小
                    fpPdfMxInfo.setSpdjSize(getSpdjFontSize(xmdj));
                    pdfMxList.add(fpPdfMxInfo);
                }
            } else {

                /*****************************/
                //TODO 现仅针对A&F要求，将所有明细合为1条，且商品数也为1
                Kpspmx t_kpspmx = t_kpspmxes.get(0);
                String s = String.valueOf(1);
                String sl = LeviedSeparate.getTaxRate(t_kpspmx.getSpsl());//t_kpspmx.getSpsl() == null ? 0 : t_kpspmx.getSpsl();
                Double xmsl = t_kpspmx.getSps();//*(t_kpspmxes.size());//t_kpspmx.getSps() == null ? 0 : t_kpspmx.getSps() ;
                FpPdfMxInfo fpPdfMxInfo = new FpPdfMxInfo(t_kpspmx.getSpmc(),
                        t_kpspmx.getSpggxh() == null ? "" : t_kpspmx.getSpggxh(),
                        t_kpspmx.getSpdw() == null ? "" : t_kpspmx.getSpdw(), df.format(/*xmsl*/1.00),
                        df.format(Double.parseDouble(totalAmount)), df.format(Double.parseDouble(totalAmount)), sl, df.format(Double.parseDouble(totalTaxAmount)), s);
                //处理商品名称字体大小
                fpPdfMxInfo.setSpmcSize(getSpmcFontSize(fpPdfMxInfo.getSpmc()));
                //处理规格型号字体大小

                fpPdfMxInfo.setSpggxhSize(getSpggxhFontSize(fpPdfMxInfo.getSpggxh()));
                //处理商品数量字体大小
                fpPdfMxInfo.setSpslSize(getSpslFontSize( df.format(/*xmsl*/1.00)));
                //处理商品单价字体大小
                fpPdfMxInfo.setSpdjSize(getSpdjFontSize( df.format(Double.parseDouble(totalAmount))));
                pdfMxList.add(fpPdfMxInfo);
                /*****************************/
            }
            in_request.setJyspmxls(pdfMxList);
            // 二维码生成部分
            String qrcode = (String) map.get("EWM");
            String imgbase64string = null;
            if (StringUtils.isBlank(qrcode)) {
                String qrcode1 = "01,10," + kpls.getFpdm() + "," + kpls.getFphm() + "," + total + "," + dateString + "," + kpls.getJym();
                // 二维码生成部分
                TwoDimensionCode handler = new TwoDimensionCode();
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                handler.encoderQRCode(qrcode1, output);// 二维码中数据的来源
                imgbase64string = Base64.encodeBase64String(output.toByteArray());
            } else {
                imgbase64string = qrcode;
            }
            if (freeDutyFlag && "0.00".equals(totalTaxAmount)) {
                //有免税并且合计税额为0的话
                totalTaxAmount = "***";
            } else {
                totalTaxAmount = "￥" + totalTaxAmount;
            }
            in_request.setTotalTaxAmountString(totalTaxAmount);

            map.put("qrcode", imgbase64string);

            // String imgbase64string = Base64.encodeBase64String(output.toByteArray());
            //20161123 kzx 修改二维码生成逻辑  end
            imgbase64string = imgbase64string.replaceAll("\r\n", "");
            in_request.setBase64Image(imgbase64string);
            // classpath 中模板路径

            String template = "config/templates/invoice2.html";
            //发票种类为纸票且设置生成纸票pdf再生成
            if(kpls.getFpzldm().equals("01")){
                template = "config/templates/invoice_zp.html";
            }
            //PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
            // 生成pdf
            generate(map, template, in_request, outputFile);
            //对pdf进行电子签章
            String sourcePdfPath = outputFile;
            if (map.get("pdfUrl") != null) {
                String pdfUrl = (String) map.get("pdfUrl");
                outputFile_AbsolutePath = pdfUrl.replace(serverUrl, "");
            } else {
                if(kpls.getGsdm().equals("afb")){
                    outputFile_AbsolutePath = xfsh +"/" + kpls.getJylsh()+ ".pdf";
                }else{
                    Cszb cszb = cszbService.getSpbmbbh(kpls.getGsdm(), kpls.getXfid(), kpls.getSkpid(), "pdfnamerules");
                    if(cszb.getCsz().equals("uuid")){
                        outputFile_AbsolutePath = xfsh + "/"
                                + dateString + "/" + UUID.randomUUID().toString() + ".pdf";
                    }else{
                        String pdfname=getPdfname(kpls,jyls,cszb.getCsz());
                        outputFile_AbsolutePath = xfsh + "/"
                                + dateString + "/" + pdfname + ".pdf";
                    }
                }
            }
            outputFile = tempPath + outputFile_AbsolutePath;
            String signImagePath = ResourceLoader.getPath("config/images") + "/"+"sign.png";
            PdfSignUtils.eInvoicePdfSign(sourcePdfPath, signImagePath, outputFile);
            //签章成功，删除源文件
            new File(sourcePdfPath).delete();

            //pdf生成jpg文件
            //先生成带章的pdf
            /*String template2 = "config/templates/invoice2.html";
            if(kpls.getFpzldm().equals("01")){
                template2 = "config/templates/invoice_zp.html";
            }
            String tmpPdfPath=null;
            if(kpls.getGsdm().equals("afb")){
                tmpPdfPath = tempPath + xfsh  + "/" + kpls.getJylsh() + "_tmp.pdf";
            }else{
                 tmpPdfPath = tempPath + xfsh + "/" + dateString + "/" + UUID.randomUUID().toString() + "_tmp.pdf";
            }
            generate(map, template2, in_request, tmpPdfPath);*/

            int pos = outputFile.lastIndexOf(".");
            String jpgFile = outputFile.substring(0, pos) + ".jpg";
            ImgPdfUtils.changePdfToImg(new File(outputFile), jpgFile);
            //删除原始pdf
            //new File(sourcePdfPath).delete();
            System.err.println("pdf转换成jpg耗时time=" + (System.currentTimeMillis() - start)
                    / 1000);

            //由于javasafeengine的使用jar过老，替换新的生成摘要信息的方法,2016-09-18，以pdf是否有图片签章为区别
            //String signData = SafeEngine.Sign(outputFile, jyls);
            String keyStorePath = ResourceLoader.getPath(PasswordConfig.keyStorePath);
            String sAlias = PasswordConfig.PDF_SIGNUSER;//私钥别名；  Rjxx1234 sKeyPin私钥密码
            String signData = CertificateUtils.signFileToBase64(outputFile, keyStorePath, sAlias, PasswordConfig.PDF_SIGNPASSWORD);
            map.put("outputFile", serverUrl + outputFile_AbsolutePath);
            logger.info("------pdf路径-------"+serverUrl);
            logger.info("------pdf路径-------"+serverUrl + outputFile_AbsolutePath);
            Cszb cszb = cszbService.getSpbmbbh(kpls.getGsdm(), kpls.getXfid(), kpls.getSkpid(), "sfuploadftp");
            if(cszb.getCsz().equals("是")){
                FileInputStream in=new FileInputStream(new File(outputFile));
                SFtpUtil.uploadFile(PasswordConfig.FTP_URL,PasswordConfig.FTP_PORT,PasswordConfig.FTP_USERNAME,PasswordConfig.FTP_PASSWORD,PasswordConfig.FTP_BASEPATH,PasswordConfig.FTP_FILEPATH,kpls.getJylsh()+".pdf",in);
            }
            map.put("signData", signData);
            map.put("BaseFilePath",outputFile);
            return true;
        }catch (Exception e){
            logger.error("生成pdf出现异常：" + kpls.getKplsh(), e);
            return false;
        }
    }
    public String getPdfname(Kpls kpls, Jyls jyls,String content){
        Map<String,Object> csmap=new HashMap<String,Object>();
        csmap.put("gfmc",kpls.getGfmc().toString());
        csmap.put("jylsh",kpls.getJylsh().toString());
        csmap.put("kplsh",kpls.getKplsh().toString());
        csmap.put("ddh",jyls.getDdh().toString());
        csmap.put("tqm",jyls.getTqm().toString());
        csmap.put("gfsh",kpls.getGfsh().toString());
        csmap.put("ddrq",new SimpleDateFormat("yyyyMMdd").format(jyls.getJylssj()));
        csmap.put("kprq",new SimpleDateFormat("yyyyMMdd").format(kpls.getKprq()));
        for (Map.Entry<String, Object> entry : csmap.entrySet()) {
            String key=entry.getKey();
            String value=(String)entry.getValue();
            content=content.replace(key,value);
        }
        return content;
    }
    public static void main(String[] args) throws Exception {
        DecimalFormat df = new DecimalFormat("######0.000000");
        //System.out.println(df.format(0.00));
    }
}
