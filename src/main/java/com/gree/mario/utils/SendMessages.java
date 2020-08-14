package com.gree.mario.utils;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/*
 * @author: ZXZ
 * @Date: 2020/8/12 14:39
 * @version: 1.0
 */
public class SendMessages {
    private static Session mqSession;
    private static Queue queue;
    private static Connection connection;
    public static void sendQueueMessage(String queueName,String message) throws  Exception{
         mqSession = GetMqSession.getMqSession();
         queue = mqSession.createQueue(queueName);
         MessageProducer producer = mqSession.createProducer(queue);
         //非持久化，MQ服务器宕机，数据就没有了，默认是持久化 PERSISTENT
         producer.setDeliveryMode(DeliveryMode.PERSISTENT);
         for (int i = 0;i<=2;i++) {
            TextMessage textMessage = mqSession.createTextMessage(message);
            producer.send(textMessage);
         }
         producer.close();
        GetMqSession.close();
    }

    public static void sendTopicMessage(String topicName,String message) throws  Exception{
        Session mqSession = GetMqSession.getMqSession();
        Topic topic = mqSession.createTopic(topicName);
        MessageProducer topicProducer = mqSession.createProducer(topic);
        TextMessage textMessage = mqSession.createTextMessage(message);
        topicProducer.send(textMessage);
        //关闭资源
        topicProducer.close();
        GetMqSession.close();
    }
    //持久化Topic
    public static void sendTopicPersistentMessage(String topicName,String message) throws Exception{
        connection = GetMqSession.getSessionNoStart();
        //此处  createSession(boolean transacted, int acknowledgeMode)
        mqSession = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Topic topic = mqSession.createTopic(topicName);
        MessageProducer topicProducer = mqSession.createProducer(topic);
        //持久化
        topicProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
        connection.start();

        TextMessage textMessage = mqSession.createTextMessage(message);
        topicProducer.send(textMessage);
        //关闭资源
        topicProducer.close();
        mqSession.close();
        connection.close();
    }

    /*
    * createSession(boolean transacted, int acknowledgeMode)
    * 事务
    * false：自动提交事务
    * true：需要手动提交事务，session.commit()
    * 手动提交事务可以进行回滚session.rollback()，业务出错了，就可以自动回滚
    * 如果忘记commit()，生产者无法生产消息，消费者能获取消息，但不是消费消息（消息一直存在MQ中）
    * 签收
    * int AUTO_ACKNOWLEDGE = 1; 自动签收
    * int CLIENT_ACKNOWLEDGE = 2; 手动签收  需要手动签收 receive.acknowledge();不然小心一直存在MQ中，导致重复消费
    * int DUPS_OK_ACKNOWLEDGE = 3; 允许重复签收
    * int SESSION_TRANSACTED = 0; 事务
    *
    *
    * 事务被成功提交，消息会被自动签收
    *
    * */
}
