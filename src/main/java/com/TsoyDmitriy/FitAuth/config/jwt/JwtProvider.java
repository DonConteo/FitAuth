package com.TsoyDmitriy.FitAuth.config.jwt;

import com.TsoyDmitriy.FitAuth.model.Role;
import com.TsoyDmitriy.FitAuth.model.User;
import io.jsonwebtoken.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static io.jsonwebtoken.lang.Strings.hasText;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpHeaders.COOKIE;

@Data
@Slf4j
@Component
@RequiredArgsConstructor
@ConfigurationProperties("jwt")
public class JwtProvider {

    private final HttpServletRequest request;

    public static final String TOKEN = "TOKEN";
    private String secret;

    public Boolean validateToken() {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(getTokenFromRequest());
            return true;
        } catch (ExpiredJwtException expEx) {
            log.info("Token expired");
        } catch (UnsupportedJwtException unsEx) {
            log.info("Unsupported jwt");
        } catch (MalformedJwtException mjEx) {
            log.info("Malformed jwt");
        } catch (SignatureException sEx) {
            log.info("Invalid signature");
        } catch (Exception e) {
            log.info("invalid token");
        }
        return false;
    }

    public String generateToken(User user) {
        boolean isBlank = user.getUsername() == null;
        Date date = getExpirationDate(isBlank);
        String[] roles = isBlank ?
            null : user.getAuthorities().stream().map(Role::getName).toArray(String[]::new);
        return Jwts.builder()
            .setId(isBlank ? null : Long.toString(user.getId()))
            .setSubject(user.getUsername())
            .claim("roles", roles)
            .setExpiration(date)
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
    }

    public String getTokenFromRequest() {
        String bearer = request.getHeader(COOKIE);
        if (hasText(bearer) && bearer.contains(TOKEN)) {
            String trimmedToken = bearer.substring(bearer.indexOf(TOKEN) + 6);
            if (trimmedToken.contains(";")) {
                return trimmedToken.substring(0, trimmedToken.indexOf(';'));
            } else {
                return trimmedToken;
            }
        }
        return null;
    }

    public Long getIdFromToken() {
        return Long.parseLong(claims().getId());
    }

    public String getLoginFromToken() {
        return claims().getSubject();
    }

    public List<Role> getRolesFromToken() {
        List<String> rolesNames = claims().get("roles", ArrayList.class);
        return rolesNames.stream().map(Role::new).collect(toList());
    }

    private Claims claims() {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(getTokenFromRequest()).getBody();
    }

    private Date getExpirationDate(Boolean stale) {
        return Date.from(stale ?
            LocalDate.now()
                .minusDays(1)
                .atTime(LocalTime.now(ZoneId.systemDefault()))
                .toInstant(ZoneOffset.ofHours(3)) :
            LocalDate.now()
                .plusDays(1)
                .atTime(LocalTime.now(ZoneId.systemDefault()))
                .toInstant(ZoneOffset.ofHours(3)));
    }
}
