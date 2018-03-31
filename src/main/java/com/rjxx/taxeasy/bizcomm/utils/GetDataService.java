package com.rjxx.taxeasy.bizcomm.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rjxx.taxeasy.domains.*;
import com.rjxx.taxeasy.service.*;
import com.rjxx.taxeasy.vo.Spvo;
import com.rjxx.utils.CheckOrderUtil;
import com.rjxx.utils.weixin.HttpClientUtil;
import org.apache.axiom.om.OMElement;
import org.apache.commons.codec.digest.DigestUtils;
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
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by xlm on 2017/7/3.
 * 鑾峰彇鍏ㄥ锛岀豢鍦颁紭椴滄帴鍙ｆ彁渚涚殑鏁版嵁
 */
@Service
public class GetDataService {
    @Autowired
    private YhService yhService;
    @Autowired
    private SkpService skpService;
    @Autowired
    private XfService xfService;
    @Autowired
    private JyxxsqService jyxxsqService;
    @Autowired
    private ZffsService zffsService;
    @Autowired
    private SpvoService spvoService;
    @Autowired
    private GsxxService gsxxService;
    @Autowired
    private CszbService cszbService;
    @Autowired
    private  CheckOrderUtil checkOrderUtil;
    @Autowired
    private  vSpbmService vSpbmService;

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private static String getSign(String QueryData, String key) {
        String signSourceData = "data=" + QueryData + "&key=" + key;
        String newSign = DigestUtils.md5Hex(signSourceData);
        return newSign;
    }
    public String  xmldata(){
        String xml3 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "\t\t\t\t<Responese>\n" +
                "\t\t\t\t<ReturnCode>9002</ReturnCode>\n" +
                "\t\t\t\t<ReturnMessage>鏈彁鍙栧埌浜ゆ槗鏁版嵁锛岃绋嶅悗鍐嶈瘯</ReturnMessage>\n" +
                "\t\t\t\t</Responese>\n";
            String xml1="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<Responese>\n" +
                    "\t<ReturnCode>Family_01</ReturnCode>\n" +
                    "\t<ReturnMessage>Family_01</ReturnMessage>\n" +
                    "\t<ReturnData>\n" +
                    "\t<ExtractCode>061402100101123456765</ExtractCode>\n" +
                    "\t<MemberID></MemberID>\n" +
                    "\t<InvType>12</InvType>\n" +
                    "\t<Spbmbbh>13.0</Spbmbbh>\n" +
                    "\t<StoreNo></StoreNo>\n" +
                    "\t<Seller>\n" +
                    "\t\t<Identifier>9131000071785090X1</Identifier>\n" +
                    "\t\t<Name>涓婃捣绂忔弧瀹朵究鍒╂湁闄愬叕鍙�</Name>\n" +
                    "\t\t<Address>涓婃捣甯傛櫘闄�鍖虹湡鍖楄矾2167鍙蜂笂娴烽缚娴峰ぇ鍘﹀晢閾�2鏍�1灞�09鍙峰晢閾�</Address>\n" +
                    "\t\t<TelephoneNo>021-62723187</TelephoneNo>\n" +
                    "\t\t<Bank>涓浗姘戠敓閾惰涓婃捣鍒嗚闄嗗鍢存敮琛�</Bank>\n" +
                    "\t\t<BankAcc>0216014180000511\t</BankAcc>\n" +
                    "\t</Seller>\n" +
                    "\t\t<Orders>\n" +
                    "\t\t\t<OrderMain>\n" +
                    "\t\t\t\t<OrderNo>123456789</OrderNo>\n" +
                    "\t\t\t\t<InvoiceList>0</InvoiceList>\n" +
                    "\t\t\t\t<InvoiceSplit>1</InvoiceSplit>\n" +
                    "\t\t\t\t<InvoiceSfdy>1</InvoiceSfdy>\n" +
                    "\t\t\t\t<OrderDate>2016-06-22 23:59:59</OrderDate>\n" +
                    "\t\t\t\t<ChargeTaxWay>0</ChargeTaxWay>\n" +
                    "\t\t\t\t<TotalAmount>10.6</TotalAmount>\n" +
                    "\t\t\t\t<TaxMark>0</TaxMark>\n" +
                    "\t\t\t\t<Remark></Remark>\n" +
                    "\t\t\t</OrderMain>\n" +
                    "\t\t\t<OrderDetails count=\"1\">\n" +
                    "\t\t\t\t<ProductItem>\n" +
                    "\t\t\t\t\t<VenderOwnCode></VenderOwnCode>\n" +
                    "\t\t\t\t\t<ProductCode>1030201030000000000</ProductCode>\n" +
                    "\t\t\t\t\t<ProductName>楗煎共</ProductName>\n" +
                    "\t\t\t\t\t<RowType>0</RowType>\n" +
                    "\t\t\t\t\t<Spec></Spec>\n" +
                    "\t\t\t\t\t<Unit></Unit>\n" +
                    "\t\t\t\t\t<Quantity>1</Quantity>\n" +
                    "\t\t\t\t\t<UnitPrice>10.00</UnitPrice>\n" +
                    "\t\t\t\t\t<Amount>10.00</Amount>\n" +
                    "\t\t\t\t\t<DeductAmount></DeductAmount>\n" +
                    "\t\t\t\t\t<TaxRate>0.06</TaxRate>\n" +
                    "\t\t\t\t\t<TaxAmount>0.6</TaxAmount>\n" +
                    "\t\t\t\t\t<MxTotalAmount>10.6</MxTotalAmount>\n" +
                    "\t\t\t\t\t<PolicyMark></PolicyMark>\n" +
                    "\t\t\t\t\t<TaxRateMark></TaxRateMark>\n" +
                    "\t\t\t\t\t<PolicyName></PolicyName>\n" +
                    "\t\t\t\t</ProductItem>\n" +
                    "\t\t\t</OrderDetails>\n" +
                    "\t\t</Orders>\n" +
                    "\t\t</ReturnData>\n" +
                    "</Responese>\n";

        String xml2="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "\t\t\t\t<Responese>\n" +
                "\t\t\t\t\t<ReturnCode>0000</ReturnCode>\n" +
                "\t\t\t\t\t<ReturnMessage>鎴愬姛</ReturnMessage>\n" +
                "\t\t\t\t\t<ReturnData>\n" +
                "\t\t\t\t\t\t<ExtractCode>38355712</ExtractCode>\n" +
                "\t\t\t\t\t\t<InvType>12</InvType>\n" +
                "\t\t\t\t\t\t<Spbmbbh>13.0</Spbmbbh>\n" +
                "\t\t\t\t\t\t<ClientNO>KP001</ClientNO>\n" +
                "\t\t\t\t\t\t<Seller>\n" +
                "\t\t\t\t\t\t\t<Identifier></Identifier>\n" +
                "\t\t\t\t\t\t\t<Name></Name>\n" +
                "\t\t\t\t\t\t\t<Address></Address>\n" +
                "\t\t\t\t\t\t\t<TelephoneNo></TelephoneNo>\n" +
                "\t\t\t\t\t\t\t<Bank></Bank>\n" +
                "\t\t\t\t\t\t\t<BankAcc></BankAcc>\n" +
                "\t\t\t\t\t\t</Seller>\n" +
                "\t\t\t\t\t\t<Orders>\n" +
                "\t\t\t\t\t\t\t<OrderMain>\n" +
                "\t\t\t\t\t\t\t\t<OrderNo>38355712</OrderNo>\n" +
                "\t\t\t\t\t\t\t\t<OrderDate>2017-08-14 14:07:24</OrderDate>\n" +
                "\t\t\t\t\t\t\t\t<ChargeTaxWay>0</ChargeTaxWay>\n" +
                "\t\t\t\t\t\t\t\t<TotalAmount>880.00</TotalAmount>\n" +
                "\t\t\t\t\t\t\t\t<TaxMark>1</TaxMark>\n" +
                "\t\t\t\t\t\t\t\t<Remark></Remark>\n" +
                "\t\t\t\t\t\t\t</OrderMain>\n" +
                "\t\t\t\t\t\t\t<OrderDetails>\n" +
                "\t\t\t\t\t\t\t\t<ProductItem>\n" +
                "\t\t\t\t\t\t\t\t<VenderOwnCode>101211</VenderOwnCode>\n" +
                "\t\t\t\t\t\t\t\t<ProductCode></ProductCode>\n" +
                "\t\t\t\t\t\t\t\t<ProductName><![CDATA[搴峰涔� 骞肩姮绮椿鍔涘辜鐘彂鑲叉垚闀块厤鏂圭嫍绮�1.4kg]]></ProductName>\n" +
                "\t\t\t\t\t\t\t\t<RowType>0</RowType>\n" +
                "\t\t\t\t\t\t\t\t<Spec><![CDATA[]]></Spec>\n" +
                "\t\t\t\t\t\t\t\t<Unit>浠�</Unit>\n" +
                "\t\t\t\t\t\t\t\t<Quantity>1</Quantity>\n" +
                "\t\t\t\t\t\t\t\t<UnitPrice>33.80</UnitPrice>\n" +
                "\t\t\t\t\t\t\t\t<Amount>33.8</Amount>\n" +
                "\t\t\t\t\t\t\t\t<DeductAmount></DeductAmount>\n" +
                "\t\t\t\t\t\t\t\t<TaxRate>0.11</TaxRate>\n" +
                "\t\t\t\t\t\t\t\t<TaxAmount></TaxAmount>\n" +
                "\t\t\t\t\t\t\t\t<MxTotalAmount>33.8</MxTotalAmount>\n" +
                "\t\t\t\t\t\t\t\t<PolicyMark></PolicyMark>\n" +
                "\t\t\t\t\t\t\t\t<TaxRateMark></TaxRateMark>\n" +
                "\t\t\t\t\t\t\t\t<PolicyName></PolicyName>\n" +
                "\t\t\t\t\t\t\t  </ProductItem><ProductItem>\n" +
                "\t\t\t\t\t\t\t\t<VenderOwnCode>100126</VenderOwnCode>\n" +
                "\t\t\t\t\t\t\t\t<ProductCode></ProductCode>\n" +
                "\t\t\t\t\t\t\t\t<ProductName><![CDATA[娉曞浗鐨囧ROYAL CANIN 鎴愮姮绮嫍绮�8kg CC]]></ProductName>\n" +
                "\t\t\t\t\t\t\t\t<RowType>0</RowType>\n" +
                "\t\t\t\t\t\t\t\t<Spec><![CDATA[]]></Spec>\n" +
                "\t\t\t\t\t\t\t\t<Unit>浠�</Unit>\n" +
                "\t\t\t\t\t\t\t\t<Quantity>2</Quantity>\n" +
                "\t\t\t\t\t\t\t\t<UnitPrice>166.00</UnitPrice>\n" +
                "\t\t\t\t\t\t\t\t<Amount>332</Amount>\n" +
                "\t\t\t\t\t\t\t\t<DeductAmount></DeductAmount>\n" +
                "\t\t\t\t\t\t\t\t<TaxRate>0.11</TaxRate>\n" +
                "\t\t\t\t\t\t\t\t<TaxAmount></TaxAmount>\n" +
                "\t\t\t\t\t\t\t\t<MxTotalAmount>332</MxTotalAmount>\n" +
                "\t\t\t\t\t\t\t\t<PolicyMark></PolicyMark>\n" +
                "\t\t\t\t\t\t\t\t<TaxRateMark></TaxRateMark>\n" +
                "\t\t\t\t\t\t\t\t<PolicyName></PolicyName>\n" +
                "\t\t\t\t\t\t\t  </ProductItem><ProductItem>\n" +
                "\t\t\t\t\t\t\t<VenderOwnCode>000000</VenderOwnCode>\n" +
                "\t\t\t\t\t\t\t<ProductCode></ProductCode>\n" +
                "\t\t\t\t\t\t\t<ProductName>杩愯緭鏈嶅姟</ProductName>\n" +
                "\t\t\t\t\t\t\t<RowType>0</RowType>\n" +
                "\t\t\t\t\t\t\t<Spec></Spec>\n" +
                "\t\t\t\t\t\t\t<Unit>浠�</Unit>\n" +
                "\t\t\t\t\t\t\t<Quantity>1</Quantity>\n" +
                "\t\t\t\t\t\t\t<UnitPrice>23.50</UnitPrice>\n" +
                "\t\t\t\t\t\t\t<Amount>23.50</Amount>\n" +
                "\t\t\t\t\t\t\t<DeductAmount></DeductAmount>\n" +
                "\t\t\t\t\t\t\t<TaxRate>0.06</TaxRate>\n" +
                "\t\t\t\t\t\t\t<TaxAmount></TaxAmount>\n" +
                "\t\t\t\t\t\t\t<MxTotalAmount>23.50</MxTotalAmount>\n" +
                "\t\t\t\t\t\t\t<PolicyMark></PolicyMark>\n" +
                "\t\t\t\t\t\t\t<TaxRateMark></TaxRateMark>\n" +
                "\t\t\t\t\t\t\t<PolicyName></PolicyName>\n" +
                "\t\t\t\t\t\t   </ProductItem><ProductItem>\n" +
                "\t\t\t\t\t\t\t\t\t\t<VenderOwnCode>3530401</VenderOwnCode>\n" +
                "\t\t\t\t\t\t\t\t\t\t<ProductCode></ProductCode>\n" +
                "\t\t\t\t\t\t\t\t\t\t<ProductName><![CDATA[闂芥睙 鐜荤拑姘存棌绠遍奔缂窰R3-580 MJ-560 58cm闀縘]></ProductName>\n" +
                "\t\t\t\t\t\t\t\t\t\t<RowType>2</RowType>\n" +
                "\t\t\t\t\t\t\t\t\t\t<Spec><![CDATA[榛戣壊]]></Spec>\n" +
                "\t\t\t\t\t\t\t\t\t\t<Unit>浠�</Unit>\n" +
                "\t\t\t\t\t\t\t\t\t\t<Quantity>2</Quantity>\n" +
                "\t\t\t\t\t\t\t\t\t\t<UnitPrice>249.00</UnitPrice>\n" +
                "\t\t\t\t\t\t\t\t\t\t<Amount>498</Amount>\n" +
                "\t\t\t\t\t\t\t\t\t\t<DeductAmount></DeductAmount>\n" +
                "\t\t\t\t\t\t\t\t\t\t<TaxRate>0.17</TaxRate>\n" +
                "\t\t\t\t\t\t\t\t\t\t<TaxAmount></TaxAmount>\n" +
                "\t\t\t\t\t\t\t\t\t\t<MxTotalAmount>498</MxTotalAmount>\n" +
                "\t\t\t\t\t\t\t\t\t\t<PolicyMark></PolicyMark>\n" +
                "\t\t\t\t\t\t\t\t\t\t<TaxRateMark></TaxRateMark>\n" +
                "\t\t\t\t\t\t\t\t\t\t<PolicyName></PolicyName>\n" +
                "\t\t\t\t\t\t\t\t\t  </ProductItem><ProductItem>\n" +
                "\t\t\t\t\t\t\t<VenderOwnCode>3530401</VenderOwnCode>\n" +
                "\t\t\t\t\t\t\t<ProductCode></ProductCode>\n" +
                "\t\t\t\t\t\t\t<ProductName><![CDATA[闂芥睙 鐜荤拑姘存棌绠遍奔缂窰R3-580 MJ-560 58cm闀縘]></ProductName>\n" +
                "\t\t\t\t\t\t\t<RowType>1</RowType>\n" +
                "\t\t\t\t\t\t\t<Spec></Spec>\n" +
                "\t\t\t\t\t\t\t<Unit>浠�</Unit>\n" +
                "\t\t\t\t\t\t\t<Quantity></Quantity>\n" +
                "\t\t\t\t\t\t\t<UnitPrice></UnitPrice>\n" +
                "\t\t\t\t\t\t\t<Amount>-7.30</Amount>\n" +
                "\t\t\t\t\t\t\t<DeductAmount></DeductAmount>\n" +
                "\t\t\t\t\t\t\t<TaxRate>0.17</TaxRate>\n" +
                "\t\t\t\t\t\t\t<TaxAmount></TaxAmount>\n" +
                "\t\t\t\t\t\t\t<MxTotalAmount>-7.30</MxTotalAmount>\n" +
                "\t\t\t\t\t\t\t<PolicyMark></PolicyMark>\n" +
                "\t\t\t\t\t\t\t<TaxRateMark></TaxRateMark>\n" +
                "\t\t\t\t\t\t\t<PolicyName></PolicyName>\n" +
                "\t\t\t\t\t\t   </ProductItem>\n" +
                "\t\t\t\t\t\t\t</OrderDetails>\n" +
                "\t\t\t\t\t\t</Orders>\n" +
                "\t\t\t\t\t</ReturnData>\n" +
                "\t\t\t\t</Responese>\n";

            return xml3;
    }

