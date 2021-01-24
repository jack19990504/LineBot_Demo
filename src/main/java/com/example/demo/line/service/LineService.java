package com.example.demo.line.service;

import com.example.demo.annotation.SendSlack;
import com.example.demo.keys.ImagesURL;
import com.example.demo.line.entity.Event;
import com.example.demo.line.message.flex.entity.MyFlexEntity;
import com.example.demo.line.message.flex.entity.MyFlexTemplate;
import com.example.demo.slack.entity.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LineService implements ImagesURL{

	private static final Logger LOG = LoggerFactory.getLogger(LineService.class);

	private final ReplyService replyService;
	private final LineProfileService lineProfileService;

	public LineService(ReplyService replyService,LineProfileService lineProfileService){
		this.replyService = replyService;
		this.lineProfileService = lineProfileService;
	}

	// show spring init components and other tags at starting server
	{
		LOG.info("init :\t" + this.getClass().getSimpleName());
	}

	@SendSlack(messageType = MessageType.TEXT)
	public void reply(Optional<Event> event) {

		// initialize some necessary data
		String replyToken = event.map(Event::getReplyToken).orElse(null);
		String message = event.map(e -> e.getMessage().getText()).orElse(null);
		// need to check source (user / room / group)
		// to do
		String userId = event.map(e -> e.getSource().getUserId()).orElse(null);
		System.out.println("replyToken : " + replyToken);
		System.out.println("message : " + message);

		// if both data are not null
		if (replyToken != null && message != null) {
			
			/*
			 * test webhook, if send reply with this token it will cause 400 bad request
			 */
			if (!replyToken.equals("00000000000000000000000000000000")) {
				switch (message) {
				case "flexMessage":

					MyFlexEntity myFlexEntity = new MyFlexEntity();
					myFlexEntity.setLogoUrl(LOGO_URL);
					myFlexEntity.setPlace("辛亥路");
					myFlexEntity.setTitle("益群健康股份有限公司");
					myFlexEntity.setQrUrl(DOGE_URL);
					myFlexEntity.setLogoUrlActionUrl(LOGO_REDIRECT_URL);
					myFlexEntity.setMessage("歡迎參加");
					myFlexEntity.setDate("2020-08-06");

					// send it
					replyService.sendResponseMessage_flex(replyToken, new MyFlexTemplate(myFlexEntity));
					break;
				case "reply":
					replyService.sendQuickReply(replyToken);
					break;
				default:
					String userName = lineProfileService.getUserName(userId);
					replyService.sendResponseMessage(replyToken, userName);
					break;
				}

			} else {
				LOG.error("is test webhook");
			}
		} else {
			LOG.warn("replyToken or message is null");
		}
	}

}
