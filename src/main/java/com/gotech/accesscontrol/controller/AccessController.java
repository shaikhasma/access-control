package com.gotech.accesscontrol.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessController {
  @GetMapping("/getAll")
  public String getAllResources() {
    return "get method which returns all resources";
  }

  @GetMapping("/get/{id}")
  public String getResource(@PathVariable int id) {
    return "get method which returns single resource by id";
  }
}
