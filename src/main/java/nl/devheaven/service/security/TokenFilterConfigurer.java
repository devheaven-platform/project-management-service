package nl.devheaven.service.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configures the security filter chain.
 */
public class TokenFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final TokenProvider provider;

    /**
     * Constructor for the filter configurator.
     *
     * @param provider the provider to use for the token filter.
     */
    TokenFilterConfigurer(TokenProvider provider) {
        this.provider = provider;
    }

    /**
     * Configures the filter chain to run the username and password authenticator
     * filter before the token filter.
     *
     * @param builder the security builder
     */
    @Override
    public void configure(HttpSecurity builder) {
        TokenFilter filter = new TokenFilter(provider);
        builder.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}