package com.liu.luntan.controller;


import com.liu.luntan.entity.Event;
import com.liu.luntan.entity.User;
import com.liu.luntan.event.EventProducer;
import com.liu.luntan.service.LikeService;
import com.liu.luntan.util.HostHolder;
import com.liu.luntan.util.LuntanConst;
import com.liu.luntan.util.LuntanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LikeController implements LuntanConst {

    @Autowired
    private LikeService likeService;

    @Autowired
    HostHolder hostHolder;

    @Autowired
    private EventProducer eventProducer;

    @RequestMapping(value = "/like", method = RequestMethod.POST)
    @ResponseBody
    public String like(int entityType, int entityId,int entityUserId, int postId) {
        User user = hostHolder.getUser();

        // 点赞zzz
        likeService.like(user.getId(), entityType, entityId, entityUserId);

        // 数量
        long likeCount = likeService.findEntityLikeCount(entityType, entityId);

        // 状态
        int likeStatus = likeService.findEntityLikeStatus(user.getId(), entityType, entityId);

        // 返回的结果
        Map<String, Object> map = new HashMap<>();
        map.put("likeCount", likeCount);
        map.put("likeStatus", likeStatus);

        // 触发点赞事件
        if (likeStatus == 1) {
            Event event = new Event()
                    .setTopic(TOPIC_LIKE)
                    .setUserId(hostHolder.getUser().getId())
                    .setEntityType(entityType)
                    .setEntityId(entityId)
                    .setEntityUserId(entityUserId)
                    .setData("postId", postId);  //postId对于点赞的是帖子而言，这个数据是冗余的
//            eventProducer.fireEvent(event);
        }

        return LuntanUtil.getJSONString(0, null, map);
    }
}


