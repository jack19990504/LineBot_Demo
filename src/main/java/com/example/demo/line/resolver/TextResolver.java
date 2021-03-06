package com.example.demo.line.resolver;

import com.example.demo.annotation.SendSlack;
import com.example.demo.properties.ImagesProperties;
import com.example.demo.line.entity.Event;
import com.example.demo.line.message.flex.entity.MyFlexEntity;
import com.example.demo.line.message.flex.entity.MyFlexTemplate;
import com.example.demo.line.service.ReplyService;
import com.example.demo.slack.entity.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class TextResolver implements Resolver{

    private final ReplyService replyService;

    public TextResolver(ReplyService replyService){
        this.replyService = replyService;
    }


    @Override
    @SendSlack(messageType = MessageType.TEXT)
    public void solve(Optional<Event> event) {
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
            case "name":
                replyService.sendResponseMessageWithUserName(replyToken,userId, message);
                break;
            default:
                replyService.sendResponseMessage(replyToken, message);
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
