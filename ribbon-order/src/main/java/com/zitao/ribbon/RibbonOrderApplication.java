package com.zitao.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@RibbonClients(value = {
//        @RibbonClient(name = "stock-nacos", configuration = RibbonRandomRule.class),
//        @RibbonClient(name = "ribbon-stock2", configuration = RibbonRandomRule.class)
//})
public class RibbonOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(RibbonOrderApplication.class);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }
}
