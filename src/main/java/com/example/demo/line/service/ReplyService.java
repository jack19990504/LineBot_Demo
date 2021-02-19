package com.example.demo.line.service;

import com.example.demo.keys.ImagesProperties;
import com.example.demo.line.action.entity.LocationAction;
import com.example.demo.line.action.entity.MessageAction;
import com.example.demo.line.action.entity.PostBackAction;
import com.example.demo.line.action.entity.QuickReplyAction;
import com.example.demo.line.message.entity.*;
import com.example.demo.line.message.flex.entity.FlexMessageTemplate;
import com.example.demo.line.message.flex.entity.MyFlexTemplate;
import com.example.demo.line.util.LineMessageAPIUtil;
import com.example.demo.util.JsonParserUtil;
import com.example.demo.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("DuplicatedCode")
@Service
@Slf4j
public class ReplyService {

	private final LineMessageAPIUtil lineMessageAPIUtil;
	private final JsonParserUtil jsonParserUtil;
	private final UUIDUtil uuidUtil;

	public ReplyService(LineMessageAPIUtil lineMessageAPIUtil, JsonParserUtil jsonParserUtil, UUIDUtil uuidUtil){
		this.jsonParserUtil = jsonParserUtil;
		this.lineMessageAPIUtil = lineMessageAPIUtil;
		this.uuidUtil = uuidUtil;
	}

	// show spring init components and other tags at starting server
	{
		log.info("init :\t" + this.getClass().getSimpleName());
	}

	// send back one
	// message.length must smaller than 3
	public void sendResponseMessage(String replyToken, String... messages) {

		List<EntityMessage> messagesList = new ArrayList<>();

		TextMessage textMessage;

		for (String message : messages) {
			textMessage = new TextMessage("text", message);
			messagesList.add(textMessage);
		}

		Reply reply = new Reply(replyToken, messagesList);

		String jsonData = jsonParserUtil.jsonToString(reply);

		lineMessageAPIUtil.sendReply(uuidUtil.getRandomUUID(),jsonData);

	}

	// one flex
	public void sendResponseMessage_flex(String replyToken, MyFlexTemplate myFlexTemplate) {

		List<EntityMessage> messageList = new ArrayList<>();

		// 將myFlexTemplate 模板轉換成 FlexMessageTemplate
		FlexMessageTemplate flexMessageTemplate = jsonParserUtil
				.stringToJson(myFlexTemplate.getFlexMessageTemplate(), FlexMessageTemplate.class);

		// 方便以物件方式操作
		messageList.add(flexMessageTemplate);

		Reply reply = new Reply(replyToken, messageList);

		String jsonData = jsonParserUtil.jsonToString(reply);

		lineMessageAPIUtil.sendReply(uuidUtil.getRandomUUID(),jsonData);
	}

	public void sendQuickReply(String replyToken) {

		List<EntityMessage> messageList = new ArrayList<>();

		List<QuickReplyAction> actionList = new ArrayList<>();


		TextMessage textMessage = new TextMessage();
		textMessage.setType("text");
		textMessage.setText("test");
		messageList.add(textMessage);

		actionList.add(new QuickReplyAction("action", new PostBackAction("postback", "post test", "data=123", "TEST")));
		actionList.add(new QuickReplyAction("action", ImagesProperties.dogeURL, new MessageAction("message", "doge", "testMessage")));
		actionList.add(new QuickReplyAction("action", ImagesProperties.logoURL, new MessageAction("message", "logo", "testLogo")));
		actionList.add(new QuickReplyAction("action", new LocationAction("location", "Send location")));

		QuickReplyMessage quickReplyMessage = new QuickReplyMessage("text", "Select one", new QuickReply(actionList));
		messageList.add(quickReplyMessage);

		Reply reply = new Reply(replyToken, messageList);

		lineMessageAPIUtil.sendReply(uuidUtil.getRandomUUID(),jsonParserUtil.jsonToString(reply));
	}

}
