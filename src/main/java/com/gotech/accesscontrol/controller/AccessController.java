package com.gotech.accesscontrol.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/access-control")
public class AccessController { // REST controller for access control
  @GetMapping("/getAll") // GET method to access all resources
  public ResponseEntity<String> getAllResources() {
    return new ResponseEntity<String>("get method which returns all resources", HttpStatus.OK);
  }

  @GetMapping("/get/{id}") // GET method to get a single resource by id
  public ResponseEntity<String> getResource(@PathVariable int id) {
    return new ResponseEntity<String>(
        "get method which returns single resource by id", HttpStatus.OK);
  }

  @PostMapping("/set") // POST method to insert resource
  public ResponseEntity<String> postResource() {
    return new ResponseEntity<String>(
        "post method which sends resource from client", HttpStatus.OK);
  }

  @PutMapping("/update/{id}") // PUT method to update the existing resource
  public ResponseEntity<String> updateResource(@PathVariable int id) {
    return new ResponseEntity<String>("put method to update the resource", HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}") // DELETE method to delete the resource by id
  public ResponseEntity<String> deleteResource(@PathVariable int id) {
    return new ResponseEntity<String>("delete method which deletes resources", HttpStatus.OK);
  }
}
