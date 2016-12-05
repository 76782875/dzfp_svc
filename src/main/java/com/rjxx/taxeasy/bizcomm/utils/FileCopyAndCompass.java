package com.rjxx.taxeasy.bizcomm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.UUID;
import org.apache.commons.io.FileUtils;
import com.rjxx.comm.utils.ApplicationContextUtils;
import com.rjxx.taxeasy.service.GdjlService;
import com.rjxx.taxeasy.vo.Fpcxvo;
import com.rjxx.utils.ReadProperties;


public class FileCopyAndCompass implements Runnable{
	
	
	private GdjlService gdService;

	private Integer id;

	private List<Fpcxvo> list;
	
	
    public FileCopyAndCompass(Integer id,List<Fpcxvo> list){
    	this.id = id;
    	this.list = list;
    	gdService = ApplicationContextUtils.getBean(GdjlService.class);
    }
	
	public void run() {
		String path = UUID.randomUUID().toString();
		String copyPath = getString("copy_path")+"/"+path+"/";
		new File(copyPath).mkdirs();            //创建文件保存路径
		String xfsh = list.get(0).getXfsh();
        for(Fpcxvo item:list){
        	String ddh  = item.getDdh();
        	String gfmc = item.getGfmc();
        	String fpdm = item.getFpdm();
        	String fphm = item.getFphm();
        	String pdfurl = item.getPdfurl();
        	//文件复制完成  
        	try {
				fileCopy(copyPath,pdfurl,ddh,gfmc,fpdm,fphm);
			} catch (Exception e) {
				e.printStackTrace();
			}         	
        }
        fileCompass(id,copyPath,xfsh);		
	}
	
	/**
     * 复制pdf文件
     * */
	public void fileCopy(String copyPath,String pdfurl,String ddh,String gfmc,String fpdm,String fphm)throws Exception{
		String pdfPath=ReadProperties.read("classpath").replaceAll("\\\\", "/");
		String httpPath = ReadProperties.read("serverUrl");
		if(pdfurl !=null && !"".equals(pdfurl)){
			String filePath = pdfurl.replace(httpPath, pdfPath);
			File file = new File(filePath);			
			if(file.isFile()){
				//String type = file.getName().substring(file.getName().lastIndexOf(".") + 1);//取得文件类型，此处皆为pdf
				String newName = ddh+"_"+gfmc+"_"+fpdm+"_"+fphm;
				FileUtils.copyFile(file, new File(copyPath+newName+".pdf")); 
			}
		}
	}
	
	// 文件通道复制文件
	public void fileChannelCopy(File s, File t) {
       FileInputStream fi = null;
       FileOutputStream fo = null;
       FileChannel in = null;
       FileChannel out = null;
       try {
           fi = new FileInputStream(s);
           fo = new FileOutputStream(t);
           in = fi.getChannel();//得到对应的文件通道
           out = fo.getChannel();//得到对应的文件通道
           in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
       } catch (IOException e) {
           e.printStackTrace();
       } finally {
           try {
               fi.close();
               in.close();
               fo.close();
               out.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
   }
	/*public static void main(String args){
		File f1 = new File("D:/dzfpFile/20161122/sql.txt");
		File f2 = new File("D:/dzfpFile/20161130/aaa.txt");
		fileChannelCopy(f1,f2);
		
	}*/
	//调用shell压缩文件
	public void fileCompass(Integer id,String copyPath,String xfsh){
		String filePath = UUID.randomUUID().toString();
		File file = new File(copyPath);
		String path = getString("compass_Path");
		new File(path+"/"+xfsh+"/compass/").mkdirs();
		String compassPath = path+"/"+xfsh+"/compass/"+filePath+".7z";
		Runtime rt = Runtime.getRuntime();
		if(file.exists()){		
			//生成得文件为savePath.tar.gz 要压缩的文件为D：/dzfpFile/savePath下的所有文件
			String command = "7za a "+compassPath+" "+copyPath;
			System.out.println(command);
			try {
				Process p = rt.exec(command);
				p.waitFor();
			} catch (Exception e) {
				e.printStackTrace();
			}  
		}
		//删除原来的文件指令
		String command1 = "rm -rf "+copyPath;
		try {
			Process p1 = rt.exec(command1);
			p1.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String requestPath = getString("gd_request_Path")+xfsh+"/compass/"+filePath+".7z";
		//更新归档记录的状态
		Map params = new HashMap<>();
		int wjsl = list.size();
		params.put("id",id);
		params.put("xzlj",requestPath);
		params.put("wjsl", wjsl);
		gdService.updategdzt(params);
	}
	
	//获取配置文件中保存压缩文件的文件夹
		public String getString(String key) {
	    	String propertyFileName = "application";
	    	ResourceBundle resourceBundle=ResourceBundle.getBundle(propertyFileName);
	        if (key == null || key.equals("") || key.equals("null")) {
	            return "";
	        }
	        String result = "";
	        try {
	            result = resourceBundle.getString(key);
	        } catch (MissingResourceException e) {
	            e.printStackTrace();
	        }
	        return result;
	    }

}
