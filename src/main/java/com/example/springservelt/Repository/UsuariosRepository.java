package com.example.springservelt.Repository;

import com.example.springservelt.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByUsername(String username);// Método que devuelve la consulta (SELECT * FROM Usuarios WHERE username = '?')
}
