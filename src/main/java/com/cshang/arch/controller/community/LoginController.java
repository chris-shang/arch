package com.cshang.arch.controller.community;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

    @GetMapping(path = "/login")
    public ResponseEntity login(
            @RequestParam("userName")
            String userName,
            @RequestParam("password")
            String password) {
        return ResponseEntity.ok().build();
    }
}
