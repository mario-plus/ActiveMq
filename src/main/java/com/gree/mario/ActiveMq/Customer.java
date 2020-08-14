package com.gree.mario.ActiveMq;

import com.gree.mario.utils.GetMessage;
import com.gree.mario.utils.GetMqSession;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/*
 * @author: ZXZ
 * @Date: 2020/8/12 9:21
 * @version: 1.0
 */
public class Customer {

    public static void main(String[] args) throws Exception {
        Customer customer = new Customer();
        customer.getMessage();
       // customer.getMessageByListener();
       // customer.getTopicMessageByListener();
        // customer.getTopicPersistentMessage();
    }
    //一对一获取队列信息1：1
    public  void getMessage() throws Exception {
        GetMessage.getMessageByQueueName("Queue_A");
    }

    //通过监听器获取队列信息1:1
    public  void getMessageByListener() throws Exception{
        GetMessage.getMessageByQueueNameAndListener("Queue_A");
    }

    //通过监听器获取topic信息1：n
    public void getTopicMessageByListener() throws  Exception{
        GetMessage.getTopicMessageByNameAndListener("topic_A");
    }
    //获取持久化topic
    public void getTopicPersistentMessage() throws Exception{
        GetMessage.getTopicPersistentMessage("Topic_B");
    }
}
