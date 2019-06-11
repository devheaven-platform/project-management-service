package nl.devheaven.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import nl.devheaven.service.models.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is used to generate and validate JWT tokens.
 */
@Component
public class TokenProvider {

    @Value("${security.jwt.header}")
    private String header;
    @Value("${security.jwt.prefix}")
    private String prefix;
    @Value("${security.jwt.secret}")
    private String secret;

    /**
     * Called before the class is initialized. This method is used to
     * encode the secret key in base64 format.
     */
    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    /**
     * Validates the JWT token. If the token is invalid the
     * parseClaimsJws() method will throw an exception that
     * will result in the method returning false.
     *
     * @param token the token to validate.
     * @return true if the token is valid else false.
     */
    boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Gets the authentication object from a token.
     *
     * @param token the token to retrieve the object from.
     * @return an new instance of the Authentication object containing
     * the user details and authorities.
     */
    Authentication getAuthentication(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        String id = claims.getBody().getSubject();
        List<Role> roles = (List<Role>) claims
                .getBody()
                .get("roles", ArrayList.class)
                .stream()
                .map(c -> Role.valueOf(c.toString()))
                .collect(Collectors.toList());

        UserDetails user = User
                .withUsername(id)
                .password("")
                .authorities(roles)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(true)
                .build();


        return new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
    }

    /**
     * Retrieves the token from the request header.
     *
     * @param request the request to retrieve the token from.
     * @return the token without the Bearer prefix or null.
     */
    String getTokenFromHttpHeader(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (token != null && token.startsWith(prefix + " ")) {
            return token.substring(7);
        }
        return null;
    }
}