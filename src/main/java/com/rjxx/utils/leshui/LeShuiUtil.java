package com.rjxx.utils.leshui;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rjxx.taxeasy.dao.leshui.FpcyJpaDao;
import com.rjxx.taxeasy.dao.leshui.FpcyjlJpaDao;
import com.rjxx.taxeasy.dao.leshui.FpcymxJpaDao;
import com.rjxx.utils.weixin.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyahui on 2018/1/4 0004.
 */
@Component
public class LeShuiUtil {
    @Autowired
    private FpcyJpaDao fpcyJpaDao;
    @Autowired
    private FpcyjlJpaDao fpcyjlJpaDao;
    @Autowired
    private FpcymxJpaDao fpcymxJpaDao;

    private final static String LESHUIAPPID = "f4404ef078794782b0c55d8ede20de3a";
    private final static String LESHUISECRET = "c25fe890-9b39-41ff-bb9c-a6bcef4f6f5c";
    /**
     * 获取token
     */
    private static final String GET_TOKEN = "https://open.leshui365.com/getToken";
    /**
     * 根据发票号码代码查验
     */
    private static final String GET_CHECK_CODE_NUM_URL = "https://open.leshui365.com/api/invoiceInfoForCom";
    /**
     * 根据二维码查验
     */
    private static final String GET_CHECK_QRCODE = "https://open.leshui365.com/api/invoiceInfoByQRCode";
    /**
     * 发信信息单个查询
     */
    private static final String GET_INVOICE_SINGLE = "https://open.leshui365.com/api/invoiceQuery";
    /**
     * 发票信息批量查询
     */
    private static final String GET_INVOICE_LIST = "https://open.leshui365.com/api/invoiceBatchQuery";
    /**
     * 发票认证
     */
    private static final String GET_INVOICE_AUTH = "https://open.leshui365.com/api/invoiceAuthorize";


    public static String getToken() {
        String url = GET_TOKEN;
        Map map = new HashMap<>();
        map.put("appKey", LESHUIAPPID);
        map.put("appSecret", LESHUISECRET);
        String json = HttpClientUtil.doGet(url, map);
        JSONObject jsonObject = JSON.parseObject(json);
        String token = jsonObject.getString("token");
        return token;
    }

