package com.rabbitmq.rabbitmq.config;

 
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabbitmq.rabbitmq.utils.QueueUtils;


@Component
public class MQConfig {

    @Autowired
    private AmqpAdmin amqpAdmin; //1l1~e todas as configurações de user
    
    private Queue queue(String queueName) {
        return new Queue(queueName, true, false, false);
    }

    private DirectExchange directExchange() {
        return new DirectExchange(QueueUtils.EXCHANGE_NAME);
    }

    private Binding relate(Queue queue, DirectExchange directExchange) {
        // cria o relacionamento fila com exchange
        return new Binding(queue.getName(), 
                            Binding.DestinationType.QUEUE, 
                            directExchange.getName(),
                            queue.getName(),
                            null
                            );
    }

    private void create() {
        Queue queue = queue(QueueUtils.QUEUE_NAME);
        DirectExchange directExchange = directExchange();
        Binding relate = relate(queue, directExchange);
    }   
}
