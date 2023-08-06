package com.example.demo.common.security.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserToken {

    @JsonProperty("account_id")
    private Long accountId;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("roles")
    private Set<String> listRole;
    private String username;
    private String email;
    private String studentCode;
}
