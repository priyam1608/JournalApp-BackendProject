package net.sharma.journalApp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.sharma.journalApp.dto.LoginUserDTO;
import net.sharma.journalApp.dto.SignUpUserDTO;
import net.sharma.journalApp.entity.User;
import net.sharma.journalApp.service.UserDetailsServiceImpl;
import net.sharma.journalApp.service.UserService;
import net.sharma.journalApp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("public")
@Slf4j
@Tag(name = "Public APIs")
public class PublicController {

    @Autowired
    UserService userService;

    @Autowired
    @Qualifier("utilAuthManager")
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("/health-check")
    @Operation(summary = "check server status")
    public String healthCheck(){
        return "OK";
    }

    @PostMapping("/signup")
    @Operation(summary = "create new user credentials")
    public void signUp(@RequestBody SignUpUserDTO userDTO){
        User user = User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .sentimentAnalysis(userDTO.getSentimentAnalysis())
                .build();
        userService.saveNewUser(user);
    }

    @PostMapping("/login")
    @Operation(summary = "login with existing user credentials")
    public ResponseEntity<String> login(@RequestBody LoginUserDTO userDTO){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(userDTO.getUsername());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e){
            log.error("Exception occurred while createAuthenticationToken ", e);
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }
}
