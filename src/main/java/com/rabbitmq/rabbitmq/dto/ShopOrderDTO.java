package com.rabbitmq.rabbitmq.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ShopOrderDTO implements Serializable{

    private String produto;

    private String status;

}
