package com.oms.orderitems.projections;


public class OrderItemProjections {

    private String productCode;
    private String productName;

    public OrderItemProjections(String productCode, String productName){
        this.productCode = productCode;
        this.productName = productName;
    }

    public String getProductCode(){
        return productCode;
    }

    public String getProductName(){
        return productName;
    }
}