    /**
     * i1 发票查验
     *
     * @invoiceCode 发票代码（长度10位或者12位）
     * @invoiceNumber 发票号码（长度8位）
     * @billTime 开票时间（时间格式必须为：2017-05-11，不支持其他格式）
     * @checkCode 校验码（检验码后六位，增值税专用发票，增值税机动车发票可以不传）
     * @invoiceAmount 开具金额、不含税价（增值税普通发票，增值税电子发票可以不传）
     */
    public String invoiceInfoForCom(String invoiceCode, String invoiceNumber, String billTime,
                                           String checkCode, String invoiceAmount) {
        String url = GET_CHECK_CODE_NUM_URL;
        Map map = new HashMap();
        map.put("invoiceCode", invoiceCode);
        map.put("invoiceNumber", invoiceNumber);
        map.put("billTime", billTime);
        map.put("checkCode", checkCode);
        map.put("invoiceAmount", invoiceAmount);
        map.put("token", getToken());
        String result = HttpClientUtil.doPost(url, map);
        JSONObject resultJson = JSON.parseObject(result);

        String rtnCode_r = resultJson.getString("RtnCode");//服务器是否正常标志
        String resultCode_r = resultJson.getString("resultCode");//查询发票状态码
        String resultMsg_r = resultJson.getString("resultMsg");//查验结果
        String invoiceName_r = resultJson.getString("invoiceName");//发票名称
        JSONObject invoiceResult_r = resultJson.getJSONObject(" invoiceResult");//发票主体信息
        //失败才有
        String invoicefalseCode_r = resultJson.getString("invoicefalseCode");//错误码;

        if (invoiceResult_r != null) {
            String invoiceDataCode_r = invoiceResult_r.getString("invoiceDataCode");//发票代码
            String invoiceNumber_r = invoiceResult_r.getString("invoiceNumber");//发票号码
            String invoiceTypeName_r = invoiceResult_r.getString("invoiceTypeName");//发票类型名称
            String invoiceTypeCode_r = invoiceResult_r.getString("invoiceTypeCode");//发票类型  01:增值税专票,02:货物运输业增值税专用发票,04:增值税普通发票,03:机动车销售统一发票,10:电子发票,11:卷式普通发票,20:国税,30:地税
            String billingTime_r = invoiceResult_r.getString("billingTime");//开票时间
            String checkDate_r = invoiceResult_r.getString("checkDate");//查询时间
            String checkCode_r = invoiceResult_r.getString("checkCode");// 校验码
            String taxDiskCode_r = invoiceResult_r.getString("taxDiskCode");//机器码
            String purchaserName_r = invoiceResult_r.getString("purchaserName");//购方名称
            String taxpayerNumber_r = invoiceResult_r.getString("taxpayerNumber");//购方识别号
            String taxpayerBankAccount_r = invoiceResult_r.getString("taxpayerBankAccount");//购方银行账号
            String taxpayerAddressOrId_r = invoiceResult_r.getString("taxpayerAddressOrId");//购方地址，电话
            String salesName_r = invoiceResult_r.getString("salesName");//销方名称
            String salesTaxpayerNum_r = invoiceResult_r.getString("salesTaxpayerNum");//销方纳税人识别号
            String salesTaxpayerBankAccount_r = invoiceResult_r.getString("salesTaxpayerBankAccount");//销方银行，账号
            String salesTaxpayerAddress_r = invoiceResult_r.getString("salesTaxpayerAddress");//销方地址，电话
            String totalTaxSum_r = invoiceResult_r.getString("totalTaxSum");//价税合计
            String totalTaxNum_r = invoiceResult_r.getString("totalTaxNum");//税额
            String totalAmount_r = invoiceResult_r.getString("totalAmount");//不含税价（金额）
            String invoiceRemarks_r = invoiceResult_r.getString("invoiceRemarks");//备注
            String isBillMark_r = invoiceResult_r.getString("isBillMark");//是否为清单票，Y：是，N：否
            String voidMark_r = invoiceResult_r.getString("voidMark");//作废标志，0：正常，1：作废
            String goodsClerk_r = invoiceResult_r.getString("goodsClerk");//收货员（卷式发票新增字段，其他票可以不用）
            String checkNum_r = invoiceResult_r.getString("checkNum");//查询次数
            //机动车与卷票共有字段
//        String machineNumber_r = invoiceResult_r.getString("machineNumber");//机打号码
            //机动车字段
//        String importCertif_r = invoiceResult_r.getString("importCertif");//进口证明书号
//        String producingArea_r = invoiceResult_r.getString("producingArea");//产地
//        String inspectionOrder_r = invoiceResult_r.getString("inspectionOrder");//商检单号
//        String taxReceiptCode_r = invoiceResult_r.getString("taxReceiptCode");//完税凭证号码
//        String taxpayerIdOrOrginCode_r = invoiceResult_r.getString("taxpayerIdOrOrginCode");//身份证号码/组织机构代码
//        String taxRate_r = invoiceResult_r.getString("taxRate");//增值税税率/或 征 收 率
//        String frameNumbr_r = invoiceResult_r.getString("frameNumbr");//车辆识别代号/车架号码
//        String limitNum_r = invoiceResult_r.getString("limitNum");//限乘人数
//        String brandType_r = invoiceResult_r.getString("brandType");//厂牌类型
//        String certifNumber_r = invoiceResult_r.getString("certifNumber");//合格证号
//        String engineNumber_r = invoiceResult_r.getString("engineNumber");//发动机号码
//        String machineCode_r = invoiceResult_r.getString("machineCode");//机打代码
//        String salesTaxpayerTel_r = invoiceResult_r.getString("salesTaxpayerTel"); //电话
//        String vehicleType_r = invoiceResult_r.getString("vehicleType");//车辆类型
//        String tonnage_r = invoiceResult_r.getString("tonnage");//吨位
//        String salesTaxpayerAccount_r = invoiceResult_r.getString("salesTaxpayerAccount");//销方银行账号
//        String taxOfficeName_r = invoiceResult_r.getString("taxOfficeName");//主管税务机关及代码
            //机动车票无此字段
            JSONArray invoiceDetailData_r = invoiceResult_r.getJSONArray("invoiceDetailData");//发票明细
            for (int i = 0; i < invoiceDetailData_r.size(); i++) {
                JSONObject o = (JSONObject) invoiceDetailData_r.get(i);
                String unit = o.getString("unit");//单位
                String model = o.getString("model");//型号
                String isBillLine = o.getString("isBillLine");//是否是清单行
                String price = o.getString("price");//单价
                String tax = o.getString("tax");//税额
                String taxRate = o.getString("taxRate");//税率
                String goodserviceName = o.getString("goodserviceName");//货劳务名称
                String sum = o.getString("sum");//金额
                String number = o.getString("number");//数量
                //卷票字段
//            String lineNum = o.getString("lineNum");//行号
            }
        }else{
        }
        return result;
    }

