package io.xc.nativex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(proxyBeanMethods = false)
public class NativeApplication {

    public static void main(String[] args) {
        SpringApplication.run(NativeApplication.class, args);
    }

}
