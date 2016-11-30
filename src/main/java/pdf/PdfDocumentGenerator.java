package pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServlet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.w3c.dom.Document;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.rjxx.taxeasy.domains.Jyls;
import com.rjxx.taxeasy.domains.Kpls;
import com.rjxx.taxeasy.domains.Kppmxx;
import com.rjxx.taxeasy.domains.Kpspmx;
import com.rjxx.taxeasy.vo.FpPdfInfo;
import com.rjxx.taxeasy.vo.FpPdfMxInfo;
import com.rjxx.utils.B64ImgReplacedElementFactory;
import com.rjxx.utils.CertificateUtils;
import com.rjxx.utils.ChinaNumber;
import com.rjxx.utils.ImgPdfUtils;
import com.rjxx.utils.LeviedSeparate;
import com.rjxx.utils.PdfSignUtils;
import com.rjxx.utils.ReadProperties;
import com.rjxx.utils.ResourceLoader;
import com.rjxx.utils.TwoDimensionCode;

import freemaker.HtmlGenerator;
import pdf.factory.ITextRendererObjectFactory;

public class PdfDocumentGenerator extends HttpServlet {
    private final static Logger logger = Logger.getLogger(PdfDocumentGenerator.class);

    private final static HtmlGenerator htmlGenerator;

    static {
        htmlGenerator = new HtmlGenerator();
    }

    /**
     * 使用模板,模板数据,生成pdf
     *
     * @param template   classpath中路径模板路径
     * @param documentVo 模板数据
     * @param outputFile 生成pdf的路径
     * @return
     * @throws DocumentGeneratingException
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
            String htmlContent = this.htmlGenerator.generate(template,
                    variables);
            this.generate(map, htmlContent, outputFile);
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

    public static boolean GeneratPDF(Map<String, Object> map, Jyls jyls, Kpls kpls)
            throws Exception {
        long start = System.currentTimeMillis();
        String logConfigPath = ResourceLoader.getPath("log4j.properties");
        PropertyConfigurator.configure(logConfigPath);

        // 模板数据
        String xfsh = kpls.getXfsh();
        String gsdm = kpls.getGsdm();
        Kppmxx kppmxx = Kppmxx.findFirst(Kppmxx.class, "xfsh = ? and gsdm = ?", new Object[]{xfsh, gsdm});
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
        in_request.setXfdz(xfdz);
        in_request.setXfyh(xfyh);
        in_request.setXfyhzh(xfyhzh);
        in_request.setXfmc(kpls.getXfmc());
        in_request.setXfdh(xfdh);

        in_request.setXfsh(xfsh);
        in_request.setYfpdm((String) map.get("FP_DM"));
        in_request.setYfphm((String) map.get("FP_HM"));
        String bz = jyls.getBz() == null ? "" : jyls.getBz();
        if ("12".equals(jyls.getFpczlxdm()) || "13".equals(jyls.getFpczlxdm())) {
            if (StringUtils.isBlank(bz)) {
                bz = "对应正数发票代码:" + jyls.getYfpdm() + "号码:" + jyls.getYfphm();
            } else {
                bz += "<br/>对应正数发票代码:" + jyls.getYfpdm() + "号码:" + jyls.getYfphm();
            }

        }
        in_request.setBz(bz);
        in_request.setKprq(ChinaNumber.getYMDNumber((String) map.get("KPRQ")));
        in_request.setJqbh((String) map.get("JQBH"));
        in_request.setJym(ChinaNumber.getWs((String) map.get("JYM")));
        String fpmw = ChinaNumber.getAt((String) map.get("FP_MW"));
        in_request.setFpmw(fpmw);
        if (StringUtils.isNotBlank(fpmw)) {
            int lineLength = fpmw.length() / 4;
            in_request.setFpmw1(fpmw.substring(0, lineLength));
            in_request.setFpmw2(fpmw.substring(lineLength, lineLength * 2));
            in_request.setFpmw3(fpmw.substring(lineLength * 2, lineLength * 3));
            in_request.setFpmw4(fpmw.substring(lineLength * 3));
        }
        String imagePath = ResourceLoader.getPath("config/images");
        in_request.setImagePath(imagePath);
        // pdf的存储路径
        String tempPath = ReadProperties.read("classpath");
        String outputFile_AbsolutePath = xfsh + "/"
                + ((String) map.get("KPRQ")).substring(0, 8) + "/" + UUID.randomUUID().toString() + ".pdf";
        String outputFile = tempPath + outputFile_AbsolutePath;

        // 发票明细部分
        List<Kpspmx> t_kpspmxes = DataOperate.getPDFSpmx(kpls.getKplsh());
        DecimalFormat df = new DecimalFormat("######0.00");
        DecimalFormat df8 = new DecimalFormat("######0.00000000");
        // 合计金额部分
        String total = df.format(kpls.getJshj());
        String totalAmount = df.format(kpls.getHjje());     //le.getTotalAmount(djh, jyspmxs);// 两位小数已保留
        String totalTaxAmount = df.format(kpls.getHjse());  //le.getTotalTaxAmount(total, totalAmount);

        in_request.setJshjdx(ChinaNumber.getCHSNumber(total));// 中文大写表示
        in_request.setTotalString(total);
        in_request.setTotalAmountString(totalAmount);
        in_request.setTotalTaxAmountString(totalTaxAmount);

        List<FpPdfMxInfo> pdfMxList = new ArrayList<>();
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
                    xmsl = df.format(sps);
                }
                //单价
                Double dj = t_kpspmx.getSpdj();
                String xmdj = "";
                if (dj != null && dj != 0) {
                    xmdj = df8.format(dj);
                }
                pdfMxList.add(new FpPdfMxInfo(t_kpspmx.getSpmc(),//商品名称
                        t_kpspmx.getSpggxh() == null ? "" : t_kpspmx.getSpggxh(),//规格型号
                        t_kpspmx.getSpdw() == null ? "" : t_kpspmx.getSpdw(),//商品单位
                        xmsl,//商品数量
                        xmdj,
                        df.format(t_kpspmx.getSpje()),
                        sl,
                        df.format(t_kpspmx.getSpse()),
                        s
                ));
            }
        } else {

            /*****************************/
            //TODO 现仅针对A&F要求，将所有明细合为1条，且商品数也为1
            Kpspmx t_kpspmx = t_kpspmxes.get(0);
            String s = String.valueOf(1);
            String sl = LeviedSeparate.getTaxRate(t_kpspmx.getSpsl());//t_kpspmx.getSpsl() == null ? 0 : t_kpspmx.getSpsl();
            Double xmsl = t_kpspmx.getSps();//*(t_kpspmxes.size());//t_kpspmx.getSps() == null ? 0 : t_kpspmx.getSps() ;
            pdfMxList.add(new FpPdfMxInfo(t_kpspmx.getSpmc(),
                    t_kpspmx.getSpggxh() == null ? "" : t_kpspmx.getSpggxh(),
                    t_kpspmx.getSpdw() == null ? "" : t_kpspmx.getSpdw(), df.format(/*xmsl*/1.00),
                    df.format(Double.parseDouble(totalAmount)), df.format(Double.parseDouble(totalAmount)), sl, df.format(Double.parseDouble(totalTaxAmount)), s));

