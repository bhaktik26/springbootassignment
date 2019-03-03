package com.online.training.controller;

import com.online.training.model.AuthToken;
import com.online.training.model.User;
import com.online.training.service.TokenProvider;
import com.online.training.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody User loginUser) throws AuthenticationException {
        
        final com.online.training.table.model.User user = userService.findByUsernameAndRoleAndPassword(loginUser.getUsername(),
                loginUser.getRole(),
                loginUser.getPassword());

        logger.info("FOUND USER = {}", user);
        final String token = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(new AuthToken(token, user.getUsername(), user.getId(), user.getRole()));
    }
}
