package com.oms.orderitems.exception;

import com.oms.orderitems.constant.OrderItemsServiceConstants;
import com.oms.orderitems.model.ErrorOrderItemResponse;
import com.oms.orderitems.model.ItemResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(OrderItemsException.class)
    public final ResponseEntity<ItemResponse<String, String>> orderItemsException(OrderItemsException ex){


        ex.printStackTrace();
        ItemResponse<String, String> response = new ItemResponse<>();
        response.setData(ex.getMessage());
        response.setCode("ERR001");
        return new ResponseEntity<ItemResponse<String, String>>(response, HttpStatus.OK);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        List<String> validationList = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        ErrorOrderItemResponse errorOrderItemResponse = new ErrorOrderItemResponse();
        errorOrderItemResponse.setMessage(OrderItemsServiceConstants.INPUT_VALIDATION_ERROR);
        errorOrderItemResponse.setErrorList(validationList);
        return new ResponseEntity<>(errorOrderItemResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ItemResponse<String, String>> systemException(Exception ex){

        ItemResponse<String, String> response = new ItemResponse<>();
        response.setData(ex.getMessage());
        response.setCode("ERR002");
        ex.printStackTrace();
        return new ResponseEntity<ItemResponse<String, String>>(response,HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
