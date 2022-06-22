package nl.hybrit.lightsaber.shop.controller;

import nl.hybrit.lightsaber.shop.controller.model.PadawanUserModel;
import nl.hybrit.lightsaber.shop.controller.model.request.LoginRequest;
import nl.hybrit.lightsaber.shop.controller.model.request.SignupRequest;
import nl.hybrit.lightsaber.shop.controller.model.response.JwtResponse;
import nl.hybrit.lightsaber.shop.controller.model.response.MessageResponse;
import nl.hybrit.lightsaber.shop.controller.model.response.UserPojo;
import nl.hybrit.lightsaber.shop.mapper.PadawanMapper;
import nl.hybrit.lightsaber.shop.mapper.UserMapper;
import nl.hybrit.lightsaber.shop.repository.RoleRepository;
import nl.hybrit.lightsaber.shop.repository.UserRepository;
import nl.hybrit.lightsaber.shop.repository.model.PadawanEntity;
import nl.hybrit.lightsaber.shop.repository.model.UserEntity;
import nl.hybrit.lightsaber.shop.security.jwt.JwtUtils;
import nl.hybrit.lightsaber.shop.security.service.MyUserDetails;
import nl.hybrit.lightsaber.shop.services.PadawanService;
import nl.hybrit.lightsaber.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private PadawanService padawanService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            UserEntity expectedUser = userService.findByEmail(loginRequest.getEmail());
            if (encoder.matches(loginRequest.getPassword(), expectedUser.getPassword())) {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtUtils.generateJwtToken(authentication);

                MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
                List<String> roles = userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList());

                return ResponseEntity.ok(new JwtResponse(jwt, new UserPojo(
                        userDetails.getUser().getId(),
                        userDetails.getUsername(),
                        userDetails.getUser().getEmail(),
                        roles)));
            } else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("incorrect password!"));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(ex.getMessage()));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody PadawanUserModel signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        PadawanEntity savedPadawan = padawanService.save(signUpRequest);
        return ResponseEntity.ok(new MessageResponse(String.format("%s has been registered successfully!", savedPadawan.getUser().getUsername())));
    }
}