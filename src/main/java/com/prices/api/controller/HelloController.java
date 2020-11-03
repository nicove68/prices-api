package com.prices.api.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO: delete when the develop finished
@RestController
@RequestMapping("/hello")
public class HelloController {


  @GetMapping
  public Map<String, String> hello() {
    Map<String, String> response = new HashMap<>();
    response.put("hello", "world");
    return response;
  }

}