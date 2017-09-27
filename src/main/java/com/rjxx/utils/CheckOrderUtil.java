package com.rjxx.utils;

import com.rjxx.taxeasy.domains.*;
import com.rjxx.taxeasy.service.CszbService;
import com.rjxx.taxeasy.service.JymxsqService;
import com.rjxx.taxeasy.service.JyxxsqService;
import com.rjxx.taxeasy.service.ZffsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Pattern;

@Service
public class CheckOrderUtil {

    @Autowired
    private JyxxsqService jyxxsqService;

    @Autowired
    private JymxsqService jymxsqService;
    
    @Autowired
    private ZffsService zffsService;

    @Autowired
    private CszbService cszbservice;

    public String checkBuyer(List<Jyxxsq> jyxxsqList, String gsdm, String Operation) {
        String result = "";
        String ddh = "";
        Jyxxsq jyxxsq = new Jyxxsq();
        List tqmList = new ArrayList();
        List jylshList = new ArrayList();
//        List ddhList = new ArrayList();
        Map tqmMap = new HashMap();
        Map jylshMap = new HashMap();
        Map ddhMap = new HashMap();
        for (int i = 0; i < jyxxsqList.size(); i++) {
            jyxxsq = jyxxsqList.get(i);
            // 订单号
            ddh = jyxxsq.getDdh();
            if (ddh != null && !ddh.equals("")) {
                if (ddh.length() > 100) {
                    result += "购方数据" + ddh + ":订单号太长;";
                }
//                ddhList.add(ddh);
            } else {
                result += "购方数据订单号不能为空;";
            }
            // 订单时间
            SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            if (null != jyxxsq.getDdrq() && !jyxxsq.getDdrq().equals("")) {
                String OrderDate = sim.format(jyxxsq.getDdrq());
                Pattern p = Pattern.compile(
                        "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s((([0-1][0-9])|(2?[0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
                if (OrderDate != null && !p.matcher(OrderDate).matches()) {
                    result += ddh + ":订单时间格式不正确;";
                }
            }
            // 价税合计
            //String TotalAmount = String.valueOf(jyxxsq.getJshj());
            Double  TotalAmount = jyxxsq.getJshj();
            if (TotalAmount == null) {
                result += ddh + ":价税合计为空;";
            } /*else if (!TotalAmount.matches("^\\-?[0-9]{0,15}+(.[0-9]{0,2})?$")) {
                result += ddh + ":价税合计格式不正确;";
            }*/

            // 价税合计
            String TaxMark = String.valueOf(jyxxsq.getHsbz());
            if (TaxMark == null || TaxMark.equals("")) {
                result += ddh + ":含税标志为空;";
            }
            // 发票类型
            String fpzldm = (String) jyxxsq.getFpzldm();
            /*
             * if (!"12".equals(fpzldm) || !"01".equals(fpzldm) ||
			 * !"02".equals(fpzldm)) { result += ddh + ":不支持" + fpzldm +
			 * ",目前只支持01、02、12;"; }
			 */

            String drawer = jyxxsq.getKpr();
            if (null == drawer || drawer.equals("")) {
                result += ddh + ":开票人不能为空;";
            }
            // 购方名称
            if (!Operation.equals("02")) {
                String buyerName = jyxxsq.getGfmc();
                if (null == buyerName || buyerName.equals("")) {
                    result += ddh + ":购方名称不能为空;";
                } else if (buyerName.length() > 100) {
                    result += ddh + ":购方名称太长;";
                }
            }
            String CustomerType = (String) jyxxsq.getGflx();
            if (CustomerType != null && !CustomerType.equals("")) {
                if(CustomerType.equals("1")){
                	String buyerIdentifier = (String) jyxxsq.getGfsh();
                	if(null == buyerIdentifier || buyerIdentifier.equals("") || buyerIdentifier.substring(0,1).equals(0)){
                		result += ddh + ":购方税号不能为空或不符合规则;";
                	}
                }
            }
            // 购方税号
            String buyerIdentifier = (String) jyxxsq.getGfsh();
            if (buyerIdentifier != null && !buyerIdentifier.equals("")) {
                if (!(buyerIdentifier.length() == 15 || buyerIdentifier.length() == 18 || buyerIdentifier.length() == 20 )) {
                    result += ddh + ":购方税号"+buyerIdentifier+"长度有误，请核对;";
                }
            }
            // 购方地址
            String buyerAddress = (String) jyxxsq.getGfdz();
            if (buyerAddress != null && !buyerAddress.equals("") && buyerAddress.length() > 100) {
                result += ddh + ":购方地址太长;";
            }
            // 购方电话
            String buyerTelephoneNo = (String) jyxxsq.getGfdh();
            if (buyerTelephoneNo != null && !buyerTelephoneNo.equals("") && buyerTelephoneNo.length() > 20) {
                result += ddh + ":购方电话超过20个字符;";
            }
            // 购方银行
            String buyerBank = (String) jyxxsq.getGfyh();
            if (buyerBank != null && !buyerBank.equals("") && buyerBank.length() > 50) {
                result += ddh + ":购方银行超过50个字符;";
            }
            // 购方银行账号
            String buyerBankAcc = (String) jyxxsq.getGfyhzh();
            if (buyerBankAcc != null && !buyerBankAcc.equals("") && buyerBankAcc.length() > 50) {
                result += ddh + ":购方银行账号超过50个字符;";
            }
            // 开具纸质专用发票时，购方所有信息必须有
            if ("01".equals(fpzldm) && !Operation.equals("02")) {
                if (null == buyerIdentifier || buyerIdentifier.equals("")) {
                    result += ddh + ":发票种类为01时购方税号不能为空;";
                }
                if (null == buyerBank || buyerBank.equals("")) {
                    result += ddh + ":发票种类为01时购方银行不能为空;";
                }
                if (null == buyerBankAcc || buyerBankAcc.equals("")) {
                    result += ddh + ":发票种类为01时购方银行账号不能为空;";
                }
            }

            // email
            String Email = (String) jyxxsq.getGfemail();
            if (Email != null && !Email.equals("") && !Email
                    .matches("^([a-z0-9A-Z]+[-|\\_.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$")) {
                result += ddh + ":请求参数<Email>格式有误;";
            }
            // 提取码校验
            String tqm = jyxxsq.getTqm();
            if (null !=tqm && !tqm.equals("")) {
                tqmList.add(tqm);
            }
            // 交易流水号校验
            String jylsh = jyxxsq.getJylsh();
            jylshList.add(jylsh);
            // 购方收件人地址
            String gfsjrdz = jyxxsq.getGfsjrdz();
            if (gfsjrdz != null && !gfsjrdz.equals("") && gfsjrdz.length() > 100) {
                result += ddh + ":购方收件人地址太长;";
            }

            String Zip = jyxxsq.getGfyb();
            if (Zip != null && Zip.length() > 10) {
                result += ddh + ":购方收件人邮编不能超过10个字符;";
            }

            String kpddm = jyxxsq.getKpddm();
            String xfsh = jyxxsq.getXfsh();
            Map tt = new HashMap();
            if (null == kpddm || kpddm.equals("")) {
                result += ddh + ":开票点不能为空;";
            } else {
                tt.put("kpddm", kpddm);
                tt.put("xfsh", xfsh);
                tt.put("gsdm", gsdm);
                Xf xf = jyxxsqService.findXfExistByXfsh(tt);
                if (null == xf || xf.equals("")) {
                    result += "该公司" + gsdm + ":对应的销方不存在!;";
                } else {
                    tt.put("xfid", xf.getId());
                    Skp skp = jyxxsqService.findskpExistByXfid(tt);
                    if (null == skp || skp.equals("")) {
                        result += "该销方" + xf.getXfsh() + ":对应的" + kpddm + "不存在!;";
                    }
                }
            }
        }
        // 一次性校验提取码和交易流水号是否重复上传，每笔交易流水号必须唯一，提取码也唯一,订单号也必须唯一
        if (null != tqmList && !tqmList.isEmpty()) {
            tqmMap.put("tqmList", tqmList);
            tqmMap.put("gsdm", gsdm);
            List<Jyxxsq> t1 = jyxxsqService.findAllByTqms(tqmMap);
            if (null != t1 && !t1.isEmpty()) {
                for (int i = 0; i < t1.size(); i++) {
                    Jyxxsq jy1 = (Jyxxsq) t1.get(i);
                    result += "提取码" + jy1.getTqm() + "已存在;";
                }
            }
        }
        jylshMap.put("jylshList", jylshList);
        jylshMap.put("gsdm", gsdm);
        List<Jyxxsq> t2 = jyxxsqService.findAllByJylshs(jylshMap);
        if (null != t2 && !t2.isEmpty()) {
            for (int i = 0; i < t2.size(); i++) {
                Jyxxsq jy2 = (Jyxxsq) t2.get(i);
                result += "交易流水号" + jy2.getJylsh() + "已存在;";
            }
        }

//        ddhMap.put("ddhList", ddhList);
//        ddhMap.put("gsdm", gsdm);
//        List<Jyxxsq> t3 = jyxxsqService.findAllByDdhs(ddhMap);
//        if (null != t3 && !t3.isEmpty()) {
//            for (int i = 0; i < t3.size(); i++) {
//                Jyxxsq jy3 = (Jyxxsq) t3.get(i);
//                if ((null == jy3.getSfcp() || jy3.getSfcp().equals(""))) {
//                    result += "订单号" + jy3.getDdh() + "已存在;";
//                } else if (Operation.equals("01")) {
//                    result += "订单号" + jy3.getDdh() + "已存在;";
//                }
//            }
//        }
        return result;
    }

    public String checkOrders(List<Jyxxsq> jyxxsqList, List<Jymxsq> jymxsqList, List<Jyzfmx> jyzfmxList, String gsdm, String Operation){

        String result = "";
        String ddh = "";
        String ddh2 = "";
        Jyxxsq jyxxsq = new Jyxxsq();
        Jymxsq jymxsq = new Jymxsq();
        Jyzfmx jyzfmx = new Jyzfmx();
//        List ddhList = new ArrayList();
        Map ddhMap = new HashMap();

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
                        if (ddh.length() > 100) {
                            result += "明细数据" + ddh + ":订单号太长;";
                        }
//                        ddhList.add(ddh);
                    } else {
                        result += "明细数据订单号不能为空;";
                    }
                    String ProductCode = (String) jymxsq.getSpdm();
                    if (ProductCode == null) {
                        result += "订单号为" + ddh + "的订单,第"+ j+1 + "行的商品税收分类编码为空!\r\n";
                    } else if (ProductCode.length() != 19) {
                        result += "订单号为" + ddh + "的订单,第"+ j+1 + "行的商品税收分类编码维护不正确，请联系商户!\r\n;";
                    }
                    // 商品名称
                    String ProductName = (String) jymxsq.getSpmc();
                    if (ProductName == null) {
                        result += "订单号为" + ddh + "的订单，第"+ j+1 + "行的商品名称为空！\r\n";
                    } else if (ProductName.length() > 50) {
                        result += "订单号为" + ddh + "的订单，第"+ j+1 + "行的商品名称维护不正确，请联系商户！\r\n";
                    }
                    // 发票行性质
                    String RowType = (String) jymxsq.getFphxz();
                    if (RowType == null) {
                        result += "订单号为" + ddh + "的订单RowType为空！\r\n";
                    } else if (!("0".equals(RowType) || "1".equals(RowType) || "2".equals(RowType))) {
                        result += "订单号为" + ddh + "的订单RowType只能填写0，1或2!\r\n";
                    }
                    // 商品数
                    /*
                     * String Quantity = String.valueOf(jymxsq.getSps()); if
					 * (Quantity != null && Double.valueOf(Quantity) >= 0) {
					 * result += "订单号为" + ddh + "的订单Quantity在红冲或换开时必须为负数！"; }
					 */
                    // 商品金额
                    String Amount = String.valueOf(jymxsq.getSpje());
                    if (Amount == null) {
                        result += "订单号为" + ddh + "的订单,第"+ j+1+"行的商品金额为空\r\n";
                    } else if (!Amount.matches("^\\-?[0-9]{0,15}+(.[0-9]{0,2})?$")) {
                        result += "订单号为" + ddh + "的订单，第"+ j+1+"行的商品金额格式不正确！\r\n";
                    }

                    if(RowType.equals("1")&&Double.valueOf(Amount)>=0){
                        result += "订单号为" + ddh + "的订单,发票行性质为1时，Amount必须小于0！\r\n";
                    }
                    // 商品税率
                    String TaxRate = String.valueOf(jymxsq.getSpsl());
                    if (TaxRate == null) {
                        result = "订单号为" + ddh + "的订单，第"+ j+1+"行的商品税率(TaxRate)为空;\r\n";
                    } else {
                        double taxRate = Double.valueOf(TaxRate);
                        if (!(taxRate == 0 || taxRate == 0.03 || taxRate == 0.04
                                || taxRate == 0.06 || taxRate == 0.11 || taxRate == 0.13
                                || taxRate == 0.17)) {
                            result += "订单号为" + ddh + "的订单,商品税率(TaxRate)格式有误，请联系商户;\r\n";
                        }
                    }
                    if((jymxsq.getSpdj()==null&&jymxsq.getSps()!=null)||(jymxsq.getSps()==null&&jymxsq.getSpdj()!=null)){
                        result += "订单号为" + ddh + "的订单第" + j+1+ "行商品单价，商品数量必须全部为空或者全部不为空！\r\n";
                    }
                    if (jymxsq.getSpdj() != null && jymxsq.getSps() != null && jymxsq.getSpje() != null) {
                        double res = jymxsq.getSpdj() * jymxsq.getSps();
                        BigDecimal big1 = new BigDecimal(res);
                        big1 = big1.setScale(2, BigDecimal.ROUND_HALF_UP);
                        BigDecimal big2 = new BigDecimal(jymxsq.getSpje().toString());
                        big2 = big2.setScale(2, BigDecimal.ROUND_HALF_UP);
                        if (big1.compareTo(big2) != 0) {
                            result += "订单号为" + ddh + "的订单第" + j+1+ "行商品单价，商品数量，商品金额之间的计算校验不通过，请检查！\r\n";
                        }
                    }
                    if(jymxsq.getSpdj() != null && jymxsq.getSps() != null && jymxsq.getSpje() != null){
                        Double spdj = jymxsq.getSpdj();
                        Double sps = jymxsq.getSps();
                        BigDecimal big1 = new BigDecimal(spdj.toString());
                        BigDecimal big2 = new BigDecimal(sps.toString());
                        if((big1.compareTo(new BigDecimal(0))==0)||(big2.compareTo(new BigDecimal(0))==0)){
                            result += "订单号为" + ddh + "的订单第" + j+1+ "行商品单价或商品数量不能为零！\r\n";
                        }
                    }
                    // 商品税额
                    String TaxAmount = String.valueOf(jymxsq.getSpse());
                    if (TaxAmount != null && TaxAmount.equals("^\\-?[0-9]{0,15}+(.[0-9]{0,2})?$")) {
                        result += "订单号为" + ddh + "的订单第" + j +1+ "条商品税额(TaxAmount)格式不正确！\r\n";
                    }

                    // 校验金额误差
                    String TaxMark = jyxxsq.getHsbz();
                    Double je = Double.valueOf(Amount);
                    Double se = 0d;
                    //含税时，忽略税额
                    if (TaxMark.equals("0")) {
                        if (TaxAmount != null && !"".equals(TaxAmount)) {
                            se = Double.valueOf(TaxAmount);
                        }
                    }
                    double sl = Double.valueOf(TaxRate);
                    if (TaxMark.equals("0") && je * sl - se >= 0.0625) {
                        result += "订单号为" + ddh + "的订单(Amount，TaxRate，TaxAmount)之间的校验不通过!\r\n";
                    }

                    BigDecimal bd = new BigDecimal(je.toString());
                    BigDecimal bd1 = new BigDecimal(se.toString());
                    ajshj = bd.add(bd1);
                    jshj = jshj.add(ajshj);

                    String ChargeTaxWay = jyxxsq.getZsfs();
                    String DeductAmount = String.valueOf(jymxsq.getKce());
                    if (ChargeTaxWay.equals("2") && (null == DeductAmount || DeductAmount.equals(""))) {
                        result += "订单号为" + ddh + "的订单DeductAmount不能为空!\r\n";
                    }
                }
            }
            BigDecimal bd2 = new BigDecimal(jyxxsq.getJshj().toString());
            if (bd2.setScale(2, BigDecimal.ROUND_HALF_UP).subtract(jshj.setScale(2, BigDecimal.ROUND_HALF_UP)).doubleValue() != 0.0) {
                result += "订单号为" + ddh + "的订单TotalAmount，Amount，TaxAmount计算校验不通过\r\n";
            }
            Map params = new HashMap();
            List zffsdmList = new ArrayList();
			if (null != jyzfmxList && !jyzfmxList.isEmpty()) {
				List kpfsList = new ArrayList();
				//kpfsList.add("02");
				params.put("gsdm", gsdm);
				//params.put("kpfsList", kpfsList);
                for(int j = 0; j < jyzfmxList.size(); j++){
                    jyzfmx = (Jyzfmx) jyzfmxList.get(j);
                    if(!zffsdmList.contains(jyzfmx.getZffsDm()))
                    zffsdmList.add(jyzfmx.getZffsDm());

                    ddh2 = jyzfmx.getDdh();
                    if (ddh.equals(ddh2)) {
                        BigDecimal zfje = new BigDecimal(jyzfmx.getZfje().toString());
                        jshj2 = jshj2.add(zfje);
                    }
                }
                Cszb cszb = cszbservice.getSpbmbbh(gsdm, jymxsq.getXfid(), jyxxsq.getSkpid(), "sfsfcl");
                if (null == cszb || cszb.getCsz().equals("否")) {
                    //交易支付明细合计！=价税合计并且交易支付明细合计舍分！=价税合计
                    if (jshj2.compareTo(bd2) !=0 && jshj2.setScale(1, BigDecimal.ROUND_DOWN).compareTo(bd2) !=0) {
                        result += "订单号为" + ddh + "的订单,商品单价合计与总金额不等;\r\n";
                    }
                }

                params.put("zffsList", zffsdmList);
                List<Zffs> zffsList = zffsService.findAllByParams(params);
                if(null == zffsList ||zffsList.isEmpty()){
					result += "请去平台支付方式管理维护对应的支付方式;\r\n";
				}else{
                    if(zffsList.size() != zffsdmList.size()){
                        result += "订单号为" + ddh + "的订单有支付方式未维护，请联系管理员进行维护\r\n";
                    }
                }
            }
        }
