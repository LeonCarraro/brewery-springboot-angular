package com.leoncarraro.breweryapi.security;

import com.leoncarraro.breweryapi.model.ApplicationUser;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class CustomUser extends User {

    private final ApplicationUser applicationUser;

    public CustomUser(ApplicationUser applicationUser, Collection<? extends GrantedAuthority> authorities) {
        super(applicationUser.getEmail(), applicationUser.getPassword(), authorities);
        this.applicationUser = applicationUser;
    }

}
