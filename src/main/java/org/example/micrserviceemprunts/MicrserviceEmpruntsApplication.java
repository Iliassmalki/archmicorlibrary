package org.example.micrserviceemprunts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "org.example.micrserviceemprunts.feign")
@SpringBootApplication
public class MicrserviceEmpruntsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicrserviceEmpruntsApplication.class, args);
    }

}
