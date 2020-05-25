package com.github.lkqm.spring.api.version.demo;

import com.github.lkqm.spring.api.version.ApiVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@ApiVersion
public class UserController {

    @GetMapping("/list")
    public String listV1() {
        return "list v1";
    }

    @GetMapping("/list")
    @ApiVersion(2)
    public String listV2() {
        return "list v2";
    }

}
