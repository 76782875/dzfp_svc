package com.rjxx.taxeasy.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GetPropertService {
	
	@Value("${pdf.classpath:''}")
	private String classpath;
	
	@Value("${pdf.serverUrl:''}")
	private String serverUrl;
	
	@Value("${email.emailHost:''}")
	private String emailHost;
	
	@Value("${email.emailUserName:''}")
	private String emailUserName;
	
	@Value("${email.emailPwd:''}")
	private String emailPwd;
	
	@Value("${email.emailForm:''}")
	private String emailForm;
	
	@Value("${email.emailTitle:''}")
	private String emailTitle;

	public String getClasspath() {
		return classpath;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public String getEmailHost() {
		return emailHost;
	}

	public String getEmailUserName() {
		return emailUserName;
	}

	public String getEmailPwd() {
		return emailPwd;
	}

	public String getEmailForm() {
		return emailForm;
	}

	public String getEmailTitle() {
		return emailTitle;
	}

	public void setEmailTitle(String emailTitle) {
		this.emailTitle = emailTitle;
	}


}
