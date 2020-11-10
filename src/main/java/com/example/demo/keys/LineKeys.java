package com.example.demo.keys;

import java.util.HashMap;
import java.util.Map;

public interface LineKeys {

	// message api
  
	String accessToken = "Fete4NBk12TWruvw3FYEqckZnSXZQJQgA3MbobnTeLWXqKhHdA+wfgvxehVaFHGv9Oa+8DfXAaltLgGQfGBDY6qTHTRNtTMoMxZ4P/Hi+Ng62sWiPVmWNmevv8DRAWiN0oSgukSGW9ojHCQM2/n20QdB04t89/1O/w1cDnyilFU=";
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
