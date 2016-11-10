package com.example.mrj.omnify.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;

public class jsonThreadPostMethod extends Thread {

	JSONObject sendObject, receiveObject;
	public String URL;

	private static DefaultHttpClient httpclient;
	private HttpPost httpost;
	private HttpResponse httpResponse;
	StringEntity se;
	int responceCode;
	Handler localHandler;

	public jsonThreadPostMethod(String Url, JSONObject jObj, Handler xHandler,
			int resp) {
		URL = Url;
		sendObject = jObj;
		localHandler = xHandler;
		responceCode = resp;
		setPriority(Thread.MAX_PRIORITY);
	}

	@Override
	public void run() {

		System.out.println("before sending " + sendObject.toString());

		httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(
				CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		receiveObject = new JSONObject();
		httpost = new HttpPost(URL);

		if (sendObject != null)
			try {
				
				try {
					if(sendObject.getString("event").equals("follow"))
					{
						String str = "event=follow";
						httpost.setEntity(new StringEntity(str,"UTF-8"));
					}
					else
					{
						httpost.setEntity(new StringEntity(sendObject.toString(),
								"UTF-8"));
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		try {
			httpResponse = httpclient.execute(httpost);
			receiveObject = parseResponse(httpResponse.getEntity().getContent());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	private JSONObject parseResponse(InputStream stream) {

		JSONObject responseObj = new JSONObject();

		BufferedReader reader = new BufferedReader(
				new InputStreamReader(stream));
		String line;
		StringBuilder builder = new StringBuilder();

		try {
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			reader.close();
			String strResp = builder.toString();
			System.out.println("We have Received " + strResp);

			responseObj = new JSONObject(strResp);
			stream.close();

			Message msg = new Message();
			msg.obj = responseObj;
			msg.what = responceCode;
			localHandler.sendMessage(msg);
			return responseObj;

		} catch (Exception e) {
			try {
				responseObj.put("error", e.getMessage());
			} catch (Exception ex) {
			}
		}
		return responseObj;
	}

}
