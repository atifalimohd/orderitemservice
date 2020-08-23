package com.oms.orderitems.service.impl;

import com.oms.orderitems.model.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface OrderItemService {

    Map<String,String> retrieveOrderItemsByProductCodes(List<String> productCodes);

    String createOrderItems(List<OrderItem> orderItem);


}
