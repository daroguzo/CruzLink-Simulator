package kr.co.direa.cruzlinksimulator.controller;

import kr.co.direa.cruzlinksimulator.dto.Test;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/fixed")
public class FixedRestController {

    @PostMapping("/client")
    public Test roleClient(@RequestBody Test data) {

        return null;
    }

    @PostMapping("/server")
    public Test roleServer(@RequestBody Test data) {

        return null;
    }
}
