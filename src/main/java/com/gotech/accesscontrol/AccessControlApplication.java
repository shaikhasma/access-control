package com.gotech.accesscontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
@EnableSwagger2
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class AccessControlApplication {
  public static void main(String[] args) {
    SpringApplication.run(AccessControlApplication.class, args);
  }

  @Bean
  public Docket productApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(basePackage("com.gotech.accesscontrol.controller"))
        .build();
  }
}
