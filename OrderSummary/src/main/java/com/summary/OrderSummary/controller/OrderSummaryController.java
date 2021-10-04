package com.summary.OrderSummary.controller;

import com.summary.OrderSummary.model.OrderItemSummary;
import com.summary.OrderSummary.model.OrderSummary;
import com.summary.OrderSummary.repository.OrderSummaryRepository;
import com.summary.OrderSummary.service.OrderSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/order")
public class OrderSummaryController {

    private final OrderSummaryRepository orderRepository;

    private final OrderSummaryService orderSummaryService;

    @Autowired
    public OrderSummaryController(OrderSummaryRepository orderRepository, OrderSummaryService orderSummaryService) {
        this.orderRepository = orderRepository;
        this.orderSummaryService = orderSummaryService;
    }

    @GetMapping("/")
    public ResponseEntity<List<OrderSummary>> getAllOrder(){
        return new ResponseEntity<>(orderRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<OrderSummary> getOrderById(@PathVariable Long Id){
        Optional<OrderSummary> orderSummary =  orderRepository.findById(Id);
        return orderSummary.map(summary -> new ResponseEntity<>(summary, HttpStatus.OK)).orElse(null);
    }

    @PostMapping("")
    public ResponseEntity<OrderSummary> postOrderById(@RequestBody OrderSummary orderSummary){
        return new ResponseEntity<>(orderRepository.save(orderSummary),HttpStatus.CREATED);
    }
    @PutMapping("/{Id}/{OrderId}")
    public ResponseEntity<OrderSummary> updateOrderById(@PathVariable Long Id,
                                                        @PathVariable Long OrderId,
                                                        @RequestBody OrderItemSummary orderItemSummary){
        OrderSummary currentOrderSummary = orderSummaryService.updateOrderSummary(Id,OrderId,orderItemSummary);
        orderRepository.save(currentOrderSummary);
        return new ResponseEntity<>(currentOrderSummary,
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{Id}")
    public void deleteOrderById(@PathVariable Long Id){
        orderRepository.deleteById(Id);
    }
}
