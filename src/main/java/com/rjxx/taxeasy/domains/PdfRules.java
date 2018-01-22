package com.rjxx.taxeasy.domains;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import com.rjxx.comm.json.JsonDateFormat;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
/**
 * pdf_rules 实体类
 * 由GenEntityMysql类自动生成
 * Mon Jan 22 11:31:37 CST 2018
 * @ZhangBing
 */ 
@Entity
@Table(name="pdf_rules")
public class PdfRules  implements Serializable {

/**
 * id
 */ 
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * pdf_path
 */ 
@Column(name="pdf_path")
	protected String pdfPath;

/**
 * nginx_pdfurl
 */ 
@Column(name="nginx_pdfurl")
	protected String nginxPdfurl;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getPdfPath(){
		return pdfPath;
	}

	public void setPdfPath(String pdfPath){
		this.pdfPath=pdfPath;
	}

	public String getNginxPdfurl(){
		return nginxPdfurl;
	}

	public void setNginxPdfurl(String nginxPdfurl){
		this.nginxPdfurl=nginxPdfurl;
	}

}

