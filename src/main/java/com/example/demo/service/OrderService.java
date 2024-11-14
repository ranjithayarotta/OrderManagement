package com.example.demo.service;

import com.example.demo.exception.InvalidOrderException;
import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderService {
    private  final OrderRepository orderRepository;

   @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository=orderRepository;
    }

    public Order createOrder(Order order){
       validateOrder(order);
       calculateTotalAmount(order);
       return orderRepository.save(order);
    }

    private  void  validateOrder(Order order){
       if(order.getItems().isEmpty()){
           throw new InvalidOrderException("Order must contain atleast one item");
       }
       for(OrderItem item:order.getItems()){
           if(item.getQuantity()<=0){
               throw new InvalidOrderException("Item quantity  must be positive");
           }
       }
    }

    private  void  calculateTotalAmount(Order order){
       BigDecimal total = order.getItems().stream().map(item->item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity()))).reduce(BigDecimal.ZERO,BigDecimal::add);
       order.setTotalAmount(total);

    }
}
