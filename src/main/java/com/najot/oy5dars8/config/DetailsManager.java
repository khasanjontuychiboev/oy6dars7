package com.najot.oy5dars8.config;

import com.najot.oy5dars8.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration

public class DetailsManager {
    private final AppUserDetailsService appUserDetailsService;

    @Autowired
    public DetailsManager(AppUserDetailsService appUserDetailsService) {
        this.appUserDetailsService = appUserDetailsService;
    }


    @Bean
    public UserDetailsService userDetailsService(){

        return new InMemoryUserDetailsManager(
                appUserDetailsService
                        .userDetails()
        );
    }
}
