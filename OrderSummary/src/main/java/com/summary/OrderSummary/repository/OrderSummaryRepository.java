package com.summary.OrderSummary.repository;

import com.summary.OrderSummary.model.OrderSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderSummaryRepository extends JpaRepository<OrderSummary,Long> {
}
