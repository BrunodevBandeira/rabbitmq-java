package com.rabbitmq.rabbitmq.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rabbitmq.rabbitmq.dto.ShopOrderDTO;
import com.rabbitmq.rabbitmq.persistence.model.ShopOrder;
import com.rabbitmq.rabbitmq.persistence.repository.ShopOrderRepository;
import com.rabbitmq.rabbitmq.utils.QueueUtils;

@Service
public class ShopOrderService {
    // ISSO AQUI É O PRODUCER
    @Autowired
    private ShopOrderRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RabbitTemplate rabbitTemplate; // RabbitTemplate é uma classe do Spring AMQP que fornece uma API de alto nível para a interação com o RabbitMQ. Ele simplifica a produção e consumo de mensagens em filas, trocas (exchanges) e roteamento.

    public void create(ShopOrderDTO orderDTO) {
        ShopOrder shopOrder = mapper.map(orderDTO, ShopOrder.class);
        repository.save(shopOrder);

        rabbitTemplate.convertAndSend(QueueUtils.QUEUE_NAME, shopOrder.getId());

    }

    public Page<ShopOrderDTO> getAll(Pageable pageable) {
        return repository.findAll(pageable)
                        .map(objShorOrder -> mapper
                        .map(objShorOrder, ShopOrderDTO.class));
    }

    @RabbitListener(queues =  QueueUtils.QUEUE_NAME)
    public void subscribe(Long id) {
        Optional<ShopOrder> shopOrder = repository.findById(id);
        if(shopOrder.isPresent()) {
            shopOrder.get().setStatus("DONE");
            repository.save(shopOrder.get());
        }
    }

}
