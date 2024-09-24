package com.project.user.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class PhoneRequestDTO {

    private String number;

    private String cityCode;

    private String countryCode;
}
