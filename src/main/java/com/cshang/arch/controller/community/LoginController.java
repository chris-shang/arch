package com.cshang.arch.controller.community;

import java.util.Optional;

import com.cshang.arch.controller.community.model.LoginRequest;
import com.cshang.arch.controller.community.model.User;
import com.cshang.arch.controller.community.service.UserService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/login")
    public ResponseEntity login(
            @RequestBody
            LoginRequest request) {

        String handle = request.getHandle();
        if (StringUtils.isBlank(handle)) {
            //TO DO
            // define the exception then throw
        }

        Optional<User> userOptional = userService.findUserByHandle(handle).join();
        return userOptional.isPresent() ? ResponseEntity.ok(userOptional.get())
                : ResponseEntity.noContent().build();
    }
}
