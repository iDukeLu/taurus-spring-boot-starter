package com.idukelu.starters.taurus.spring.boot.starter.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController("/logger")
public class LogChanger {

    private RestTemplate restTemplate;

    @Autowired
    private LogChanger(@Qualifier("ok3RestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/off")
    public void off() {
    }

    @GetMapping("/error")
    public void error() {

    }

    @GetMapping("/warn")
    public void warn() {

    }

    @GetMapping("/info")
    public void info() {

    }

    @GetMapping("/debug")
    public void debug() {

    }

    @GetMapping("/trace")
    public void trace() {

    }
}
