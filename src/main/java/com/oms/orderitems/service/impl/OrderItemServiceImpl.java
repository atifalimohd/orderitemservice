package com.oms.orderitems.service.impl;

import com.oms.orderitems.constant.OrderItemsServiceConstants;
import com.oms.orderitems.entity.OrderItemEntity;
import com.oms.orderitems.exception.OrderItemsException;
import com.oms.orderitems.model.OrderItem;
import com.oms.orderitems.projections.OrderItemProjections;
import com.oms.orderitems.repository.OrderItemRepository;
import com.oms.orderitems.util.OrderItemUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderItemUtility orderItemUtility;

   @Override
    public  Map<String,String> retrieveOrderItemsByProductCodes(List<String> productCodes) {

        List<OrderItemProjections> productItems = orderItemRepository.findByProductCodeIn(productCodes);
        Map<String,String>  productDetails = new HashMap<>();

       productItems.forEach( items ->{
            productDetails.put(items.getProductCode(),items.getProductName());
        });
        return productDetails;
    }

    @Override
    public String createOrderItems(List<OrderItem> orderItems) {

        List<OrderItemEntity> orderItemEntities = new ArrayList<>();

        orderItems.forEach(orderItem1 -> {
            Optional<OrderItemEntity> orderItemEntityFromDB = orderItemRepository.findById(orderItem1.getProductCode());
            if (orderItemEntityFromDB.isPresent()) {
                if (orderItemEntityFromDB.get().getQuantity() >= orderItem1.getQuantity()) {
                    orderItemEntityFromDB.get().setQuantity(orderItemEntityFromDB.get().getQuantity() - orderItem1.getQuantity());
                    orderItemEntities.add(orderItemEntityFromDB.get());
                } else {
                    throw new OrderItemsException(OrderItemsServiceConstants.LOW_INVENTORY_MESSAGE + orderItem1.getProductCode() + " is only " + orderItemEntityFromDB.get().getQuantity());
                }
            } else {
                throw new OrderItemsException(OrderItemsServiceConstants.INVALID_PRODUCT_CODE + orderItem1.getProductCode());
            }
        });
        //update the inventory
        updateInventory(orderItemEntities);
        return OrderItemsServiceConstants.SUCCESS;

    }

    private void updateInventory(List<OrderItemEntity> orderItemEntities) {

        for(OrderItemEntity orderItemEntity: orderItemEntities){
            orderItemRepository.save(orderItemEntity);
        }

    }

}
