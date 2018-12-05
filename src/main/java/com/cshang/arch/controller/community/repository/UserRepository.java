package com.cshang.arch.controller.community.repository;

import com.cshang.arch.controller.community.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);
}
