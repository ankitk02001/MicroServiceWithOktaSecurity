package com.ankit;

import com.ankit.external.services.RatingService;
import com.ankit.runner.CreateRatingClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class Application {
public static void main(String[] args) {

	ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
	CreateRatingClass bean = context.getBean(CreateRatingClass.class);
	//bean.createRating();//sending post to RATING SERVICETO CREATE RATING IN RATING CLASS

}





}
