package com.gree.mario.bootActiveMq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.jms.Topic;

/*
 * @author: ZXZ
 * @Date: 2020/8/14 11:15
 * @version: 1.0
 */
@Component
public class ActiveMqConsumerController {
    /*@Autowired
    Queue queue;
    @Autowired
    Topic topic;
    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;
    @GetMapping("getQueueMessage")
    public String getQueueMessage() throws JMSException {
        Message<?> message = jmsMessagingTemplate.receive("Queue_boot");
        TextMessage textMessage = (TextMessage) message;
        return textMessage.getText();
    }
    @GetMapping("getTopicMessage")
    public String getTopicMessage() throws  JMSException{
        javax.jms.Message message = jmsMessagingTemplate.getJmsTemplate().receive("");
        return null;
    }
    */
    @JmsListener(destination="Queue_boot")
    public void ListenQueue(String msg){
        System.out.println("接收到queue消息：" + msg);
    }

    //接收topic类型消息
    //destination对应配置类中ActiveMQTopic("springboot.topic")设置的名字
    //containerFactory对应配置类中注册JmsListenerContainerFactory的bean名称
    @JmsListener(destination="Topic_boot", containerFactory="jmsTopicListenerContainerFactory" )
    public void ListenTopic(String msg){
        System.out.println("接收到topic消息：" + msg);
    }
}
