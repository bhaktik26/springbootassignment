package com.online.training.service;

import com.online.training.model.NewUser;
import com.online.training.model.Result;
import com.online.training.repository.UserRepository;
import com.online.training.table.model.User;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "userService")
public class UserService implements UserDetailsService, IUserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserRepository userRepository;


    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public User register(NewUser newUser) {
        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setPassword(bcryptEncoder.encode(newUser.getPassword()));
        user.setRole(newUser.getRole());
        return save(user);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(long id) {
        //userRepository.deleteById(id);
    }

    @Override
    public User findOne(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(Long id) {
        //return userRepository.findById(id).get();
    	return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public User findByUsername(String username) {
        User user =  findOne(username);
        logger.info("USERNAME = {}", username);
        return user;
    }
    public User findByUsernameAndRoleAndPassword(String username, String role, String password) {
        User user = userRepository.findByUsernameAndRole(username, role);
        if (user != null && bcryptEncoder.matches(password, user.getPassword()))
            return user;
        return null;
    }
}
