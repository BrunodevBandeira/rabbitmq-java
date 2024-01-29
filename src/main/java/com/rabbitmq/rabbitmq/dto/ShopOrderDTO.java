package com.rabbitmq.rabbitmq.dto;

import java.io.Serializable;

import lombok.Data;
/**
 * Trânsfere dados especificos da minha entidade
 * 
 * @author Bruno Bandeira
 * @since 1.0
 *
 */

@Data
public class ShopOrderDTO implements Serializable{
// Serializable , transforma os dados em btcode, e a fila quando é criada, lê as informações desta forma

    private String produto;

    private String status;

}