//        ddhMap.put("ddhList", ddhList);
//        ddhMap.put("gsdm", gsdm);
//        List<Jymxsq> t3 = jymxsqService.findAllByDdhs(ddhMap);
//        if (null != t3 && !t3.isEmpty()) {
//            for (int i = 0; i < t3.size(); i++) {
//                Jymxsq jy3 = (Jymxsq) t3.get(i);
//                result += "明细数据订单号" + jy3.getDdh() + "已存在;";
//            }
//        }
        return result;

    }
    public String checkAll(List<Jyxxsq> jyxxsqList, List<Jymxsq> jymxsqList,  String gsdm, String Operation) {
       return  checkAll( jyxxsqList,  jymxsqList,new ArrayList<Jyzfmx>(),   gsdm,  Operation);
    }

    public String checkAll(List<Jyxxsq> jyxxsqList, List<Jymxsq> jymxsqList, List<Jyzfmx> jyzfmxList, String gsdm, String Operation) {
        	String result = "";
        	result = checkBuyer(jyxxsqList, gsdm, Operation);
        	result += checkOrders(jyxxsqList, jymxsqList, jyzfmxList, gsdm, Operation);
        	return result;
        }

    public static void main(String[] args) {

        String s = "123;";
        // System.out.print(s.replace(s.substring(s.length()-1), ""));
        System.out.print(s.substring(0,1));
        System.out.print(Double.parseDouble(".00") == 0);
    }
}
