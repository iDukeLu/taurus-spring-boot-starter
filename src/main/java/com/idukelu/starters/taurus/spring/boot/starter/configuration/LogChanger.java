package com.idukelu.starters.taurus.spring.boot.starter.configuration;

import com.idukelu.starters.taurus.spring.boot.starter.util.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author duke
 */
@RestController("/loggers")
public class LogChanger {

    private static final String LOGGER_ACTUATOR_PATH = "/actuator/loggers/";

    private static final List<String> LOGGER_LEVEL = Arrays.asList("OFF", "ERROR", "WARN", "INFO", "DEBUG", "TRACE");

    private RestTemplate restTemplate;

    private HttpServletRequest request;

    @Autowired
    private LogChanger(HttpServletRequest request, RestTemplate restTemplate) {
        this.request = request;
        this.restTemplate = restTemplate;
    }

    @GetMapping()
    public void loggers() {

    }

    @GetMapping("/{pack}")
    public void loggers(@PathVariable String pack) {

    }

    @GetMapping("/{level}/{pack}")
    public void off(@PathVariable String level, @PathVariable String pack) {
        String lv = level.toUpperCase();
        if (!LOGGER_LEVEL.contains(lv)) {

        }


        String url = UrlUtils.getRequestAddress(request) + LOGGER_ACTUATOR_PATH;
        HashMap<String, String> body = new HashMap<>();
        body.put(pack, level);

        restTemplate.postForEntity(url, new HttpEntity<>(body), null);
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
