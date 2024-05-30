package au.liw.wiki.controller;

import au.liw.wiki.domain.Test;
import au.liw.wiki.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {


    @Value("${test.hello:default}")
    private String testHello;

    @Autowired
    private TestService testService;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World: " + testHello;
    }

    @GetMapping("/test/list")
    public List<Test> list() {
        return testService.list();
    }
}
