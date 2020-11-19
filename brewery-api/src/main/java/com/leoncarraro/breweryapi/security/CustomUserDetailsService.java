package com.leoncarraro.breweryapi.security;

import com.leoncarraro.breweryapi.model.ApplicationUser;
import com.leoncarraro.breweryapi.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ApplicationUser applicationUser = userRepository.findByEmail(email).
                orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos!"));

        return new CustomUser(applicationUser, getPermissions());
    }

    private Collection<? extends GrantedAuthority> getPermissions() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return authorities;
    }

}
