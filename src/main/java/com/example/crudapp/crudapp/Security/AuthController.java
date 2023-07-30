package com.example.crudapp.crudapp.Security;

import com.example.crudapp.crudapp.Entity.RefreshToken;
import com.example.crudapp.crudapp.Exceptions.RefreshTokenException;
import com.example.crudapp.crudapp.Payloads.RefreshTokenRequest;
import com.example.crudapp.crudapp.Payloads.TokenRefreshResponse;
import com.example.crudapp.crudapp.Services.RefreshTokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.impl.DefaultClaims;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        //RefreshToken refreshToken = refreshTokenService.createRefrehToken() .createRefreshToken(userDetails.getId());

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(HttpServletRequest request) throws Exception {
        // From the HttpRequest get the claims
        DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");

        Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
        String token = helper.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
        //return ResponseEntity.ok(new AuthenticationResponse(token));
        return ResponseEntity.ok(""+token);
    }

    public Map<String, Object> getMapFromIoJsonwebtokenClaims( DefaultClaims claims) {
        Map<String, Object> expectedMap = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            expectedMap.put(entry.getKey(), entry.getValue());
        }
        return expectedMap;
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }


//    @PostMapping("/refreshToken")
//    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
//        String requestRefreshToken = request.getRefreshToken();
//
//        return this.refreshTokenService.findByToken(requestRefreshToken)
//                .map(refreshTokenService::verifyExpiration)
//                .map(RefreshToken::getStudent)
//                .map((student)-> {
//                    String token = helper.getUsernameFromToken(student.getUsername());
//                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
//                })
//                .orElseThrow(()-> new RefreshTokenException(requestRefreshToken,"refresh token is not in database"));
//    }

}
