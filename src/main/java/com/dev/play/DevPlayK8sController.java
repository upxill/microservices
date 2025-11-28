package com.dev.play;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DevPlayK8sController {
    @GetMapping("/hello")
    public String hello() {
        return "working on k8s...";
    }
}

