package net.sharma.journalApp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sharma.journalApp.api.response.WeatherResponse;
import net.sharma.journalApp.dto.LoginUserDTO;
import net.sharma.journalApp.entity.User;
import net.sharma.journalApp.service.UserService;
import net.sharma.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@Tag(name = "User APIs")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    WeatherService weatherService;

    @PutMapping
    @Operation(summary = "update an existing user")
    public ResponseEntity<?> updateUser(@RequestBody LoginUserDTO userDTO){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User userInDb = userService.findByUsername(username);
        if(userInDb != null){
            userInDb.setUsername(userDTO.getUsername());
            userInDb.setPassword(userDTO.getPassword());
            userService.saveNewUser(userInDb);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping
    @Operation(summary = "delete an existing user")
    public ResponseEntity<?> deleteUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User userInDb = userService.findByUsername(username);
        if(userInDb != null){
            userService.deleteByUsername(username);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping
    @Operation(summary = "greeting to the user")
    public ResponseEntity<?> greeting(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        WeatherResponse weatherResponse = weatherService.getWeather("delhi");
        String greeting="";

        if(weatherResponse != null){
            greeting=" , today feels like "+weatherResponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hi "+ username + greeting, HttpStatus.OK);
    }
}
