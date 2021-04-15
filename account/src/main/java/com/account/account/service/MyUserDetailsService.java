package com.account.account.service;

import com.account.account.model.Users;
import com.account.account.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    public UserDetails loadUserByUsername(String email){
        Optional<Users> maybeUser = usersRepository.findByEmail(email);

        if (maybeUser.isEmpty()) {
            throw new UsernameNotFoundException("Something went wrong!!!");
        }

        Users users = maybeUser.get();

        Set<GrantedAuthority> grantedAuthorities = users.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        return org.springframework.security.core.userdetails.User.builder()
                .username(email)
                .password(users.getPassword())
                .authorities(grantedAuthorities)
                .build();
    }
}
