package net.sharma.journalApp.controller;

import lombok.extern.slf4j.Slf4j;
import net.sharma.journalApp.entity.User;
import net.sharma.journalApp.repository.UserRepository;
import net.sharma.journalApp.service.UserDetailsServiceImpl;
import net.sharma.journalApp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("auth/google")
@Slf4j
public class GoogleAuthController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private String clientId;        // Your client id

    private String clientSecret;    // Your client Secret

    @GetMapping("/callback")
    public ResponseEntity<?> handleGoogleCallback(@RequestParam String code){

        try {
            // Exchange Authorization code with Access Token
            String tokenEndpoint = "https://oauth2.googleapis.com/token";

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("code",code);
            params.add("client_id",clientId);
            params.add("client_secret",clientSecret);
            params.add("redirect_uri","https://developers.google.com/oauthplayground");
            params.add("grant_type","authorization_code");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(params, headers);
            ResponseEntity<Map> tokenResponse = restTemplate.postForEntity(tokenEndpoint, request, Map.class);

            String tokenId = (String) tokenResponse.getBody().get("id_token");
            String userInfoUrl = tokenEndpoint + "info?id_token=" + tokenId;
            ResponseEntity<Map> userInfoResponse = restTemplate.getForEntity(userInfoUrl, Map.class);

            if(userInfoResponse.getStatusCode() == HttpStatus.OK){
                Map<String, Object> userInfo = userInfoResponse.getBody();

                String email = (String) userInfo.get("email");
                UserDetails userDetails = null;
                try{
                    userDetails = userDetailsService.loadUserByUsername(email);
                } catch (Exception e) {
                    User user = User.builder()
                            .username(email)
                            .password(passwordEncoder.encode(UUID.randomUUID().toString()))
                            .email(email)
                            .sentimentAnalysis(true)
                            .roles(Arrays.asList("USER"))
                            .build();

                    userRepository.save(user);
                }

                String jwtToken = jwtUtil.generateToken(email);
                return ResponseEntity.ok(Collections.singletonMap("token",jwtToken));
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        } catch (Exception e) {
            log.error("Exception occured while handling Google callback",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
