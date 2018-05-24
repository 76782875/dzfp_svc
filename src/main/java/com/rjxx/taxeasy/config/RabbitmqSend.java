package com.rjxx.taxeasy.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @ClassName RabbitmqSend
 * @Description TODO
 * @Author 许黎明
 * @Date 2018-05-09 12:32
 * @Version 1.0
 **/
@Service("rabbitmqSend")
public class RabbitmqSend implements RabbitTemplate.ConfirmCallback{


    private static Logger logger = LoggerFactory.getLogger(RabbitmqSend.class);


    public static String EXCHANGE_NAME = "INVOICE_EXCHANGE";

    @Autowired
    private ConnectionFactory connectionFactory;

    @Value("${rabbitmq.concurrency}")
    private int concurrency;

    @Value("${rabbitmq.maxconcurrency}")
    private int maxconcurrency;



    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitmqSend(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this);
    }

    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue queue() {
        return new Queue("queue_ErrorException_Sk_12", true);
    }

    @Bean
    public Queue queuebox() {
        return new Queue("queue_box", true);
    }

    @Bean
    public Binding bindingbox() {
        return BindingBuilder.bind(queuebox()).to(defaultExchange()).with("queue_box");
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(defaultExchange()).with("queue_ErrorException_Sk_12");
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info(" 消息id:" + correlationData);
        if (ack) {
            logger.info("消息发送确认成功");
        } else {
            logger.info("消息发送确认失败:" + cause);
        }
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrentConsumers(concurrency);
        factory.setMaxConcurrentConsumers(maxconcurrency);
        return factory;
    }

    public void send(Object message) {

        //执行保存
        String uuid = UUID.randomUUID().toString();
        CorrelationData correlationId = new CorrelationData(uuid);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "queue_ErrorException_Sk_12",message ,correlationId);
    }

    public void sendbox(Object message) {

        //执行保存
        String uuid = UUID.randomUUID().toString();
        CorrelationData correlationId = new CorrelationData(uuid);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "queue_box",message ,correlationId);
    }
    public Object receivebox() {
        //执行保存
        return rabbitTemplate.receiveAndConvert("queue_box");
    }
}
