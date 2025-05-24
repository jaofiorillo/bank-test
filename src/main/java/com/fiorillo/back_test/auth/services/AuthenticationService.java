package com.fiorillo.back_test.auth.services;

import com.fiorillo.back_test.auth.dto.JwtResponse;
import com.fiorillo.back_test.auth.dto.LoginRequest;
import com.fiorillo.back_test.auth.jwt.JwtUtils;
import com.fiorillo.back_test.modules.user.model.User;
import com.fiorillo.back_test.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;

    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        try {
            var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.userName(), loginRequest.password()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            var jwt = jwtUtils.generateJwtToken(authentication);
            var userDetails = (UserDetailsImpl) authentication.getPrincipal();

            return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername()));
        } catch (BadCredentialsException ex) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "Credenciais inválidas"));
        }
    }

    public User getAuthenticatedUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() &&
            authentication.getPrincipal() instanceof UserDetailsImpl userDetails) {

            return userRepository.findByUserNameAndPassword(userDetails.getUsername(), userDetails.getPassword())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        }

        return null;
    }
}
