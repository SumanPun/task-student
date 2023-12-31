package com.example.crudapp.crudapp.Payloads;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenRefreshResponse {

    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";

    public TokenRefreshResponse(String token, String requestRefreshToken) {
        this.accessToken = token;
        this.refreshToken = requestRefreshToken;
    }

//    public TokenRefreshResponse(String token, String requestRefreshToken) {
//        this.accessToken = token;
//        this.refreshToken = requestRefreshToken;
//    }
}
