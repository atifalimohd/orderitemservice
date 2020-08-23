package com.oms.orderitems.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.oms.orderitems.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderItemsRequest {

    @Valid
    @NotEmpty(message="Please provide the order items list")
    private List<OrderItem> createOrderItems;

}
