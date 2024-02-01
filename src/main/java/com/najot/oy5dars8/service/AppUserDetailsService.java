package com.najot.oy5dars8.service;

import com.najot.oy5dars8.entity.AppRole;
import com.najot.oy5dars8.repository.AppUserRepository;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Data
public class AppUserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository userRepository;

    public Set<UserDetails> userDetails(){

        return userRepository.findAll()
                .stream()
                .map(user -> {
                            String[] arr = (String[]) user.getRoles()
                                    .stream()
                                    .map(AppRole::getRoleName)
                                    .toList()
                                    .toArray();

                            Set<SimpleGrantedAuthority> arrp = new HashSet<>();
                                user.getRoles().stream()
                                    .peek(role->{
                                        role.getPermissions()
                                            .forEach(x->arrp
                                                    .add(new SimpleGrantedAuthority(x.getPermissionName())));
                                    }
                                    ).collect(Collectors.toSet());


                    return User.builder()
                                    .username(user.getUsername())
                                    .password(passwordEncoder.encode(user.getPassword()))
                                    .roles(arr)
                                    .authorities(arrp)
                                    .build();
                        }
                ).collect(Collectors.toSet());

    }
}
