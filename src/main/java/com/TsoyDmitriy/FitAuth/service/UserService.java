package com.TsoyDmitriy.FitAuth.service;

import com.TsoyDmitriy.FitAuth.config.jwt.JwtProvider;
import com.TsoyDmitriy.FitAuth.model.Role;
import com.TsoyDmitriy.FitAuth.model.User;
import com.TsoyDmitriy.FitAuth.repo.UserRepo;
import com.TsoyDmitriy.FitAuth.util.Encoder;
import com.TsoyDmitriy.FitAuth.util.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import static java.util.Collections.singletonList;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final Encoder encoder;
    private final JwtProvider jwtProvider;
    private final UserRepo userRepo;

    public User getIdentity() {
        String token = jwtProvider.getTokenFromRequest();
        if (!jwtProvider.validateToken()) {
            MyUnauthorizedException.expired();
        }
        return token == null ? null : new User(
                jwtProvider.getIdFromToken(),
                jwtProvider.getLoginFromToken(),
                jwtProvider.getRolesFromToken());
    }

    public void registerUser(User user, Role role) {
        user.setAuthorities(singletonList(role));
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    public void resetUserPassword(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username).orElseThrow(MyConflictException::userNotFound);
    }

    public void checkLoginDuplicate(String username) {
        if (isExists(username)) {
            throw MyConflictException.alreadyRegistered(username);
        }
    }

    private boolean isExists(String username) {
        return userRepo.existsByUsername(username);
    }

}
