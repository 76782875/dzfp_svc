package com.rjxx.taxeasy.task;

import com.rjxx.taxeasy.dao.shouqianba.*;
import com.rjxx.taxeasy.domains.shouqianba.PayActivate;
import com.rjxx.taxeasy.domains.shouqianba.PayOut;
import com.rjxx.taxeasy.domains.shouqianba.PayPayments;
import com.rjxx.taxeasy.domains.shouqianba.PayRecord;
import com.rjxx.taxeasy.dto.shouqianba.QueryBizResponse;
import com.rjxx.taxeasy.dto.shouqianba.QueryData;
import com.rjxx.taxeasy.dto.shouqianba.QueryPayment;
import com.rjxx.taxeasy.dto.shouqianba.QueryResult;
import com.rjxx.utils.shouqianba.PayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/6/13
 */
public class PayTask implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(PayTask.class);
    private String terminalSn;
    private String clientSn;
    private String sn;
    private String gsdm;
    private String orderNo;
    private String storeNo;
    private String reflect;
    private String sign;
    private PayRecordRepository payRecordRepository;
    private PayOutRepository payOutRepository;
    private PayPaymentsRepository payPaymentsRepository;
    private PayCodeRepository payCodeRepository;
    private PayActivateRepository payActivateRepository;

    private static final String TIME_OUT = "120000";
    private static final String SLOW_QUERY = "30000";

    @Override
    public void run() {
        //获取最终状态List
        List<String> finalCodeList = payCodeRepository.findByIsFinal();
        PayActivate payActivate = payActivateRepository.findOneByTerminalSn(terminalSn);
        String terminal_key = payActivate.getTerminalKey();
        logger.info("terminal_key={}", terminal_key);
        //开始轮询
        boolean flag = true;
        QueryResult query = null;
        BigInteger initTime = BigInteger.valueOf(System.currentTimeMillis());
        x:
        if (flag) {
            //查询获得结果
            QueryResult q = PayUtil.query(terminalSn, terminal_key, clientSn, sn);
            if (q == null) {
                BigInteger curTime = BigInteger.valueOf(System.currentTimeMillis());
                if (curTime.subtract(initTime).compareTo(new BigInteger(TIME_OUT)) == 1) {
                    cancel(terminalSn, terminal_key, sn, clientSn);
                    return;
                }
                break x;
            }
            QueryBizResponse biz_response = q.getBiz_response();
            QueryData data = biz_response.getData();
            PayOut savePayOut = this.saveRecord(gsdm, orderNo, clientSn, sn, terminalSn, sign, reflect, q, "0",storeNo);
            if (savePayOut == null) {
                return;
            }
            List<QueryPayment> payment_list = data.getPayment_list();
            List<PayPayments> byoutId = payPaymentsRepository.findByoutId(savePayOut.getId());
            if (byoutId == null) {
                for (QueryPayment queryPayment : payment_list) {
                    PayPayments payPayments = new PayPayments();
                    payPayments.setLrsj(new Date());
                    payPayments.setGsdm(gsdm);
                    payPayments.setAmountTotal(queryPayment.getAmount_total());
                    payPayments.setType(queryPayment.getType());
                    payPayments.setOutId(savePayOut.getId());
                    payPaymentsRepository.save(payPayments);
                }
            }

            String order_status = data.getOrder_status();
            if (finalCodeList.contains(order_status)) {
                flag = false;
            } else {
                try {
                    BigInteger curTime = BigInteger.valueOf(System.currentTimeMillis());
                    if (curTime.subtract(initTime).compareTo(new BigInteger(TIME_OUT)) == 1) {
                        cancel(terminalSn, terminal_key, sn, clientSn);
                        return;
                    }

                    if (curTime.subtract(initTime).compareTo(new BigInteger(SLOW_QUERY)) == 1) {
                        Thread.sleep(5000);
                    } else {
                        Thread.sleep(2000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            break x;
        }
    }


    public void cancel(String terminalSn, String terminal_key, String sn, String clientSn) {
        //撤单
        QueryResult cancel = PayUtil.cancel(terminalSn, terminal_key, sn, clientSn);
        if (cancel == null) {
            PayRecord cancelFailRecord = new PayRecord();
            cancelFailRecord.setLrsj(new Date());
            cancelFailRecord.setGsdm(gsdm);
            cancelFailRecord.setOrderNo(orderNo);
            cancelFailRecord.setTerminalSn(terminalSn);
            cancelFailRecord.setTerminalKey(terminal_key);
            payRecordRepository.save(cancelFailRecord);
            PayOut cancelFailOut = payOutRepository.findOneByGsdmAndOrderNo(gsdm, orderNo);
            cancelFailOut.setOrderStatus("CANCEL_TIME_OUT");
            payOutRepository.save(cancelFailOut);
        }
        saveRecord(gsdm, orderNo, clientSn, sn, terminalSn, sign, reflect, cancel, "1",storeNo);
    }

    public PayOut saveRecord(String gsdm, String orderNo, String clientSn, String sn, String terminalSn,
                             String sign, String reflect, QueryResult queryResult, String reqType,String storeNo) {
        QueryBizResponse biz_response = queryResult.getBiz_response();
        QueryData data = biz_response.getData();

        //创建记录表对象
        PayRecord payRecord = new PayRecord();
        payRecord.setLrsj(new Date());
        payRecord.setGsdm(gsdm);
        payRecord.setOrderNo(orderNo);
        payRecord.setqResultCode(biz_response.getResult_code());
        payRecord.setqOrderStatus(data.getOrder_status());
        payRecord.setErrorCode(biz_response.getError_code());
        payRecord.setErrorMessage(biz_response.getError_message());
        payRecord.setClientSn(data.getClient_sn());
        payRecord.setSn(data.getSn());
        payRecord.setTradeNo(data.getTrade_no());
        payRecord.setTotalAmount(data.getTotal_amount());
        payRecord.setStatus(data.getStatus());
        payRecord.setReqType(reqType);//0查询 1撤单
        payRecord.setStoreNo(storeNo);
        payRecordRepository.save(payRecord);

        if (data == null) {
            logger.info("未查到数据-------sn=" + sn + "-------clientSn=" + clientSn);
            return null;
        }

        PayOut oldOutCancel = payOutRepository.findOneByGsdmAndOrderNo(gsdm, orderNo);
        if (oldOutCancel == null) {
            logger.info("支付时未记录成功-------sn=" + sn + "-------clientSn=" + clientSn);
            return null;
        }

        //更新结果表
        oldOutCancel.setXgsj(new Date());
        oldOutCancel.setGsdm(gsdm);
        oldOutCancel.setClientSn(clientSn);
        oldOutCancel.setTerminalSn(terminalSn);
        oldOutCancel.setSn(sn);
        oldOutCancel.setReflect(reflect);
        oldOutCancel.setSign(sign);
        oldOutCancel.setErrorCode(biz_response.getError_code());
        oldOutCancel.setErrorMessage(biz_response.getError_message());
        oldOutCancel.setOperator(data.getOperator());
        oldOutCancel.setqResultCode(biz_response.getResult_code());
        oldOutCancel.setStatus(data.getStatus());
        oldOutCancel.setTotalAmount(data.getTotal_amount());
        oldOutCancel.setSubject(data.getSubject());
        oldOutCancel.setTradeNo(data.getTrade_no());
        oldOutCancel.setChannelFinishTime(data.getChannel_finish_time());
        oldOutCancel.setFinishTime(data.getFinish_time());
        oldOutCancel.setNetAmount(data.getNet_amount());
        oldOutCancel.setPayerUid(data.getPayer_uid());
        oldOutCancel.setPaywayName(data.getPayway_name());
        oldOutCancel.setOrderStatus(data.getOrder_status());
        oldOutCancel.setStoreNo(storeNo);

        return payOutRepository.save(oldOutCancel);
    }


    public String getTerminalSn() {
        return terminalSn;
    }

    public void setTerminalSn(String terminalSn) {
        this.terminalSn = terminalSn;
    }

    public String getClientSn() {
        return clientSn;
    }

    public void setClientSn(String clientSn) {
        this.clientSn = clientSn;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getGsdm() {
        return gsdm;
    }

    public void setGsdm(String gsdm) {
        this.gsdm = gsdm;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getReflect() {
        return reflect;
    }

    public void setReflect(String reflect) {
        this.reflect = reflect;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public PayRecordRepository getPayRecordRepository() {
        return payRecordRepository;
    }

    public void setPayRecordRepository(PayRecordRepository payRecordRepository) {
        this.payRecordRepository = payRecordRepository;
    }

    public PayOutRepository getPayOutRepository() {
        return payOutRepository;
    }

    public void setPayOutRepository(PayOutRepository payOutRepository) {
        this.payOutRepository = payOutRepository;
    }

    public PayPaymentsRepository getPayPaymentsRepository() {
        return payPaymentsRepository;
    }

    public void setPayPaymentsRepository(PayPaymentsRepository payPaymentsRepository) {
        this.payPaymentsRepository = payPaymentsRepository;
    }

    public PayCodeRepository getPayCodeRepository() {
        return payCodeRepository;
    }

    public void setPayCodeRepository(PayCodeRepository payCodeRepository) {
        this.payCodeRepository = payCodeRepository;
    }

    public PayActivateRepository getPayActivateRepository() {
        return payActivateRepository;
    }

    public void setPayActivateRepository(PayActivateRepository payActivateRepository) {
        this.payActivateRepository = payActivateRepository;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }
}
