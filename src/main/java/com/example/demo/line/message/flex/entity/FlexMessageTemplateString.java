package com.example.demo.line.message.flex.entity;

public class FlexMessageTemplateString{
	
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

	public void setFlexMessageTemplate(String flexMessageTemplate) {
		this.flexMessageTemplate = flexMessageTemplate;
	}

	/*
	 * replace parameters
	 * 
	 * LogoUrl replaceRedirectUrl Title Date Place QrUrl Message
	 * 
	 */

	public void setMessage(FlexMessage flexMessage) {

		flexMessageTemplate = flexMessageTemplate.replace("replaceTitle", flexMessage.getTitle());
		flexMessageTemplate = flexMessageTemplate.replace("replaceLogoUrl", flexMessage.getLogoUrl());
		flexMessageTemplate = flexMessageTemplate.replace("replaceDate", flexMessage.getDate());
		flexMessageTemplate = flexMessageTemplate.replace("replacePlace", flexMessage.getPlace());
		flexMessageTemplate = flexMessageTemplate.replace("replaceQrUrl", flexMessage.getQrUrl());
		flexMessageTemplate = flexMessageTemplate.replace("replaceMessage", flexMessage.getMessage());
		flexMessageTemplate = flexMessageTemplate.replace("replaceRedirectUrl", flexMessage.getLogoUrlActionUrl());

	}

	public FlexMessageTemplateString(FlexMessage flexMessage) {
		flexMessageTemplate = flexMessageTemplate.replace("replaceTitle", flexMessage.getTitle());
		flexMessageTemplate = flexMessageTemplate.replace("replaceLogoUrl", flexMessage.getLogoUrl());
		flexMessageTemplate = flexMessageTemplate.replace("replaceDate", flexMessage.getDate());
		flexMessageTemplate = flexMessageTemplate.replace("replacePlace", flexMessage.getPlace());
		flexMessageTemplate = flexMessageTemplate.replace("replaceQrUrl", flexMessage.getQrUrl());
		flexMessageTemplate = flexMessageTemplate.replace("replaceMessage", flexMessage.getMessage());
		flexMessageTemplate = flexMessageTemplate.replace("replaceRedirectUrl", flexMessage.getLogoUrlActionUrl());
	}

	public FlexMessageTemplateString() {
		super();
	}
	
	

}