package com.management.hospital.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginResponseDto {

    String jwt;
    Long userId;

}
