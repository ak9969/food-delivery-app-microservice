package com.summary.OrderSummary.service;

import com.summary.OrderSummary.model.OrderItemSummary;
import com.summary.OrderSummary.model.OrderSummary;
import org.springframework.stereotype.Service;

@Service
public interface OrderSummaryService {
    OrderSummary updateOrderSummary(Long id, Long orderId, OrderItemSummary orderItemSummary);
}
