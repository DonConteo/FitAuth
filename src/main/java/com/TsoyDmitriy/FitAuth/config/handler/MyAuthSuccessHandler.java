package com.TsoyDmitriy.FitAuth.config.handler;

import com.TsoyDmitriy.FitAuth.service.CookieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class MyAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final CookieService cookieService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        clearAuthenticationAttributes(request);
        Cookie cookie = cookieService.makeCookie(authentication);
        response.addCookie(cookie);
        response.getOutputStream()
                .print(new ObjectMapper().writeValueAsString(authentication.getPrincipal()));
    }
}
