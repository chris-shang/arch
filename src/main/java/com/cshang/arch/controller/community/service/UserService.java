package com.cshang.arch.controller.community.service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.cshang.arch.controller.community.model.User;

public interface UserService {

    CompletableFuture<Optional<User>> findUserByUserName(String userName);

    CompletableFuture<User> saveUser(User user);

}
