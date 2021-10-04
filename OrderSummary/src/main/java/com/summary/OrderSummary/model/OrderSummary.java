package com.summary.OrderSummary.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderSummary{
    @Id
    @GeneratedValue
    private Long Id;

    @NotNull
    private int restaurantId;

    @NotBlank
    private String restaurantName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private List<OrderItemSummary> orderItemList;

}
