package com.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2016/3/13.
 */
@Controller
public class GreetingController {

    private List<String> users = new ArrayList<>();

    @Autowired
    public SimpMessagingTemplate template;

    @MessageMapping("/hello")
    public void greeting(HelloMessage message) throws Exception {
        template.convertAndSend("/topic/greetings/" + message.getArea(), new Greeting(message.getContent()));
    }


    @RequestMapping("/login")
    @ResponseBody
    public User login() {
        String id = UUID.randomUUID().toString().replace("-", "");
        User user = new User();
        System.out.println("id 为---》" + id);
        user.setId(id);
        users.add(id);
        return user;
    }
}
