package com.example.demo.line.util;

import com.example.demo.keys.LineKeys;
import com.example.demo.line.util.entity.HttpClientUtil;
import com.example.demo.line.util.entity.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Component
public class SendMessageUtil implements LineKeys {

	private static final Logger LOG = LoggerFactory.getLogger(SendMessageUtil.class);
	// show spring init components and other tags at starting server
	{
		LOG.info("init :\t" + this.getClass().getSimpleName());
	}

	private final UUIDUtil uuidUtil;
	private final HttpClientUtil httpClientUtil;

	@Autowired
	public SendMessageUtil(UUIDUtil uuidUtil,HttpClientUtil httpClientUtil){
		this.uuidUtil = uuidUtil;
		this.httpClientUtil = httpClientUtil;
	}

	public boolean sendReplyMessage(String uuid,String message) {
		int respCode = 0;
		try {
			System.out.println("data : " + message);
			// 回傳的json格式訊息
			URL myurl = new URL(URL_REPLY); // 回傳的網址
			HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection(); // 使用HttpsURLConnection建立https連線
			con.setRequestMethod("POST");// 設定post方法
			con.setRequestProperty("Content-Type", "application/json; charset=utf-8"); // 設定Content-Type為json
			con.setRequestProperty("Authorization", "Bearer " + accessToken); // 設定Authorization
			con.setRequestProperty("X-Line-Retry-Key", uuid);
			con.setDoOutput(true);
			con.setDoInput(true);
			DataOutputStream output = new DataOutputStream(con.getOutputStream()); // 開啟HttpsURLConnection的連線
			output.write(message.getBytes(StandardCharsets.UTF_8)); // 回傳訊息編碼為utf-8
			output.flush();
			output.close();
			respCode = con.getResponseCode();
			System.out.println("Resp Code:" + con.getResponseCode() + "; Resp Message:" + con.getResponseMessage()); // 顯示回傳的結果，若code為200代表回傳成功

			if (respCode == 200) {
				System.out.println(getReturn(con));
			} else if (respCode == 409) {
				System.out.println("this message has been sent");
			}
		} catch (MalformedURLException e) {
			System.out.println("1Message: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Message: " + e.getMessage());
			e.printStackTrace();
		}

		return respCode == 200;
	}



	public boolean sendPostMessage(String uuid,String message) {
		int respCode = 0;
		try {
			System.out.println(message);
			// 回傳的json格式訊息
			URL myurl = new URL(URL_PUSH); // 回傳的網址
			HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection(); // 使用HttpsURLConnection建立https連線
			con.setRequestMethod("POST");// 設定post方法
			con.setRequestProperty("Content-Type", "application/json; charset=utf-8"); // 設定Content-Type為json
			con.setRequestProperty("Authorization", "Bearer " + accessToken); // 設定Authorization
			con.setRequestProperty("X-Line-Retry-Key", uuid);
			con.setDoOutput(true);
			con.setDoInput(true);
			DataOutputStream output = new DataOutputStream(con.getOutputStream()); // 開啟HttpsURLConnection的連線
			output.write(message.getBytes(StandardCharsets.UTF_8)); // 回傳訊息編碼為utf-8
			output.close();
			respCode = con.getResponseCode();
			System.out.println("Resp Code:" + con.getResponseCode() + "; Resp Message:" + con.getResponseMessage()); // 顯示回傳的結果，若code為200代表回傳成功

			if (respCode == 200) {
				System.out.println(getReturn(con));
			} else if (respCode == 409) {
				System.out.println("this message has been sent");
			}
		} catch (MalformedURLException e) {
			System.out.println("1Message: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Message: " + e.getMessage());
			e.printStackTrace();
		}
		return respCode == 200;
	}

	public static String getReturn(HttpsURLConnection connection) throws IOException {
		StringBuilder buffer = new StringBuilder();
		// 將返回的輸入流轉換成字符串
		try (InputStream inputStream = connection.getInputStream();
			 InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
			 BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			String str;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			return buffer.toString();
		}
	}

	public HttpResponse sendReply(String uuid,String message) {

		HttpPost post = httpClientUtil.setMessage(URL_REPLY,accessToken,message,uuid);

		HttpResponse httpResponse;

		httpResponse = httpClientUtil.doRequest(post);

		return httpResponse;
	}

	public HttpResponse sendPost(String uuid,String message) {

		HttpPost post = httpClientUtil.setMessage(URL_REPLY,accessToken,message,uuid);

		HttpResponse httpResponse;

		httpResponse = httpClientUtil.doRequest(post);

		return httpResponse;
	}
}
