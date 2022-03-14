package com.softserveinc.ita.javaclub.volleyblog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO class for authentication (login) request.
 *
 * @author Paul Smorodko
 * @version 1.0
 */

@Getter
@Setter
@NoArgsConstructor
public class AuthenticationRequestDto {
    private String username;
    private String password;
}
