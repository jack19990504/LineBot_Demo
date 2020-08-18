package com.example.demo.line.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.keys.ImagesURL;
import com.example.demo.keys.LineKeys;
import com.example.demo.line.entity.Event;
import com.example.demo.line.message.entity.FlexMessage;
import com.example.demo.mybatis.entity.Activity;
import com.example.demo.mybatis.entity.Member;
import com.example.demo.mybatis.entity.Registration;
import com.example.demo.mybatis.entity.RegistrationDetail;
import com.example.demo.mybatis.service.ActivityService;
import com.example.demo.mybatis.service.MemberService;
import com.example.demo.mybatis.service.RegistrationService;

@Service
public class LineService implements ImagesURL, LineKeys {

	private static final Logger LOG = LoggerFactory.getLogger(LineService.class);

	@Autowired
	private ReplyService replyService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private ActivityService activityService;

	// show spring init components and other tags at starting server
	{
		LOG.info("init :\t" + this.getClass().getSimpleName());
	}

	public void getUesrDetail(String code) {

	}

	public void message_text_Simple_Reply(Optional<Event> event) {

		// initialize some necessary data
		String replyToken = event.map(e -> e.getReplyToken()).orElse(null);
		String message = event.map(e -> e.getMessage().getText()).orElse(null);
		//String userId = event.map(e -> e.getSource().getUserId()).orElse(null);
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
					replyService.sendResponseMessage_flex(replyToken, flexMessage);
					break;
				case "我是誰":
//					Optional<String> flexMessageResponse = getFlexMessage(userId);
//					if (flexMessageResponse.isPresent()) {
//						replyService.sendResponseMessage_flex(replyToken, flexMessageResponse.get());
//					} else {
//						LOG.warn("send flexMessage went wrong");
//					}
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

	private FlexMessage getMemberFlexMessageTemplateMessage(RegistrationDetail registrationDetail) {

		FlexMessage flexMessage = new FlexMessage();
		flexMessage.setLogoUrl(LOGO_URL);
		flexMessage.setPlace(registrationDetail.getActivity().getActivityAddress());
		flexMessage.setTitle(registrationDetail.getActivity().getActivityHost());
		flexMessage.setQrUrl(DOGE_URL);
		flexMessage.setLogoUrlActionUrl(LOGO_REDIRECT_URL);
		flexMessage.setMessage("歡迎參加");

		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(registrationDetail.getActivity().getActivityStartTime());
		String endDate = sdf.format(registrationDetail.getActivity().getActivityEndTime());

		flexMessage.setDate(startDate + " ~ " + endDate);

		return flexMessage;

	}

	@SuppressWarnings("unused")
	private Optional<FlexMessage> getFlexMessage(String userId) {
		// get member
		final Optional<Member> member = Optional.ofNullable(memberService.getMemberByLineId(userId));
		final String memberEmail = member.map(m -> m.getMemberEmail()).orElse("");
		RegistrationDetail registrationDetail = new RegistrationDetail();
		String activityId;
		Optional<Registration> registration;
		Optional<Activity> activity;

		if (!memberEmail.equals("")) {
			registration = Optional.ofNullable(registrationService.getRegistrationListByMemberEmail(memberEmail));
			activityId = registration.map(r -> r.getActivity_Id()).orElse("");
			if (!activityId.equals("")) {
				activity = Optional.ofNullable(activityService.getActivityById(activityId));
				if (member.isPresent() && activity.isPresent() && registration.isPresent()) {
					registrationDetail.setActivity(activity.get());
					registrationDetail.setMember(member.get());
					registrationDetail.setRegistration(registration.get());
				} else {
					LOG.error("member or activity or registration is null");
				}
			} else {
				LOG.error("activtiyId is null");
			}
		} else {
			LOG.error("memberEmail is null");
		}
		return Optional.ofNullable(getMemberFlexMessageTemplateMessage(registrationDetail));
	}
}
