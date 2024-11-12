package com.SocialmediaAPI.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SocialmediaAPI.Services.CustomerUserDetailsService;
import com.SocialmediaAPI.Services.UserServiceImplementation;
import com.SocialmediaAPI.config.JwtProvider;
import com.SocialmediaAPI.models.User;
import com.SocialmediaAPI.repositry.UserRepositry;
import com.SocialmediaAPI.request.LoginRequest;
import com.SocialmediaAPI.response.AuthResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserServiceImplementation userServiceImplementation;

	@Autowired
	private UserRepositry userRepositry;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CustomerUserDetailsService customerUserDetailsService;

	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) throws Exception {

		User isExist = userRepositry.findByEmail(user.getEmail());

		if (isExist != null) {
			throw new Exception("This email already used in another account ");
		}

		User newUser = new User();

		newUser.setEmail(user.getEmail());
		newUser.setFirstname(user.getFirstname());
		newUser.setLastname(user.getLastname());
		newUser.setGender(user.getGender());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));

		User savedUser = userRepositry.save(newUser);

		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),
				savedUser.getPassword());

		String token = JwtProvider.generateToken(authentication);

		AuthResponse res = new AuthResponse(token, "Register Success....");

		return res;

	}

	@PostMapping("/signin")
	public AuthResponse signin(@RequestBody LoginRequest loginrequest) {

		Authentication authentication = authenticate(loginrequest.getEmail(), loginrequest.getPassword());

		String token = JwtProvider.generateToken(authentication);

		AuthResponse res = new AuthResponse(token, "Login Success....");

		return res;
	}

	private Authentication authenticate(String email, String password) {

		UserDetails userDetails = customerUserDetailsService.loadUserByUsername(email);

		if (userDetails == null) {
			throw new BadCredentialsException("Invalid username");
		}
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Wrong password");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

}
