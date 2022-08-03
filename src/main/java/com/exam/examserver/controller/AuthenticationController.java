package com.exam.examserver.controller;

import com.exam.examserver.config.JwtUtils;
import com.exam.examserver.entity.User;
import com.exam.examserver.entity.dto.LoginRequest;
import com.exam.examserver.entity.dto.LoginResponse;
import com.exam.examserver.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsImpl userDetails;

    @Autowired
    JwtUtils jwtUtils;

//    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/")
    public ResponseEntity<?> generateToken(@RequestBody LoginRequest loginRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),
                    loginRequest.getPassword()));
            final UserDetails user= userDetails.loadUserByUsername(loginRequest.getUserName());
            String jwt = jwtUtils.generateToken(user);
            return ResponseEntity.ok().body(new LoginResponse(jwt));
        } catch (RuntimeException badCredentialsException) {
            System.out.println(badCredentialsException.getMessage());
        }
        return  ResponseEntity.badRequest().body("Error");
    }

    @GetMapping("/")
    public User getCurrentUser(Principal principal)
    {
        return (User) this.userDetails.loadUserByUsername(principal.getName());
    }
//    private void authenticate(String userName,String password){
//        try{
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,password));
//        }
//        catch (DisabledException e){
//            System.out.println("User is disabled ");;
//        }
//        catch (BadCredentialsException e){
//            System.out.println("Invalid Credentials");
//        }
//    }
}
