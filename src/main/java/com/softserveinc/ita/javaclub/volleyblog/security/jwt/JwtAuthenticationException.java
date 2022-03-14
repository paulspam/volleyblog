package com.softserveinc.ita.javaclub.volleyblog.security.jwt;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

/**
 * Authentication exception for JwtAppDemo application.
 *
 * @author Paul Smorodko
 * @version 1.0
 */

@Getter
public class JwtAuthenticationException extends AuthenticationException {
    private HttpStatus httpStatus;
    public JwtAuthenticationException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }
    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}
