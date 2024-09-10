package com.wexad.spring_security_jwt.controller;

import com.wexad.spring_security_jwt.config.JwtTokenUtil;
import com.wexad.spring_security_jwt.dto.TokenRequestDTO;
import com.wexad.spring_security_jwt.model.AuthUser;
import com.wexad.spring_security_jwt.repository.AuthUserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtTokenUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final AuthUserRepository authUserRepository;

    public AuthController(JwtTokenUtil jwtUtil, AuthenticationManager authenticationManager1, PasswordEncoder passwordEncoder, AuthUserRepository authUserRepository) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager1;
        this.passwordEncoder = passwordEncoder;
        this.authUserRepository = authUserRepository;
    }

    @PostMapping("/register")
    public TokenRequestDTO register(@RequestBody TokenRequestDTO user) {
        authUserRepository.save(AuthUser.builder()
                .username(user.password())
                .password(passwordEncoder.encode(user.password()))
                .role("USER")
                .build());
        return user;
    }

    @PostMapping("/token")
    public String getToken(@RequestBody TokenRequestDTO tokenRequestDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(tokenRequestDTO.username(), tokenRequestDTO.password());
        authenticationManager.authenticate(authenticationToken);
        return jwtUtil.generateToken(tokenRequestDTO.username());
    }
}
