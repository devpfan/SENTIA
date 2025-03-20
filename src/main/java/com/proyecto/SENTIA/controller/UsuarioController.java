package com.proyecto.SENTIA.controller;

import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.SENTIA.model.dto.LoginRequest;
import com.proyecto.SENTIA.model.dto.UsuarioDTO;
import com.proyecto.SENTIA.service.UsuarioService;
import org.slf4j.Logger;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

     private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/all")
    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Long id) {
        return usuarioService.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/crear")
    public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        String encodedPassword = Base64.getEncoder().encodeToString(usuarioDTO.getPassword().getBytes());
        usuarioDTO.setPassword(encodedPassword); 
        UsuarioDTO createdUsuario = usuarioService.save(usuarioDTO);
        return ResponseEntity.status(201).body(createdUsuario);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteById(id);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        logger.debug("Identificación recibida: " + loginRequest.getIdentificacion());
        logger.debug("Contraseña recibida: " + loginRequest.getPassword());
    
        if (loginRequest.getIdentificacion() == null || loginRequest.getIdentificacion().isEmpty()) {
            return ResponseEntity.status(400).body(Map.of("error", "Identificación no proporcionada"));
        }
    
        boolean isValid = usuarioService.validarCredenciales(loginRequest);
        if (isValid) {
            return ResponseEntity.ok(Map.of("mensaje", "Login exitoso"));
        }
        return ResponseEntity.status(401).body(Map.of("error", "Credenciales incorrectas"));
    }
    

}
