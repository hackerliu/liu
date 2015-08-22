package com.xmpp.Chat.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by think on 2015/8/22.
 */
public class BaseHttpClient {
    public static String post(String url, Map<String, String> params) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String body = null;

        try {
            HttpPost post = postForm(url, params);
            HttpConnectionParams.setConnectionTimeout(post.getParams(), 10000);
            //HttpConnectionParams.setSoTimeout(post.getParams(), Configuration.READ_TIME_OUT);
            body = invoke(httpClient, post);
            httpClient.getConnectionManager().shutdown();
        } catch (Exception e) {
            httpClient.getConnectionManager().shutdown();
            e.printStackTrace();
            return "error";
        }


        return body;
    }

    public static String post(String url, JSONObject params) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String body = null;

        try {
            //logger.info("create http post : {}", url);
            HttpPost post = new HttpPost(url);
            StringEntity s = new StringEntity(params.toString(), "UTF-8");
            s.setContentType("application/json");
            post.setEntity(s);
            HttpConnectionParams.setConnectionTimeout(post.getParams(), 10000);
            //HttpConnectionParams.setSoTimeout(post.getParams(), Configuration.READ_TIME_OUT);
            // body=httpClient.execute(post,makeResponseHandler("utf-8"));
            body = invoke(httpClient, post);
            body = new String(body.getBytes("ISO-8859-1"), "utf8");
            httpClient.getConnectionManager().shutdown();
        } catch (Exception e) {
            httpClient.getConnectionManager().shutdown();
            e.printStackTrace();
            return "error";
        }
        return body;
    }

    private static String invoke(DefaultHttpClient httpclient,
                                 HttpUriRequest httpost) {

        HttpResponse response = sendRequest(httpclient, httpost);
        String body = paseResponse(response);

        return body;
    }

    private static String paseResponse(HttpResponse response) {
        HttpEntity entity = response.getEntity();

        //logger.debug("response status: " + response.getStatusLine());
        //logger.debug(EntityUtils.getContentCharSet(entity));

        String body = null;
        try {
            body = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return body;
    }

    private static HttpResponse sendRequest(DefaultHttpClient httpClient,
                                            HttpUriRequest httpost) {
        HttpResponse response = null;

        try {

            response = httpClient.execute(httpost);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private static HttpPost postForm(String url, Map<String, String> params) {

        HttpPost httPost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            nvps.add(new BasicNameValuePair(key, params.get(key)));
        }

        try {
            httPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return httPost;
    }
}
