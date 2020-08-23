package com.oms.orderitems;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class OrderItemsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderItemsServiceApplication.class, args);
	}

	@Bean
	public ModelMapper getBean(){
		return new ModelMapper();
	}


}
