package com.example.demo.keys;

import java.util.HashMap;
import java.util.Map;

public interface LineKeys {

	// message api
	String accessToken = "your accessToken";
	String retryKey = "94d662a0-88e3-4cb6-9772-def44dd611f0";

	Map<String, String> replyFailedHashMap = new HashMap<String, String>();
	Map<String, String> pushFailedHashMap = new HashMap<String, String>();

	// line login
	String grant_type = "authorization_code";
	String redirect_uri = "your redirect uri";
	String client_id = "your client id";
	String client_secret = "your client secret";
}
