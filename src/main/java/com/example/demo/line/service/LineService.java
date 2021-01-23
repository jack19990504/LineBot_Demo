package com.example.demo.line.service;

import com.example.demo.annotation.SendSlack;
import com.example.demo.keys.ImagesURL;
import com.example.demo.keys.LineKeys;
import com.example.demo.line.entity.Event;
import com.example.demo.line.message.flex.entity.FlexMessage;
import com.example.demo.line.message.flex.entity.FlexMessageTemplateString;
import com.example.demo.slack.entity.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LineService implements ImagesURL, LineKeys {

	private static final Logger LOG = LoggerFactory.getLogger(LineService.class);

	private final ReplyService replyService;

	public LineService(ReplyService replyService){
		this.replyService = replyService;
	}
	// show spring init components and other tags at starting server
	{
		LOG.info("init :\t" + this.getClass().getSimpleName());
	}

	@SendSlack(messageType = MessageType.TEXT)
	public void message_text_Simple_Reply(Optional<Event> event) {

		// initialize some necessary data
		String replyToken = event.map(Event::getReplyToken).orElse(null);
		String message = event.map(e -> e.getMessage().getText()).orElse(null);
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

					FlexMessage flexMessage = new FlexMessage();
					flexMessage.setLogoUrl(LOGO_URL);
					flexMessage.setPlace("辛亥路");
					flexMessage.setTitle("益群健康股份有限公司");
					flexMessage.setQrUrl(DOGE_URL);
					flexMessage.setLogoUrlActionUrl(LOGO_REDIRECT_URL);
					flexMessage.setMessage("歡迎參加");
					flexMessage.setDate("2020-08-06");

					// send it
					replyService.sendResponseMessage_flex(replyToken, new FlexMessageTemplateString(flexMessage));
					break;
				case "reply":
					replyService.sendQuickReply(replyToken);
					break;
				default:
					replyService.sendResponseMessage(replyToken, message);
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
