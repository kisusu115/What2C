package com.mop.what2c.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class W2cController {
    @GetMapping("/example")
    public String Example() {
        return "example";
    }
}