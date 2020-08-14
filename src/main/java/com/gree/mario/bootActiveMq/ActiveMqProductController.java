package com.gree.mario.bootActiveMq;

import org.apache.activemq.command.ActiveMQQueue;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;

/*
 * @author: ZXZ
 * @Date: 2020/8/14 10:47
 * @version: 1.0
 */
@RestController
@RequestMapping("/activemq")
public class ActiveMqProductController {
    @Autowired
    private Queue queue;
    @Autowired
    private Topic topic;
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @GetMapping("sendQueueMessage")
    public void sendQueue(@RequestParam(value = "message") String message){
        sendMessage(queue,message);
    }

    @GetMapping("sendTopicMessage")
    public void getQueueMessage(@RequestParam(value = "message") String message){
        sendMessage(topic,message);
    }
    //为了简便，此处就不写在service中
    public void sendMessage(Destination destination,String message){
        jmsMessagingTemplate.convertAndSend(destination,message);
    }
}
