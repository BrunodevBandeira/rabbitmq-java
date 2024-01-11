package com.rabbitmq.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.rabbitmq.dto.ShopOrderDTO;
import com.rabbitmq.rabbitmq.services.ShopOrderService;

@RestController
@RequestMapping("/v1")
public class ShopOrderController {

    @Autowired
    private ShopOrderService service;
    
    @PostMapping
    public ResponseEntity create(@RequestBody ShopOrderDTO orderDTO) {
        service.create(orderDTO);

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
