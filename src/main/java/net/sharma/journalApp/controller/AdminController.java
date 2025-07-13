package net.sharma.journalApp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sharma.journalApp.dto.SignUpUserDTO;
import net.sharma.journalApp.entity.User;
import net.sharma.journalApp.service.UserService;
import net.sharma.journalApp.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
@Tag(name = "Admin APIs")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    UtilityService utilityService;

    @GetMapping("all-users")
    @Operation(summary = "get all registered users")
    public ResponseEntity<?> getAllUsers(){
        List<User> users = userService.getAll();
        if(users != null && !users.isEmpty()){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("/get-user/{username}")
    @Operation(summary = "get a username")
    public User getUserByUsername(@PathVariable String username){
        return userService.findByUsername(username);
    }

    @PostMapping("create-admin-user")
    @Operation(summary = "Create a new admin user")
    public void createNewAdmin(@RequestBody SignUpUserDTO userDTO){
        User user = User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .sentimentAnalysis(userDTO.getSentimentAnalysis())
                .build();

        userService.saveNewAdmin(user);
    }

    @GetMapping("clear-app-cache")
    @Operation(summary = "Clear In-App Cache Memory")
    public void clearAppCache(){
        utilityService.init();
    }
}
