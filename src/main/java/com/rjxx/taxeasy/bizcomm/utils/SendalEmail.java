package com.rjxx.taxeasy.bizcomm.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

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

public class SendalEmail {
	static @Autowired YjjlService yjjlService;

	public static boolean sendEmail(String djh, String gsdm, String sjryx, String type, String ref_Id, String yjnr,
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
		yjjl.setYjnr(yjnr);
		yjjl.setYjbt(yjbt);
		yjjlService.save(yjjl);
		return true;
	}

	public static void main(String[] args) {
		Boolean msg = SendalEmail.sendEmail("123456", "rjxx", "179637014@qq.com", "发票开具", "123456", "您的发票已开具成功",
				"电子发票");
		System.out.println(msg);
	}
}