    /**
     * 娉㈠缃�--璋冪敤鎺ュ彛鑾峰彇鏁版嵁
     * @param code
     * @param gsdm
     * @param url
     * @return
     */
    public Map getDataForBqw(String code,String gsdm,String url){
        logger.info("鎷夊彇鏁版嵁鍙傛暟鍊糲ode"+code+"鍏徃浠ｇ爜"+gsdm+"url鍦板潃"+url);
        Map parmsMap=new HashMap();
        Map parms=new HashMap();
        parms.put("gsdm",gsdm);
        Gsxx gsxx=gsxxService.findOneByParams(parms);
        try {
            String Secret = getSign(code,gsxx.getSecretKey());
            Map map = new HashMap();
            map.put("method","getOrderInfo");
            map.put("ExtractCode",code);
            map.put("sign",Secret);
            String response = HttpClientUtil.doPost(url, map);
            logger.info("娉㈠缃�---鎺ユ敹杩斿洖鍊�:" + response);
            parmsMap=interpretingForBqw(gsdm,response);
            String error = (String) parmsMap.get("error");
            if(error==null) {
                List<Jyxxsq> jyxxsqList = (List) parmsMap.get("jyxxsqList");
                List<Jymxsq> jymxsqList = (List) parmsMap.get("jymxsqList");
                List<Jyzfmx> jyzfmxList = (List) parmsMap.get("jyzfmxList");
                String msg = checkOrderUtil.checkOrders(jyxxsqList,jymxsqList,jyzfmxList,gsdm,"");
                if(null!=msg&& !"".equals(msg)){
                    parmsMap.put("msg",msg);
                }
            }
        }catch (Exception e){
            logger.info("msg=" + e.getMessage());
            e.printStackTrace();
        }
        return parmsMap;
    }
    /**
     * 娉㈠缃�-- 瑙ｆ瀽鏁版嵁
     * @param gsdm
     * @param data
     * @return
     * @throws Exception
     */
    public Map interpretingForBqw(String gsdm,String data)throws Exception {
        Map rsMap= null;
        try {
            Map params1 = new HashMap();
            params1.put("gsdm", gsdm);
            Yh yh = yhService.findOneByParams(params1);
            int lrry = yh.getId();
            List<Jyxxsq> jyxxsqList = new ArrayList();
            List<Jymxsq> jymxsqList = new ArrayList();
            List<Jyzfmx> jyzfmxList = new ArrayList<Jyzfmx>();
            rsMap = new HashMap();
            Document xmlDoc = null;
            OMElement root = null;
            try {
                xmlDoc = DocumentHelper.parseText(data);
                root = XmlMapUtils.xml2OMElement(data);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Map rootMap = XmlMapUtils.xml2Map(root, "Responese");
            String ReturnCode=rootMap.get("ReturnCode").toString();
            String ReturnMessage=rootMap.get("ReturnMessage").toString();
            if(!ReturnCode.equals("0000")){
                rsMap.put("jyxxsqList", jyxxsqList);
                rsMap.put("jymxsqList", jymxsqList);
                rsMap.put("jyzfmxList", jyzfmxList);
                rsMap.put("error",ReturnCode+":"+ReturnMessage);
                logger.info("------閿欒淇℃伅--------"+ReturnCode+":"+ReturnMessage);
                return rsMap;
            }
            Element ReturnData  = (Element) xmlDoc.selectSingleNode("Responese/ReturnData");
            // 鎻愬彇鐮�
            String ExtractCode = "";
            if (null != ReturnData.selectSingleNode("ExtractCode")
                    && !ReturnData.selectSingleNode("ExtractCode").equals("")) {
                ExtractCode = ReturnData.selectSingleNode("ExtractCode").getText();
            }
            // 鍙戠エ绉嶇被
            String InvType = "";
            if (null != ReturnData.selectSingleNode("InvType")
                    && !ReturnData.selectSingleNode("InvType").equals("")) {
                InvType = ReturnData.selectSingleNode("InvType").getText();
            }
            // 鍟嗗搧缂栫爜鐗堟湰鍙�
            String Spbmbbh = "";
            if (null != ReturnData.selectSingleNode("Spbmbbh")
                    && !ReturnData.selectSingleNode("Spbmbbh").equals("")) {
                Spbmbbh = ReturnData.selectSingleNode("Spbmbbh").getText();
            }
            // 寮�绁ㄧ偣缂栫爜
            String ClientNO = "";
            if (null != ReturnData.selectSingleNode("ClientNO")
                    && !ReturnData.selectSingleNode("ClientNO").equals("")) {
                ClientNO = ReturnData.selectSingleNode("ClientNO").getText();
            }

            //浜岀骇鑺傜偣--閿�鏂逛俊鎭�
            Element Seller  = (Element) xmlDoc.selectSingleNode("Responese/ReturnData/Seller");
            // 閿�鏂圭◣鍙�
            String Identifier = "";
            if (null != Seller.selectSingleNode("Identifier")
                    && !Seller.selectSingleNode("Identifier").equals("")) {
                Identifier = Seller.selectSingleNode("Identifier").getText();
            }
            // 閿�鏂瑰悕绉�
            String Name = "";
            if (null != Seller.selectSingleNode("Name")
                    && !Seller.selectSingleNode("Name").equals("")) {
                Name = Seller.selectSingleNode("Name").getText();
            }
            // 閿�鏂瑰湴鍧�
            String Address = "";
            if (null != Seller.selectSingleNode("Address")
                    && !Seller.selectSingleNode("Address").equals("")) {
                Address = Seller.selectSingleNode("Address").getText();
            }
            // 閿�鏂圭數璇�
            String TelephoneNo = "";
            if (null != Seller.selectSingleNode("TelephoneNo")
                    && !Seller.selectSingleNode("TelephoneNo").equals("")) {
                TelephoneNo = Seller.selectSingleNode("TelephoneNo").getText();
            }
            // 閿�鏂归摱琛�
            String Bank = "";
            if (null != Seller.selectSingleNode("Bank")
                    && !Seller.selectSingleNode("Bank").equals("")) {
                Bank = Seller.selectSingleNode("Bank").getText();
            }
            // 閿�鏂归摱琛岃处鍙�
            String BankAcc = "";
            if (null != Seller.selectSingleNode("BankAcc")
                    && !Seller.selectSingleNode("BankAcc").equals("")) {
                BankAcc = Seller.selectSingleNode("BankAcc").getText();
            }
            //浜岀骇鑺傜偣--寰呭紑绁ㄤ俊鎭�
            List<Element> xnList = xmlDoc.selectNodes("Responese/ReturnData/Orders");
            if (null != xnList && xnList.size() > 0) {
                for (Element xn : xnList) {
                    Jyxxsq jyxxsq = new Jyxxsq();
                    //涓夌骇鑺傜偣--寰呭紑绁ㄤ氦鏄撲富淇℃伅
                    Element orderMainMap = (Element) xn.selectSingleNode("OrderMain");
                    // 璁㈠崟鍙�
                    String orderNo = "";
                    if (null != orderMainMap.selectSingleNode("OrderNo")
                            && !orderMainMap.selectSingleNode("OrderNo").equals("")) {
                        orderNo = orderMainMap.selectSingleNode("OrderNo").getText();
                    }
                    // 璁㈠崟鏃堕棿
                    String orderDate = "";
                    if (null != orderMainMap.selectSingleNode("OrderDate")
                            && !orderMainMap.selectSingleNode("OrderDate").equals("")) {
                        orderDate = orderMainMap.selectSingleNode("OrderDate").getText();
                    }
                    // 寰佺◣鏂瑰紡
                    String chargeTaxWay = "";
                    if (null != orderMainMap.selectSingleNode("ChargeTaxWay")
                            && !orderMainMap.selectSingleNode("ChargeTaxWay").equals("")) {
                        chargeTaxWay = orderMainMap.selectSingleNode("ChargeTaxWay").getText();
                    }
                    // 浠风◣鍚堣
                    String totalAmount = "";
                    if (null != orderMainMap.selectSingleNode("TotalAmount")
                            && !orderMainMap.selectSingleNode("TotalAmount").equals("")) {
                        totalAmount = orderMainMap.selectSingleNode("TotalAmount").getText();
                    }
                    // 鍚◣鏍囧織
                    String taxMark = "";
                    if (null != orderMainMap.selectSingleNode("TaxMark")
                            && !orderMainMap.selectSingleNode("TaxMark").equals("")) {
                        taxMark = orderMainMap.selectSingleNode("TaxMark").getText();
                    }

                    // 澶囨敞
                    String remark = "";
                    if (null != orderMainMap.selectSingleNode("Remark")
                            && !orderMainMap.selectSingleNode("Remark").equals("")) {
                        remark = orderMainMap.selectSingleNode("Remark").getText();
                    }
                    jyxxsq.setTqm(ExtractCode);
                    jyxxsq.setDdh(orderNo);
                    SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        jyxxsq.setDdrq(orderDate == null ? new Date() : sim.parse(orderDate));
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    Xf x = new Xf();
                    x.setGsdm(gsdm);
                    x.setXfsh(Identifier);
                    //娴嬭瘯閿�鏂�
                    //x.setXfsh("500102010003643");
                    Xf xf = xfService.findOneByParams(x);
                    if(null==xf){
                        rsMap.put("jyxxsqList", jyxxsqList);
                        rsMap.put("jymxsqList", jymxsqList);
                        rsMap.put("jyzfmxList", jyzfmxList);
                        rsMap.put("error","9003:閿�鏂逛俊鎭湭缁存姢锛岃鑱旂郴鍟嗗");
                        return rsMap;
                    }
                    Map params=new HashMap();
                    params.put("xfid",xf.getId());
                    Skp skp=skpService.findOneByParams(params);
                    if(skp==null){
                        rsMap.put("jyxxsqList", jyxxsqList);
                        rsMap.put("jymxsqList", jymxsqList);
                        rsMap.put("jyzfmxList", jyzfmxList);
                        rsMap.put("error", "寮�绁ㄧ偣淇℃伅鏈淮鎶わ紝璇疯仈绯诲晢瀹�!");
                        return rsMap;
                    }
                    jyxxsq.setXfid(xf.getId());
                    jyxxsq.setJylsh(ExtractCode);
                    //娴嬭瘯
                    jyxxsq.setJshj(Double.valueOf(totalAmount));
                    jyxxsq.setHsbz(taxMark);
                    jyxxsq.setBz(remark);
                    jyxxsq.setFpzldm(InvType);
                    jyxxsq.setZsfs(chargeTaxWay);
                    jyxxsq.setKpr(xf.getKpr());
                    jyxxsq.setSkr(xf.getSkr());
                    jyxxsq.setFhr(xf.getFhr());
                    jyxxsq.setKpddm(ClientNO);
                    jyxxsq.setXfmc(Name);
                    jyxxsq.setXfsh(Identifier);
                    jyxxsq.setXfdz(Address);
                    jyxxsq.setXfdh(TelephoneNo);
                    jyxxsq.setXfyh(Bank);
                    jyxxsq.setXfyhzh(BankAcc);
                    jyxxsq.setYkpjshj(Double.valueOf("0.00"));
                    jyxxsq.setYxbz("1");
                    jyxxsq.setLrsj(new Date());
                    jyxxsq.setLrry(lrry);
                    jyxxsq.setXgry(lrry);
                    jyxxsq.setFpczlxdm("11");
                    jyxxsq.setXgsj(new Date());
                    jyxxsq.setGsdm(gsdm);
                    jyxxsq.setSjly("1");
                    jyxxsq.setClztdm("00");
                    jyxxsq.setQjzk(0d);
                    jyxxsqList.add(jyxxsq);
                    //涓夌骇鑺傜偣--寰呭紑绁ㄤ氦鏄撴槑缁嗗晢鍝佷俊鎭�
                    Element OrderDetails = (Element) xn.selectSingleNode("OrderDetails");
                    //鍥涚骇鑺傜偣
                    List<Element> orderDetailsList = (List<Element>) OrderDetails.elements("ProductItem");
                    if (null != orderDetailsList && orderDetailsList.size() > 0) {
                        int spmxxh = 0;
                        for (Element orderDetails : orderDetailsList) {
                            Jymxsq jymxsq = new Jymxsq();
                            // Map ProductItem = (Map) orderDetailsList.get(j);
                            spmxxh++;

                            // 鍟嗗搧缂栫爜
                            String ProductCode = "";
                            if (null != orderDetails.selectSingleNode("ProductCode")
                                    && !orderDetails.selectSingleNode("ProductCode").equals("")) {
                                ProductCode = orderDetails.selectSingleNode("ProductCode").getText();
                            }

                            jymxsq.setDdh(jyxxsq.getDdh());
                           // jymxsq.setSpdm(ProductCode);
                            // 鍟嗗搧鍚嶇О
                            String ProductName = "";
                            if (null != orderDetails.selectSingleNode("ProductName")
                                    && !orderDetails.selectSingleNode("ProductName").equals("")) {
                                ProductName = orderDetails.selectSingleNode("ProductName").getText();
                            }

                            jymxsq.setSpmc(ProductName);
                            jymxsq.setDdh(jyxxsq.getDdh());
                            jymxsq.setHsbz(jyxxsq.getHsbz());
                            // 鍙戠エ琛屾�ц川
                            String RowType = "";
                            if (null != orderDetails.selectSingleNode("RowType")
                                    && !orderDetails.selectSingleNode("RowType").equals("")) {
                                RowType = orderDetails.selectSingleNode("RowType").getText();
                            }

                            jymxsq.setFphxz(RowType);
                            // 鍟嗗搧瑙勬牸鍨嬪彿
                            String Spec = "";
                            if (null != orderDetails.selectSingleNode("Spec")
                                    && !orderDetails.selectSingleNode("Spec").equals("")) {
                                Spec = orderDetails.selectSingleNode("Spec").getText();
                            }

                            jymxsq.setSpggxh(Spec);
                            // 鍟嗗搧鍗曚綅
                            String Unit = "";
                            if (null != orderDetails.selectSingleNode("Unit")
                                    && !orderDetails.selectSingleNode("Unit").equals("")) {
                                Unit = orderDetails.selectSingleNode("Unit").getText();
                            }

                            jymxsq.setSpdw(Unit);
                            // 鍟嗗搧鏁伴噺
                            String Quantity = "";
                            if (null != orderDetails.selectSingleNode("Quantity")
                                    && !orderDetails.selectSingleNode("Quantity").equals("")) {
                                Quantity = orderDetails.selectSingleNode("Quantity").getText();
                                try{jymxsq.setSps(Double.valueOf(Quantity));}catch (Exception e){jymxsq.setSps(null);}
                            }
                            // 鍟嗗搧鍗曚环
                            String UnitPrice = "";
                            if (null != orderDetails.selectSingleNode("UnitPrice")
                                    && !orderDetails.selectSingleNode("UnitPrice").equals("")) {
                                UnitPrice = orderDetails.selectSingleNode("UnitPrice").getText();
                                try{jymxsq.setSpdj(Double.valueOf(UnitPrice));}catch (Exception e){jymxsq.setSpdj(null);}
                            }
                            // 鍟嗗搧閲戦
                            String Amount = "";
                            if (null != orderDetails.selectSingleNode("Amount")
                                    && !orderDetails.selectSingleNode("Amount").equals("")) {
                                Amount = orderDetails.selectSingleNode("Amount").getText();
                                try{jymxsq.setSpje(Double.valueOf(Amount));}catch (Exception e){jymxsq.setSpje(null);}

                            }
                            // 鎵ｉ櫎閲戦
                            String DeductAmount = "";
                            if (null != orderDetails.selectSingleNode("DeductAmount")
                                    && !orderDetails.selectSingleNode("DeductAmount").equals("")) {
                                DeductAmount = orderDetails.selectSingleNode("DeductAmount").getText();
                                jymxsq.setKce((null == DeductAmount || DeductAmount.equals("")) ? Double.valueOf("0.00")
                                        : Double.valueOf(DeductAmount));
                            }
                            //鍟嗗搧绋庣巼
                            String TaxRate = "";
                            if (null != orderDetails.selectSingleNode("TaxRate")
                                    && !orderDetails.selectSingleNode("TaxRate").equals("")) {
                                TaxRate = orderDetails.selectSingleNode("TaxRate").getText();
                                jymxsq.setSpsl(Double.valueOf(TaxRate));
                            }
                            //鍟嗗搧绋庨
                            String TaxAmount = "";
                            if (null != orderDetails.selectSingleNode("TaxAmount")
                                    && !orderDetails.selectSingleNode("TaxAmount").equals("")) {
                                TaxAmount = orderDetails.selectSingleNode("TaxAmount").getText();
                                if(TaxAmount!=null&&!"".equals(TaxAmount)){
                                    jymxsq.setSpse(Double.valueOf(TaxAmount));
                                }
                            }
                            //浠风◣鍚堣
                            String MxTotalAmount = "";
                            if (null != orderDetails.selectSingleNode("MxTotalAmount")
                                    && !orderDetails.selectSingleNode("MxTotalAmount").equals("")) {
                                MxTotalAmount = orderDetails.selectSingleNode("MxTotalAmount").getText();
                                jymxsq.setJshj(Double.valueOf(MxTotalAmount));
                            }
                            //鍟嗗搧鏄庣粏搴忓彿
                            jymxsq.setSpmxxh(spmxxh);
                            //鍙紑鍏烽噾棰�
                            jymxsq.setKkjje(Double.valueOf(MxTotalAmount));
                            //宸插紑鍏烽噾棰�
                            jymxsq.setYkjje(0d);
                            //鍟嗗搧鑷缂栫爜
                            String VenderOwnCode = "";
                            if (null != orderDetails.selectSingleNode("VenderOwnCode")
                                    && !orderDetails.selectSingleNode("VenderOwnCode").equals("")) {
                                VenderOwnCode = orderDetails.selectSingleNode("VenderOwnCode").getText();
                            }
                            jymxsq.setSpzxbm(VenderOwnCode);

                            Map spvoMap = new HashMap();
                            spvoMap.put("gsdm",gsdm);
                            spvoMap.put("spdm",VenderOwnCode);
                            Spvo spvo = spvoService.findOneSpvo(spvoMap);
                            if(null==spvo){
                                rsMap.put("jyxxsqList", jyxxsqList);
                                rsMap.put("jymxsqList", jymxsqList);
                                rsMap.put("jyzfmxList", jyzfmxList);
                                rsMap.put("error", "鍟嗗搧淇℃伅鏈淮鎶わ紝璇疯仈绯诲晢瀹�!");
                                return rsMap;
                            }
                            jymxsq.setSpdm(spvo.getSpbm());
                            jymxsq.setYhzcbs(spvo.getYhzcbs());
                            jymxsq.setLslbz(spvo.getLslbz());
                            jymxsq.setYhzcmc(spvo.getYhzcmc());
                            //浼樻儬鏀跨瓥鏍囪瘑
                            String PolicyMark = "";
                            if (null != orderDetails.selectSingleNode("PolicyMark")
                                    && !orderDetails.selectSingleNode("PolicyMark").equals("")) {
                                PolicyMark = orderDetails.selectSingleNode("PolicyMark").getText();
                            }
                            //闆剁◣鐜囨爣蹇�
                            String TaxRateMark = "";
                            if (null != orderDetails.selectSingleNode("TaxRateMark")
                                    && !orderDetails.selectSingleNode("TaxRateMark").equals("")) {
                                TaxRateMark = orderDetails.selectSingleNode("TaxRateMark").getText();
                            }
                            //浼樻儬鏀跨瓥鍚嶇О
                            String PolicyName = "";
                            if (null != orderDetails.selectSingleNode("PolicyName")
                                    && !orderDetails.selectSingleNode("PolicyName").equals("")) {
                                PolicyName = orderDetails.selectSingleNode("PolicyName").getText();
                            }
                            jymxsq.setGsdm(gsdm);
                            jymxsq.setLrry(lrry);
                            jymxsq.setLrsj(new Date());
                            jymxsq.setXgry(lrry);
                            jymxsq.setXgsj(new Date());
                            jymxsq.setYxbz("1");
                            jymxsqList.add(jymxsq);
                        }
                    }
                }
            }
            rsMap.put("jyxxsqList", jyxxsqList);
            rsMap.put("jymxsqList", jymxsqList);
            rsMap.put("jyzfmxList", jyzfmxList);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return rsMap;
    }

    /**
     * 鍏ㄥ--璋冪敤鎺ュ彛鑾峰彇鏁版嵁
     * @param ExtractCode
     * @param gsdm
     * @return
     */
    public Map getData(String ExtractCode,String gsdm){
            Map parmsMap=new HashMap();
            String strMessage = "";
            BufferedReader reader = null;
            StringBuffer buffer = new StringBuffer();
            Map parms=new HashMap();
            parms.put("gsdm",gsdm);
            Gsxx gsxx=gsxxService.findOneByParams(parms);
            Map resultMap = null;
//            HttpPost httpPost = new HttpPost("http://103.13.247.68:6180/EinvoiceWeb/service/EInvoiceWS/QueryOrder");
            HttpPost httpPost = new HttpPost("http://172.16.0.221:6180/EinvoiceWeb/service/EInvoiceWS/QueryOrder");
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
                String Secret=getSign(ExtractCode,gsxx.getSecretKey());
                logger.info("-------------"+ExtractCode+"----------"+gsxx.getSecretKey());
                nvps.put("ExtractCode", ExtractCode);
                nvps.put("sign", Secret);
                StringEntity requestEntity = new StringEntity(JSON.toJSONString(nvps), "utf-8");
                httpPost.setEntity(requestEntity);
                response = httpClient.execute(httpPost, new BasicHttpContext());
                if (response.getStatusLine().getStatusCode() != 200) {
                    System.out.println("request url failed, http code=" + response.getStatusLine().getStatusCode()
                            + ", url=" + "");
                }
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    reader = new BufferedReader(new InputStreamReader(entity.getContent(), "utf-8"));
                    while ((strMessage = reader.readLine()) != null) {
                        buffer.append(strMessage);
                    }
                }
                System.out.println("鎺ユ敹杩斿洖鍊�:" + buffer.toString());
                parmsMap=interpreting(gsdm,buffer.toString());
                String error = (String) parmsMap.get("error");
                if(error==null) {
                    List<Jyxxsq> jyxxsqList = (List) parmsMap.get("jyxxsqList");
                    List<Jymxsq> jymxsqList = (List) parmsMap.get("jymxsqList");
                    List<Jyzfmx> jyzfmxList = (List) parmsMap.get("jyzfmxList");
                   // String tmp = this.checkAll(jyxxsqList, jymxsqList, jyzfmxList, gsdm);
                    String msg = checkOrderUtil.checkOrders(jyxxsqList,jymxsqList,jyzfmxList,gsdm,"");
                    parmsMap.put("tmp", msg);
                }
            }catch (Exception e){
                System.out.println("request url=" + "" + ", exception, msg=" + e.getMessage());
                e.printStackTrace();
                e.printStackTrace();
            }
            return parmsMap;
    }

