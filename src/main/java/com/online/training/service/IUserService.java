package com.online.training.service;

import com.online.training.table.model.User;

import java.util.List;

public interface IUserService {
    User save(User user);
    List<User> findAll();
    void delete(long id);
    User findOne(String username);
    User findById(Long id);

}
