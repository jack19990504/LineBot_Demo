package com.example.demo.line.service;

import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.keys.LineKeys;
import com.example.demo.line.util.SendMessageUtil;

@Service
public class ResendService implements LineKeys {
	
	private static final Logger LOG = LoggerFactory.getLogger(ResendService.class);
	{
		LOG.info("init :\t" + this.getClass().getSimpleName());
	}
	@Autowired
	SendMessageUtil sendMessageUtil;


	/*
	 * do this method per 15s
	 * using iterator to get all unsent message 
	 * if send successfully, then remove it from map
	 * 
	 */
	
	@Scheduled(fixedRate = 15000)
	public void resendFailMessage() {
		
		if (!replyFailedHashMap.isEmpty()) {
			System.out.println("start send reply task");
			Iterator<Map.Entry<String, String>> iter = replyFailedHashMap.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<String, String> next = iter.next();
				if (sendMessageUtil.sendReplyMessage(next.getKey(), next.getValue())) {
					// replyFailedHashMap.remove(k);
					iter.remove();
				}
			}
		}
		
		if (!pushFailedHashMap.isEmpty()) {
			System.out.println("start send push task");
			Iterator<Map.Entry<String, String>> iter = pushFailedHashMap.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<String, String> next = iter.next();
				if (sendMessageUtil.sendPostMessage(next.getKey(), next.getValue())) {
					// replyFailedHashMap.remove(k);
					iter.remove();
				}
			}
		}
		System.out.println("task is done");
	}

	/*
	 * lambda for each, can send message but can not remove element while iterating
	 */

//	if (!replyFailedHashMap.isEmpty()) {
//
//		System.out.println("start task");
//		replyFailedHashMap.forEach((k, v) -> {
//			System.out.println("uuid : " + k + " message : " + v);
//			if (sendMessageUtil.sendReplyMessage(k, v)) {
//				// replyFailedHashMap.remove(k);
//				replyFailedHashMap.remove(k, v);
//			}
//		});
//	}

}
