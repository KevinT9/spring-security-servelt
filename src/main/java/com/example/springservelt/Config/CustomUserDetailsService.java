package com.example.springservelt.Config;

import com.example.springservelt.Entities.Rol;
import com.example.springservelt.Entities.Usuario;
import com.example.springservelt.Repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
 * Clase creada para personalizar la obtención de los datos para validar los usuarios
 * Para este caso se usa una RELACION MUCHOS A MUCHOS ENTRE 2 TABLAS
 * Tablas: User and Roles
 * */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuariosRepository userRepository;// Permite usar los metodos para JPA para la entidad usuarios

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Metodo equivalente a hacer un SELECT * FROM USER WITH username = "Variable";
        Usuario user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        // GrantedAuthority representa una interfaz con objetos que permiten la autenticación en spring
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Rol role : user.getRoles()) {
            System.out.println("Rol_name: " + role.getName());
            // Almacena una String que representa una autoridad otorgada.
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        System.out.println("----auth:" + authorities);
        /* Retorna un objeto que contiene el nombre de usuario, password y la autoridad que tiene en la aplicación dependiendo
         de los roles agregaoso a la lista authorities */
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
