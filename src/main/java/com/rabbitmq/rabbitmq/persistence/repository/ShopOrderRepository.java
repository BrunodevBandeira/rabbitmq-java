package com.rabbitmq.rabbitmq.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rabbitmq.rabbitmq.persistence.model.ShopOrder;

@Repository
public interface ShopOrderRepository extends JpaRepository<ShopOrder, Long>{
    
}
