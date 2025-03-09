package com.proyecto.SENTIA.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.SENTIA.model.dto.UsuarioDTO;
import com.proyecto.SENTIA.model.entity.Usuario;
import com.proyecto.SENTIA.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<UsuarioDTO> findAll(){
        return usuarioRepository.findAll().stream().map(UsuarioDTO::new).collect(Collectors.toList());
    }

    public Optional<UsuarioDTO> findById(Long id){
        return usuarioRepository.findById(id).map(UsuarioDTO::new);
    }   

    public UsuarioDTO save(UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioDTO.toEntity();
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return new UsuarioDTO(usuarioRepository.save(usuario));
    }

    public void deleteById(Long id){
        usuarioRepository.deleteById(id);
    }
   

}
