package com.group2.detecthief.infrastructure.config;

import com.group2.detecthief.domain.repository.UserRepository;
import com.group2.detecthief.infrastructure.persistence.adapter.UserRepositoryAdapter;
import com.group2.detecthief.infrastructure.persistence.repository.JpaUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public UserRepository userRepository(JpaUserRepository jpaUserRepository) {
        return new UserRepositoryAdapter(jpaUserRepository);
    }
}