    /**
     * i2 单张发票查询
     *
     * @uniqueId 唯一编码（20位），客户系统生成，规则："QBI"+"yyyyMMddhhmmss+001"
     * @invoiceCode 发票代码
     * @invoiceNo 发票号码
     * @taxCode 纳税人识别号(一般为购方纳税人识别号，即客户系统公司纳税人识别号)
     */
    public static String invoiceQuery(String uniqueId, String invoiceCode, String invoiceNo,
                                      String taxCode) {
        String url = GET_INVOICE_SINGLE;
        Map map = new HashMap();
        map.put("uniqueId", uniqueId);
        map.put("invoiceCode", invoiceCode);
        map.put("invoiceNo", invoiceNo);
        map.put("taxCode", taxCode);
        Map param = new HashMap();
        param.put("head", map);
        String json = JSON.toJSONString(param);
        String result = HttpClientUtil.doPostJson(url, json);
        return result;
    }

    /**
     * i3 发票信息批量查询
     *
     * @uniqueId 唯一编码（20位），客户系统生成，规则："QBI"+"yyyyMMddhhmmss+001"
     * @startTime 开始时间
     * @endTime 结束时间
     * @taxCode 纳税人识别号(一般为购方纳税人识别号，即客户系统公司纳税人识别号)
     * @pageNo 第几页
     */
    public static String invoiceBatchQuery(String uniqueId, String startTime, String endTime,
                                           String taxCode, String pageNo) {
        String url = GET_INVOICE_LIST;
        Map map = new HashMap();
        map.put("uniqueId", uniqueId);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("taxCode", taxCode);
        map.put("pageNo", pageNo);
        Map param = new HashMap();
        param.put("head", map);
        param.put("token", getToken());
        String json = JSON.toJSONString(param);
        String result = HttpClientUtil.doPostJson(url, json);
        return result;
    }

    /**
     * i4 发票认证
     *
     * @batchId 唯一编码（20位），客户系统生成，规则："QBI"+"yyyyMMddhhmmss+001"
     * @taxCode 纳税人识别号(一般为购方纳税人识别号，即客户系统公司纳税人识别号)
     * @body 需要认证的发票信息 invoiceCode&invoiceNo
     */
    public static String invoiceAuthorize(String batchId, String taxCode,
                                          List body) {
        String url = GET_INVOICE_AUTH;
        Map param = new HashMap();
        Map head = new HashMap();
        head.put("batchId", batchId);
        head.put("taxCode", taxCode);
        param.put("head", head);
        param.put("body", body);
        param.put("token", getToken());
        String json = JSON.toJSONString(param);
        String result = HttpClientUtil.doPostJson(url, json);
        return result;
    }
}
