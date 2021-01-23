package com.example.demo.line.service;

import com.example.demo.keys.ImagesURL;
import com.example.demo.keys.LineKeys;
import com.example.demo.line.action.entity.LocationAction;
import com.example.demo.line.action.entity.MessageAction;
import com.example.demo.line.action.entity.PostBackAction;
import com.example.demo.line.action.entity.QuickReplyAction;
import com.example.demo.line.message.entity.*;
import com.example.demo.line.message.flex.entity.FlexMessageTemplate;
import com.example.demo.line.message.flex.entity.MyFlexTemplate;
import com.example.demo.line.util.SendMessageUtil;
import com.example.demo.util.JsonParserUtil;
import com.example.demo.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("DuplicatedCode")
@Service
public class ReplyService implements LineKeys, ImagesURL {

	private static final Logger LOG = LoggerFactory.getLogger(ReplyService.class);


	private final SendMessageUtil sendMessageUtil;
	private final JsonParserUtil jsonParserUtil;
	private final UUIDUtil uuidUtil;

	@Autowired
	public ReplyService(SendMessageUtil sendMessageUtil,JsonParserUtil jsonParserUtil,UUIDUtil uuidUtil){
		this.jsonParserUtil = jsonParserUtil;
		this.sendMessageUtil = sendMessageUtil;
		this.uuidUtil = uuidUtil;
	}

	// show spring init components and other tags at starting server
	{
		LOG.info("init :\t" + this.getClass().getSimpleName());
	}

	// send back one
	// message.length must smaller than 3
	public void sendResponseMessage(String replyToken, String... messages) {

		String uuid = uuidUtil.getRandomUUID();

		List<EntityMessage> messagesList = new ArrayList<>();

		TextMessage textMessage;

		for (String message : messages) {
			textMessage = new TextMessage("text", message);
			messagesList.add(textMessage);
		}
		Reply reply = new Reply(replyToken, messagesList);

		String jsonData = jsonParserUtil.jsonToString(reply);

		sendMessageUtil.sendReply(uuid,jsonData);

	}

	// one flex
	public void sendResponseMessage_flex(String replyToken, MyFlexTemplate myFlexTemplate) {

		String uuid = uuidUtil.getRandomUUID();

		List<EntityMessage> messageList = new ArrayList<>();

		FlexMessageTemplate flexMessageTemplate = jsonParserUtil
				.stringToJson(myFlexTemplate.getFlexMessageTemplate(), FlexMessageTemplate.class);

		messageList.add(flexMessageTemplate);

		Reply reply = new Reply(replyToken, messageList);

		String jsonData = jsonParserUtil.jsonToString(reply);

		sendMessageUtil.sendReply(uuid,jsonData);
	}

	public void sendQuickReply(String replyToken) {

		String uuid = uuidUtil.getRandomUUID();

		List<EntityMessage> messageList = new ArrayList<>();

		List<QuickReplyAction> actionList = new ArrayList<>();
		

		TextMessage textMessage = new TextMessage();
		textMessage.setType("text");
		textMessage.setText("test");
		messageList.add(textMessage);

		actionList.add(new QuickReplyAction("action", new PostBackAction("postback", "post test", "data=123", "TEST")));
		actionList.add(new QuickReplyAction("action", DOGE_URL, new MessageAction("message", "doge", "testMessage")));
		actionList.add(new QuickReplyAction("action", LOGO_URL, new MessageAction("message", "logo", "testLogo")));
		actionList.add(new QuickReplyAction("action", new LocationAction("location", "Send location")));

		QuickReplyMessage quickReplyMessage = new QuickReplyMessage("text", "Select one", new QuickReply(actionList));
		messageList.add(quickReplyMessage);

		Reply reply = new Reply(replyToken, messageList);

		String jsonData = jsonParserUtil.jsonToString(reply);

		sendMessageUtil.sendReply(uuid,jsonData);
	}

}