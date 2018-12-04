package com.cshang.arch.controller.community.service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import javax.validation.Validator;

import com.cshang.arch.controller.community.model.User;
import com.cshang.arch.controller.community.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final Validator validator;

    public UserServiceImpl(UserRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public CompletableFuture<Optional<User>> findUserByHandle(String handle) {
        return CompletableFuture.completedFuture(
                Optional.ofNullable(repository.findByHandle(handle)));
    }
}
