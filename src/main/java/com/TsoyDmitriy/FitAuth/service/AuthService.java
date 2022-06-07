package com.TsoyDmitriy.FitAuth.service;

import com.TsoyDmitriy.FitAuth.dto.UserDTO;
import com.TsoyDmitriy.FitAuth.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final RoleService roleService;
    private final UserService userService;

    public void registration(UserDTO userDTO) {
        userService.checkLoginDuplicate(userDTO.getUsername());
        User user = new User(userDTO.getUsername(), userDTO.getPassword());
        userService.registerUser(user, roleService.findById(1L));
    }
}
