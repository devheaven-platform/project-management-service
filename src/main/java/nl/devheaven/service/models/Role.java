package nl.devheaven.service.models;

import org.springframework.security.core.GrantedAuthority;

/**
 * This model represents a role in the system.
 */
public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_DEVELOPER,
    ROLE_HR,
    ROLE_MANAGER;

    public String getAuthority() {
        return name();
    }
}