package com.proyecto.SENTIA.controller;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.SENTIA.model.dto.FotoDTO;
import com.proyecto.SENTIA.model.dto.LoginRequest;
import com.proyecto.SENTIA.model.dto.UsuarioDTO;
import com.proyecto.SENTIA.service.UsuarioService;
import org.slf4j.Logger;


@RestController
@RequestMapping("/usuarios")
//@CrossOrigin(origins = "http://localhost:8081")
public class UsuarioController {

     private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/all")
    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioService.findAll();
    }

    @GetMapping("/info/{identificacion}")
        public ResponseEntity<UsuarioDTO> getUsuarioByIdentificacion(@PathVariable String identificacion) {
         return usuarioService.findByIdentificacion(identificacion)
                .map(usuario -> ResponseEntity.ok(new UsuarioDTO(usuario)))
                .orElseGet(() -> ResponseEntity.notFound().build());
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
            String rol = usuarioService.obtenerRol(loginRequest.getIdentificacion()); // Obtener el rol del usuario
            return ResponseEntity.ok(Map.of(
                "mensaje", "Login exitoso",
                "rol", rol
            ));
        }
        return ResponseEntity.status(401).body(Map.of("error", "Credenciales incorrectas"));
    }

    //cambiar password
    @PostMapping("/cambiar-password")
    public ResponseEntity<Map<String, String>> changePassword(@RequestBody Map<String, String> request) {
        String identificacion = request.get("identificacion");
        String oldPassword = request.get("oldPassword");
        String newPassword = request.get("newPassword");

        boolean isChanged = usuarioService.changePassword(identificacion, oldPassword, newPassword);
        if (isChanged) {
            return ResponseEntity.ok(Map.of("mensaje", "Contraseña actualizada con éxito"));
        } else {
            return ResponseEntity.status(400).body(Map.of("error", "No se pudo actualizar la contraseña. Verifique las credenciales."));
        }
    }

    //actualizar usuario
    @PutMapping("/actualizar/{identificacion}")
    public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable String identificacion, @RequestBody UsuarioDTO usuarioDTO) {
        Optional<UsuarioDTO> updatedUsuario = usuarioService.updateUsuario(identificacion, usuarioDTO);
        if (updatedUsuario.isPresent()) {
            return ResponseEntity.ok(updatedUsuario.get());
        } else {
            return ResponseEntity.status(404).body(null); // Usuario no encontrado
        }
    }

    @PutMapping("/foto/{identificacion}")
        public ResponseEntity<Map<String, String>> updateFoto(@PathVariable String identificacion, @RequestBody FotoDTO fotoDTO) {
            if (fotoDTO.getFoto() == null || fotoDTO.getFoto().isEmpty()) {
                return ResponseEntity.status(400).body(Map.of("error", "La foto no puede estar vacía"));
            }

            boolean isUpdated = usuarioService.updateFoto(identificacion, fotoDTO.getFoto());
            if (isUpdated) {
                return ResponseEntity.ok(Map.of("mensaje", "Foto actualizada con éxito"));
            } else {
                return ResponseEntity.status(404).body(Map.of("error", "Usuario no encontrado"));
            }
    }

    

}
