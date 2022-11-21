package kr.co.direa.cruzlinksimulator.controller;

import kr.co.direa.cruzlinksimulator.dto.Test;
import kr.co.direa.cruzlinksimulator.service.SimulService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class SimulRestController {

    private final SimulService simulService;

    @GetMapping("/test")
    public Test test(@RequestBody @Valid Test test) throws Exception {
        System.out.println(test);
        simulService.start(test);
        return null;
    }
}
