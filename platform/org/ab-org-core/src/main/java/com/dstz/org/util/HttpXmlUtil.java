package com.dstz.org.util;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 接口工具类
 */
public class HttpXmlUtil {

	protected static transient final Log logger = LogFactory.getLog(HttpXmlUtil.class);

	protected static String  time = null;
	
	/**
	 * 执行一个HTTP GET请求，返回请求响应的HTML
	 * @param url 请求的URL地址

	 * @return 返回请求响应的HTML
	 */
	public static String doGet(String url) throws Exception {
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		// 创建GET方法的实例
		GetMethod getMethod = new GetMethod(url);
		// 使用系统提供的默认的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		try {
			// 执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + getMethod.getStatusLine());
			}
			// 读取内容
			byte[] responseBody = getMethod.getResponseBody();
			// 处理内容
			System.out.println(new String(responseBody,"GBK"));
			return new String(responseBody,"GBK");
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			// 发生网络异常
			e.printStackTrace();
		} finally {
			// 释放连接
			getMethod.releaseConnection();
		}
		return null;
	}

    public static String doGetNew(String url) throws Exception {
        // 构造HttpClient的实例
        HttpClient httpClient = new HttpClient();
        // 创建GET方法的实例
        GetMethod getMethod = new GetMethod(url);
        // 使用系统提供的默认的恢复策略
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
        try {
            // 执行getMethod
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + getMethod.getStatusLine());
            }
            // 读取内容
            byte[] responseBody = getMethod.getResponseBody();
            // 处理内容
            System.out.println(new String(responseBody,"utf-8"));
            return new String(responseBody,"utf-8");
        } catch (HttpException e) {
            // 发生致命的异常，可能是协议不对或者返回的内容有问题
            System.out.println("Please check your provided http address!");
            e.printStackTrace();
        } catch (IOException e) {
            // 发生网络异常
            e.printStackTrace();
        } finally {
            // 释放连接
            getMethod.releaseConnection();
        }
        return null;
    }
	
	/**
	 * 执行一个HTTP POST请求，返回请求响应的HTML
	 * 
	 * @param url
	 *            请求的URL地址
	 * @param params
	 *            请求的查询参数,可以为null
	 * @return 返回请求响应的HTML
	 */
	public static String doPost(String url, Map<String, String> params) {
		String response = null;
		HttpClient client = HttpClientFactory.getHttpClient();
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		// 设置Post数据
		if (params!=null && !params.isEmpty()) {
			int i = 0;
			NameValuePair[] data = new NameValuePair[params.size()];
			for (Entry<String, String> entry : params.entrySet()) {
				data[i] = new NameValuePair(entry.getKey(), entry.getValue());
				i++;
			}
			postMethod.setRequestBody(data);
		}
		try {
			client.executeMethod(postMethod);
			if (postMethod.getStatusCode() == HttpStatus.SC_OK) {
				response = postMethod.getResponseBodyAsString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}
		return response;
	}
	
	
	public static String _doPost(String url, String params) throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		post.setHeader("Accept", "application/json");
		post.setHeader("Content-Type", "application/json");
		if(!"".equals(params) && params!=null){
			post.setEntity(new StringEntity(params, "UTF-8"));
		}

		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String response = httpClient.execute(post, responseHandler);
		return new String(response.getBytes(),"UTF-8");
	}

	public static String OdoPost(String url, String params) throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		// post.setHeader("Accept", "application/json");
		post.setHeader("Content-Type", "application/json");
		if(!"".equals(params) && params!=null){
			post.setEntity(new StringEntity(params, "UTF-8"));
		}

		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String response = httpClient.execute(post, responseHandler);
		return new String(response.getBytes(),"UTF-8");
	}

	/**
	 * 发送string字符串到指定的URL上，并返回响应信息；
	 * @throws IOException 
	 * @throws ClientProtocolException
	 */
	public static String post(String url, String paramString,String authorization) throws ClientProtocolException, IOException {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		post.setHeader("Accept", "application/json");
		post.setHeader("Content-Type", "application/json");
		if(!"".equals(authorization) && authorization!=null){
			post.setHeader("Authorization", authorization);
		}
		post.setEntity(new StringEntity(paramString, "UTF-8"));

		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String response = httpClient.execute(post, responseHandler);
		return new String(response.getBytes(),"UTF-8");
	}
	
	
	// MD5加密函数
		public static String MD5Encode(String sourceString) {
			String resultString = null;
			try {
				resultString = new String(sourceString);
				MessageDigest md = MessageDigest.getInstance("MD5");
				resultString = byte2hexString(md.digest(resultString.getBytes()));
			} catch (Exception ex) {
			}
			return resultString;
		}

		public static final String byte2hexString(byte[] bytes) {
			StringBuffer bf = new StringBuffer(bytes.length * 2);
			for (int i = 0; i < bytes.length; i++) {
				if ((bytes[i] & 0xff) < 0x10) {
					bf.append("0");
				}
				bf.append(Long.toString(bytes[i] & 0xff, 16));
			}
			return bf.toString();
		}
		
		public static void main(String[] args) {
			 long _t = System.currentTimeMillis();//时间戳
			  //String sign = EncryptUtil.Md5("8"+"3"+guideIdStr+_t+"zbom20180922!@#$");
			  Map params = new HashMap();
			  String url = "http://180.97.81.219:7004/manage";
			  params.put("messagename","222");
			  params.put("messagecontent", "333");
			  params.put("appid", "8");
			  params.put("messagetype", "1");
			  params.put("messageurl","www.baidu.com");
			  params.put("messagesee", "3");
			  params.put("messageuser", "2231");
			  params.put("timestamp", String.valueOf(_t));
			  
			  HttpXmlUtil.doPost(url, params);

		}
}
