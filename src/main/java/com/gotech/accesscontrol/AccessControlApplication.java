package com.gotech.accesscontrol;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

@EnableSwagger2 // enable to use swagger ui for this application
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class AccessControlApplication {
    @Value("${controller.path}")
    private String path;
  public static void main(String[] args) {
    SpringApplication.run(AccessControlApplication.class, args);
  }

  @Bean
  public Docket productApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(
            basePackage(
                path)) // base package set as a controller for ui
                                                        // documentation
        .build();
  }
}
