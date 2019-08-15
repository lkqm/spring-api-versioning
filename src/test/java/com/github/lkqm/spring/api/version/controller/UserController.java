package com.github.lkqm.spring.api.version.controller;

import com.github.lkqm.spring.api.version.ApiVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@ApiVersion
public class UserController {

    @GetMapping("/list")
    public String list() {
        return "list";
    }

    @GetMapping("/list")
    @ApiVersion(2)
    public String list2() {
        return "list2";
    }

}
