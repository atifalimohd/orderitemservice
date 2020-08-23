package com.oms.orderitems.controller;

import com.oms.orderitems.model.CreateOrderItemsRequest;
import com.oms.orderitems.constant.OrderItemsServiceConstants;
import com.oms.orderitems.model.ItemResponse;
import com.oms.orderitems.model.RetrieveOrderItemsRequest;
import com.oms.orderitems.service.impl.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class OrderItemController {

    @Autowired
    OrderItemService orderItemService;

    @PostMapping("/createorderitems")
    public ResponseEntity<ItemResponse> createOrderItems(@Valid @RequestBody CreateOrderItemsRequest createOrderItemsRequest) {

        orderItemService.createOrderItems(createOrderItemsRequest.getCreateOrderItems());
        ItemResponse response = new ItemResponse();
        response.setCode(OrderItemsServiceConstants.CREATED);
        return new ResponseEntity<>( response, HttpStatus.CREATED);

    }

    @PostMapping("/retrieveorderitems")
    public ResponseEntity<ItemResponse<String,Map<String,String>>> retrieveOrderItems(@RequestBody RetrieveOrderItemsRequest retrieveOrderItemsRequest) {

        Map<String, String> productDetails = orderItemService.retrieveOrderItemsByProductCodes(retrieveOrderItemsRequest.getProductCodes());
        ItemResponse<String,Map<String,String>> itemResponse = new ItemResponse<>();
        itemResponse.setCode(OrderItemsServiceConstants.RETRIEVED);
        itemResponse.setData(productDetails);
        return new ResponseEntity<>(itemResponse, HttpStatus.OK);

    }
}
