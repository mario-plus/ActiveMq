package com.gree.mario.utils;
import javax.jms.*;
/*
 * @author: ZXZ
 * @Date: 2020/8/12 11:07
 * @version: 1.0
 */
public class GetMessage {
    private static  Session mqSession;
    private static Queue queue;

    public static void getMessageByQueueName(String queueName) throws Exception {
         mqSession = GetMqSession.getMqSession();
         queue = mqSession.createQueue(queueName);
        MessageConsumer mesConsumer = mqSession.createConsumer(queue);
        mesConsumer.setMessageListener(message -> {
            if (message instanceof TextMessage){
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println(textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.in.read();
        //关闭资源
        mesConsumer.close();
        GetMqSession.close();
    }

    public static void getMessageByQueueNameAndListener(String queueName) throws  Exception{
        mqSession = GetMqSession.getMqSession();
        queue= mqSession.createQueue(queueName);
        MessageConsumer mesConsumer = mqSession.createConsumer(queue);
        mesConsumer.setMessageListener(message -> {
            if (message instanceof  TextMessage){
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println(textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.in.read();
        //关闭资源
        mesConsumer.close();
        GetMqSession.close();
    }

    public static void getTopicMessageByNameAndListener(String topicName) throws Exception{
        mqSession = GetMqSession.getMqSession();
        Topic topic = mqSession.createTopic(topicName);
        MessageConsumer mesConsumer = mqSession.createConsumer(topic);
        mesConsumer.setMessageListener(message -> {
            if (message instanceof  TextMessage){
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println(textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.in.read();
        //关闭资源
        mesConsumer.close();
        GetMqSession.close();
    }

    //获取持久化topic消息（关注后，订阅者重新上线后能够接到发布者之前发的消息）
    public static void getTopicPersistentMessage(String topicName) throws  Exception{
        Connection connection = GetMqSession.getSessionNoStart();
        connection.setClientID("zhou");
        mqSession = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Topic topic = mqSession.createTopic(topicName);
        //创建订阅者 createDurableSubscriber(Topic var1, String var2) var2:订阅者名称（不同的名称会是另一个订阅者）
        TopicSubscriber subscriber = mqSession.createDurableSubscriber(topic, "小米");
        connection.start();
        Message receive = subscriber.receive();
        while (null!=receive){
            if (receive instanceof  TextMessage){
                System.out.println(((TextMessage) receive).getText());
                receive = subscriber.receive();
            }
        }
        subscriber.close();
        //关闭资源
        GetMqSession.close();
    }
}
