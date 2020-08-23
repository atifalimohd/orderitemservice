package com.oms.orderitems.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ErrorOrderItemResponse implements Serializable {

    private String message;
    private List<String> errorList;


    //private String code;



}
