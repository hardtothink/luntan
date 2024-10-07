package com.liu.luntan.event;

import com.alibaba.fastjson.JSONObject;
import com.liu.luntan.entity.Event;
import com.liu.luntan.entity.Message;
import com.liu.luntan.service.MessageService;
import com.liu.luntan.util.LuntanConst;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class EventConsumer implements LuntanConst {
//
//    private static final Logger logger = LoggerFactory.getLogger(EventConsumer.class);
//
//    @Autowired
//    private MessageService messageService;
//
//    @KafkaListener(topics = {TOPIC_COMMENT, TOPIC_LIKE, TOPIC_FOLLOW})
//    public void handleCommentMessage(ConsumerRecord record) {
//        if (record == null || record.value() == null) {
//            logger.error("消息的内容为空!");
//            return;
//        }
//
//        Event event = JSONObject.parseObject(record.value().toString(), Event.class);
//        if (event == null) {
//            logger.error("消息格式错误!");
//            return;
//        }
//
//        // 发送站内通知
//        Message message = new Message();
//        message.setFromId(SYSTEM_USER_ID);
//        message.setToId(event.getEntityUserId());
//        message.setConversationId(event.getTopic());
//        message.setCreateTime(new Date());
//
//        Map<String, Object> content = new HashMap<>();
//        content.put("userId", event.getUserId());
//        content.put("entityType", event.getEntityType());
//        content.put("entityId", event.getEntityId());
//
//        //这里代码要再盘一下 额外数据，比如postId
//        if (!event.getData().isEmpty()) {
//            for (Map.Entry<String, Object> entry : event.getData().entrySet()) {
//                content.put(entry.getKey(), entry.getValue());
//            }
//        }
//
//        message.setContent(JSONObject.toJSONString(content));
//        messageService.addMessage(message);
//    }
}
