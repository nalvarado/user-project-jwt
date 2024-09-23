package com.project.user.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {

    private String id;

    private Date created;

    private Date modified;

    @JsonProperty("last_login")
    private Date lastLogin;

    @JsonProperty("is_active")
    private boolean active;

    private String token;
}
