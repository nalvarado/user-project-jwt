package com.project.user.domains;

import com.project.user.model.PhoneModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Builder
@Getter
@Setter
@AllArgsConstructor
public class UserRequestDTO {

    @NotBlank(message = "Attribute 'email' should not be empty.")
    @Email(message = "Invalid email format.", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;

    @NotBlank(message = "Attribute 'password' should not be empty.")
    private String password;

    @NotBlank(message = "Attribute 'password' should not be empty.")
    private String name;

    private List<PhoneModel> phones;

}
