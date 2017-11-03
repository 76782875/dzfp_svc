package com.rjxx.taxeasy.bizcomm.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.rjxx.taxeasy.domains.Yjjl;
import com.rjxx.taxeasy.service.YjjlService;

@Service
public class SendalEmail {
	 @Autowired YjjlService yjjlService;
	 /**
	  * 
	  * @param djh 单据号,没有可以不填
	  * @param gsdm 公司代码
	  * @param sjryx 收件人邮箱
	  * @param type 什么功能发送的邮件
	  * @param ref_Id 关联id 现djh
	  * @param yjnr 邮件内容
	  * @param yjbt 邮件标题
	  * @return
	  */
	public boolean sendEmail(String djh, String gsdm, String sjryx, String type, String ref_Id, String yjnr,
			String yjbt) {
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "5T6XUKr6uxSfhNAu",
				"a7cBFQR3avT4NSIR6dFtP8GLvzcL5G");
		IAcsClient client = new DefaultAcsClient(profile);
		SingleSendMailRequest request = new SingleSendMailRequest();
		String message = "";
		try {
			request.setAccountName("service@einvoice.datarj.com");
			request.setFromAlias("invoice");
			request.setAddressType(1);
			request.setTagName("rjxx");
			request.setReplyToAddress(true);
			request.setToAddress(sjryx);
			request.setSubject(yjbt);
			request.setHtmlBody(yjnr);
			SingleSendMailResponse httpResponse = client.getAcsResponse(request);
			message = httpResponse.getRequestId();
		} catch (ServerException e) {
			e.printStackTrace();
			return false;
		} catch (ClientException e) {
			e.printStackTrace();
			return false;
		}
	   Yjjl yjjl = new Yjjl();
	    yjjl.setSjryx(sjryx);
		yjjl.setGsdm(gsdm);
		yjjl.setType(type);
		yjjl.setLrsj(new Date());
		yjjl.setRefId(djh);
		yjjl.setReturnid(message);
		//yjjl.setYjnr(yjnr);
		yjjl.setYjbt(yjbt);
		yjjlService.save(yjjl);
	   return true;
	}
	
	 /**
	  * 
	  * @param djh 单据号,没有可以不填
	  * @param gsdm 公司代码
	  * @param sjryx 收件人邮箱
	  * @param type 什么功能发送的邮件
	  * @param ref_Id 关联id 现djh
	  * @param yjnr 邮件内容
	  * @param yjbt 邮件标题
	  * @param isSave 保存邮件记录表1保存，0不保存
	  * @return
	  */
	public boolean sendEmail(String djh, String gsdm, String sjryx, String type, String ref_Id, String yjnr,
			String yjbt,String isSave) {
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "5T6XUKr6uxSfhNAu",
				"a7cBFQR3avT4NSIR6dFtP8GLvzcL5G");
		IAcsClient client = new DefaultAcsClient(profile);
		SingleSendMailRequest request = new SingleSendMailRequest();
		String message = "";
		try {
			request.setAccountName("service@einvoice.datarj.com");
			request.setFromAlias("invoice");
			request.setAddressType(1);
			request.setTagName("rjxx");
			request.setReplyToAddress(true);
			request.setToAddress(sjryx);
			request.setSubject(yjbt);
			request.setHtmlBody(yjnr);
			SingleSendMailResponse httpResponse = client.getAcsResponse(request);
			message = httpResponse.getRequestId();
		} catch (ServerException e) {
			e.printStackTrace();
			return false;
		} catch (ClientException e) {
			e.printStackTrace();
			return false;
		}
		
		if(isSave.equals("1")){
			Yjjl yjjl = new Yjjl();
		    yjjl.setSjryx(sjryx);
			yjjl.setGsdm(gsdm);
			yjjl.setType(type);
			yjjl.setLrsj(new Date());
			yjjl.setRefId(djh);
			yjjl.setReturnid(message);
			yjjl.setYjnr(yjnr);
			yjjl.setYjbt(yjbt);
			yjjlService.save(yjjl);
		}
	   return true;
	}

	public static void main(String[] args) {
		GetYjnr te = new GetYjnr();
		SendalEmail se = new SendalEmail();
		Boolean msg = se.sendEmail("123456", "rjxx", "kangzhongxu@datarj.com", "发票开具", "123456", te.getAfEmail("", ""),
				"电子发票");
		System.out.println(msg);
	}
}