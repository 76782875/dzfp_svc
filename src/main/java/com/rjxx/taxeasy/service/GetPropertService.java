package com.rjxx.taxeasy.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GetPropertService {
	
	@Value("${pdf.classpath}")
	private String classpath;
	
	@Value("${pdf.serverUrl}")
	private String serverUrl;

	public String getClasspath() {
		return classpath;
	}

	public String getServerUrl() {
		return serverUrl;
	}


}
