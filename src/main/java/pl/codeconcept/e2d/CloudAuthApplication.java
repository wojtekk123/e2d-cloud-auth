package pl.codeconcept.e2d;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableDiscoveryClient

public class CloudAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudAuthApplication.class, args);
    }

}
