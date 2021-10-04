package com.summary.OrderSummary.service.imp;

import com.summary.OrderSummary.model.OrderItemSummary;
import com.summary.OrderSummary.model.OrderSummary;
import com.summary.OrderSummary.repository.OrderSummaryRepository;
import com.summary.OrderSummary.service.OrderSummaryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class OrderSummaryServiceImp implements OrderSummaryService {
    private final OrderSummaryRepository orderRepository;

    @Autowired
    public OrderSummaryServiceImp(OrderSummaryRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderSummary updateOrderSummary(Long id, Long orderId, OrderItemSummary orderItemSummary) {
        Optional<OrderSummary> currentOrderSummary = orderRepository.findById(id);
        OrderSummary getOrderSummary = null;
        if(currentOrderSummary.isPresent()){
            getOrderSummary = currentOrderSummary.get();
            List<OrderItemSummary> getAllOrderItemSummaries = getOrderSummary.getOrderItemList();
            OrderItemSummary currentItem = (OrderItemSummary) getOrderSummary.getOrderItemList().stream()
                    .filter(p->p.getOrderId().equals(orderId));
            getAllOrderItemSummaries.remove(currentItem);
            currentItem.setItemPrice(orderItemSummary.getItemPrice());
            currentItem.setOrderId(orderItemSummary.getOrderId());
            currentItem.setItemName(orderItemSummary.getItemName());
            currentItem.setDiscount(orderItemSummary.getDiscount());
            currentItem.setQuantity(orderItemSummary.getQuantity());
            getAllOrderItemSummaries.add(orderItemSummary);
        }
        return getOrderSummary;
    }
}
