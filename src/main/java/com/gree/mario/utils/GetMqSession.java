package com.gree.mario.utils;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

/*
 * @author: ZXZ
 * @Date: 2020/8/12 10:24
 * @version: 1.0
 */
public class GetMqSession {
    private static  final String BROKER_URL="tcp://192.168.209.128:61616";
    public static ActiveMQConnectionFactory activeMQConnectionFactory;
    public static Connection connection;
    public static Session mqSession;
    public static Session getMqSession() throws  Exception{
        activeMQConnectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        connection = activeMQConnectionFactory.createConnection();
        mqSession = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //开启连接
        connection.start();
        return mqSession;
    }
    public static void close() throws Exception{
        mqSession.close();
        connection.close();
    }

    //topic持久化时，connection.start()位置有所变化
    public static Connection getSessionNoStart() throws JMSException {
        activeMQConnectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        connection = activeMQConnectionFactory.createConnection();
        return connection;
    }
}
