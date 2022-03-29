package com.softserveinc.ita.javaclub.volleyblog.controller;

import com.softserveinc.ita.javaclub.volleyblog.dto.AuthenticationRequestDto;
import com.softserveinc.ita.javaclub.volleyblog.exception.RecordNotFoundException;
import com.softserveinc.ita.javaclub.volleyblog.model.User;
import com.softserveinc.ita.javaclub.volleyblog.security.jwt.JwtTokenProvider;
import com.softserveinc.ita.javaclub.volleyblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * REST controller for authentication requests (login, logout, register, etc.)
 *
 * @author Paul Smorodko
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) throws RecordNotFoundException {

        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,
                    requestDto.getPassword()));
            User user = userService.findByUserName(username);
            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }
            String token = jwtTokenProvider.createToken(username, user.getRole());
            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
//            return new ResponseEntity<>("Invalid username/password combination", HttpStatus.FORBIDDEN);
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request,response, null);
    }
}
