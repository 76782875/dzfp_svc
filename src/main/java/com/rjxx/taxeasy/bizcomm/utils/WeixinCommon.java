package com.rjxx.taxeasy.bizcomm.utils;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rjxx.comm.utils.ApplicationContextUtils;
import com.rjxx.taxeasy.domains.Gsxx;
import com.rjxx.taxeasy.domains.Wxfs;
import com.rjxx.taxeasy.service.GsxxService;
import com.rjxx.taxeasy.service.WxfsService;
import com.rjxx.utils.WeixinUtil;
/*
 * 微信开发公共方法
 * **/
public class WeixinCommon {
	
	Logger logger = Logger.getLogger(WeixinCommon.class);

	public static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";// 获取access
	
	public static final String SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
	
	private GsxxService gsxxSercie = ApplicationContextUtils.getBean(GsxxService.class);
	
	private WxfsService wxfsService = ApplicationContextUtils.getBean(WxfsService.class);
	/**
	 * 获取accessToken
	 * 
	 * */
	public String getAccessToken(){
		String APP_ID = "wx9abc729e2b4637ee";
		String SECRET = "6415ee7a53601b6a0e8b4ac194b382eb";    //正式环境的appid与secret
		Map params = new HashMap<>();
		params.put("gsdm", "rjxx");
		Gsxx gsxx = gsxxSercie.findOneByParams(params);
		if(gsxx !=null){
			APP_ID = gsxx.getWxappid();
			SECRET = gsxx.getWxsecret();
		}
		String accessToken = "";
		String turl = GET_TOKEN_URL + "?grant_type=client_credential&appid=" + APP_ID + "&secret=" + SECRET;
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(turl);
		ObjectMapper jsonparer = new ObjectMapper();
		try {
			HttpResponse res = client.execute(get);
			String responseContent = null; // 响应内容
			HttpEntity entity = res.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
			Map map = jsonparer.readValue(responseContent, Map.class);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				if (map.get("errcode") != null) {// 错误时微信会返回错误码等信息，{"errcode":40013,"errmsg":"invalid appid"}

					return "获取微信token失败,错误代码为" + map.get("errcode");
				} else {     // 正常情况下{"access_token":"ACCESS_TOKEN","expires_in":7200}
					accessToken = ((String) map.get("access_token"));					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接 ,释放资源
			client.getConnectionManager().shutdown();
		}
		return accessToken;
	}
	/**
	 * jsonMsg:{"expire_seconds": 604800, "action_name": "QR_SCENE", "action_info": {"scene": {"scene_id": 123}}}
	 * expire_seconds:该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
	 * action_name：二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久,QR_LIMIT_STR_SCENE为永久的字符串参数值
	 * action_info：二维码详细信息
	 * scene_id：场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
	 * */
	public String getQr(String jsonMsg){
		String accessToken = getAccessToken();
		String url = "";
		String ticket = "";
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+accessToken;
		String jsonStr = WeixinUtil.httpRequest(requestUrl, "POST", jsonMsg);
		if(jsonStr !=null){
			ObjectMapper jsonparer = new ObjectMapper();// 初始化解析json格式的对象
			try {
				Map map = jsonparer.readValue(jsonStr, Map.class);
				ticket = (String) map.get("ticket");
				url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=";
				url = url+ticket;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return url;
	}
	
	 /**
	  * 用ticket获取二维码 
	  * ticket正确情况下，http 返回码是200，是一张图片，可以直接展示或者下载。
	  * */ 
	public String chageQr(String ticket){ 
		String str = "";
        String requestUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";  
        requestUrl = requestUrl.replace("TICKET", ticket);  
        String jsonStr = WeixinUtil.httpRequest(requestUrl, "GET", null);   
        if(null != jsonStr){
        	str = jsonStr;
        }  
        return str;  
    } 
	
	/**
	 * 订阅成功微信推送消息
	 * @param template_id 微信模板id
	 * @param url  模板跳转的url
	 * */
	public Map<String,Object> sentWxMsg(Map<Object, Object> content,String openid,String template_id,String url){
		Map<String,Object> result = new HashMap<String,Object>();
		String accessToken = getAccessToken();
		HttpPost httpPost = new HttpPost(SEND_URL + accessToken);
		ObjectMapper mapper = new ObjectMapper();
		Map<Object, Object> data = new HashMap<>();
		data.put("touser", openid);
		data.put("template_id", template_id);
		data.put("url", url);
		data.put("data", content);
		Map map = new HashMap<>();
		try {
			HttpClient client = new DefaultHttpClient();
			String json = mapper.writeValueAsString(data);
			httpPost.setEntity(new StringEntity(json, "UTF-8"));
			HttpResponse response = client.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String responseContent = EntityUtils.toString(entity, "UTF-8");
			ObjectMapper jsonparer = new ObjectMapper();
			map = jsonparer.readValue(responseContent, Map.class);
			if (map.get("errmsg").equals("ok")) {
				result.put("success", true);
			}else{
				result.put("success", false);
			}
		} catch (Exception e) {
			logger.info("微信消息推送失败！");
			e.printStackTrace();
		}
		return result;
	}
	
	/*public static void main(String[] args){
		String token = "8qAJUfehb8uqyPNqeKvwoflx2uDc5v5kswjozVl0clZ572hsmxFPfJazhDGYg3ikRUQQy3iQXwEx5mraI62dHZX3JUUQNkavIrJRiQm7yO8_7Fm4uEa63uMY0T2WcL_aOHJaACABUW";
		String json = "{\"expire_seconds\": 604800, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": 123}}}";
		String str = getQr(token,json);
		boolean flag = chageQr(str);
		System.out.println(flag);
	}*/
	/**
	 * 微信推送消息
	 * @param template_id 微信申请的发送消息的模板
	 * @param url 点击消息跳转的地址
	 * @param data1 拼写的消息
	 * */
	public void sendMassage(Integer yhid,String gsdm,String openid,String template_id,String url,Map<Object, Object> data1){
		HttpClient client = new DefaultHttpClient();
		String token = getAccessToken();
		HttpPost httpPost = new HttpPost(SEND_URL +token);
		ObjectMapper mapper = new ObjectMapper();
		Map<Object, Object> data = new HashMap<>();
		data.put("touser", openid);
		data.put("template_id", template_id);
		data.put("url", url);
		data.put("data", data1);
		Wxfs wx = new Wxfs();
		Map map = new HashMap<>();
		String msg = "";
		try {
			String json = mapper.writeValueAsString(data);
			httpPost.setEntity(new StringEntity(json, "UTF-8"));
			HttpResponse response = client.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String responseContent = EntityUtils.toString(entity, "UTF-8");
			ObjectMapper jsonparer = new ObjectMapper();
			map = jsonparer.readValue(responseContent, Map.class);
			wx.setReturnmsg(String.valueOf(map.get("errmsg")));
			if (map.get("errmsg").equals("ok")) {
				msg = "成功";
			} else {
				msg = "失败";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "失败";
		}
		wx.setDjh(yhid);
		wx.setGsdm(gsdm);
		wx.setIssuccess(msg);
		wx.setOpenid(openid);
		wx.setLrsj(new Date());
		wxfsService.save(wx);
	}

}
