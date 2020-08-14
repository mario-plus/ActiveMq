package com.gree.mario.ActiveMq;

import com.gree.mario.utils.SendMessages;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/*
 * @author: ZXZ
 * @Date: 2020/8/12 9:20
 * @version: 1.0
 */
public class QueueProduct {

    public static void main(String[] args) throws Exception {
        sendQueueMessage();
    }

    public static void sendQueueMessage() throws Exception {
        SendMessages.sendQueueMessage("Queue_A","生成者生产一条Queue消息！");
    }

}
