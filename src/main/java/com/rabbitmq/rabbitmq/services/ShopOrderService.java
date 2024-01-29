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

    /**
	 * Método é responsável por criar um novo pedido de loja.
	 * 
	 * @param ShopOrderDTO
	 * @return Ele recebe um objeto ShopOrderDTO que representa os dados do pedido a ser criado.
 	 * @since 1.0
	 * @author Bruno Bandeira
	 * 
	 */

    public void create(ShopOrderDTO orderDTO) {
        ShopOrder shopOrder = mapper.map(orderDTO, ShopOrder.class);
        repository.save(shopOrder);

        rabbitTemplate.convertAndSend(QueueUtils.QUEUE_NAME, shopOrder.getId());

    }

    /**
	 * Este método retorna todos os pedidos de loja paginados
	 * 
	 * @param Pageable
	 * @return Utiliza o ShopOrderRepository para buscar os pedidos no banco de dados
 	 * @since 1.0
	 * @author Bruno Bandeira
	 * 
	 */
    public Page<ShopOrderDTO> getAll(Pageable pageable) {
        return repository.findAll(pageable)
                        .map(objShorOrder -> mapper
                        .map(objShorOrder, ShopOrderDTO.class));
    }

    
    /**
	 * É um ouvinte de mensagens da fila(QueueUtils.QUEUE_NAME)
	 * 
	 * @param QUEUE_NAME
	 * @return Quando uma mensagem é recebida na fila, este método é chamado automaticamente.
 	 * @since 1.0
	 * @author Bruno Bandeira
	 * 
	 */

    @RabbitListener(queues =  QueueUtils.QUEUE_NAME)
    public void subscribe(Long id) {
        Optional<ShopOrder> shopOrder = repository.findById(id);
        if(shopOrder.isPresent()) {
            shopOrder.get().setStatus("DONE");
            repository.save(shopOrder.get());
        }
    }

}
