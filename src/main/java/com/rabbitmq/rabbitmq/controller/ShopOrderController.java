package com.rabbitmq.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.rabbitmq.dto.ShopOrderDTO;
import com.rabbitmq.rabbitmq.services.ShopOrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Controlador de Produtos", description = "Utilitario de Produtos")
@RestController
@RequestMapping("/v1")
public class ShopOrderController {

    @Autowired
    private ShopOrderService service;
    
    @Operation(summary = "Salva os dados no banco", method = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Retorna pisitivo dos dados cadastrados"),
        @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
        @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
        @ApiResponse(responseCode = "500", description = "Erro no servidor(Backend)"),
    })
    @PostMapping
    public ResponseEntity create(@RequestBody ShopOrderDTO orderDTO) {
        service.create(orderDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }


    @Operation(summary = "Pega todos os dados do banco", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Requisição feita com sucesso"),
        @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
        @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
        @ApiResponse(responseCode = "500", description = "Erro no servidor(Backend)"),
    })
    @GetMapping
    public ResponseEntity<Page<ShopOrderDTO>> getAll(Pageable pageable) {
        Page<ShopOrderDTO> orderDTOS = service.getAll(pageable);
        return ResponseEntity.ok(orderDTOS);
    }
}
