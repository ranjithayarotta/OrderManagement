package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public  void shouldCreateOrderSucesfuly(){
        OrderItem orderItem =new OrderItem();
        orderItem.setProductCode("123");
        orderItem.setQuantity(2);
        Order order= new Order();
        order.setItems(List.of(orderItem));
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);
      Order createOrder=  orderService.createOrder(order);
      Assertions.assertNotNull(createOrder);

    }
}
