package com.TsoyDmitriy.FitAuth.config.handler;

import com.TsoyDmitriy.FitAuth.model.User;
import com.TsoyDmitriy.FitAuth.service.CookieService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    private final CookieService cookieService;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse, Authentication authentication) {
        Cookie cookie = cookieService.makeCookie(User.none());
        httpServletResponse.addCookie(cookie);
    }
}
