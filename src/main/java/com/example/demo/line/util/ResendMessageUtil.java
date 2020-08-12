package com.example.demo.line.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.keys.LineKeys;

@Component
public class ResendMessageUtil implements LineKeys {

	private static final Logger LOG = LoggerFactory.getLogger(ResendMessageUtil.class);
	{
		LOG.info("init :\t" + this.getClass().getSimpleName());
	}
	@Autowired
	SendMessageUtil sendMessageUtil;
	

	//@Scheduled(fixedRate = 5000)
	public void resendFailMessage() {
//		if(!replyFailedHashMap.isEmpty())
//		{
//			 for (Entry<String, String> failedMessage : replyFailedHashMap.entrySet()) {
//		            System.out.println(failedMessage.getKey() + ":" + failedMessage.getValue());
//		        }
//		}
//		
		System.out.println("task is done");
	}
}
