package com.rms.security;
import com.rms.rest.handler.UserHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
@Log4j2
public class AuthHandler {

    private final AuthenticationManager authenticationManager;

    private final TokenProvider tokenProvider;
    @Value("${app.jwt.token.expiration-in-ms}")
    private Long tokenExpirationMillis;

    @Value("${app.jwt.refresh.expiration-in-ms}")
    private Long refreshTokenExpirationMillis;


    @Autowired
    private UserHandler userHandler;



    public ResponseEntity<?> login(LoginRequestDto loginRequest) {
        log.info("start login for user {}", loginRequest.getUsername());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        HttpHeaders responseHeaders = new HttpHeaders();
        String accessToken = tokenProvider.generateAccessToken(loginRequest.getUsername());
        String refreshToken = tokenProvider.generateRefreshToken(loginRequest.getUsername());
        return ResponseEntity.ok().body(new LoginResponseDto(accessToken, refreshToken));
    }


    public ResponseEntity<?> refresh(RefreshTokenRequestDto dto) {
        Boolean refreshTokenValid = tokenProvider.validateToken(dto.getRefreshToken());
        if (!refreshTokenValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String currentUsername = tokenProvider.getUsernameFromToken(dto.getRefreshToken());

        String newAccessToken = tokenProvider.generateAccessToken(currentUsername);
        HttpHeaders responseHeaders = new HttpHeaders();

        return ResponseEntity.ok().body(new LoginResponseDto(newAccessToken, dto.getRefreshToken()));
    }


    public ResponseEntity<?> getUserInfo(UserDetails userDetails) {
        UserDto userDto = UserDto.builder().username(userDetails.getUsername()).authorities((Set<GrantedAuthority>) userDetails.getAuthorities()).build();

        return ResponseEntity.ok(userDto);
    }

}