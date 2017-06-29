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

    private Map<String, Boolean> hasInitMap = new ConcurrentHashMap<>();

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     *
     * @param sksbh   税控设备号
     * @param fpzldm
     * @param message
     * @throws Exception
     */
    public void sendMsg(String sksbh, String fpzldm, String message) throws Exception {
        String queueName = getQueueName(sksbh, fpzldm);
        initQueue(queueName);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, queueName, message);
    }

    /**
     * 初始化队列
     *
     * @param queueName
     * @throws Exception
     */
    public boolean initQueue(String queueName) throws Exception {
        if (hasInitMap.containsKey(queueName)) {
            return true;
        }
        Channel channel = connectionFactory.createConnection().createChannel(false);
        channel.exchangeDeclare(EXCHANGE_NAME, "direct", true);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, EXCHANGE_NAME, queueName);
        channel.close();
        hasInitMap.put(queueName, true);
        return true;
    }

    /**
     * 获取channel
     *
     * @return
     */
    public Channel getChannel() {
        return connectionFactory.createConnection().createChannel(false);
    }

    /**
     * 获取队列名称
     *
     * @param sksbh
     * @param fpzldm
     * @return
     */
    public String getQueueName(String sksbh, String fpzldm) throws Exception {
        String queueName = "queue_" + sksbh + "_" + fpzldm;
        initQueue(queueName);
        return queueName;
    }

    /**
     * 接收消息
     *
     * @param sksbh
     * @return
     */
    public Object receiveMsg(String sksbh, String fpzldm) throws Exception {
        String queueName = getQueueName(sksbh, fpzldm);
        initQueue(queueName);
        return rabbitTemplate.receiveAndConvert(queueName);
    }

}
