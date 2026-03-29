package com.management.hospital.security;

import com.management.hospital.dto.LoginRequestDto;
import com.management.hospital.dto.LoginResponseDto;
import com.management.hospital.dto.SignUpResponseDto;
import com.management.hospital.entity.User;
import com.management.hospital.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername() ,  // ✅ correct,
                        loginRequestDto.getPassword()
                )
        );

        User user = (User) authentication.getPrincipal();
        String token = authUtil.generateAccessToken(user);

        return new LoginResponseDto(token, user.getId()); // ✅ FIXED
    }

    public SignUpResponseDto signup(LoginRequestDto signupRequestDto) {
        User user = userRepository.findByUsername(signupRequestDto.getUsername()).orElse(null);
        if(user != null) throw new IllegalArgumentException("User already exception");

        user = userRepository.save(User.builder().username(signupRequestDto.getUsername()).password(passwordEncoder.encode(signupRequestDto.getPassword())).build());
        return new SignUpResponseDto(user.getId(),user.getUsername());
    }
}