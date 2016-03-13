package com.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        return new Greeting("Hello, " + message.getName() + "!");
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

    @MessageMapping("/message")
    @SendToUser("/message")
    public Greeting userMessage(Greeting userMessage) throws Exception {
        return userMessage;
    }


    @RequestMapping("/random")
    @ResponseBody
    public void random() {
        Random random = new Random();
        int i = random.nextInt(users.size());
        String s = users.get(i);
        System.out.println(s);
        String id = UUID.randomUUID().toString();
        template.convertAndSendToUser(s, "/message", new Greeting("Hello,单点消息---》 " + id + "!"));
        template.convertAndSend("/topic/greetings", new Greeting("Hello,---》 " + id + "!"));
        template.convertAndSend("/user/" + s + "/message", new Greeting("Hello,单点消息---》 " + id + "!"));
    }


}
