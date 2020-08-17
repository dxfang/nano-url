package com.nanourl;

import com.nanourl.util.BaseConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NanoUrlApplication {

    public static void main(String[] args) {
        SpringApplication.run(NanoUrlApplication.class, args);
    }

    @Bean
    public BaseConverter baseConverter() {
        return new BaseConverter();
    }
}
