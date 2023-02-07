package com.example.springservelt.Controller;

import com.example.springservelt.Entities.Rol;
import com.example.springservelt.Entities.Usuario;
import com.example.springservelt.Repository.RolesRepository;
import com.example.springservelt.Repository.UsuariosRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuariosController {

    private final UsuariosRepository usuariosRepository;
    private final RolesRepository rolesRepository;

    public UsuariosController(UsuariosRepository usuariosRepository, RolesRepository rolesRepository) {
        this.usuariosRepository = usuariosRepository;
        this.rolesRepository = rolesRepository;
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuariosRepository.findAll());
    }


    @GetMapping("/roles")
    public ResponseEntity<List<Rol>> roles() {
        return ResponseEntity.ok(rolesRepository.findAll());
    }

}
