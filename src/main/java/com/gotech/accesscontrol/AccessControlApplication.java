package com.gotech.accesscontrol;

import com.gotech.accesscontrol.config.Log4j2Config;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AccessControlApplication {
 private static final Logger mainLogger=Log4j2Config.getLoggerVariable("AccessControlApplication");
  public static void main(String[] args) {
    SpringApplication.run(AccessControlApplication.class, args);
    mainLogger.info("main loaded successfully");
  }
}
