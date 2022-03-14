package com.softserveinc.ita.javaclub.volleyblog.security.jwt;

import com.softserveinc.ita.javaclub.volleyblog.model.Status;
import com.softserveinc.ita.javaclub.volleyblog.model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.Date;

/**
 * Implementation of Factory Method for class {@link JwtUser}.
 *
 * @author Paul Smorodko
 * @version 1.0
 */

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getUserId(),
                user.getUserName(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getStatus().equals(Status.ACTIVE),
                new Date(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getRoleName()))
        );
    }
}
