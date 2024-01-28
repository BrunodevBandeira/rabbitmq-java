package com.rabbitmq.rabbitmq.config;

 
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabbitmq.rabbitmq.utils.QueueUtils;

import jakarta.annotation.PostConstruct;


@Component
public class MQConfig {

    @Autowired
    private AmqpAdmin amqpAdmin; // AmqpAdmin é uma interface no Spring AMQP que fornece métodos para administração de recursos no broker do RabbitMQ, como filas, trocas, ligações (bindings) e configurações relacionadas.
    
    private Queue queue(String queueName) {
        // cria a fila
        return new Queue(queueName, true, false, false);
    }

    private DirectExchange directExchange() {
        //cria a exchang
        return new DirectExchange(QueueUtils.EXCHANGE_NAME);
    }

    private Binding relate(Queue queue, DirectExchange directExchange) {
        // cria o relacionamento, fila com exchange
        return new Binding(queue.getName(), 
                            Binding.DestinationType.QUEUE, 
                            directExchange.getName(),
                            queue.getName(),
                            null
                            );
    }

    @PostConstruct //vai rodar automaticament
    private void create() {
        Queue queue = queue(QueueUtils.QUEUE_NAME);
        DirectExchange directExchange = directExchange();
        Binding relate = relate(queue, directExchange);
        
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareExchange(directExchange);
        amqpAdmin.declareBinding(relate);
    }   

}
