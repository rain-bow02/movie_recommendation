package com.example.shopserve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Properties;

@SpringBootApplication
public class ShopserveApplication {

    public static void main(String[] args) {

        Properties properties = System.getProperties();
        // 用 select 1 替换 ping 来检测连接保活
        properties.setProperty("druid.mysql.usePingMethod", "false");
        SpringApplication.run(ShopserveApplication.class, args);
    }

}
