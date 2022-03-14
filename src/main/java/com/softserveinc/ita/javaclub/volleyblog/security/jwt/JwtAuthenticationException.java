package com.softserveinc.ita.javaclub.volleyblog.security.jwt;

import org.springframework.security.core.AuthenticationException;

/**
 * Authentication exception for JwtAppDemo application.
 *
 * @author Paul Smorodko
 * @version 1.0
 */

public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}
