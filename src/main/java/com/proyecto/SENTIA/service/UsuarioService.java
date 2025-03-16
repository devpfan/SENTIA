package com.proyecto.SENTIA.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.proyecto.SENTIA.model.dto.LoginRequest;
import com.proyecto.SENTIA.model.dto.UsuarioDTO;
import com.proyecto.SENTIA.model.entity.Usuario;
import com.proyecto.SENTIA.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
   

    public List<UsuarioDTO> findAll(){
        return usuarioRepository.findAll().stream().map(UsuarioDTO::new).collect(Collectors.toList());
    }

    public Optional<UsuarioDTO> findById(Long id){
        return usuarioRepository.findById(id).map(UsuarioDTO::new);
    }   

    public UsuarioDTO save(UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioDTO.toEntity();
        String encodedPassword = Base64.getEncoder().encodeToString(usuario.getPassword().getBytes());
        usuario.setPassword(encodedPassword);
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return new UsuarioDTO(usuarioGuardado);
    }

    public void deleteById(Long id){
        usuarioRepository.deleteById(id);
    }
   
    public Usuario registrarUsuario(UsuarioDTO usuarioDto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setApellido(usuarioDto.getApellido());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setDepartamento(usuarioDto.getDepartamento());
        usuario.setTelefono(usuarioDto.getTelefono());
        usuario.setIdentificacion(usuarioDto.getIdentificacion());
        String encodedPassword = Base64.getEncoder().encodeToString(usuarioDto.getPassword().getBytes());
        usuario.setPassword(encodedPassword);
        usuario.setRol("USER"); // Se asigna rol por defecto
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> findByIdentificacion(String identificacion) {
        return usuarioRepository.findByIdentificacion(identificacion);
    }

    public boolean validarCredenciales(LoginRequest loginRequest) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByIdentificacion(loginRequest.getIdentificacion());

        if (usuarioOpt.isPresent()) {
            String passwordEnBase64 = usuarioOpt.get().getPassword();
            String passwordIngresada = Base64.getEncoder().encodeToString(loginRequest.getPassword().getBytes());

            System.out.println("Password almacenada en Base64: " + passwordEnBase64);
            System.out.println("Password ingresada en Base64: " + passwordIngresada);
    
            return passwordIngresada.equals(passwordEnBase64);
        }

        return false;
    }

}
