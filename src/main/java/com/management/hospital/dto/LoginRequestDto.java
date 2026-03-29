package com.management.hospital.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoginRequestDto {
    private String username;
    private String password;



}
