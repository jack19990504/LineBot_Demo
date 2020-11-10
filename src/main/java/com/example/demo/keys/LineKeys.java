package com.example.demo.keys;

import java.util.HashMap;
import java.util.Map;

public interface LineKeys {

	// message api
  
	String accessToken = "vENCdgmXBbFN845x8zbYCGtQTkXmbIP3VzUMaiMMSVBvFAyUv+1F/RjO2Q4izvxa9Oa+8DfXAaltLgGQfGBDY6qTHTRNtTMoMxZ4P/Hi+NihfL87UClI+N6lqa4nDJUsvuNuWvrbkTNGjv5WV5MvggdB04t89/1O/w1cDnyilFU=";
	String URL_REPLY = "https://api.line.me/v2/bot/message/reply";
	String URL_PUSH = "https://api.line.me/v2/bot/message/push";


	Map<String, String> replyFailedHashMap = new HashMap<>();
	Map<String, String> pushFailedHashMap = new HashMap<>();

	// line login
	String grant_type = "authorization_code";
	String redirect_uri = "your redirect uri";
	String client_id = "your client id";
	String client_secret = "your client secret";
	String URL_TOKEN = "https://api.line.me/oauth2/v2.1/token";

}
