package com.online.training.controller;

import com.online.training.model.NewUser;
import com.online.training.model.Result;
import com.online.training.model.User;
import com.online.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public boolean login(@RequestBody User user) {
        return
                user.getUsername().equals("user") && user.getPassword().equals("password");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody NewUser newUser) {
         userService.register(newUser);
         return ResponseEntity.ok(new Result("SUCCESS"));
    }
}
