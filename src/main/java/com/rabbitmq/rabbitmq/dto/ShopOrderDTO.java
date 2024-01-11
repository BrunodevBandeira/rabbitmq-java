package com.rabbitmq.rabbitmq.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ShopOrderDTO implements Serializable{
// Serializable , transforma os dados em btcode, e a fila quando é criada, lê as informações desta forma

    private String produto;

    private String status;

}
