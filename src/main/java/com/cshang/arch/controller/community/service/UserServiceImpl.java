package com.cshang.arch.controller.community.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.cshang.arch.controller.community.model.User;
import com.cshang.arch.controller.community.repository.UserRepository;
import com.cshang.arch.exception.ValidationError;
import com.cshang.arch.exception.ValidationException;

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
    public CompletableFuture<Optional<User>> findUserByUserName(String userName) {
        return CompletableFuture.completedFuture(
                Optional.ofNullable(repository.findByUserName(userName)));
    }

    @Override
    public CompletableFuture<User> saveUser(User user) {
        validate(user);
        return CompletableFuture.completedFuture(repository.save(user));
    }

    private void validate(User user) {
        Set<ConstraintViolation<User>> violations = new HashSet<>();
        violations.addAll(validator.validate(user));
        List<ValidationError> errors = violations.stream()
                .map(violation -> new ValidationError(violation.getMessage())
                        .withFieldName(violation.getPropertyPath().toString()))
                .collect(Collectors.toList());
        if (!errors.isEmpty()) {
            throw new ValidationException("Requested object is not valid", errors);
        }
    }
}
