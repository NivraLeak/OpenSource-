package com.acme.ideogo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VersionController {
    @RequestMapping(method={RequestMethod.GET},value={"/version"})
    @GetMapping("/version")
    public String getVersion() {
        return "1.0";
    }
}

