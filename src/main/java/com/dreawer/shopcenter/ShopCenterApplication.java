package com.dreawer.shopcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ShopCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopCenterApplication.class, args);
    }

    @Bean // 定义REST客户端，RestTemplate实例
    @LoadBalanced // 开启负载均衡的能力
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(1500);// 设置超时
        requestFactory.setReadTimeout(2000);
        return new RestTemplate(requestFactory);
    }
}
