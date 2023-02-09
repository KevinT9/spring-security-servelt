package com.example.springservelt.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> {
                    try {
                        authorize
                                // Permite el acceso a todas los endpoints declarados
                                .mvcMatchers("/api/usuarios", "/api/roles").permitAll()
                                // Permite el acceso solo para los hasRole("ADMIN"), el hasRole equivale a (ROLE_ADMIN)
                                .mvcMatchers("/boostrap", "/test", "hola").hasRole("ADMIN")
                                // Deniega acceso a todos los endpoints que no esten autenticados
                                .anyRequest().authenticated()
                                .and()
                                // Se agrega la forma de autenticarse en este caso mediante un formulario que viene predefinido en spring
                                // Se puede modificar el login por uno propio mediante .formLogin(form -> form.loginPage("/login").permitAll())
                                .formLogin()
                                .and()
                                .httpBasic();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );
//
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /* Se define el modo de autenticación, mediante una clase que retorna UserDetails con usuario, contraseña y
           el rol. Luego con passwordEcoder se decodifica la contraseña del usuario
        */
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // método de spring para retornar el tipo de cifrado a usar
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HttpFirewall looseHttpFirewall() {
        // Método de spring para controlar los errores generados cuando ingresas caracteres especiales a una url como http://localhost/;h"o;la
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowSemicolon(true); // Determina si se permite el punto y coma en la URL (es decir, variables de matriz).
        firewall.setAllowBackSlash(true); // Determina si una barra invertida "\" o una barra invertida codificada en URL "%5C" deben permitirse en la ruta o no
        firewall.setAllowUrlEncodedSlash(true); // Determina si una barra inclinada "/" con codificación URL "%2F" debe permitirse en la ruta o no.
        return firewall;
    }
}
