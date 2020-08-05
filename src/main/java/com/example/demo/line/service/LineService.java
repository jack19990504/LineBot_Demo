package com.example.demo.line.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.line.entity.Event;

@Service
public class LineService {

	private static final Logger LOG = LoggerFactory.getLogger(LineService.class);
	
	@Autowired
	ReplyService replyService;

	// show spring init components and other tags at starting server
	{
		LOG.info("init :"+this.getClass().getSimpleName());
		LOG.debug("debug test");
	}

	public void message_text_Simple_Reply(Optional<Event> event) {
		
		String replyToken = event.map(e -> e.getReplyToken()).orElse(null);
		String message = event.map(e -> e.getMessage().getText()).orElse(null);
		
		/* test webhook
		 * if send reply with this token
		 * it will cause 400 bad request 
		 */
		if(!replyToken.equals("00000000000000000000000000000000"))
		{
			replyService.sendResponseMessage(replyToken, message);
		}
		
		
	}

}
