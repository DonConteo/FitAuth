package com.TsoyDmitriy.FitAuth.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserDTO {

    private String username;
    private String password;
}