            /*****************************/
        }
        in_request.setJyspmxls(pdfMxList);
        // 二维码生成部分
        TwoDimensionCode handler = new TwoDimensionCode();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
      //20161123 kzx 修改二维码生成逻辑  begin
       /* String qrcode1 = "01,10," + map.get("FP_DM") + "," + map.get("FP_HM")
                + "," + total.toString() + ","
                + ((String) map.get("KPRQ")).substring(0, 8) + ","
                + map.get("JYM");
        map.put("qrcode", qrcode1);
        String qrcode = (String) map.get("EWM");
        handler.encoderQRCode(qrcode1, output);// 二维码中数据的来源
*/        
       
        String qrcode = (String) map.get("EWM");
        map.put("qrcode", qrcode);
        String imgbase64string =qrcode;

       // String imgbase64string = Base64.encodeBase64String(output.toByteArray());
        //20161123 kzx 修改二维码生成逻辑  end 
        imgbase64string = imgbase64string.replaceAll("\r\n", "");
        in_request.setBase64Image(imgbase64string);
        // classpath 中模板路径
        String template = "config/templates/invoice.html";
        PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
        // 生成pdf
        pdfGenerator.generate(map, template, in_request, outputFile);
        //对pdf进行电子签章
        String sourcePdfPath = outputFile;
        outputFile_AbsolutePath = xfsh + "/"
                + ((String) map.get("KPRQ")).substring(0, 8) + "/" + UUID.randomUUID().toString() + ".pdf";
        outputFile = tempPath + outputFile_AbsolutePath;
        String signImagePath = ResourceLoader.getPath("config/images") + "/" + xfsh + ".png";
        PdfSignUtils.eInvoicePdfSign(sourcePdfPath, signImagePath, outputFile);
        //签章成功，删除源文件
        new File(sourcePdfPath).delete();

        //pdf生成jpg文件
        //先生成带章的pdf
        String template2 = "config/templates/invoice2.html";
        String tmpPdfPath = tempPath + xfsh + "/" + ((String) map.get("KPRQ")).substring(0, 8) + "/" + UUID.randomUUID().toString() + "_tmp.pdf";
        pdfGenerator.generate(map, template2, in_request, tmpPdfPath);

        int pos = outputFile.lastIndexOf(".");
        String jpgFile = outputFile.substring(0, pos) + ".jpg";
        ImgPdfUtils.changePdfToImg(new File(tmpPdfPath), jpgFile);
        //删除原始pdf
        new File(tmpPdfPath).delete();
        System.err.println("pdf转换成jpg耗时time=" + (System.currentTimeMillis() - start)
                / 1000);

        //由于javasafeengine的使用jar过老，替换新的生成摘要信息的方法,2016-09-18，以pdf是否有图片签章为区别
        //String signData = SafeEngine.Sign(outputFile, jyls);
        String keyStorePath = ResourceLoader.getPath("config/keys") + "/tydzfp.jks";
        String sAlias = "1tydzfp";//私钥别名；  Rjxx1234 sKeyPin私钥密码
        String signData = CertificateUtils.signFileToBase64(outputFile, keyStorePath, sAlias, "Rjxx1234");

        map.put("outputFile", ReadProperties.read("serverUrl")
                + outputFile_AbsolutePath);
        map.put("signData", signData);
        return true;
    }

    public static void main(String[] args) throws Exception {
        DecimalFormat df = new DecimalFormat("######0.000000");
        //System.out.println(df.format(0.00));
    }
}
