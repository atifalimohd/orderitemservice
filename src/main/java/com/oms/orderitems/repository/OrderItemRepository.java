package com.oms.orderitems.repository;

import com.oms.orderitems.entity.OrderItemEntity;
import com.oms.orderitems.projections.OrderItemProjections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity,String> {

   // @Query(value = "select orditem from OrderItemEntity orditem where orditem.productCode in :productCodes")
    List<OrderItemProjections> findByProductCodeIn(List<String> productCodes);


    /*@Query(value = "select orditem from OrderItemEntity orditem where orditem.productCode=:productCode")
    //public OrderItemEntity findById(@Param("productCode") String productCode);*/
}
