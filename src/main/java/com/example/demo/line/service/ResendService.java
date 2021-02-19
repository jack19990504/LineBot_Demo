package com.example.demo.line.service;

import com.example.demo.keys.LineKeys;
import com.example.demo.line.util.LineMessageAPIUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ResendService implements LineKeys {

	{
		log.info("init :\t" + this.getClass().getSimpleName());
	}

	private final LineMessageAPIUtil lineMessageAPIUtil;

	public ResendService(LineMessageAPIUtil lineMessageAPIUtil){
		this.lineMessageAPIUtil = lineMessageAPIUtil;
	}

	/*
	 * do this method per 60s using iterator to get all unsent message if send
	 * successfully, then remove it from map
	 *
	 */

	@Scheduled(fixedRate = 60000)
	public void resendFailMessage() {

		if (replyFailedHashMap.isEmpty() && pushFailedHashMap.isEmpty())
			return;
		if (!replyFailedHashMap.isEmpty()) {
			// reply can not use retry key to resend message, it might need to add to pushFailHashMap to resend
			// to do
			log.info("start send reply task");
			replyFailedHashMap.entrySet().removeIf(next -> lineMessageAPIUtil.sendReply(next.getKey(), next.getValue()));
		}
		if (!pushFailedHashMap.isEmpty()) {
			log.info("start send push task");
			pushFailedHashMap.entrySet().removeIf(next -> lineMessageAPIUtil.sendPush(next.getKey(), next.getValue()));
		}
		log.info("task is done");


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
