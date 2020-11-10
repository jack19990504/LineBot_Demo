package com.example.demo.keys;

import java.util.HashMap;
import java.util.Map;

public interface LineKeys {

	// message api
	String accessToken = "Fete4NBk12TWruvw3FYEqckZnSXZQJQgA3MbobnTeLWXqKhHdA+wfgvxehVaFHGv9Oa+8DfXAaltLgGQfGBDY6qTHTRNtTMoMxZ4P/Hi+Ng62sWiPVmWNmevv8DRAWiN0oSgukSGW9ojHCQM2/n20QdB04t89/1O/w1cDnyilFU=";
	String retryKey = "94d662a0-88e3-4cb6-9772-def44dd611f0";

	Map<String, String> replyFailedHashMap = new HashMap<String, String>();
	Map<String, String> pushFailedHashMap = new HashMap<String, String>();

	// line login
	String grant_type = "authorization_code";
	String redirect_uri = "your redirect uri";
	String client_id = "your client id";
	String client_secret = "your client secret";
	
	// default char set
	String charSet = "UTF-8";
	
	// post url
	String postUrl = "https://api.line.me/v2/bot/message/push";
	
	// reply url
	String replyUrl = "https://api.line.me/v2/bot/message/reply";
	
}
