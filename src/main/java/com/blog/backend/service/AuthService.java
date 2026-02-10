package com.blog.backend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.backend.dto.AuthResponse;
import com.blog.backend.dto.LoginRequest;
import com.blog.backend.dto.RegisterRequest;
import com.blog.backend.entity.Role;
import com.blog.backend.entity.User;
import com.blog.backend.repository.UserRepository;
import com.blog.backend.security.JwtService;

@Service
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}

	public AuthResponse register(RegisterRequest request) {

		if (userRepository.existsByEmail(request.getEmail())) {
			return new AuthResponse(null, "Email already registered!");
		}

		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(Role.USER);

		userRepository.save(user);

		String token = jwtService.generateToken(user.getEmail());
		return new AuthResponse(token, "User registered successfully!");
	}

	public AuthResponse login(LoginRequest request) {

		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new RuntimeException("Invalid email or password"));

		boolean isPasswordMatch = passwordEncoder.matches(request.getPassword(), user.getPassword());

		if (!isPasswordMatch) {
			throw new RuntimeException("Invalid email or password");
		}

		String token = jwtService.generateToken(user.getEmail());
		return new AuthResponse(token, "Login successful!");
	}
}
