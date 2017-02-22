package com.rjxx.taxeasy.bizcomm.utils;

import java.util.Map;

import com.rjxx.utils.SendMessage;

public class SendMessageNew {
	SendMessage sendmessage = new SendMessage();

	/**
	 * qmmc "泰易电子发票" mbCode 模板代码 rep 信息内容 sjhm 手机号
	 */
	public String sendMessage(String qmmc, String mbCode, Map rep, String sjhm) {
		String result = "";
		sendmessage.sendMessage(qmmc, mbCode, rep, sjhm);
		return result;
	}

}
