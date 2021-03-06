package com.yu.cvs.network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

public class HttpConnection {
	
	public static final int SUCCESS            = 200;
	public static final int IO_ERROR           = 9001;
	public static final int SAVE_SESSION       = 9004;

	private URL _url;

	public static HttpConnection CreateHttpConnection() {
		return new HttpConnection();
	}

	private HttpConnection() {

	}

	public HttpResponse sendRequestInPost(String command_url, String req, String session, Handler handler) {
		
		if(handler != null){
			Message startMsg = handler.obtainMessage(HttpRequest.BEGIN_TRANSMISSION);
			handler.sendMessage(startMsg);
		}

		HttpResponse res = new HttpResponse();
		HttpURLConnection urlConnection = null;
		try {

			byte[] reqArray = req.getBytes();

			_url = new URL(command_url);
			urlConnection = (HttpURLConnection) _url.openConnection();
			urlConnection.setConnectTimeout(10000); // ���ӳ�ʱ 10 ��
			urlConnection.setReadTimeout(10000); // ��ȡ��ʱ 10 ��
			urlConnection.setDoOutput(true);
			if(!TextUtils.isEmpty(session)){
				urlConnection.setRequestProperty("Cookie", session);
			}
			urlConnection.setFixedLengthStreamingMode(reqArray.length);

			OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
			out.write(reqArray);
			out.flush();

			reqArray = null;

			res.responseCode = urlConnection.getResponseCode();
			if (res.responseCode == SUCCESS) {
				byte[] buffer = new byte[1024];
				ByteArrayOutputStream baos = new ByteArrayOutputStream(4 * 1024);
				InputStream in = new BufferedInputStream(urlConnection.getInputStream());
				int read = 0;
				while ((read = in.read(buffer)) != -1) {
					baos.write(buffer, 0, read);
				}
				res.content = new String(baos.toByteArray());
				
				String s = urlConnection.getHeaderField("Set-Cookie");
				
				if(handler != null){
					Message msg = handler.obtainMessage(SAVE_SESSION, s);
					handler.sendMessage(msg);
				}
				
			} else {
				res.content = "NetWork ERROR";
			}

		} catch (MalformedURLException e) {
			res.responseCode = IO_ERROR;
			res.content = "Wrong Url";
		} catch (SocketTimeoutException e) {
			res.responseCode = IO_ERROR;
			res.content = "Time out";
		} catch (IOException e) {
			res.responseCode = IO_ERROR;
			res.content = "Network disconnected";
		} finally {
			
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
			
			if(handler != null){
				Message msg = handler.obtainMessage(res.responseCode, res.content);
				handler.sendMessage(msg);
			}
		}
		
		return res;
	}

	public HttpResponse sendRequestInGet(String command_url, HashMap<String, String> req) {

		StringBuilder builder = new StringBuilder();
		builder.append(command_url);

		for (String key : req.keySet()) {
			String value = req.get(key);
			builder.append(String.format("&%s=%s", key, value));
		}

		HttpResponse res = new HttpResponse();
		HttpURLConnection urlConnection = null;
		try {

			_url = new URL(builder.toString());
			urlConnection = (HttpURLConnection) _url.openConnection();
			urlConnection.setConnectTimeout(10000); // ���ӳ�ʱ 10 ��
			urlConnection.setReadTimeout(10000); // ��ȡ��ʱ 10 ��
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("GET");

			res.responseCode = urlConnection.getResponseCode();
			if (res.responseCode == 200) {
				byte[] buffer = new byte[1024];
				ByteArrayOutputStream baos = new ByteArrayOutputStream(4 * 1024);
				InputStream in = new BufferedInputStream(urlConnection.getInputStream());
				int read = 0;
				while ((read = in.read(buffer)) != -1) {
					baos.write(buffer, 0, read);
				}
				res.content = new String(baos.toByteArray());
			} else {
				res.content = "NetWork ERROR";
			}

			System.out.println(String.format("Get Command URL: %s", _url));
			System.out.println(String.format("Http response code: %d ;\nHttp response content: %s", res.responseCode, res.content));

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			res.responseCode = 9001;
			res.content = "Wrong Url";
		} catch (SocketTimeoutException e) {
			// TODO Auto-generated catch block
			res.responseCode = 9002;
			res.content = "Time out";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			res.responseCode = 9003;
			res.content = "Network disconnected";
		} finally {
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
		}

		return res;

	}
}
