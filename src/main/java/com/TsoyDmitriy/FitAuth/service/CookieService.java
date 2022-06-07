package com.TsoyDmitriy.FitAuth.service;

import com.TsoyDmitriy.FitAuth.config.jwt.JwtProvider;
import com.TsoyDmitriy.FitAuth.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

import static com.TsoyDmitriy.FitAuth.config.jwt.JwtProvider.TOKEN;

@Service
@RequiredArgsConstructor
public class CookieService {

    private final JwtProvider jwtProvider;

    public Cookie makeCookie(String token) {
        return createCookie(token);
    }

    public Cookie makeCookie(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        String token = jwtProvider.generateToken(user);
        return createCookie(token);
    }

    public Cookie makeCookie(User user) {
        String token = jwtProvider.generateToken(user);
        return createCookie(token);
    }

    private Cookie createCookie(String token) {
        Cookie cookie = new Cookie(TOKEN, token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        return cookie;
    }
}