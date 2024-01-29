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
    
    /**
     * Cria e retorna uma instância de fila (Queue) do RabbitMQ.
     * 
     * @author Bruno Bandeira
     * @since 1.0
     *
     */
    private Queue queue(String queueName) {
        // cria a fila
        return new Queue(queueName, true, false, false);
    }

    
    /**
     *  Cria e retorna uma instância de uma exchange de tipo "direct" no RabbitMQ
     * 
     * @author Bruno Bandeira
     * @since 1.0
     *
     */
    private DirectExchange directExchange() {
        //cria a exchang
        return new DirectExchange(QueueUtils.EXCHANGE_NAME);
    }


    /**
     *  Cria e retorna uma instância de um relacionamento (binding) entre uma fila e uma exchange do tipo "direct".
     * 
     * @author Bruno Bandeira
     * @since 1.0
     *
     */
    private Binding relate(Queue queue, DirectExchange directExchange) {
        // cria o relacionamento, fila com exchange
        return new Binding(queue.getName(), 
                            Binding.DestinationType.QUEUE, 
                            directExchange.getName(),
                            queue.getName(),
                            null
                            );
    }

    /**
     *  Chama os métodos queue, directExchange e relate para obter as instâncias de fila, exchange e binding.
     * 
     * @author Bruno Bandeira
     * @since 1.0
     *
     */
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
