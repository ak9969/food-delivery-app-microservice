package com.summary.OrderSummary.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false,name = "order_id")
    private Long orderId;

    @NotBlank
    private String itemName;

    @NotNull
    private Double itemPrice;

    @NotNull
    private Float discount;

    @NotNull
    private Integer quantity;

}

