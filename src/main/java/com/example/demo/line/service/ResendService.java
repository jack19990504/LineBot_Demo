package com.example.demo.line.service;

import com.example.demo.feign.MessageAPI;
import com.example.demo.line.message.entity.Push;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class ResendService {

	{
		log.info("init :\t" + this.getClass().getSimpleName());
	}

	private final MessageAPI messageAPI;
	private final Map<String, Push> failedMap;

	public ResendService(MessageAPI messageAPI,@Qualifier("failedMap")Map<String,Push> map){
		this.messageAPI = messageAPI;
		this.failedMap = map;
	}

	/*
	 * do this method per 60s using iterator to get all unsent message if send
	 * successfully, then remove it from map
	 *
	 */

	@Scheduled(fixedRate = 60000)
	public void resendFailMessage() {

		if (!failedMap.isEmpty()) {
			log.info("start send push task");
			failedMap.entrySet().removeIf(next -> messageAPI.push(next.getValue(), next.getKey()).status() == 200);
		}
		log.info("task is done");
	}

}
