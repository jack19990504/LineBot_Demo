package com.example.demo.line.service;

import com.example.demo.annotation.SendSlack;
import com.example.demo.keys.ImagesProperties;
import com.example.demo.line.entity.Event;
import com.example.demo.line.message.flex.entity.MyFlexEntity;
import com.example.demo.line.message.flex.entity.MyFlexTemplate;
import com.example.demo.slack.entity.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class LineMessageAPIService {

	private final ReplyService replyService;
	private final LineProfileService lineProfileService;

	public LineMessageAPIService(ReplyService replyService, LineProfileService lineProfileService){
		this.replyService = replyService;
		this.lineProfileService = lineProfileService;
	}

	// show spring init components and other tags at starting server
	{
		log.info("init :\t" + this.getClass().getSimpleName());
	}

	@SendSlack(messageType = MessageType.TEXT)
	public void reply(Optional<Event> event) {

		// initialize some necessary data
		String replyToken = event.map(Event::getReplyToken).orElse(null);
		String message = event.map(e -> e.getMessage().getText()).orElse(null);
		// need to check source (user / room / group)
		// to do
		String userId = event.map(e -> e.getSource().getUserId()).orElse(null);
		log.info("replyToken = {} ",replyToken);
		log.info("message = {}",message);

		// if both data are not null
		if (replyToken == null || message == null) {
			log.error("replyToken or message is null");
			return;
		}
		if(isTestWebHook(replyToken)){
			log.info("is test webhook");
			return;
		}

		switch (message) {
			case "flexMessage":
				// send it
				replyService.sendResponseMessage_flex(replyToken, new MyFlexTemplate(prepareFlexEntity()));
				break;
			case "reply":
				replyService.sendQuickReply(replyToken);
				break;
			default:
				String userName = lineProfileService.getUserName(userId);
				replyService.sendResponseMessage(replyToken, userName);
				break;
		}
	}

	private MyFlexEntity prepareFlexEntity(){
		MyFlexEntity myFlexEntity = new MyFlexEntity();
		myFlexEntity.setLogoUrl(ImagesProperties.logoURL);
		myFlexEntity.setPlace("辛亥路");
		myFlexEntity.setTitle("益群健康股份有限公司");
		myFlexEntity.setQrUrl(ImagesProperties.dogeURL);
		myFlexEntity.setLogoUrlActionUrl(ImagesProperties.redirectURL);
		myFlexEntity.setMessage("歡迎參加");
		myFlexEntity.setDate("2020-08-06");
		return  myFlexEntity;
	}

	private boolean isTestWebHook(String replyToken){
		return replyToken.equals("00000000000000000000000000000000");
	}

}
