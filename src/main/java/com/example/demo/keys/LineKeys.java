package com.example.demo.keys;

import java.util.HashMap;
import java.util.Map;

public interface LineKeys {

	// message api
  
	String accessToken = "your accessToken";
	String URL_REPLY = "https://api.line.me/v2/bot/message/reply";
	String URL_PUSH = "https://api.line.me/v2/bot/message/push";


	Map<String, String> replyFailedHashMap = new HashMap<String, String>();
	Map<String, String> pushFailedHashMap = new HashMap<String, String>();

	// line login
	String grant_type = "authorization_code";
	String redirect_uri = "your redirect uri";
	String client_id = "your client id";
	String client_secret = "your client secret";
	String URL_TOKEN = "https://api.line.me/oauth2/v2.1/token";

}
