package com.rjxx.taxeasy.bizcomm.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.params.CoreConnectionPNames;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The rabbitMQ management class
 */
@Service
public class ServerSend {
    @Autowired
    private DataOperate dataOperate;
	/**
	 * 发送到税控服务器
	 *
	 * @param sendMes
	 * @param key
	 * @param xfsh
	 * @param jylsh
	 * @throws Exception
	 */
	public Map send(String sendMes, String url, String key, String xfsh, String jylsh) throws Exception {
		SingleClientConnManager sccm = new SingleClientConnManager();
		HttpClient httpclient = new DefaultHttpClient(sccm);
		// HttpGet httpget = new HttpGet(urisToGet[i]);
		// HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);
		StringBuffer buffer = new StringBuffer();
		StringEntity myEntity = new StringEntity(sendMes, "GBK");
		httppost.addHeader("Content-Type", "text/xml");
		httppost.setEntity(myEntity);
		HttpResponse response;
		String strMessage = "";
		BufferedReader reader = null;
		Map resultMap = null;
		try {
			
			httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,1000*60);//连接超时时间
			httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,1000*60);//数据传输时间
			response = httpclient.execute(httppost);
			HttpEntity resEntity = response.getEntity();
			// reader=new BufferedReader(new
			// InputStreamReader(inputStream,"gbk"));
			reader = new BufferedReader(new InputStreamReader(resEntity.getContent(), "gbk"));
			while ((strMessage = reader.readLine()) != null) {
				buffer.append(strMessage);
			}
			System.out.println("接收返回值:" + buffer.toString());
			resultMap = handerReturnMes(buffer.toString(),key);
			if (null != resultMap && !resultMap.isEmpty()) {

				int pos = key.indexOf("$");
				if (pos != -1) {
					key = key.substring(0, pos);
				}

				if (resultMap.get("returncode").equals("0000")) {
					dataOperate.saveLog(Integer.valueOf(key), "91", "1", "Send:send",
							"(服务端)发送服务器成功" + resultMap.get("returnmsg").toString(), 2, xfsh, jylsh);
				} else {
					dataOperate.saveLog(Integer.valueOf(key), "92", "1", "Send:send",
							"(服务端)发送服务器失败" + resultMap.get("returnmsg").toString(), 2, xfsh, jylsh);
				}
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			int pos = key.indexOf("$");
			if (pos != -1) {
				key = key.substring(0, pos);
			}
			dataOperate.saveLog(Integer.valueOf(key), "92", "1", "Send:send", "(服务端)发送服务器异常" + e.getMessage(), 2, xfsh,
					jylsh);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			int pos = key.indexOf("$");
			if (pos != -1) {
				key = key.substring(0, pos);
			}
			dataOperate.saveLog(Integer.valueOf(key), "92", "1", "Send:send", "(服务端)发送服务器异常" + e.getMessage(), 2, xfsh,
					jylsh);
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return resultMap;
	}

	/**
	 * 接收返回报文并做后续处理
	 *
	 * @param returnMes
	 * 
	 * @throws Exception
	 */
	public Map handerReturnMes(String returnMes,String key) throws Exception {

		// 将放回报文字符串转成Document对象
		Document document = DocumentHelper.parseText(returnMes);
		Element root = document.getRootElement();
		List<Element> childElements = root.elements();
		// List resultList = new ArrayList();
		Map resultMap = new HashMap();

		for (Element child : childElements) {
			// 未知属性名情况下
			/*
			 * List<Attribute> attributeList = child.attributes(); for
			 * (Attribute attr : attributeList) {
			 * System.out.println(attr.getName() + ": " + attr.getValue()); }
			 */

			// 已知属性名情况下
			// System.out.println("id: " + child.attributeValue("id"));

			// 未知子元素名情况下
			/*
			 * List<Element> elementList = child.elements(); for (Element ele :
			 * elementList) { System.out.println(ele.getName() + ": " +
			 * ele.getText()); } System.out.println();
			 */

			// 已知子元素名的情况下
			// System.out.println("RETURNCODE" +
			// child.elementText("RETURNCODE"));
			// System.out.println("RETURNMSG" + child.elementText("RETURNMSG"));
			/// 这行是为了格式化美观而存在
			// System.out.println();
			int pos = key.indexOf("$");
			if (pos != -1) {
				key = key.substring(pos+1);
				System.out.println("传入开票流水号:" + key);
			}
			if (child.elementText("RETURNCODE").equals("0000")) {
				resultMap.put("FP_DM", child.elementText("FP_DM"));// 返回结果，发票代码
				resultMap.put("FP_HM", child.elementText("FP_HM"));// 发票号码
				resultMap.put("FP_MW", child.element("FP_MW").getText());// 发票密文
				resultMap.put("JYM", child.element("JYM").getText());// 校验码
				resultMap.put("EWM", child.element("EWM").getText());// 二维码
				resultMap.put("JQBH", child.element("JQBH").getText());// 机器编号
				resultMap.put("KPRQ", child.element("KPRQ").getText());
				resultMap.put("returncode", child.elementText("RETURNCODE"));
				resultMap.put("returnmsg", child.elementText("RETURNMSG"));
				resultMap.put("KPLSH", key);
			}else{
				resultMap.put("returncode", child.elementText("RETURNCODE"));
				resultMap.put("returnmsg", child.elementText("RETURNMSG"));
				resultMap.put("KPLSH", key);
			}
			// resultList.add(child.elementText("RETURNCODE"));
			// resultList.add(child.elementText("RETURNMSG"));

		}
		return resultMap;
	}

	public static void main(String[] args) throws Exception {
		String s = "<?xml version=\"1.0\" encoding=\"gbk\"?>"
				+ "<business id=\"FPKJ\" comment=\"发票开具\"><RESPONSE_COMMON_FPKJ class=\"RESPONSE_COMMON_FPKJ\"><FPQQLSH>JY20151216180448564</FPQQLSH>"
				+ "<FP_DM>131001570055</FP_DM><FP_HM>09100038</FP_HM><KPRQ>20151217092121</KPRQ><JQBH>499000103734</JQBH>"
				+ "<FP_MW>03*5*6*-48*02+&lt;87443784*-04/33588*0&gt;-1+097+/7&lt;774*1&gt;6/&lt;71--&lt;4832967&gt;3446+-8+743-2597610&lt;59&gt;0/+019*63194+9&gt;51*+/8</FP_MW><JYM>00105086858870292222</JYM><EWM></EWM><BZ>机器编号:499000103734"
				+ "测试购方备注</BZ><RETURNCODE>0000</RETURNCODE><RETURNMSG>成功</RETURNMSG></RESPONSE_COMMON_FPKJ></business>";
		// send(s, 151);
	}
}