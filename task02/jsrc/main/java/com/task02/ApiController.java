package com.task02;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class ApiController {

    @GetMapping("hello")
    Map<String, Object> getMethod() {
        System.out.println("Hello from lambda");
        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("statusCode", 200);
        resultMap.put("message", "Hello from Lambda");
        return resultMap;
    }

    @GetMapping
    Map<String, Object> helloMethod() {
        System.out.println("Hello from lambda");
        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("statusCode", 200);
        resultMap.put("message", "Hello from Lambda");
        return resultMap;
    }
}
