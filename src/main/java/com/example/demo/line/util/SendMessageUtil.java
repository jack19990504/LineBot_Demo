package com.example.demo.line.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.demo.keys.LineKeys;

@Component
public class SendMessageUtil implements LineKeys {

	private static final Logger LOG = LoggerFactory.getLogger(SendMessageUtil.class);
	// show spring init components and other tags at starting server
	{
		LOG.info("init :\t" + this.getClass().getSimpleName());
	}

	public boolean sendReplyMessage(String message) {
		Integer respCode = 0;
		try {
			System.out.println("data : " + message);
			String uuid = UUID.randomUUID().toString();
			// 回傳的json格式訊息
			URL myurl = new URL("https://api.line.me/v2/bot/message/reply"); // 回傳的網址
			HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection(); // 使用HttpsURLConnection建立https連線
			con.setRequestMethod("POST");// 設定post方法
			con.setRequestProperty("Content-Type", "application/json; charset=utf-8"); // 設定Content-Type為json
			con.setRequestProperty("Authorization", "Bearer " + accessToken); // 設定Authorization
			con.setRequestProperty("X-Line-Retry-Key", uuid);
			con.setDoOutput(true);
			con.setDoInput(true);
			DataOutputStream output = new DataOutputStream(con.getOutputStream()); // 開啟HttpsURLConnection的連線
			output.write(message.getBytes(Charset.forName("utf-8"))); // 回傳訊息編碼為utf-8
			output.flush();
			output.close();
			respCode = con.getResponseCode();
			System.out.println("Resp Code:" + con.getResponseCode() + "; Resp Message:" + con.getResponseMessage()); // 顯示回傳的結果，若code為200代表回傳成功

			if (respCode == 200) {
				System.out.println(getReturn(con));
			}
			else {
				System.out.println("error accurs");
				replyFailedHashMap.put(uuid, message);
			}
		} catch (MalformedURLException e) {
			System.out.println("1Message: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Message: " + e.getMessage());
			e.printStackTrace();
		}

		return respCode == 200 ? true : false;
	}

	public boolean sendReplyMessageAgain(String message,String uuid) {
		Integer respCode = 0;
		try {
			System.out.println("data : " + message);
			// 回傳的json格式訊息
			URL myurl = new URL("https://api.line.me/v2/bot/message/reply"); // 回傳的網址
			HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection(); // 使用HttpsURLConnection建立https連線
			con.setRequestMethod("POST");// 設定post方法
			con.setRequestProperty("Content-Type", "application/json; charset=utf-8"); // 設定Content-Type為json
			con.setRequestProperty("Authorization", "Bearer " + accessToken); // 設定Authorization
			con.setRequestProperty("X-Line-Retry-Key", uuid);
			con.setDoOutput(true);
			con.setDoInput(true);
			DataOutputStream output = new DataOutputStream(con.getOutputStream()); // 開啟HttpsURLConnection的連線
			output.write(message.getBytes(Charset.forName("utf-8"))); // 回傳訊息編碼為utf-8
			output.flush();
			output.close();
			respCode = con.getResponseCode();
			System.out.println("Resp Code:" + con.getResponseCode() + "; Resp Message:" + con.getResponseMessage()); // 顯示回傳的結果，若code為200代表回傳成功

			if (respCode == 200) {
				System.out.println(getReturn(con));
			}
			else if (respCode == 409) {
				System.out.println("this message has been sent");
			}
		} catch (MalformedURLException e) {
			System.out.println("1Message: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Message: " + e.getMessage());
			e.printStackTrace();
		}

		return respCode == 200 ? true : false;
	}

	public boolean sendPostMessage(String message) {
		Integer respCode = 0;
		try {
			System.out.println(message);
			String uuid = UUID.randomUUID().toString();
			// 回傳的json格式訊息
			URL myurl = new URL("https://api.line.me/v2/bot/message/push"); // 回傳的網址
			HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection(); // 使用HttpsURLConnection建立https連線
			con.setRequestMethod("POST");// 設定post方法
			con.setRequestProperty("Content-Type", "application/json; charset=utf-8"); // 設定Content-Type為json
			con.setRequestProperty("Authorization", "Bearer " + accessToken); // 設定Authorization
			con.setRequestProperty("X-Line-Retry-Key", uuid);
			con.setDoOutput(true);
			con.setDoInput(true);
			DataOutputStream output = new DataOutputStream(con.getOutputStream()); // 開啟HttpsURLConnection的連線
			output.write(message.getBytes(Charset.forName("utf-8"))); // 回傳訊息編碼為utf-8
			output.close();
			respCode = con.getResponseCode();
			System.out.println("Resp Code:" + con.getResponseCode() + "; Resp Message:" + con.getResponseMessage()); // 顯示回傳的結果，若code為200代表回傳成功
			if(respCode != 200)
			{
				System.out.println("error accurs");
				pushFailedHashMap.put(uuid, message);
			}
			else
			{
				System.out.println(getReturn(con));
			}
		} catch (MalformedURLException e) {
			System.out.println("1Message: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Message: " + e.getMessage());
			e.printStackTrace();
		}
		return respCode == 200 ? true : false;
	}
	public boolean sendPostMessageAgain(String message,String uuid) {
		Integer respCode = 0;
		try {
			System.out.println(message);
			// 回傳的json格式訊息
			URL myurl = new URL("https://api.line.me/v2/bot/message/push"); // 回傳的網址
			HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection(); // 使用HttpsURLConnection建立https連線
			con.setRequestMethod("POST");// 設定post方法
			con.setRequestProperty("Content-Type", "application/json; charset=utf-8"); // 設定Content-Type為json
			con.setRequestProperty("Authorization", "Bearer " + accessToken); // 設定Authorization
			con.setRequestProperty("X-Line-Retry-Key", uuid);
			con.setDoOutput(true);
			con.setDoInput(true);
			DataOutputStream output = new DataOutputStream(con.getOutputStream()); // 開啟HttpsURLConnection的連線
			output.write(message.getBytes(Charset.forName("utf-8"))); // 回傳訊息編碼為utf-8
			output.close();
			respCode = con.getResponseCode();
			System.out.println("Resp Code:" + con.getResponseCode() + "; Resp Message:" + con.getResponseMessage()); // 顯示回傳的結果，若code為200代表回傳成功
		
			if (respCode == 200) {
				System.out.println(getReturn(con));
			}
			else if (respCode == 409) {
				System.out.println("this message has been sent");
			}
		} catch (MalformedURLException e) {
			System.out.println("1Message: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Message: " + e.getMessage());
			e.printStackTrace();
		}
		return respCode == 200 ? true : false;
	}
	public static String getReturn(HttpsURLConnection connection) throws IOException {
		StringBuilder buffer = new StringBuilder();
		// 將返回的輸入流轉換成字符串
		try (InputStream inputStream = connection.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			String result = buffer.toString();
			return result;
		}
	}

	
}
