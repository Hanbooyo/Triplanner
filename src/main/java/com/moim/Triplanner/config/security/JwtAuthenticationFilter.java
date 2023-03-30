package com.moim.Triplanner.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;
    private final String secret;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,
                                   @Value("${jwt.secret}") String secret) {
        this.authenticationManager = authenticationManager;
        this.secret = secret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            // Authorization header에서 JWT 토큰 추출
            String token = extractTokenFromRequestHeader(request);
            if (token != null) {
                // JWT 토큰 파싱
                Claims claims = parseToken(token);
                if (claims != null) {
                    // JWT 토큰 검증
                    boolean isTokenValid = validateToken(claims);
                    if (isTokenValid) {
                        // JWT 토큰에서 사용자 정보 추출
                        User user = getUserFromToken(claims);
                        // Spring Security Authentication 객체 생성
                        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
                        authentication = addDetailsToAuthentication(request, authentication);
                        // Spring Security Context에 Authentication 객체 설정
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        } catch (Exception e) {
            log.error("An error occurred while processing JWT token", e);
        }

        // 다음 필터 실행
        filterChain.doFilter(request, response);
    }

    private String extractTokenFromRequestHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private Claims parseToken(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.error("An error occurred while parsing JWT token", e);
            return null;
        }
    }

    private boolean validateToken(Claims claims) {
        Date expiration = claims.getExpiration();
        return expiration.after(new Date());
    }

    private User getUserFromToken(Claims claims) {
        String username = claims.getSubject();
        List<String> authorities = (List<String>) claims.get("authorities");
        List<SimpleGrantedAuthority> grantedAuthorities = authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new User(username, "", grantedAuthorities);
    }

    private Authentication addDetailsToAuthentication(HttpServletRequest request, Authentication authResult) {
        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
                authResult.getPrincipal(), authResult.getCredentials(), authResult.getAuthorities());
        result.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return result;
    }
}

