package com.gree.mario.ActiveMq;

import com.gree.mario.utils.GetMqSession;
import com.gree.mario.utils.SendMessages;

import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/*
 * @author: ZXZ
 * @Date: 2020/8/12 10:40
 * @version: 1.0
 */
public class TopicProduct {
    public static void main(String[] args) throws Exception{
        TopicProduct topicProduct = new TopicProduct();
        //topicProduct.sendTopicMessage();
        topicProduct.sendTopicPersistentMessage();
    }
    public  void sendTopicMessage() throws Exception{
        SendMessages.sendTopicMessage("Topic_A","生产者发送一条Topic消息");
    }
    public void sendTopicPersistentMessage() throws Exception{
        SendMessages.sendTopicPersistentMessage("Topic_B","生产者发送一条持久化Topic消息");
    }
}
