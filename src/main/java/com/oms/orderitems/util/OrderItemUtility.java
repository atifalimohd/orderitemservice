package com.oms.orderitems.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oms.orderitems.entity.OrderItemEntity;
import com.oms.orderitems.model.OrderItem;
import net.bytebuddy.utility.RandomString;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderItemUtility {

    @Autowired
    ModelMapper modelMapper;

    public OrderItemEntity convertModelToEntity(OrderItem orderItem){

        return  modelMapper.map(orderItem,OrderItemEntity.class);
    }

    public OrderItem convertEntityToModel(OrderItemEntity orderItemEntity){

        return  modelMapper.map(orderItemEntity,OrderItem.class);
    }

    public List<OrderItem> convertEntityToModel(List<OrderItemEntity> orderItemEntities){

        return  orderItemEntities.stream().map(orderItemEntity -> modelMapper.map(orderItemEntity,OrderItem.class)).collect(Collectors.toList());

    }

}
