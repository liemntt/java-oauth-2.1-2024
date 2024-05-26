package dev.thanhliem.oauth.security;

import dev.thanhliem.oauth.constants.Constants;
import dev.thanhliem.oauth.services.resources.UserService;
import dev.thanhliem.oauth.utils.Utils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider provider;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var mayBeToken = getToken(request);
        if (mayBeToken.isPresent() && provider.isTokenValid(mayBeToken.get())) {
            var token = mayBeToken.get();
            String username = provider.getUsername(token);
            var userDetails = userService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }

    private Optional<String> getToken(HttpServletRequest request) {
        var token = request.getHeader(Constants.Headers.AUTHORIZATION);
        if (Utils.nonEmpty(token) && token.startsWith("Bearer")) {
            return Optional.of(token.substring(7));
        }
        return Optional.empty();
    }
}
