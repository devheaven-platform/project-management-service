package nl.devheaven.service.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This filter checks if the token is valid and populates the security context.
 */
public class TokenFilter extends OncePerRequestFilter {

    private final TokenProvider provider;

    /**
     * Constructor for the token filter.
     *
     * @param provider the token provider to user.
     */
    TokenFilter(TokenProvider provider) {
        this.provider = provider;
    }

    /**
     * Filter method that is called by spring.
     *
     * @param request  the request
     * @param response the response
     * @param chain    the chain of next filters
     * @throws ServletException if an error occurred
     * @throws IOException      if an error occurred
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = provider.getTokenFromHttpHeader(request);

        try {
            if (token != null && provider.validateToken(token)) {
                Authentication authentication = provider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            System.out.println(ex);
            SecurityContextHolder.clearContext();
        }

        chain.doFilter(request, response);
    }

}