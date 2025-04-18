package com.proyecto.SENTIA.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.proyecto.SENTIA.model.dto.LoginRequest;
import com.proyecto.SENTIA.model.dto.UsuarioDTO;
import com.proyecto.SENTIA.model.entity.Usuario;
import com.proyecto.SENTIA.repository.UsuarioRepository;
import com.proyecto.SENTIA.util.BeanUtilsHelper;

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

        String encodedPassword = Base64.getEncoder().encodeToString(usuarioDto.getPassword().trim().getBytes());
        usuario.setPassword(encodedPassword);
        
        usuario.setRol("USER"); // Se asigna rol por defecto
        return usuarioRepository.save(usuario);
    }

    //actualizar usuario con beanutils
    public Optional<UsuarioDTO> updateUsuario(String identificacion, UsuarioDTO usuarioDTO) {
    Optional<Usuario> usuarioOpt = usuarioRepository.findByIdentificacion(identificacion);

    if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            BeanUtils.copyProperties(usuarioDTO, usuario, BeanUtilsHelper.getNullPropertyNames(usuarioDTO));
            Usuario usuarioActualizado = usuarioRepository.save(usuario);
            return Optional.of(new UsuarioDTO(usuarioActualizado));
        }

    return Optional.empty(); 
    }


    public boolean changePassword(String identificacion, String oldPassword, String newPassword) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByIdentificacion(identificacion);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            String oldPasswordEncoded = Base64.getEncoder().encodeToString(oldPassword.trim().getBytes());

            // Log para depuración
           /*System.out.println("Contraseña actual almacenada: " + usuario.getPassword());
            System.out.println("Contraseña actual proporcionada (codificada): " + oldPasswordEncoded);*/

            if (usuario.getPassword().equals(oldPasswordEncoded)) {
                String newPasswordEncoded = Base64.getEncoder().encodeToString(newPassword.trim().getBytes());
                usuario.setPassword(newPasswordEncoded);
                usuarioRepository.save(usuario);
                return true;
            } else {
                System.out.println("La contraseña actual no coincide.");
            }
        } else {
            System.out.println("Usuario no encontrado con la identificación: " + identificacion);
        }

        return false;
    }

    public Optional<Usuario> findByIdentificacion(String identificacion) {
        return usuarioRepository.findByIdentificacion(identificacion);
    }

    public boolean validarCredenciales(LoginRequest loginRequest) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByIdentificacion(loginRequest.getIdentificacion());

        if (usuarioOpt.isPresent()) {
            String passwordEnBase64 = usuarioOpt.get().getPassword().trim();
            String passwordIngresada = Base64.getEncoder().encodeToString(loginRequest.getPassword().trim().getBytes());

            System.out.println("Password almacenada en Base64: " + passwordEnBase64);
            System.out.println("Password ingresada en Base64: " + passwordIngresada);
    
            return passwordIngresada.equals(passwordEnBase64);
        }

        return false;
    }

    public String obtenerRol(String identificacion) {
        Usuario usuario = usuarioRepository.findByIdentificacion(identificacion).orElse(null);
        return (usuario != null) ? usuario.getRol() : "usuario"; 
    }

    public boolean updateFoto(String identificacion, String fotoBase64) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByIdentificacion(identificacion);
    
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            byte[] fotoBytes = Base64.getDecoder().decode(fotoBase64);
            usuario.setFoto(fotoBytes);
            usuarioRepository.save(usuario);
            return true;
        }
    
        return false; 
    }

}
