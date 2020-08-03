package com.example.demo.line.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.line.entity.Event;

@Service
public class LogicService {

	@Autowired
	ReplyService replyService;

	// show spring init components and other tags at starting server
	{
		System.out.println("init :" + this.getClass().getSimpleName());
	}

	public void dismantle(Map<String, Object> data) {
		Optional<Event> event;
		for (Map.Entry<String, Object> entry : data.entrySet()) {
			event = Optional.ofNullable((Event) (entry.getValue()));
			if (event.isPresent()) {
				switch (event.get().getType()) {
				case "message":
					// if it is a text message, do something, it can be a image or a video as well
					switch (event.get().getMessage().getType()) {
					case "text":
						// print some detail
						System.out.println("ReplyToken : " + event.get().getReplyToken());
						System.out.println("Text : " + event.get().getMessage().getText());
						System.out.println("UserId : " + event.get().getSource().getUserId());

						// send back same text
						replyService.sendResponseMessage(event.get().getReplyToken(), event.get().getMessage().getText());
						break;
					default:
						break;
					}
					break;
				default:
					System.out.println("it is not a message event!");
					break;
				}
			}

		}
		data.clear();
	}

}
