package com.dstz.org.util;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

public class HttpClientFactory {
	 /**
     * http链接管理器
     */
    private static final  HttpConnectionManager httpConnectionManager;
    public static HttpClient getHttpClient(){
       return  new HttpClient(httpConnectionManager);
    }
    static{
        httpConnectionManager = new MultiThreadedHttpConnectionManager();
        HttpConnectionManagerParams params = httpConnectionManager.getParams();
        params.setConnectionTimeout(60*1000);
        params.setSoTimeout(60*1000);
        params.setDefaultMaxConnectionsPerHost(100);//very important!!
        params.setMaxTotalConnections(500);//very important!!
    }
}
