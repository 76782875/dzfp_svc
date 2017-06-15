package com.rjxx.taxeasy.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2017-06-13.
 */
@Component
public class RabbitmqUtils {

    public static String EXCHANGE_NAME = "INVOICE_EXCHANGE";

    private Map<Integer, Boolean> hasInitMap = new ConcurrentHashMap<>();

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     *
     * @param kpdid
     * @param fpzldm
     * @param message
     * @throws Exception
     */
    public void sendMsg(int kpdid, String fpzldm, String message) throws Exception {
        if (!hasInitMap.containsKey(kpdid)) {
            initQueue(kpdid, fpzldm);
        }
        String queueName = getQueueName(kpdid, fpzldm);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, queueName, message);
    }

    /**
     * 初始化队列
     *
     * @param kpdid
     * @throws Exception
     */
    public boolean initQueue(int kpdid, String fpzldm) throws Exception {
        if (hasInitMap.containsKey(kpdid)) {
            return true;
        }
        Channel channel = connectionFactory.createConnection().createChannel(false);
        channel.exchangeDeclare(EXCHANGE_NAME, "direct", true);
        String queueName = getQueueName(kpdid, fpzldm);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, EXCHANGE_NAME, queueName);
        hasInitMap.put(kpdid, true);
        return true;
    }

    /**
     * 获取队列名称
     *
     * @param kpdid
     * @param fpzldm
     * @return
     */
    public String getQueueName(int kpdid, String fpzldm) {
        return "queue_" + fpzldm + "_" + kpdid;
    }

    /**
     * 接收消息
     *
     * @param kpdid
     * @return
     */
    public Object receiveMsg(int kpdid, String fpzldm) {
        String queueName = getQueueName(kpdid, fpzldm);
        return rabbitTemplate.receiveAndConvert(queueName);
    }

}
