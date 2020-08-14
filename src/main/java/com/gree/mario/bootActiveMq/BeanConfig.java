package com.gree.mario.bootActiveMq;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;


/*
 * @author: ZXZ
 * @Date: 2020/8/14 10:50
 * @version: 1.0
 */
@Configuration
public class BeanConfig {
    @Bean
    public Queue queue(){
        return new ActiveMQQueue("Queue_boot");
    }


    //使用topic需要配置该bean
    @Bean
    public JmsListenerContainerFactory jmsTopicListenerContainerFactory(ConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        //这里必须设置为true，false则表示是queue类型
        factory.setPubSubDomain(true);
        return factory;
    }

    @Bean
    public Topic topic(){
        return new ActiveMQTopic("Topic_boot");
    }
}
