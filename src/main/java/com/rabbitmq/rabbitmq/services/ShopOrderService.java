package com.rabbitmq.rabbitmq.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabbitmq.rabbitmq.dto.ShopOrderDTO;
import com.rabbitmq.rabbitmq.persistence.model.ShopOrder;
import com.rabbitmq.rabbitmq.persistence.repository.ShopOrderRepository;

@Service
public class ShopOrderService {

    @Autowired
    private ShopOrderRepository repository;

    @Autowired
    private ModelMapper mapper;

    public void create(ShopOrderDTO orderDTO) {

        ShopOrder shopOrder = mapper.map(orderDTO, ShopOrder.class);
        
        repository.save(shopOrder);

        System.out.println("====> " + shopOrder.getId());

    }

}
