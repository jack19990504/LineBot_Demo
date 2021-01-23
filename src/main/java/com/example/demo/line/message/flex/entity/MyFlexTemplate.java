package com.example.demo.line.message.flex.entity;

public class MyFlexTemplate {
	
	private String flexMessageTemplate =  	
		      "{" + 	
		      "\"type\": \"flex\"," + 	
		      "\"altText\": \"This is a Flex Message\"," + 	
		      "\"contents\":"+	
		      "{" + 	
		      "\"type\": \"bubble\"," + 	
		      "\"hero\": {" + 	
		      "\"type\": \"image\"," + 	
		      "\"url\": \"replaceLogoUrl\"," + 	
		      "\"size\": \"full\"," + 	
		      "\"aspectRatio\": \"20:13\"," + 	
		      "\"aspectMode\": \"cover\"," + 	
		      "\"action\": {" + 	
		      "\"type\": \"uri\"," + 	
		      "\"uri\": \"replaceRedirectUrl\"" + 	
		      "}" + 	
		      "}," + 	
		      "\"body\": {" + 	
		      "\"type\": \"box\"," + 	
		      "\"layout\": \"vertical\"," + 	
		      "\"spacing\": \"md\"," +  	
		      "\"contents\": [" + 	
		      "{" + 	
		      "\"type\": \"text\"," + 	
		      "\"text\": \"replaceTitle\"," + 	
		      "\"wrap\": true," + 	
		      "\"weight\": \"bold\"," + 	
		      "\"gravity\": \"center\"," + 	
		      "\"size\": \"xl\"" + 	
		      "}," + 	
		      "{" + 	
		      "\"type\": \"box\"," + 	
		      "\"layout\": \"vertical\"," + 	
		      "\"margin\": \"lg\"," + 	
		      "\"spacing\": \"sm\"," + 	
		      "\"contents\": [" + 	
		      "{" + 	
		      "\"type\": \"box\"," + 	
		      "\"layout\": \"baseline\"," + 	
		      "\"spacing\": \"sm\"," + 	
		      "\"contents\": [" + 	
		      "{" + 	
		      "\"type\": \"text\"," + 	
		      "\"text\": \"時間\"," + 	
		      "\"color\": \"#aaaaaa\"," + 	
		      "\"size\": \"sm\"," + 	
		      "\"flex\": 1" + 	
		      "}," + 	
		      "{" + 	
		      "\"type\": \"text\"," + 	
		      "\"text\": \"replaceDate\"," + 	
		      "\"wrap\": true," + 	
		      "\"size\": \"sm\"," + 	
		      "\"color\": \"#666666\"," + 	
		      "\"flex\": 4" + 	
		      "}" + 	
		      "]" + 	
		      "}," + 	
		      "{" + 	
		      "\"type\": \"box\"," + 	
		      "\"layout\": \"baseline\"," + 	
		      "\"spacing\": \"sm\"," + 	
		      "\"contents\": [" + 	
		      "{" + 	
		      "\"type\": \"text\"," + 	
		      "\"text\": \"地點\"," + 	
		      "\"color\": \"#aaaaaa\"," + 	
		      "\"size\": \"sm\"," + 	
		      "\"flex\": 1" + 	
		      "}," + 	
		      "{" + 	
		      "\"type\": \"text\"," + 	
		      "\"text\": \"replacePlace\"," + 	
		      "\"wrap\": true," + 	
		      "\"color\": \"#666666\"," + 	
		      "\"size\": \"sm\"," + 	
		      "\"flex\": 4" + 	
		      "}" + 	
		      "]" + 	
		      "}" + 	
		      "]" + 	
		      "}," + 	
		      "{" + 	
		      "\"type\": \"box\"," + 	
		      "\"layout\": \"vertical\"," + 	
		      "\"margin\": \"xxl\"," + 	
		      "\"contents\": [" + 	
		      "{" + 	
		      "\"type\": \"spacer\"" + 	
		      "}," + 	
		      "{" + 	
		      "\"type\": \"image\"," + 	
		      "\"url\": \"replaceQrUrl\"," + 	
		      "\"aspectMode\": \"cover\"," + 	
		      "\"size\": \"xl\"" + 	
		      "}," + 	
		      "{" + 	
		      "\"type\": \"text\"," + 	
		      "\"text\": \"replaceMessage\"," + 	
		      "\"color\": \"#aaaaaa\"," + 	
		      "\"wrap\": true," + 	
		      "\"margin\": \"xxl\"," + 	
		      "\"size\": \"xs\"" + 	
		      "}" + 	
		      "]" + 	
		      "}" + 	
		      "]" + 	
		      "}" + 	
		      "}}"; 	

	public String getFlexMessageTemplate() {
		return flexMessageTemplate;
	}

	public MyFlexTemplate(MyFlexEntity myFlexEntity) {
		flexMessageTemplate = flexMessageTemplate.replace("replaceTitle", myFlexEntity.getTitle());
		flexMessageTemplate = flexMessageTemplate.replace("replaceLogoUrl", myFlexEntity.getLogoUrl());
		flexMessageTemplate = flexMessageTemplate.replace("replaceDate", myFlexEntity.getDate());
		flexMessageTemplate = flexMessageTemplate.replace("replacePlace", myFlexEntity.getPlace());
		flexMessageTemplate = flexMessageTemplate.replace("replaceQrUrl", myFlexEntity.getQrUrl());
		flexMessageTemplate = flexMessageTemplate.replace("replaceMessage", myFlexEntity.getMessage());
		flexMessageTemplate = flexMessageTemplate.replace("replaceRedirectUrl", myFlexEntity.getLogoUrlActionUrl());
	}

	public MyFlexTemplate() {
		super();
	}
	
	

}