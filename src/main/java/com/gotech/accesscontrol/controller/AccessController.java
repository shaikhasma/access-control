package com.gotech.accesscontrol.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/access-control")
public class AccessController {
    @GetMapping("/getAll")
    public ResponseEntity<String> getAllResources() {
        return new ResponseEntity<>("get method which returns all resources", HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<String> getResource(@PathVariable int id) {
        return new ResponseEntity<>("get method which returns single resource by id", HttpStatus.OK);
    }

    @PostMapping("/set")
    public ResponseEntity<String> postResource() {
        return new ResponseEntity<>(
                "post method which sends resource from client", HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updateResource(@PathVariable int id) {
        return new ResponseEntity<>("patch method to update the resource", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteResource(@PathVariable int id) {
        return new ResponseEntity<>("delete method which deletes resources", HttpStatus.OK);
    }
}
