package com.rms.security;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;


@Builder
@Getter
public class UserDto {

    private String username;
    private Set<GrantedAuthority> authorities;

}