    /**
     * 鏍￠獙鏂规硶
     * @param jyxxsqList
     * @param jymxsqList
     * @param jyzfmxList
     * @param gsdm
     * @return
     */
    public String checkAll(List<Jyxxsq> jyxxsqList, List<Jymxsq> jymxsqList, List<Jyzfmx> jyzfmxList, String gsdm) {
        String result = "";
        String ddh = "";
        String ddh2 = "";
        List tqmList = new ArrayList();
        List jylshList = new ArrayList();
        Map tqmMap = new HashMap();
        Map jylshMap = new HashMap();
        Jyxxsq jyxxsq = new Jyxxsq();
        Jymxsq jymxsq = new Jymxsq();
        Jyzfmx jyzfmx = new Jyzfmx();

        for (int i = 0; i < jyxxsqList.size(); i++) {
            BigDecimal ajshj;
            BigDecimal jshj = new BigDecimal("0");
            BigDecimal jshj2 = new BigDecimal("0");
            for (int j = 0; j < jymxsqList.size(); j++) {
                jymxsq = (Jymxsq) jymxsqList.get(j);
                ddh = jymxsq.getDdh();
                if (jyxxsqList.get(i).getDdh().equals(jymxsq.getDdh())) {
                    jyxxsq = jyxxsqList.get(i);
                    if (ddh != null && !ddh.equals("")) {
                        if (ddh.length() > 20) {
                            result += "鏄庣粏鏁版嵁" + ddh + ":璁㈠崟鍙峰お闀�;";
                        }
                    } else {
                        result += "鏄庣粏鏁版嵁璁㈠崟鍙蜂笉鑳戒负绌�;";
                    }
                    String ProductCode = (String) jymxsq.getSpdm();
                    if (ProductCode == null) {
                        result += "璁㈠崟鍙蜂负" + ddh + "鐨勮鍗昉roductCode涓虹┖";
                    } else if (ProductCode.length() != 19) {
                        result += "璁㈠崟鍙蜂负" + ddh + "鐨勮鍗昉roductCode涓嶇瓑浜�19浣�;";
                    }
                    // 鍟嗗搧鍚嶇О
                    String ProductName = (String) jymxsq.getSpmc();
                    if (ProductName == null) {
                        result += "璁㈠崟鍙蜂负" + ddh + "鐨勮鍗昉roductName涓虹┖锛�";
                    } else if (ProductName.length() > 50) {
                        result += "璁㈠崟鍙蜂负" + ddh + "鐨勮鍗昉roductName澶暱锛�";
                    }
                    // 鍙戠エ琛屾�ц川
                    String RowType = (String) jymxsq.getFphxz();
                    if (RowType == null) {
                        result += "璁㈠崟鍙蜂负" + ddh + "鐨勮鍗昍owType涓虹┖;";
                    } else if (!("0".equals(RowType) || "1".equals(RowType) || "2".equals(RowType))) {
                        result += "璁㈠崟鍙蜂负" + ddh + "鐨勮鍗昍owType鍙兘濉啓0锛�1鎴�2;";
                    }


                    // 鍟嗗搧閲戦
                    String Amount = String.valueOf(jymxsq.getSpje());
                    if (Amount == null) {
                        result += "璁㈠崟鍙蜂负" + ddh + "鐨勮鍗曞晢鍝丄mount涓虹┖;";
                    } else if (!Amount.matches("^\\-?[0-9]{0,15}+(.[0-9]{0,2})?$")) {
                        result += "璁㈠崟鍙蜂负" + ddh + "鐨勮鍗旳mount鏍煎紡涓嶆纭紒";
                    }
                    // 鍟嗗搧绋庣巼
                    String TaxRate = String.valueOf(jymxsq.getSpsl());
                    if (TaxRate == null) {
                        result = "璁㈠崟鍙蜂负" + ddh + "鐨勮鍗昑axRate涓虹┖;";
                    } else {
                        double taxRate = Double.valueOf(TaxRate);
                        if (!(taxRate == 0 || taxRate == 0.03 || taxRate == 0.04
                                || taxRate == 0.06 || taxRate == 0.11 || taxRate == 0.13
                                || taxRate == 0.17)) {
                            result += "璁㈠崟鍙蜂负" + ddh + "鐨勮鍗昑axRate鏍煎紡鏈夎;";
                        }
                    }
                    if((jymxsq.getSpdj()==null&&jymxsq.getSps()!=null)||(jymxsq.getSps()==null&&jymxsq.getSpdj()!=null)){
                        result += "璁㈠崟鍙蜂负" + ddh + "鐨勮鍗曠" + i+1+ "琛屽晢鍝佸崟浠凤紝鍟嗗搧鏁伴噺蹇呴』鍏ㄩ儴涓虹┖鎴栬�呭叏閮ㄤ笉涓虹┖锛�";
                    }
                    if (jymxsq.getSpdj() != null && jymxsq.getSps() != null && jymxsq.getSpje() != null) {
                        double res = jymxsq.getSpdj() * jymxsq.getSps();
                        BigDecimal big1 = new BigDecimal(res);
                        big1 = big1.setScale(2, BigDecimal.ROUND_HALF_UP);
                        BigDecimal big2 = new BigDecimal(jymxsq.getSpje());
                        big2 = big2.setScale(2, BigDecimal.ROUND_HALF_UP);
                        if (big1.compareTo(big2) != 0) {
                            result += "璁㈠崟鍙蜂负" + ddh + "鐨勮鍗曠" + i+1+ "琛屽晢鍝佸崟浠凤紝鍟嗗搧鏁伴噺锛屽晢鍝侀噾棰濅箣闂寸殑璁＄畻鏍￠獙涓嶉�氳繃锛岃妫�鏌ワ紒";
                        }
                    }
                    if(jymxsq.getSpdj() != null && jymxsq.getSps() != null && jymxsq.getSpje() != null){
                        double spdj = jymxsq.getSpdj();
                        double sps = jymxsq.getSps();
                        BigDecimal big1 = new BigDecimal(spdj);
                        BigDecimal big2 = new BigDecimal(sps);
                        if((big1.compareTo(new BigDecimal(0))==0)||(big2.compareTo(new BigDecimal(0))==0)){
                            result += "璁㈠崟鍙蜂负" + ddh + "鐨勮鍗曠" + i+1+ "琛屽晢鍝佸崟浠锋垨鍟嗗搧鏁伴噺涓嶈兘涓洪浂锛�";
                        }
                    }
                    // 鍟嗗搧绋庨
                    String TaxAmount = String.valueOf(jymxsq.getSpse());
                    if (TaxAmount != null && TaxAmount.equals("^\\-?[0-9]{0,15}+(.[0-9]{0,2})?$")) {
                        result += "璁㈠崟鍙蜂负" + ddh + "鐨勮鍗曠" + i +1+ "鏉″晢鍝乀axAmount鏍煎紡涓嶆纭紒";
                    }

                    // 鏍￠獙閲戦璇樊
                    String TaxMark = jyxxsq.getHsbz();
                    double je = Double.valueOf(Amount);
                    double se = 0;
                    //鍚◣鏃讹紝蹇界暐绋庨
                    if (TaxMark.equals("0")) {
                        if (TaxAmount != null && !"".equals(TaxAmount)) {
                            se = Double.valueOf(TaxAmount);
                        }
                    }
                    double sl = Double.valueOf(TaxRate);
                    if (TaxMark.equals("0") && je * sl - se >= 0.0625) {
                        result += "璁㈠崟鍙蜂负" + ddh + "鐨勮鍗�(Amount锛孴axRate锛孴axAmount)涔嬮棿鐨勬牎楠屼笉閫氳繃";
                    }

                    BigDecimal bd = new BigDecimal(je);
                    BigDecimal bd1 = new BigDecimal(se);
                    ajshj = bd.add(bd1);
                    jshj = jshj.add(ajshj);
                    String ChargeTaxWay = jyxxsq.getZsfs();
                    String DeductAmount = String.valueOf(jymxsq.getKce());
                    if (ChargeTaxWay.equals("2") && (null == DeductAmount || DeductAmount.equals(""))) {
                        result += "璁㈠崟鍙蜂负" + ddh + "鐨勮鍗旸eductAmount涓嶈兘涓虹┖";
                    }
                }
            }
            // 浠风◣鍚堣
            Double  TotalAmount = jyxxsq.getJshj();
            if (TotalAmount == null) {
                result += ddh + ":浠风◣鍚堣涓虹┖;";
            }
            // 璁㈠崟鍙�
            ddh = jyxxsq.getDdh();
            if (ddh != null && !ddh.equals("")) {
                if (ddh.length() > 100) {
                    result += "浜ゆ槗鏁版嵁" + ddh + ":璁㈠崟鍙峰お闀�;";
                }
            } else {
                result += "浜ゆ槗鏁版嵁璁㈠崟鍙蜂笉鑳戒负绌�;";
            }
            // 璁㈠崟鏃堕棿
            SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            if (null != jyxxsq.getDdrq() && !jyxxsq.getDdrq().equals("")) {
                String OrderDate = sim.format(jyxxsq.getDdrq());
                Pattern p = Pattern.compile(
                        "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s((([0-1][0-9])|(2?[0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
                if (OrderDate != null && !p.matcher(OrderDate).matches()) {
                    result += ddh + ":璁㈠崟鏃堕棿鏍煎紡涓嶆纭�;";
                }
            }
            BigDecimal bd2 = new BigDecimal(jyxxsq.getJshj());
            if (bd2.setScale(2, BigDecimal.ROUND_HALF_UP).subtract(jshj.setScale(2, BigDecimal.ROUND_HALF_UP)).doubleValue() != 0.0) {
                result += "璁㈠崟鍙蜂负" + ddh + "鐨勮鍗昑otalAmount锛孉mount锛孴axAmount璁＄畻鏍￠獙涓嶉�氳繃";
            }
            // 鎻愬彇鐮佹牎楠�
            String tqm = jyxxsq.getTqm();
            if (null !=tqm && !tqm.equals("")) {
                tqmList.add(tqm);
            }
            // 浜ゆ槗娴佹按鍙锋牎楠�
            String jylsh = jyxxsq.getJylsh();
            jylshList.add(jylsh);
            // 涓�娆℃�ф牎楠屾彁鍙栫爜鍜屼氦鏄撴祦姘村彿鏄惁閲嶅涓婁紶锛屾瘡绗斾氦鏄撴祦姘村彿蹇呴』鍞竴锛屾彁鍙栫爜涔熷敮涓�,璁㈠崟鍙蜂篃蹇呴』鍞竴
            if (null != tqmList && !tqmList.isEmpty()) {
                tqmMap.put("tqmList", tqmList);
                tqmMap.put("gsdm", gsdm);
                List<Jyxxsq> t1 = jyxxsqService.findAllByTqms(tqmMap);
                if (null != t1 && !t1.isEmpty()) {
                    for (int a = 0; a < t1.size(); a++) {
                        Jyxxsq jy1 = (Jyxxsq) t1.get(a);
                        result += "鎻愬彇鐮�" + jy1.getTqm() + "宸插瓨鍦�;";
                    }
                }
            }
            jylshMap.put("jylshList", jylshList);
            jylshMap.put("gsdm", gsdm);
            List<Jyxxsq> t2 = jyxxsqService.findAllByJylshs(jylshMap);
            if (null != t2 && !t2.isEmpty()) {
                for (int b = 0; b < t2.size(); b++) {
                    Jyxxsq jy2 = (Jyxxsq) t2.get(b);
                    result += "浜ゆ槗娴佹按鍙�" + jy2.getJylsh() + "宸插瓨鍦�;";
                }
            }
            if (null != jyzfmxList && !jyzfmxList.isEmpty()) {
                List kpfsList = new ArrayList();
                Map params = new HashMap();
                params.put("gsdm", gsdm);
                params.put("kpfsList", kpfsList);
                List<Zffs> zffsList = zffsService.findAllByParams(params);
                if(null == zffsList ||zffsList.isEmpty()){
                    result += "璇峰幓骞冲彴鏀粯鏂瑰紡绠＄悊缁存姢瀵瑰簲鐨勬敮浠樻柟寮�;";
                }
                String flag ="0";
                for (int j = 0; j < jyzfmxList.size(); j++) {
                    jyzfmx = (Jyzfmx) jyzfmxList.get(j);
                    if(null != zffsList && !zffsList.isEmpty()){
                        for(int k=0;k<zffsList.size();k++){
                            Zffs  zffs = zffsList.get(k);
                            if(jyzfmx.getZffsDm().equals(zffs.getZffsDm())){
                                flag = "1";
                            }
                        }
                        if(flag.equals("0")){
                            result += "璁㈠崟鍙蜂负" + ddh + "鐨勮鍗�,鏀粯鏂瑰紡浠ｇ爜"+jyzfmx.getZffsDm()+"鏈湪骞冲彴缁存姢;";
                        }
                    }
                    ddh2 = jyzfmx.getDdh();
                    if (ddh.equals(ddh2)) {
                        BigDecimal zfje = new BigDecimal(jyzfmx.getZfje());
                        jshj2 = jshj2.add(zfje);
                    }
                }
                if (jshj2.compareTo(bd2) !=0) {
                    result += "璁㈠崟鍙蜂负" + ddh + "鐨勮鍗昉ayPrice鍚堣涓嶵otalAmount涓嶇瓑;";
                }
            }
        }
        return result;
    }
    /**
     * 鍏ㄥ -- 瑙ｆ瀽鏁版嵁
     * @param gsdm
     * @param data
     * @return
     * @throws Exception
     */
    public Map interpreting(String gsdm,String data)throws Exception {
        Map params1 = new HashMap();
        params1.put("gsdm", gsdm);
        Yh yh = yhService.findOneByParams(params1);
        int lrry = yh.getId();
        List<Jyxxsq> jyxxsqList = new ArrayList();
        List<Jymxsq> jymxsqList = new ArrayList();
        List<Jyzfmx> jyzfmxList = new ArrayList<Jyzfmx>();
        Map rsMap=new HashMap();
        Document xmlDoc = null;
        OMElement root = null;
        try {
            xmlDoc = DocumentHelper.parseText(data);
            root = XmlMapUtils.xml2OMElement(data);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Map rootMap = XmlMapUtils.xml2Map(root, "Responese");
        String ReturnCode=rootMap.get("ReturnCode").toString();
        String ReturnMessage=rootMap.get("ReturnMessage").toString();
        if(!ReturnCode.equals("0000")){
            rsMap.put("jyxxsqList", jyxxsqList);
            rsMap.put("jymxsqList", jymxsqList);
            rsMap.put("jyzfmxList", jyzfmxList);
            rsMap.put("error",ReturnCode+":"+ReturnMessage);
            logger.info("------閿欒淇℃伅--------"+ReturnCode+":"+ReturnMessage);
            return rsMap;
        }
        Element ReturnData  = (Element) xmlDoc.selectSingleNode("Responese/ReturnData");
        // 鎻愬彇鐮�
        String ExtractCode = "";
        if (null != ReturnData.selectSingleNode("ExtractCode")
                && !ReturnData.selectSingleNode("ExtractCode").equals("")) {
            ExtractCode = ReturnData.selectSingleNode("ExtractCode").getText();
        }
        // 浼氬憳鍙�
        String MemberID = "";
        if (null != ReturnData.selectSingleNode("MemberID")
                && !ReturnData.selectSingleNode("MemberID").equals("")) {
            MemberID = ReturnData.selectSingleNode("MemberID").getText();
        }
        // 鍙戠エ绉嶇被
        String InvType = "";
        if (null != ReturnData.selectSingleNode("InvType")
                && !ReturnData.selectSingleNode("InvType").equals("")) {
            InvType = ReturnData.selectSingleNode("InvType").getText();
        }
        // 鍟嗗搧缂栫爜鐗堟湰鍙�
        String Spbmbbh = "";
        if (null != ReturnData.selectSingleNode("Spbmbbh")
                && !ReturnData.selectSingleNode("Spbmbbh").equals("")) {
            Spbmbbh = ReturnData.selectSingleNode("Spbmbbh").getText();
        }
        // 闂ㄥ簵鍙�
        String StoreNo = "";
        if (null != ReturnData.selectSingleNode("StoreNo")
                && !ReturnData.selectSingleNode("StoreNo").equals("")) {
            StoreNo = ReturnData.selectSingleNode("StoreNo").getText();
        }
        Element Seller  = (Element) xmlDoc.selectSingleNode("Responese/ReturnData/Seller");
        // 閿�鏂圭◣鍙�
        String Identifier = "";
        if (null != Seller.selectSingleNode("Identifier")
                && !Seller.selectSingleNode("Identifier").equals("")) {
            Identifier = Seller.selectSingleNode("Identifier").getText();
        }
        // 閿�鏂瑰悕绉�
        String Name = "";
        if (null != Seller.selectSingleNode("Name")
                && !Seller.selectSingleNode("Name").equals("")) {
            Name = Seller.selectSingleNode("Name").getText();
        }
        // 閿�鏂瑰湴鍧�
        String Address = "";
        if (null != Seller.selectSingleNode("Address")
                && !Seller.selectSingleNode("Address").equals("")) {
            Address = Seller.selectSingleNode("Address").getText();
        }
        // 閿�鏂圭數璇�
        String TelephoneNo = "";
        if (null != Seller.selectSingleNode("TelephoneNo")
                && !Seller.selectSingleNode("TelephoneNo").equals("")) {
            TelephoneNo = Seller.selectSingleNode("TelephoneNo").getText();
        }
        // 閿�鏂归摱琛�
        String Bank = "";
        if (null != Seller.selectSingleNode("Bank")
                && !Seller.selectSingleNode("Bank").equals("")) {
            Bank = Seller.selectSingleNode("Bank").getText();
        }
        // 閿�鏂归摱琛岃处鍙�
        String BankAcc = "";
        if (null != Seller.selectSingleNode("BankAcc")
                && !Seller.selectSingleNode("BankAcc").equals("")) {
            BankAcc = Seller.selectSingleNode("BankAcc").getText();
        }
        List<Element> xnList = xmlDoc.selectNodes("Responese/ReturnData/Orders");
        if (null != xnList && xnList.size() > 0) {
            for (Element xn : xnList) {
                Jyxxsq jyxxsq = new Jyxxsq();
                Element orderMainMap = (Element) xn.selectSingleNode("OrderMain");
                // 璁㈠崟鍙�
                String orderNo = "";
                if (null != orderMainMap.selectSingleNode("OrderNo")
                        && !orderMainMap.selectSingleNode("OrderNo").equals("")) {
                    orderNo = orderMainMap.selectSingleNode("OrderNo").getText();
                }
                // 璁㈠崟鏃堕棿
                String orderDate = "";
                if (null != orderMainMap.selectSingleNode("OrderDate")
                        && !orderMainMap.selectSingleNode("OrderDate").equals("")) {
                    orderDate = orderMainMap.selectSingleNode("OrderDate").getText();
                }
                // 寰佺◣鏂瑰紡
                String chargeTaxWay = "";
                if (null != orderMainMap.selectSingleNode("ChargeTaxWay")
                        && !orderMainMap.selectSingleNode("ChargeTaxWay").equals("")) {
                    chargeTaxWay = orderMainMap.selectSingleNode("ChargeTaxWay").getText();
                }
                // 浠风◣鍚堣
                String totalAmount = "";
                if (null != orderMainMap.selectSingleNode("TotalAmount")
                        && !orderMainMap.selectSingleNode("TotalAmount").equals("")) {
                    totalAmount = orderMainMap.selectSingleNode("TotalAmount").getText();
                }
                // 鍚◣鏍囧織
                String taxMark = "";
                if (null != orderMainMap.selectSingleNode("TaxMark")
                        && !orderMainMap.selectSingleNode("TaxMark").equals("")) {
                    taxMark = orderMainMap.selectSingleNode("TaxMark").getText();
                }

                // 澶囨敞
                String remark = "";
                if (null != orderMainMap.selectSingleNode("Remark")
                        && !orderMainMap.selectSingleNode("Remark").equals("")) {
                    remark = orderMainMap.selectSingleNode("Remark").getText();
                }
                jyxxsq.setTqm(ExtractCode);
                jyxxsq.setDdh(orderNo);
                SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    jyxxsq.setDdrq(orderDate == null ? new Date() : sim.parse(orderDate));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Xf x = new Xf();
                x.setGsdm(gsdm);
                x.setXfsh(Identifier);
                Xf xf = xfService.findOneByParams(x);
                if(null==xf){
                    rsMap.put("jyxxsqList", jyxxsqList);
                    rsMap.put("jymxsqList", jymxsqList);
                    rsMap.put("jyzfmxList", jyzfmxList);
                    rsMap.put("error","9003:寮�绁ㄤ俊鎭湁璇紝璇疯仈绯诲晢瀹�");
                    logger.info("------閿欒淇℃伅--------"+"9003:寮�绁ㄤ俊鎭湁璇紝璇疯仈绯诲晢瀹�");
                    return rsMap;
                }
                Map params=new HashMap();
                params.put("xfid",xf.getId());
                Skp skp=skpService.findOneByParams(params);
                jyxxsq.setXfid(xf.getId());
                jyxxsq.setJylsh(ExtractCode);
                //String kpddm=ExtractCode.substring(4,10);
                jyxxsq.setKpddm(skp.getKpddm());
                jyxxsq.setJshj(Double.valueOf(totalAmount));
                jyxxsq.setHsbz(taxMark);
                jyxxsq.setBz(remark);
                jyxxsq.setFpzldm(InvType);
                jyxxsq.setZsfs(chargeTaxWay);
                jyxxsq.setKpr(xf.getKpr());
                jyxxsq.setSkr(xf.getSkr());
                jyxxsq.setFhr(xf.getFhr());
                jyxxsq.setXfsh(Identifier);
                jyxxsq.setXfmc(Name);
                jyxxsq.setXfdz(Address);
                jyxxsq.setXfdh(TelephoneNo);
                jyxxsq.setXfyh(Bank);
                jyxxsq.setXfyhzh(BankAcc);
                jyxxsq.setYkpjshj(Double.valueOf("0.00"));
                jyxxsq.setYxbz("1");
                jyxxsq.setLrsj(new Date());
                jyxxsq.setLrry(lrry);
                jyxxsq.setXgry(lrry);
                jyxxsq.setFpczlxdm("11");
                jyxxsq.setXgsj(new Date());
                jyxxsq.setGsdm(gsdm);
                jyxxsq.setSjly("1");
                jyxxsq.setClztdm("00");
                jyxxsq.setQjzk(0d);
                jyxxsqList.add(jyxxsq);
                Element OrderDetails = (Element) xn.selectSingleNode("OrderDetails");
                List<Element> orderDetailsList = (List<Element>) OrderDetails.elements("ProductItem");
                if (null != orderDetailsList && orderDetailsList.size() > 0) {
                    int spmxxh = 0;
                    for (Element orderDetails : orderDetailsList) {
                        Jymxsq jymxsq = new Jymxsq();
                        // Map ProductItem = (Map) orderDetailsList.get(j);
                        spmxxh++;
                        // 鍟嗗搧缂栫爜
                        String ProductCode = "";
                        if (null != orderDetails.selectSingleNode("ProductCode")
                                && !orderDetails.selectSingleNode("ProductCode").equals("")) {
                            ProductCode = orderDetails.selectSingleNode("ProductCode").getText();
                        }
                        if(ProductCode==null||ProductCode.equals("")){
                            rsMap.put("jyxxsqList", jyxxsqList);
                            rsMap.put("jymxsqList", jymxsqList);
                            rsMap.put("jyzfmxList", jyzfmxList);
                            rsMap.put("error","9003:寮�绁ㄤ俊鎭湁璇紝璇疯仈绯诲晢瀹�");
                            logger.info("------閿欒淇℃伅--------"+"9003:寮�绁ㄤ俊鎭湁璇紝璇疯仈绯诲晢瀹�");
                            return rsMap;
                        }
                        jymxsq.setDdh(jyxxsq.getDdh());
                        jymxsq.setSpdm(ProductCode);
                        // 鍟嗗搧鍚嶇О
                        String ProductName = "";
                        if (null != orderDetails.selectSingleNode("ProductName")
                                && !orderDetails.selectSingleNode("ProductName").equals("")) {
                            ProductName = orderDetails.selectSingleNode("ProductName").getText();
                        }

                        jymxsq.setSpmc(ProductName);
                        jymxsq.setDdh(jyxxsq.getDdh());
                        jymxsq.setHsbz(jyxxsq.getHsbz());
                        // 鍙戠エ琛屾�ц川
                        String RowType = "";
                        if (null != orderDetails.selectSingleNode("RowType")
                                && !orderDetails.selectSingleNode("RowType").equals("")) {
                            RowType = orderDetails.selectSingleNode("RowType").getText();
                        }

                        jymxsq.setFphxz(RowType);
                        // 鍟嗗搧瑙勬牸鍨嬪彿
                        String Spec = "";
                        if (null != orderDetails.selectSingleNode("Spec")
                                && !orderDetails.selectSingleNode("Spec").equals("")) {
                            Spec = orderDetails.selectSingleNode("Spec").getText();
                        }

                        jymxsq.setSpggxh(Spec);
                        // 鍟嗗搧鍗曚綅
                        String Unit = "";
                        if (null != orderDetails.selectSingleNode("Unit")
                                && !orderDetails.selectSingleNode("Unit").equals("")) {
                            Unit = orderDetails.selectSingleNode("Unit").getText();
                        }

                        jymxsq.setSpdw(Unit);
                        // 鍟嗗搧鏁伴噺
                        String Quantity = "";
                        if (null != orderDetails.selectSingleNode("Quantity")
                                && !orderDetails.selectSingleNode("Quantity").equals("")) {
                            Quantity = orderDetails.selectSingleNode("Quantity").getText();
                            try{jymxsq.setSps(Double.valueOf(Quantity));}catch (Exception e){jymxsq.setSps(null);}
                        }

                        // 鍟嗗搧鍗曚环
                        String UnitPrice = "";
                        if (null != orderDetails.selectSingleNode("UnitPrice")
                                && !orderDetails.selectSingleNode("UnitPrice").equals("")) {
                            UnitPrice = orderDetails.selectSingleNode("UnitPrice").getText();
                            try{jymxsq.setSpdj(Double.valueOf(UnitPrice));}catch (Exception e){jymxsq.setSpdj(null);}
                        }

                        // 鍟嗗搧閲戦
                        String Amount = "";
                        if (null != orderDetails.selectSingleNode("Amount")
                                && !orderDetails.selectSingleNode("Amount").equals("")) {
                            Amount = orderDetails.selectSingleNode("Amount").getText();
                            try{jymxsq.setSpje(Double.valueOf(Amount));}catch (Exception e){jymxsq.setSpje(null);}

                        }

                        // 鎵ｉ櫎閲戦
                        String DeductAmount = "";
                        if (null != orderDetails.selectSingleNode("DeductAmount")
                                && !orderDetails.selectSingleNode("DeductAmount").equals("")) {
                            DeductAmount = orderDetails.selectSingleNode("DeductAmount").getText();
                            jymxsq.setKce((null == DeductAmount || DeductAmount.equals("")) ? Double.valueOf("0.00")
                                    : Double.valueOf(DeductAmount));
                        }
                        //鍟嗗搧绋庣巼
                        String TaxRate = "";
                        if (null != orderDetails.selectSingleNode("TaxRate")
                                && !orderDetails.selectSingleNode("TaxRate").equals("")) {
                            TaxRate = orderDetails.selectSingleNode("TaxRate").getText();
                            jymxsq.setSpsl(Double.valueOf(TaxRate));
                        }
                        //鍟嗗搧绋庨
                        String TaxAmount = "";
                        if (null != orderDetails.selectSingleNode("TaxAmount")
                                && !orderDetails.selectSingleNode("TaxAmount").equals("")) {
                            TaxAmount = orderDetails.selectSingleNode("TaxAmount").getText();
                            if(TaxAmount!=null&&!"".equals(TaxAmount)){
                                jymxsq.setSpse(Double.valueOf(TaxAmount));
                            }
                        }
                        //浠风◣鍚堣
                        String MxTotalAmount = "";
                        if (null != orderDetails.selectSingleNode("MxTotalAmount")
                                && !orderDetails.selectSingleNode("MxTotalAmount").equals("")) {
                            MxTotalAmount = orderDetails.selectSingleNode("MxTotalAmount").getText();
                            jymxsq.setJshj(Double.valueOf(MxTotalAmount));
                        }
                        //鍟嗗搧鏄庣粏搴忓彿
                        jymxsq.setSpmxxh(spmxxh);
                        //鍙紑鍏烽噾棰�
                        jymxsq.setKkjje(Double.valueOf(MxTotalAmount));
                        //宸插紑鍏烽噾棰�
                        jymxsq.setYkjje(0d);
                        //鍟嗗搧鑷缂栫爜
                        String VenderOwnCode = "";
                        if (null != orderDetails.selectSingleNode("VenderOwnCode")
                                && !orderDetails.selectSingleNode("VenderOwnCode").equals("")) {
                            VenderOwnCode = orderDetails.selectSingleNode("VenderOwnCode").getText();
                        }
                        jymxsq.setSpzxbm(VenderOwnCode);
                        Map spbmMap=new HashMap();
                        spbmMap.put("spbm",ProductCode);
                        spbmMap.put("gsdm",gsdm);
                        vSpbm spbm=vSpbmService.findOneByParams(spbmMap);
                        if(spbm!=null){
                            if(Double.valueOf(TaxRate)>0){
                                jymxsq.setYhzcbs("0");
                                jymxsq.setLslbz("");
                                jymxsq.setYhzcmc("");
                            }else{
                                jymxsq.setYhzcbs(spbm.getYhzcbs().toString());
                                jymxsq.setLslbz(spbm.getLslbz());
                                jymxsq.setYhzcmc(spbm.getYhzcmc());
                            }
                        }
                        //浼樻儬鏀跨瓥鏍囪瘑
                        String PolicyMark = "";
                        if (null != orderDetails.selectSingleNode("PolicyMark")
                                && !orderDetails.selectSingleNode("PolicyMark").equals("")) {
                            PolicyMark = orderDetails.selectSingleNode("PolicyMark").getText();
                        }
                        //闆剁◣鐜囨爣蹇�
                        String TaxRateMark = "";
                        if (null != orderDetails.selectSingleNode("TaxRateMark")
                                && !orderDetails.selectSingleNode("TaxRateMark").equals("")) {
                            TaxRateMark = orderDetails.selectSingleNode("TaxRateMark").getText();
                        }
                        //浼樻儬鏀跨瓥鍚嶇О
                        String PolicyName = "";
                        if (null != orderDetails.selectSingleNode("PolicyName")
                                && !orderDetails.selectSingleNode("PolicyName").equals("")) {
                            PolicyName = orderDetails.selectSingleNode("PolicyName").getText();
                        }
                        jymxsq.setGsdm(gsdm);
                        jymxsq.setLrry(lrry);
                        jymxsq.setLrsj(new Date());
                        jymxsq.setXgry(lrry);
                        jymxsq.setXgsj(new Date());
                        jymxsq.setYxbz("1");
                        jymxsqList.add(jymxsq);
                    }
                }
                // 鑾峰彇鍙傛暟涓搴旂殑鏀粯淇℃伅

                /*Element payments = (Element) xn.selectSingleNode("Payments");
                if (null != payments && !payments.equals("")) {
                    List<Element> paymentItemList = (List<Element>) payments.elements("PaymentItem");
                    if (null != paymentItemList && paymentItemList.size() > 0) {
                        for (Element PaymentItem : paymentItemList) {
                            Jyzfmx jyzfmx = new Jyzfmx();
                            //鏀粯鏂瑰紡浠ｇ爜
                            String zffsDm = "";
                            if (null != PaymentItem.selectSingleNode("PayCode")
                                    && !PaymentItem.selectSingleNode("PayCode").equals("")) {
                                zffsDm = PaymentItem.selectSingleNode("PayCode").getText();
                                jyzfmx.setZffsDm(zffsDm);
                            }
                            //鏀粯鎬婚噾棰�
                            String zfje = "";
                            if (null != PaymentItem.selectSingleNode("PayPrice")
                                    && !PaymentItem.selectSingleNode("PayPrice").equals("")) {
                                zfje = PaymentItem.selectSingleNode("PayPrice").getText();
                                jyzfmx.setZfje(Double.valueOf(zfje));
                            }
                            //鏀粯搴忓垪鍙�
                            String PayNumber = "";
                            if (null != PaymentItem.selectSingleNode("PayNumber")
                                    && !PaymentItem.selectSingleNode("PayNumber").equals("")) {
                                PayNumber = PaymentItem.selectSingleNode("PayNumber").getText();
                                jyzfmx.setPaynumber(PayNumber);
                            }
                            jyzfmx.setGsdm(gsdm);
                            jyzfmx.setDdh(jyxxsq.getDdh());
                            jyzfmx.setLrry(lrry);
                            jyzfmx.setLrsj(new Date());
                            jyzfmx.setXgry(lrry);
                            jyzfmx.setXgsj(new Date());
                            jyzfmxList.add(jyzfmx);
                        }
                    }
                }*/
            }
        }

        rsMap.put("jyxxsqList", jyxxsqList);
        rsMap.put("jymxsqList", jymxsqList);
        rsMap.put("jyzfmxList", jyzfmxList);
        return rsMap;
    }

    /**
     * 缁垮湴浼橀矞--鑾峰彇楠岀鎺ュ彛
     * @param ExtractCode
     * @param gsdm
     * @return
     */
    public Map getldyxFirData(String ExtractCode,String gsdm){

            Map parmsMap=new HashMap();
            String strMessage = "";
            BufferedReader reader = null;
            StringBuffer buffer = new StringBuffer();
            Map parms=new HashMap();
            parms.put("gsdm",gsdm);
            //鏌ヨ鍙傛暟鎬昏〃url
            Cszb zb1 = cszbService.getSpbmbbh(gsdm, null,null, "shhqtokenurl");
            Map resultMap = null;
            HttpPost httpPost = new HttpPost(zb1.getCsz());
            CloseableHttpResponse response = null;
            RequestConfig requestConfig = RequestConfig.custom().
                    setSocketTimeout(120*1000).setConnectionRequestTimeout(120*1000).setConnectTimeout(120*1000).build();
            CloseableHttpClient httpClient = HttpClients.custom()
                    .setDefaultRequestConfig(requestConfig)
                    .build();
            //httpPost.setConfig(requestConfig);
            httpPost.addHeader("Accept", "application/json");
            httpPost.addHeader("Authorization","Basic aWZpZWxkOmlmaWVsZDEyMzQ=");
            //浼犻�掓暟鎹獙璇佺爜涓簀son鏍煎紡
             //Map nvps = new HashMap();
            try {
                //nvps.put("Authorization", "Basic aWZpZWxkOmlmaWVsZDEyMzQ=");
                StringEntity requestEntity = new StringEntity(JSON.toJSONString(""), "utf-8");
                httpPost.setEntity(requestEntity);
                response = httpClient.execute(httpPost, new BasicHttpContext());
                if (response.getStatusLine().getStatusCode() != 200) {
                    System.out.println("request url failed, http code=" + response.getStatusLine().getStatusCode()
                            + ", url=" + "");
                }
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    reader = new BufferedReader(new InputStreamReader(entity.getContent(), "utf-8"));
                    while ((strMessage = reader.readLine()) != null) {
                        buffer.append(strMessage);
                    }
                }
                System.out.println("鎺ユ敹杩斿洖鍊�:" + buffer.toString());
                //瑙ｆ瀽json鑾峰彇token
                parmsMap = interpretFirstForJson(gsdm, buffer.toString());
        }catch (Exception e){
            logger.info("request url=" +zb1.getCsz()+ ", exception, msg=" + e.getMessage());
            e.printStackTrace();
            return null;
        }
        return parmsMap;
    }

    /**
     * 缁垮湴浼橀矞 --璋冪敤鎺ュ彛鑾峰彇鑾峰彇鏁版嵁
     * @param ExtractCode
     * @param gsdm
     * @param token
     * @return
     */
    public Map getldyxSecData(String ExtractCode,String gsdm,String token){

        Map parmsMap=new HashMap();
        String strMessage = "";
        BufferedReader reader = null;
        StringBuffer buffer = new StringBuffer();
        Map parms=new HashMap();
        parms.put("gsdm",gsdm);

        //鏌ヨ鍙傛暟鎬昏〃绗簩娆rl
        Cszb zb2 = cszbService.getSpbmbbh(gsdm, null,null, "sfhhurl");
        String uri = zb2.getCsz()+"?access_token="+token;
        //String uri = zb2.getCsz();
        //System.out.println("jkdz"+uri);
        System.out.println("two  url"+uri);
        Map resultMap = null;
        HttpPost httpPost = new HttpPost(uri);
        CloseableHttpResponse response = null;
        RequestConfig requestConfig = RequestConfig.custom().
                setSocketTimeout(120*1000).setConnectionRequestTimeout(120*1000).setConnectTimeout(120*1000).build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();
        //httpPost.setConfig(requestConfig);
        httpPost.addHeader("Content-Type", "application/json");
        //浼犻�掓暟鎹獙璇佺爜涓簀son鏍煎紡
        Map nvps = new HashMap();
        try {
            nvps.put("channel","rongjin");
            nvps.put("listno", ExtractCode);
            System.out.println("kaipiaohao"+nvps.get("listno").toString());
            StringEntity requestEntity = new StringEntity(JSON.toJSONString(nvps), "utf-8");
            httpPost.setEntity(requestEntity);
            response = httpClient.execute(httpPost, new BasicHttpContext());
            if (response.getStatusLine().getStatusCode() != 200) {
                System.out.println("request url failed, http code=" + response.getStatusLine().getStatusCode()
                        + ", url=" + "");
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                reader = new BufferedReader(new InputStreamReader(entity.getContent(), "utf-8"));
                while ((strMessage = reader.readLine()) != null) {
                    buffer.append(strMessage);
                }
            }
            System.out.println("鎺ユ敹杩斿洖鍊�:" + buffer.toString());
            //瑙ｆ瀽json鑾峰彇璐墿灏忕エ鏁版嵁 灏佽鏁版嵁
            parmsMap = interpretSecForJson(gsdm, buffer.toString(),ExtractCode);
            List<Jyxxsq> jyxxsqList = (List) parmsMap.get("jyxxsqList");
            List<Jymxsq> jymxsqList = (List) parmsMap.get("jymxsqList");
            List<Jyzfmx> jyzfmxList = (List) parmsMap.get("jyzfmxList");
            if(null!=jyxxsqList &&!"".equals(jyxxsqList)&& null!=jymxsqList && !"".equals(jymxsqList)){
                String msg = checkOrderUtil.checkOrders(jyxxsqList,jymxsqList,jyzfmxList,gsdm,"");
                if(null!=msg&& !"".equals(msg)){
                    parmsMap.put("msg",msg);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return parmsMap;
    }

    public static void main(String[] args) {
        String json=" {\n" +
                "    \"code\": 0,\n" +
                "    \"data\": [{\n" +
                "      \"billno\": \"001170701000423096\",\n" +
                "      \"tradeDate\": \"2017-07-01 00:00:00\",\n" +
                "      \"tradeTime\": \"20:00:00\",\n" +
                "      \"voidbillno\":\"\",\n" +
                "      \"shopid\": \"001\" ,\n" +
                "      \"shopname\": \"XXXX搴梊",\n" +
                "      \"posid\": \"004\",\n" +
                "      \"listno\": \"23096\",\n" +
                "      \"cardno\": \"77777777\",\n" +
                "      \"payamount\": 20.00,\n" +
                "      \"addcodev\": \"V1.0.0\",\n" +
                "      \"salelist\":[{\n" +
                "          \"goodsid\": \"1\",\n" +
                "          \"goodsname\": \"鍙箰\",\n" +
                "          \"qty\": 1.000,\n" +
                "          \"amount\": 10.00,\n" +
                "          \"discamount\": 0.00\n" +
                "      },\n" +
                "      {\n" +
                "          \"goodsid\": \"2\",\n" +
                "          \"goodsname\": \"闆ⅶ\",\n" +
                "          \"qty\": 1.000,\n" +
                "          \"amount\": 10.00,\n" +
                "          \"discamount\": 0.00\n" +
                "      }],\n" +
                "      \"paylist\":[{\n" +
                "          \"paytype\": \"鐜伴噾\",\n" +
                "          \"payamount\": 20.00,\n" +
                "          \"cardno\":\"\"\n" +
                "      },\n" +
                "      {\n" +
                "          \"paytype\": \"浼氬憳鍗",\n" +
                "          \"payamount\": 0.00,\n" +
                "          \"cardno\":\"77777777\"\n" +
                "      }]\n" +
                "    }]\n" +
                "  }";
        String json1="{\n" +
                "    \"storeNo\": \"S017\",\n" +
                "    \"total\": \"1689.00000000\",\n" +
                "    \"orderNo\": \"S017-05144911\",\n" +
                "    \"totalDiscounts\": \"337.80000000\",\n" +
                "    \"details\": [\n" +
                "        {\n" +
                "            \"unitPrice\": 790,\n" +
                "            \"quantity\": 1,\n" +
                "            \"priceDiscounts\": 100,\n" +
                "            \"name\": \"FACNM1705\",\n" +
                "            \"afterDiscountPrice\": 690\n" +
                "        },\n" +
                "        {\n" +
                "            \"unitPrice\": 999,\n" +
                "            \"quantity\": 1,\n" +
                "            \"priceDiscounts\": 0,\n" +
                "            \"name\": \"HOYA閲戠骇闀滅墖\",\n" +
                "            \"afterDiscountPrice\": 999\n" +
                "        }\n" +
                "    ],\n" +
                "    \"payment\": [\n" +
                "        {\n" +
                "            \"pay_amout\": \"248.0\",\n" +
                "            \"pay_code\": \"PT_06\",\n" +
                "            \"pay_name\": \"宸嶅悍鍗"\n" +
                "        },\n" +
                "        {\n" +
                "            \"pay_amout\": \"1103.2\",\n" +
                "            \"pay_code\": \"PT_15\",\n" +
                "            \"pay_name\": \"鍥藉唴閾惰鍗"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"orderDate\": \"2017-10-17 22:10:09.0\",\n" +
                "    \"afterDiscountTotal\": \"1351.20000000\",\n" +
                "    \"status\": \"COMPLETED\"\n" +
                "}";
          GetDataService getDataService=new GetDataService();
        try {
            getDataService.interpretForGVC("gvc",json1,"S017-05144911");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 缁垮湴浼橀矞--瑙ｆ瀽json鏁版嵁,鑾峰彇token
     * @param gsdm
     * @param data
     * @return
     * @throws Exception
     */
    public Map interpretFirstForJson(String gsdm,String data)throws Exception {

        Map resultMap=new HashMap();
        //浼犲叆鏁版嵁
        JSONObject jsonObj = JSONObject.parseObject(data);
        //鑾峰彇accessToken
        String accessToken ="";
        if (null!=jsonObj.getString("access_token")&&!jsonObj.getString("access_token").equals("")){
            accessToken =  jsonObj.get("access_token").toString();
        }
        //鑾峰彇	token绫诲瀷
        String tokenType ="";
        if (null!=jsonObj.getString("token_type")&&!jsonObj.getString("token_type").equals("")){
            tokenType =  jsonObj.get("token_type").toString();
        }
        //鑾峰彇
        String refreshToken ="";
        if (null!=jsonObj.getString("refresh_token")&&!jsonObj.getString("refresh_token").equals("")){
            refreshToken =  jsonObj.get("refresh_token").toString();
        }
        //鑾峰彇杩囨湡鏃堕棿
        Integer expiresIn =null;
        if (null!=jsonObj.getInteger("expires_in")&&!jsonObj.getInteger("expires_in").equals("")){
            expiresIn =   jsonObj.getInteger("expires_in");
        }
        //鑾峰彇鏉冮檺
        String scope ="";
        if (null!=jsonObj.getString("scope")&&!jsonObj.getString("scope").equals("")){
            scope =  jsonObj.getString("scope").toString();
        }
        //鑾峰彇缁勭粐
        String organization ="";
        if (null!=jsonObj.getString("organization")&&!jsonObj.getString("organization").equals("")){
            organization =  jsonObj.getString("organization").toString();
        }
        resultMap.put("accessToken",accessToken);
        resultMap.put("tokenType",tokenType);
        resultMap.put("expiresIn",expiresIn);
        resultMap.put("scope",scope);
        resultMap.put("organization",organization);
        return  resultMap;
    }

    /**
     * 缁垮湴浼橀矞--瑙ｆ瀽json鏁版嵁,灏佽
     * @param gsdm
     * @param data
     * @return
     * @throws Exception
     */
    public Map interpretSecForJson(String gsdm,String data,String tqm)throws Exception {

        Map rsMap = new HashMap();
        Map params1 = new HashMap();
        params1.put("gsdm", gsdm);//鍏徃浠ｇ爜
        Yh yh = yhService.findOneByParams(params1);
        int lrry = yh.getId();
        List<Jyxxsq> jyxxsqList = new ArrayList();//浜ゆ槗淇℃伅鐢宠
        List<Jymxsq> jymxsqList = new ArrayList();//浜ゆ槗鏄庣粏鐢宠
        List<Jyzfmx> jyzfmxList = new ArrayList<Jyzfmx>();//浜ゆ槗鏀粯鏄庣粏
        //浼犲叆鏁版嵁
        JSONObject jsonObj = JSONObject.parseObject(data);
        String code = jsonObj.getString("code"); //code鍊间负0 琛ㄧず鏁版嵁姝ｅ父
        //鏍规嵁data鑾峰彇璐墿灏忕エ淇℃伅
        if(null!=code&&code.equals("0")) {
            JSONArray jsondata = jsonObj.getJSONArray("data");
            if (jsondata.size() > 0) {
                //System.out.println("杩涘叆data鏁版嵁");
                for (int i = 0; i < jsondata.size(); i++) {
                    //鍩烘湰淇℃伅鑾峰彇
                    JSONObject jo = jsondata.getJSONObject(i);
                    //鑾峰彇璐墿灏忕エ鍙�(ld)
                    String ExtractCode = "";
                    if (null != jo.getString("billno") && !jo.getString("billno").equals("")) {
                        ExtractCode = jo.getString("billno").toString();
                    }
                    //鑾峰彇鍙戠敓鏃ユ湡
                    Date tradeDate = null;
                    if (null != jo.getDate("tradedate") && !jo.getDate("tradedate").equals("")) {
                        tradeDate = jo.getDate("tradedate");
                    }
                    //System.out.println("鑾峰彇鍙戠敓鏃ユ湡"+tradeDate);
                    //鑾峰彇鍙戠敓鏃堕棿
                    String tradeTime = "";
                    if (null != jo.getString("tradetime") && !jo.getString("tradetime").equals("")) {
                        tradeTime = jo.getString("tradetime").toString();
                    }
                    //鑾峰彇	閫�璐ф椂锛屽師璐墿灏忕エ鍙�
                    String voidbillno = "";
                    if (null != jo.getString("voidbillno") && !jo.getString("voidbillno").equals("")) {
                        voidbillno = jo.getString("voidbillno").toString();
                    }
                    //鑾峰彇		闂ㄥ簵缂栫爜
                    String shopid = "";
                    if (null != jo.getString("shopid") && !jo.getString("shopid").equals("")) {
                        shopid = jo.getString("shopid").toString();
                    }
                    //鑾峰彇		闂ㄥ簵鍚嶇О
                    String shopname = "";
                    if (null != jo.getString("shopname") && !jo.getString("shopname").equals("")) {
                        shopname = jo.getString("shopname").toString();
                    }
                    //鑾峰彇		鏀堕摱鏈哄彿
                    String posid = "";
                    if (null != jo.getString("posid") && !jo.getString("posid").equals("")) {
                        posid = jo.getString("posid").toString();
                    }
                    //鑾峰彇		灏忕エ娴佹按鍙�
                    Integer listno = null;
                    if (null != jo.getInteger("listno") && !jo.getInteger("listno").equals("")) {
                        listno = jo.getInteger("listno");
                    }
                    //鑾峰彇		浼氬憳鍗″彿(ld)
                    String MemberID = "";
                    if (null != jo.getString("cardno") && !jo.getString("cardno").equals("")) {
                        MemberID = jo.getString("cardno").toString();
                    }
                    //鑾峰彇		椤鹃搴斾粯鎬婚噾棰�
                    Double payamount = null;
                    if (null != jo.getDouble("payamount") && !jo.getDouble("payamount").equals("")) {
                        payamount = jo.getDouble("payamount");
                    }
                    //鑾峰彇		闄勭爜鐗堟湰
                    String addcodev = "";
                    if (null != jo.getString("addcodev") && !jo.getString("addcodev").equals("")) {
                        addcodev = jo.getString("addcodev").toString();
                    }
                    //鍩烘湰鏁版嵁灏佽杩涗氦鏄撲俊鎭敵璇�
                    Jyxxsq jyxxsq = new Jyxxsq();
                    jyxxsq.setDdh(ExtractCode);//璁㈠崟缂栧彿 瀵瑰簲灏忕エ娴佹按鍙�
                    jyxxsq.setTqm(tqm);// 鎻愬彇鐮�  瀵瑰簲璐墿灏忕エ娴佹按鍙�
                    jyxxsq.setJylsh("JY" + new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date()));//浜ゆ槗娴佹按鍙�
                    //String kpddm = "A" + tqm.substring(0, 3);
                    String kpddm=tqm.substring(0,3);
                    jyxxsq.setKpddm(kpddm);
                    logger.info("寮�绁ㄧ偣浠ｇ爜涓�----" + jyxxsq.getKpddm());
                    //鏍规嵁鍏徃浠ｇ爜銆佸紑绁ㄧ偣浠ｇ爜鏌ヨ绋庢帶鐩�
                    Map skpmap = new HashMap();
                    skpmap.put("gsdm", gsdm);
                    skpmap.put("kpddm", kpddm);
                    Skp skpdata = skpService.findOneByParams(skpmap);
                    if(skpdata==null){
                        rsMap.put("jyxxsqList", jyxxsqList);
                        rsMap.put("jymxsqList", jymxsqList);
                        rsMap.put("jyzfmxList", jyzfmxList);
                        rsMap.put("msg","寮�绁ㄧ偣淇℃伅鏈淮鎶わ紝璇疯仈绯诲晢瀹�");
                        return rsMap;
                    }
                    //鏍规嵁閿�鏂筰d  鏌ヨ
                    Xf x = new Xf();
                    x.setId(skpdata.getXfid());
                    Xf xf = xfService.findOneByParams(x);
                    if(xf==null){
                        rsMap.put("msg","閿�鏂逛俊鎭湭缁存姢锛岃鑱旂郴鍟嗗");
                        rsMap.put("jyxxsqList", jyxxsqList);
                        rsMap.put("jymxsqList", jymxsqList);
                        rsMap.put("jyzfmxList", jyzfmxList);
                        return rsMap;
                    }
                    jyxxsq.setXfid(xf.getId());//閿�鏂筰d
                    jyxxsq.setFpzldm("12"); //鍙戠エ绉嶇被
                    jyxxsq.setJshj(Double.valueOf(payamount));//浠风◣鍚堣
                    jyxxsq.setHsbz("1");//鍚◣鏍囧織 1鍚◣
                    jyxxsq.setBz("");//澶囨敞
                    jyxxsq.setZsfs("");//寰佺◣鏂瑰紡
                    jyxxsq.setKpr(xf.getKpr());
                    jyxxsq.setSkr(xf.getSkr());
                    jyxxsq.setFhr(xf.getFhr());
                    jyxxsq.setDdrq(new Date());
                    jyxxsq.setXfsh(xf.getXfsh());
                    jyxxsq.setXfmc(xf.getXfmc());
                    jyxxsq.setXfdz(xf.getXfdz());
                    jyxxsq.setXfdh(xf.getXfdh());
                    jyxxsq.setXfyh(xf.getXfyh());
                    jyxxsq.setXfyhzh(xf.getXfyhzh());
                    jyxxsq.setYkpjshj(Double.valueOf("0.00"));
                    jyxxsq.setYxbz("1");
                    jyxxsq.setLrsj(new Date());
                    jyxxsq.setLrry(lrry);
                    jyxxsq.setXgry(lrry);
                    jyxxsq.setFpczlxdm("11");
                    jyxxsq.setXgsj(new Date());
                    jyxxsq.setGsdm(gsdm);
                    jyxxsq.setSjly("1");
                    jyxxsq.setClztdm("00");
                    //鍏ㄥ眬鎶樻墸
                    jyxxsq.setQjzk(0d);
                    jyxxsqList.add(jyxxsq);
                    JSONArray salelist = jo.getJSONArray("salelist");
                    if (null != salelist && salelist.size() > 0) {
                        //鍟嗗搧鏄庣粏鑾峰彇
                        int spmxxh = 0;
                        for (int s = 0; s < salelist.size(); s++) {

                            Jymxsq jymxsq = new Jymxsq();
                            JSONObject saleData = salelist.getJSONObject(s);
                            //鑾峰彇     鍟嗗搧绋庡姟闄勭爜
                            String goodsid = "";
                            if (null != saleData.getString("goodsid") && !saleData.getString("goodsid").equals("")) {
                                goodsid = saleData.getString("goodsid").toString();
                                //String spdm = goodsid.replaceAll("\r\n");
                                jymxsq.setSpdm(goodsid);
                            }
                            logger.info("鑾峰彇salelist鎴愬姛,鏁版嵁鍟嗗搧绋庡姟闄勭爜gooid" + goodsid);
                            //鑾峰彇     	鍟嗗搧鍚嶇О
                            String goodsname = "";
                            if (null != saleData.getString("goodsname") && !saleData.getString("goodsname").equals("")) {
                                String goodsna = saleData.getString("goodsname").toString();
                                goodsname = goodsna.replaceAll("\n", "");
                                jymxsq.setSpmc(goodsname.trim());
                            }
                            //鑾峰彇     	鏁伴噺锛岃礋鏁颁负閫�璐ф暟閲�
                            Double qty = null;
                            if (null != saleData.getDouble("qty") && !saleData.getDouble("qty").equals("")) {
                                qty = saleData.getDouble("qty");
                                jymxsq.setSps(Double.valueOf(qty));//鍟嗗搧鏁伴噺
                            }

                            //鑾峰彇      椤惧搴斾粯閲戦锛岃礋鏁颁负閫�璐ч噾棰�
                            BigDecimal amount = null;
                            if (null != saleData.getBigDecimal("amount") && !saleData.getBigDecimal("amount").equals("")) {
                                amount = saleData.getBigDecimal("amount");
                            }

                            //鑾峰彇      閿�鍞◣鐜�
                            BigDecimal taxrate = null;
                            if (null != saleData.getBigDecimal("taxrate") && !saleData.getBigDecimal("taxrate").equals("")) {
                                BigDecimal taxrates = saleData.getBigDecimal("taxrate");
                                if (taxrates.compareTo(new BigDecimal(1)) > 0) {
                                    taxrate = taxrates.multiply(new BigDecimal(0.01));
                                } else {
                                    taxrate = taxrates;
                                }
                                jymxsq.setSpsl(taxrate.doubleValue());// 鍟嗗搧绋庣巼
                            }

                            //鑾峰彇     	瀹為檯鍗曚环,椤惧搴斾粯閲戦 / 鏁伴噺
                            Double price = null;
                            if (null != saleData.getDouble("price") && !saleData.getDouble("price").equals("")) {
                                price = saleData.getDouble("price");
                                jymxsq.setSpdj(price);//鍟嗗搧鍗曚环
                            }
                            //鑾峰彇      	淇冮攢閲戦
                            Double discamount = null;
                            if (null != saleData.getDouble("discamount") && !saleData.getDouble("discamount").equals("")) {
                                discamount = saleData.getDouble("discamount");
                            }
                            //鍟嗗搧鏄庣粏 灏佽杩涗氦鏄撴槑缁嗙敵璇�
                            spmxxh++;
                            jymxsq.setSpmxxh(spmxxh);//鍟嗗搧鏄庣粏搴忓彿
                            jymxsq.setDdh(jyxxsq.getDdh());//璁㈠崟鍙�
                            jymxsq.setHsbz(jyxxsq.getHsbz());
                            jymxsq.setFphxz("0");//鍙戠エ琛屾�ц川 0锛氭甯歌
                            //jymxsq.setSpggxh("");//鍟嗗搧瑙勬牸鍨嬪彿
                            //jymxsq.setSpdw("");//鍟嗗搧鍗曚綅
                            //璁＄畻涓嶅惈绋庨噾棰�
                            BigDecimal big = new BigDecimal("1");
                            //BigDecimal bhsamount =   amount.divide(big.add(taxrate));
                            BigDecimal bhsamount = InvoiceSplitUtils.div(amount, big.add(taxrate), 6);
                            jymxsq.setSpje(amount.doubleValue());//鍟嗗搧閲戦
                            //璁＄畻鍟嗗搧绋庨
                            BigDecimal spseAmount = bhsamount.multiply(taxrate);
                            //jymxsq.setSpse(spseAmount.doubleValue());
                            jymxsq.setJshj(amount.doubleValue());//绋庝环鍚堣涓虹豢鍦颁紶杩涚殑閲戦
                            //鍙紑鍏烽噾棰�  = amount
                            //jymxsq.setKkjje(amount.doubleValue());
                            //宸插紑鍏烽噾棰�  = 0
                            jymxsq.setYkjje(0d);
                            Map spbmMap=new HashMap();
                            spbmMap.put("spbm",goodsid);
                            spbmMap.put("gsdm",gsdm);
                            vSpbm spbm=vSpbmService.findOneByParams(spbmMap);
                            if(spbm!=null){
                                if(Double.valueOf(taxrate.toString())>0){
                                    jymxsq.setYhzcbs("0");
                                    jymxsq.setLslbz("");
                                    jymxsq.setYhzcmc("");
                                }else{
                                    jymxsq.setYhzcbs(spbm.getYhzcbs().toString());
                                    jymxsq.setLslbz(spbm.getLslbz());
                                    jymxsq.setYhzcmc(spbm.getYhzcmc());
                                }
                            }
                           /* Map spbmMap = new HashMap();
                            spbmMap.put("spbm", goodsid);
                            spbmMap.put("gsdm", gsdm);
                            Spvo spvo = spvoService.findOneSpvo(spbmMap);
                            if (spvo != null) {
                                jymxsq.setYhzcbs(spvo.getYhzcbs());
                                jymxsq.setLslbz(spvo.getLslbz());
                                jymxsq.setYhzcmc(spvo.getYhzcmc());
                            }*/
                            jymxsq.setGsdm(gsdm);
                            jymxsq.setLrry(lrry);
                            jymxsq.setLrsj(new Date());
                            jymxsq.setXgry(lrry);
                            jymxsq.setXgsj(new Date());
                            jymxsq.setYxbz("1");
                            jymxsqList.add(jymxsq);
                        }

                    }

                    Double bkkjje = 0.00;
                    JSONArray paylist = jo.getJSONArray("paylist");
                    if (null != paylist && paylist.size() > 0) {
                        // 鑾峰彇鏀粯鏄庣粏
                        for (int p = 0; p < paylist.size(); p++) {
                            Jyzfmx jyzfmx = new Jyzfmx();
                            JSONObject payData = paylist.getJSONObject(p);
                            //鑾峰彇     鏀粯鏂瑰紡浠ｇ爜
                            String paytype = "";
                            if (null != payData.getString("paytype") && !payData.getString("paytype").equals("")) {
                                paytype = payData.getString("paytype");
                                jyzfmx.setZffsDm(paytype);
                            }
                            //鑾峰彇     椤惧鏀粯鏂瑰紡鏀粯瀹為檯閲戦锛岃礋鏁颁负閫�娆�
                            Double zfje = null;
                            if (null != payData.getDouble("payamount") && !payData.getDouble("payamount").equals("")) {
                                zfje = payData.getDouble("payamount");
                                jyzfmx.setZfje(Double.valueOf(zfje));//鏀粯閲戦
                            }
                            //鑾峰彇     鏀粯鏂瑰紡鏄偍鍊煎崱鎴栦細鍛樺崱鏃讹紝璁板綍鍗″彿
                            String paycardno = "";
                            if (null != payData.getString("cardno") && !payData.getString("cardno").equals("")) {
                                paycardno = payData.getString("cardno").toString();
                            }
                            //鏀粯鏄庣粏灏佽浜ゆ槗鏀粯鏄庣粏
                            jyzfmx.setGsdm(gsdm);
                            jyzfmx.setDdh(jyxxsq.getDdh());
                            jyzfmx.setLrry(lrry);
                            jyzfmx.setLrsj(new Date());
                            jyzfmx.setXgry(lrry);
                            jyzfmx.setXgsj(new Date());
                            jyzfmxList.add(jyzfmx);
                        }
                    }
                }
            }else {
                String msg ="鑾峰彇鏁版嵁涓虹┖锛岃绋嶅悗鍐嶈瘯锛�";
                rsMap.put("msg",msg);
            }
            rsMap.put("jyxxsqList", jyxxsqList);
            rsMap.put("jymxsqList", jymxsqList);
            rsMap.put("jyzfmxList", jyzfmxList);
            return rsMap;
        }else {
            String msg = jsonObj.getString("msg");
            if(null!=msg && !"".equals(msg)){
                rsMap.put("msg",msg);
                rsMap.put("jyxxsqList", jyxxsqList);
                rsMap.put("jymxsqList", jymxsqList);
                rsMap.put("jyzfmxList", jyzfmxList);
            }else {
                 msg = "鑾峰彇鏁版嵁澶辫触锛岃閲嶈瘯锛�";
                rsMap.put("msg", msg);
                rsMap.put("jyxxsqList", jyxxsqList);
                rsMap.put("jymxsqList", jymxsqList);
                rsMap.put("jyzfmxList", jyzfmxList);
            }
        }
        return rsMap;
    }

    /**
     * 鍏夊敮灏�--璋冪敤鎺ュ彛鑾峰彇鏁版嵁
     * @param orderNo
     * @param gsdm
     * @param url
     * @return
     */
    public Map getDataForGvc(String orderNo,String gsdm,String url){
        Map parmsMap=new HashMap();
        try {
            Map map = new HashMap();
            map.put("orderNo",orderNo);
            //map.put("gsdm",gsdm);
            logger.info("鍏夊敮灏�---浼犲叆鍙傛暟"+JSON.toJSONString(map));
            String response = HttpClientUtil.doGet(url, map);
            logger.info("鍏夊敮灏�---鎺ユ敹杩斿洖鍊�:" + response);
            //瑙ｆ瀽杩斿洖鏁版嵁
            parmsMap=interpretForGVC(gsdm,response,orderNo);
            String msg = (String) parmsMap.get("msg");
                List<Jyxxsq> jyxxsqList = (List) parmsMap.get("jyxxsqList");
                List<Jymxsq> jymxsqList = (List) parmsMap.get("jymxsqList");
                List<Jyzfmx> jyzfmxList = (List) parmsMap.get("jyzfmxList");
                /*if(null!=jyxxsqList &&!"".equals(jyxxsqList)&& null!=jymxsqList && !"".equals(jymxsqList)){
                    String msgss = checkOrderUtil.checkOrders(jyxxsqList,jymxsqList,jyzfmxList,gsdm,"");
                    if(null!=msgss&& !"".equals(msgss)){
                        parmsMap.put("msg",msgss);
                    }
                }*/
            logger.info("-----灏佽濂界殑鏁版嵁"+JSON.toJSON(parmsMap));
        }catch (Exception e){
            logger.info("msg=" + e.getMessage());
            e.printStackTrace();
        }
        return parmsMap;
    }

    /**
     * 鍏夊敮灏�--瑙ｆ瀽鏁版嵁骞跺皝瑁�
     * @param gsdm
     * @param data
     * @param orderNo
     * @return
     * @throws Exception
     */
    public Map interpretForGVC(String gsdm,String data,String orderNo)throws Exception {

        Map rsMap = new HashMap();
        Map params1 = new HashMap();
        params1.put("gsdm", gsdm);//鍏徃浠ｇ爜
        Yh yh = yhService.findOneByParams(params1);
        int lrry = yh.getId();
        List<Jyxxsq> jyxxsqList = new ArrayList();//浜ゆ槗淇℃伅鐢宠
        List<Jymxsq> jymxsqList = new ArrayList();//浜ゆ槗鏄庣粏鐢宠
        List<Jyzfmx> jyzfmxList = new ArrayList<Jyzfmx>();//浜ゆ槗鏀粯鏄庣粏
        String nowdate ="";
        String storeno ="";
        Double zkjine = 0d;
        String kpqssj="";
        String kpjssj="";
        //浼犲叆鏁版嵁
        JSONObject jsonObj = JSONObject.parseObject(data);
        String code = jsonObj.getString("code"); //code鍊间负0 琛ㄧず鏁版嵁姝ｅ父
        if(null!=code&&code.equals("0000")) {
            JSONObject jsondata = jsonObj.getJSONObject("data");
            if (jsondata!=null) {
                //for (int i = 0; i < jsondata.size(); i++) {
                    JSONObject jo = jsondata;
                    String storeNo = "";
                    if (null != jo.getString("storeNo") && !jo.getString("storeNo").equals("")) {
                        storeNo = jo.getString("storeNo").toString();
                    }
                    Date orderDate = null;
                    if (null != jo.getDate("orderDate") && !jo.getDate("orderDate").equals("")) {
                        orderDate = jo.getDate("orderDate");
                    }
                    Double total = null;
                    if (null != jo.getDouble("total") && !jo.getDouble("total").equals("")) {
                        total = jo.getDouble("total");
                    }
                    Double totalDiscounts = null;
                    if (null != jo.getDouble("totalDiscounts") && !jo.getDouble("totalDiscounts").equals("")) {
                        totalDiscounts = jo.getDouble("totalDiscounts");
                    }
                    Double afterDiscountTotal = null;
                    if (null != jo.getString("afterDiscountTotal") && !jo.getDouble("afterDiscountTotal").equals("")) {
                        afterDiscountTotal = jo.getDouble("afterDiscountTotal");
                    }
                    String status = "";
                    if (null != jo.getString("status") && !jo.getString("status").equals("")) {
                        status = jo.getString("status").toString();
                    }
                    String remark = null;
                    if (null != jo.getString("remark") && !jo.getString("remark").equals("")) {
                        remark = jo.getString("remark").toString();
                    }
                    String nowDate = null;
                    if (null != jo.getString("nowDate") && !jo.getString("nowDate").equals("")) {
                        nowDate = jo.getString("nowDate").toString();
                    }
                    nowdate= nowDate;
                    storeno =storeNo;
                    zkjine=afterDiscountTotal;
                    //鍩烘湰鏁版嵁灏佽杩涗氦鏄撲俊鎭敵璇�
                    Jyxxsq jyxxsq = new Jyxxsq();
                    jyxxsq.setDdh(orderNo);//璁㈠崟缂栧彿 瀵瑰簲灏忕エ娴佹按鍙�
                    jyxxsq.setTqm(orderNo);// 鎻愬彇鐮�  瀵瑰簲璐墿灏忕エ娴佹按鍙�
                    jyxxsq.setJylsh("JY" + new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date()));//浜ゆ槗娴佹按鍙�
                    jyxxsq.setKpddm(storeNo);
//                    jyxxsq.setKpddm("gvc_01");
                //鏍规嵁鍏徃浠ｇ爜銆佸紑绁ㄧ偣浠ｇ爜鏌ヨ绋庢帶鐩�
                Map skpmap = new HashMap();
                    skpmap.put("gsdm", gsdm);
                    skpmap.put("kpddm", storeNo);
//                    skpmap.put("kpddm", "gvc_01");
                    Skp skpdata = skpService.findOneByParams(skpmap);
                    if(skpdata==null){
                        rsMap.put("jyxxsqList", jyxxsqList);
                        rsMap.put("jymxsqList", jymxsqList);
                        rsMap.put("jyzfmxList", jyzfmxList);
                        rsMap.put("nowdate",nowdate);
                        rsMap.put("storeno",storeno);
                        rsMap.put("zkjine",zkjine);
                        rsMap.put("msg","寮�绁ㄧ偣淇℃伅鏈淮鎶わ紝璇疯仈绯诲晢瀹讹紒");
                        return rsMap;
                    }
                    if(skpdata.getKpqssj()!=null){
                        kpqssj =skpdata.getKpqssj().toString();
                    }
                    if(skpdata.getKpjssj()!=null){
                        kpjssj =skpdata.getKpjssj().toString();
                    }
                    //鏍规嵁閿�鏂筰d  鏌ヨ
                    Xf x = new Xf();
                    x.setId(skpdata.getXfid());
                    Xf xf = xfService.findOneByParams(x);
                    if(xf==null){
                        rsMap.put("msg","閿�鏂逛俊鎭湭缁存姢锛岃鑱旂郴鍟嗗锛�");
                        rsMap.put("jyxxsqList", jyxxsqList);
                        rsMap.put("jymxsqList", jymxsqList);
                        rsMap.put("jyzfmxList", jyzfmxList);
                        rsMap.put("nowdate",nowdate);
                        rsMap.put("storeno",storeno);
                        rsMap.put("zkjine",zkjine);
                        rsMap.put("kpqssj",kpqssj);
                        rsMap.put("kpjssj",kpjssj);
                        return rsMap;
                    }
                    jyxxsq.setXfid(xf.getId());//閿�鏂筰d
                    jyxxsq.setFpzldm("12"); //鍙戠エ绉嶇被
                    jyxxsq.setJshj(Double.valueOf(total));//浠风◣鍚堣
                    //鍏ㄥ眬鎶樻墸
                    jyxxsq.setQjzk(Double.valueOf(totalDiscounts));
                    jyxxsq.setHsbz("1");//鍚◣鏍囧織 1鍚◣
                    jyxxsq.setZsfs("");//寰佺◣鏂瑰紡
                    jyxxsq.setKpr(xf.getKpr());
                    jyxxsq.setSkr(xf.getSkr());
                    jyxxsq.setFhr(xf.getFhr());
                    jyxxsq.setDdrq(orderDate);
                    jyxxsq.setXfsh(xf.getXfsh());
                    jyxxsq.setXfmc(xf.getXfmc());
                    jyxxsq.setXfdz(xf.getXfdz());
                    jyxxsq.setXfdh(xf.getXfdh());
                    jyxxsq.setXfyh(xf.getXfyh());
                    jyxxsq.setXfyhzh(xf.getXfyhzh());
                    jyxxsq.setYkpjshj(Double.valueOf("0.00"));
                    jyxxsq.setYxbz("1");
                    jyxxsq.setLrsj(new Date());
                    jyxxsq.setLrry(lrry);
                    jyxxsq.setXgry(lrry);
                    jyxxsq.setFpczlxdm("11");
                    jyxxsq.setXgsj(new Date());
                    jyxxsq.setGsdm(gsdm);
                    jyxxsq.setSjly("1");
                    jyxxsq.setClztdm("00");
                    jyxxsq.setBz(remark);
                    jyxxsqList.add(jyxxsq);
                    JSONArray salelist = jo.getJSONArray("details");
                    if (null != salelist && salelist.size() > 0) {
                        //鍟嗗搧鏄庣粏鑾峰彇
                        int spmxxh = 1;
                        for (int s = 0; s < salelist.size(); s++) {

                            JSONObject saleData = salelist.getJSONObject(s);
                            String name = "";//鍚嶇О
                            if (null != saleData.getString("name") && !saleData.getString("name").equals("")) {
                                String goodsna = saleData.getString("name").toString();
                                name = goodsna.replaceAll("\n", "");
                            }
                            BigDecimal quantity = null;//鏁伴噺
                            if (null != saleData.getBigDecimal("quantity") && saleData.getBigDecimal("quantity").compareTo(BigDecimal.ZERO)!=0) {
                                quantity = saleData.getBigDecimal("quantity");
                            }
                            BigDecimal unitPrice = null;//鍗曚环
                            if (null != saleData.getBigDecimal("unitPrice") && saleData.getBigDecimal("unitPrice").compareTo(BigDecimal.ZERO)!=0) {
                                unitPrice = saleData.getBigDecimal("unitPrice");
                            }
                            BigDecimal priceDiscounts = null;
                            if (null != saleData.getBigDecimal("priceDiscounts") && !saleData.getBigDecimal("priceDiscounts").equals("")) {
                                priceDiscounts = saleData.getBigDecimal("priceDiscounts");
                            }
                            BigDecimal afterDiscountPrice = null;
                            if (null != saleData.getBigDecimal("afterDiscountPrice") && !saleData.getBigDecimal("afterDiscountPrice").equals("")) {
                                afterDiscountPrice = saleData.getBigDecimal("afterDiscountPrice");
                            }

                            //鍟嗗搧鏄庣粏 灏佽杩涗氦鏄撴槑缁嗙敵璇�
                            Cszb cszb = cszbService.getSpbmbbh("gvc",xf.getId(), skpdata.getId(), "dyspbmb");
                            int b = priceDiscounts.compareTo(new BigDecimal("0"));
                            if(b==0){
                                Jymxsq jymxsq = new Jymxsq();
                                jymxsq.setSpmc(name.trim());
                                jymxsq.setFphxz("0");//鍙戠エ琛屾�ц川 0锛氭甯歌
                                jymxsq.setSpmc(name);
                                jymxsq.setSpdj(new Double(unitPrice.toString()));
                                jymxsq.setSps(new Double(quantity.toString()));
                                BigDecimal zch = unitPrice.multiply(quantity);
                                Double zchSpje = new Double(zch.toString());
                                jymxsq.setSpje(zchSpje);
                                jymxsq.setJshj(zchSpje);
                                jymxsq.setSpmxxh(spmxxh);//鍟嗗搧鏄庣粏搴忓彿
                                spmxxh++;
                                jymxsq.setDdh(jyxxsq.getDdh());//璁㈠崟鍙�
                                jymxsq.setHsbz(jyxxsq.getHsbz());
                                jymxsq.setYkjje(0d);
                                Map mapoo = new HashMap();
                                mapoo.put("gsdm", "gvc");
                                if (cszb.getCsz() != null) {
                                    mapoo.put("spdm", cszb.getCsz());
                                }
                                Spvo spvo = spvoService.findOneSpvo(mapoo);
                                if (spvo == null) {
                                    rsMap.put("msg","鍟嗗搧淇℃伅鏈淮鎶わ紝璇疯仈绯诲晢瀹讹紒");
                                    rsMap.put("jyxxsqList", jyxxsqList);
                                    rsMap.put("jymxsqList", jymxsqList);
                                    rsMap.put("jyzfmxList", jyzfmxList);
                                    rsMap.put("nowdate",nowdate);
                                    rsMap.put("storeno",storeno);
                                    rsMap.put("zkjine",zkjine);
                                    rsMap.put("kpqssj",kpqssj);
                                    rsMap.put("kpjssj",kpjssj);
                                    return rsMap;
                                }
                                jymxsq.setSpsl(spvo.getSl());
                                jymxsq.setSpdm(spvo.getSpbm());
                                jymxsq.setSpse(0d);
                                jymxsq.setYhzcbs(spvo.getYhzcbs());
                                jymxsq.setLslbz(spvo.getLslbz());
                                jymxsq.setYhzcmc(spvo.getYhzcmc());
                                jymxsq.setGsdm(gsdm);
                                jymxsq.setLrry(lrry);
                                jymxsq.setLrsj(new Date());
                                jymxsq.setXgry(lrry);
                                jymxsq.setXgsj(new Date());
                                jymxsq.setYxbz("1");
                                jymxsqList.add(jymxsq);
                            }else {
                                Jymxsq jymxsq2 = new Jymxsq(); //琚姌鎵ｈ
                                jymxsq2.setSpmc(name.trim());
                                jymxsq2.setFphxz("2");//鍙戠エ琛屾�ц川 2锛氳鎶樻墸琛�
                                jymxsq2.setHsbz(jyxxsq.getHsbz());
                                jymxsq2.setSpdj(new Double(unitPrice.toString()));
                                jymxsq2.setSps(new Double(quantity.toString()));
                                //璁＄畻琚姌鎵ｈ  鍗曚环涔樹互鏁伴噺
                                BigDecimal bzkh = unitPrice.multiply(quantity);
                                Double spje2 = new Double(bzkh.toString());
                                jymxsq2.setSpje(spje2);//鍟嗗搧閲戦
                                //璁＄畻鍟嗗搧绋庨
                                jymxsq2.setJshj(spje2);//绋庝环鍚堣
                                jymxsq2.setSpmxxh(spmxxh);//鍟嗗搧鏄庣粏搴忓彿
                                spmxxh++;
                                jymxsq2.setDdh(jyxxsq.getDdh());//璁㈠崟鍙�
                                //宸插紑鍏烽噾棰�  = 0
                                jymxsq2.setYkjje(0d);
                                Map mapoo = new HashMap();
                                mapoo.put("gsdm", "gvc");
                                if (cszb.getCsz() != null) {
                                    mapoo.put("spdm", cszb.getCsz());
                                }
                                Spvo spvo = spvoService.findOneSpvo(mapoo);
                                if (spvo == null) {
                                    rsMap.put("msg","鍟嗗搧淇℃伅鏈淮鎶わ紝璇疯仈绯诲晢瀹讹紒");
                                    rsMap.put("jyxxsqList", jyxxsqList);
                                    rsMap.put("jymxsqList", jymxsqList);
                                    rsMap.put("jyzfmxList", jyzfmxList);
                                    rsMap.put("nowdate",nowdate);
                                    rsMap.put("storeno",storeno);
                                    rsMap.put("zkjine",zkjine);
                                    rsMap.put("kpqssj",kpqssj);
                                    rsMap.put("kpjssj",kpjssj);
                                    return rsMap;
                                }
                                jymxsq2.setSpsl(spvo.getSl());
                                jymxsq2.setSpdm(spvo.getSpbm());
                                jymxsq2.setSpse(0d);
                                jymxsq2.setYhzcbs(spvo.getYhzcbs());
                                jymxsq2.setLslbz(spvo.getLslbz());
                                jymxsq2.setYhzcmc(spvo.getYhzcmc());
                                jymxsq2.setGsdm(gsdm);
                                jymxsq2.setLrry(lrry);
                                jymxsq2.setLrsj(new Date());
                                jymxsq2.setXgry(lrry);
                                jymxsq2.setXgsj(new Date());
                                jymxsq2.setYxbz("1");
                                jymxsqList.add(jymxsq2);

                                //鎶樻墸琛�
                                Jymxsq jymxsq1 = new Jymxsq();
                                jymxsq1.setSpmc(name.trim());
                                jymxsq1.setFphxz("1");
                                //jymxsq1.setSpdj(new Double(unitPrice.toString()));
                                //jymxsq1.setSps(new Double(quantity.toString()));
                                BigDecimal zkh = priceDiscounts.multiply(new BigDecimal(-1));
                                Double spje1 = new Double(zkh.toString());
                                jymxsq1.setSpje(spje1);//鍟嗗搧閲戦
                                //璁＄畻鍟嗗搧绋庨
                                jymxsq1.setJshj(spje1);//绋庝环鍚堣
                                jymxsq1.setSpmxxh(spmxxh);//鍟嗗搧鏄庣粏搴忓彿
                                spmxxh++;
                                jymxsq1.setDdh(jyxxsq.getDdh());//璁㈠崟鍙�
                                jymxsq1.setHsbz(jyxxsq.getHsbz());
                                //宸插紑鍏烽噾棰�  = 0
                                jymxsq1.setYkjje(0d);
                                if (spvo == null) {
                                    rsMap.put("msg","鍟嗗搧淇℃伅鏈淮鎶わ紝璇疯仈绯诲晢瀹讹紒");
                                    rsMap.put("jyxxsqList", jyxxsqList);
                                    rsMap.put("jymxsqList", jymxsqList);
                                    rsMap.put("jyzfmxList", jyzfmxList);
                                    rsMap.put("nowdate",nowdate);
                                    rsMap.put("storeno",storeno);
                                    rsMap.put("zkjine",zkjine);
                                    rsMap.put("kpqssj",kpqssj);
                                    rsMap.put("kpjssj",kpjssj);
                                    return rsMap;
                                }
                                jymxsq1.setSpsl(spvo.getSl());
                                jymxsq1.setSpdm(spvo.getSpbm());
                                jymxsq1.setSpse(0d);
                                jymxsq1.setYhzcbs(spvo.getYhzcbs());
                                jymxsq1.setLslbz(spvo.getLslbz());
                                jymxsq1.setYhzcmc(spvo.getYhzcmc());
                                jymxsq1.setGsdm(gsdm);
                                jymxsq1.setLrry(lrry);
                                jymxsq1.setLrsj(new Date());
                                jymxsq1.setXgry(lrry);
                                jymxsq1.setXgsj(new Date());
                                jymxsq1.setYxbz("1");
                                jymxsqList.add(jymxsq1);
                            }
                        }
                    }
                    JSONArray paylist = jo.getJSONArray("payment");
                    if (null != paylist && paylist.size() > 0) {
                        // 鑾峰彇鏀粯鏄庣粏
                        for (int p = 0; p < paylist.size(); p++) {
                            Jyzfmx jyzfmx = new Jyzfmx();
                            JSONObject payData = paylist.getJSONObject(p);
                            String pay_code = "";
                            if (null != payData.getString("pay_code") && !payData.getString("pay_code").equals("")) {
                                pay_code = payData.getString("pay_code");
                                jyzfmx.setZffsDm(pay_code);
                            }
                            Double pay_amount = null;
                            if (null != payData.getDouble("pay_amount") && !payData.getDouble("pay_amount").equals("")) {
                                pay_amount = payData.getDouble("pay_amount");
                                jyzfmx.setZfje(pay_amount);//鏀粯閲戦
                            }
                            String pay_name = "";
                            if (null != payData.getString("pay_name") && !payData.getString("pay_name").equals("")) {
                                pay_name = payData.getString("pay_name").toString();
                            }
                            //鏀粯鏄庣粏灏佽浜ゆ槗鏀粯鏄庣粏
                            jyzfmx.setGsdm(gsdm);
                            jyzfmx.setDdh(jyxxsq.getDdh());
                            jyzfmx.setLrry(lrry);
                            jyzfmx.setLrsj(new Date());
                            jyzfmx.setXgry(lrry);
                            jyzfmx.setXgsj(new Date());
                            jyzfmxList.add(jyzfmx);
                        }
                    }
            }else {
                String msg ="鑾峰彇鏁版嵁涓虹┖锛岃绋嶅悗鍐嶈瘯锛�";
                rsMap.put("msg",msg);
            }
            rsMap.put("jyxxsqList", jyxxsqList);
            rsMap.put("jymxsqList", jymxsqList);
            rsMap.put("jyzfmxList", jyzfmxList);
            rsMap.put("nowdate",nowdate);
            rsMap.put("storeno",storeno);
            rsMap.put("zkjine",zkjine);
            rsMap.put("kpqssj",kpqssj);
            rsMap.put("kpjssj",kpjssj);
            return rsMap;
        }else {
            String msg = jsonObj.getString("msg");
            if(null!=msg && !"".equals(msg)){
                rsMap.put("msg",msg);
                rsMap.put("jyxxsqList", jyxxsqList);
                rsMap.put("jymxsqList", jymxsqList);
                rsMap.put("jyzfmxList", jyzfmxList);
                rsMap.put("nowdate",nowdate);
                rsMap.put("storeno",storeno);
                rsMap.put("zkjine",zkjine);
                rsMap.put("kpqssj",kpqssj);
                rsMap.put("kpjssj",kpjssj);
            }else {
                msg = "鑾峰彇鏁版嵁澶辫触锛岃閲嶈瘯锛�";
                rsMap.put("msg", msg);
                rsMap.put("jyxxsqList", jyxxsqList);
                rsMap.put("jymxsqList", jymxsqList);
                rsMap.put("jyzfmxList", jyzfmxList);
                rsMap.put("nowdate",nowdate);
                rsMap.put("storeno",storeno);
                rsMap.put("zkjine",zkjine);
                rsMap.put("kpqssj",kpqssj);
                rsMap.put("kpjssj",kpjssj);
            }
        }
        return rsMap;
    }
}
