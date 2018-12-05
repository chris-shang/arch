package com.cshang.arch.controller.community;

import java.util.Optional;

import com.cshang.arch.controller.community.model.LoginRequest;
import com.cshang.arch.controller.community.model.Password;
import com.cshang.arch.controller.community.model.UpdateUserRequest;
import com.cshang.arch.controller.community.model.User;
import com.cshang.arch.controller.community.service.UserService;
import com.cshang.arch.exception.ApiErrorName;
import com.cshang.arch.exception.ApiException;
import com.cshang.arch.exception.ValidationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/login")
    public ResponseEntity<User> login(
            LoginRequest request) {

        String userName = request.getUserName();
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(request.getPassword())) {
            throw new ApiException(ApiErrorName.InvalidInput, "the userName/password is required");
        }
        Optional<User> userOptional = userService.findUserByUserName(userName).join();
        if (!userOptional.isPresent()) {
            throw new ApiException(ApiErrorName.InvalidInput,
                    "couldn't find a user with input user name");
        }
        User user = userOptional.get();
        Password password = user.getPassword();
        String encryptPassword = Password.encryptPasswordWithBCrypt
                (password.getSalt(), request.getPassword());
        if (!password.getEncryptedPassword().equals(encryptPassword)) {
            throw new ValidationException("username/password is not correct");
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<User> register(
            @ModelAttribute
            UpdateUserRequest request) {
        User user = new User();
        request.applyTo(user);
        User persistedUser = userService.saveUser(user).join();
        return ResponseEntity.ok(persistedUser);
    }

}
