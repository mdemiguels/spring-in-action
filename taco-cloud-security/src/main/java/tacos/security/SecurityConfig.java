package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import tacos.data.UserRepository;
import tacos.model.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {

        return username -> {
            User user = userRepository.findByUsername(username);
            if (user != null) return user;

            throw new UsernameNotFoundException("User '" + username + "' not found.");
        };

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/design").hasRole("USER")
                        .requestMatchers("/orders").hasRole("USER")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/", "/**").permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/design", true)
                )
                .oauth2Login(oauth -> oauth
                        .loginPage("/login")
                        .defaultSuccessUrl("/design", true)
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService)
                        )
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")
                )
                .cors(Customizer.withDefaults())
                .headers(headers -> headers
                        .frameOptions(Customizer.withDefaults())
                        .disable()
                )
                .build();
    }

    @Bean
    public User adminUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        User admin = new User("admin", passwordEncoder.encode("admin"), "admin", "admin", "admin", "admin", "admin", "admin");
        admin.setAdmin(true);
        userRepository.save(admin);
        return admin;
    }

}
