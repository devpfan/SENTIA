package com.proyecto.SENTIA.model.dto;

import java.util.Base64;

import com.proyecto.SENTIA.model.entity.Usuario;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String departamento;
    private String rol;
    private String telefono;
    private String password;
    private String identificacion;
    private String cargo;
    private String foto;    

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.apellido = usuario.getApellido();
        this.email = usuario.getEmail();
        this.departamento = usuario.getDepartamento();
        this.rol = usuario.getRol();
        this.telefono = usuario.getTelefono();
        this.password = usuario.getPassword();
        this.identificacion = usuario.getIdentificacion();
        this.cargo = usuario.getCargo();
        this.foto = usuario.getFoto() != null ? Base64.getEncoder().encodeToString(usuario.getFoto()) : null;
    }

    public Usuario toEntity() {
        Usuario usuario = new Usuario();
        usuario.setId(this.id);
        usuario.setNombre(this.nombre);
        usuario.setApellido(this.apellido);
        usuario.setEmail(this.email);   
        usuario.setDepartamento(this.departamento);
        usuario.setRol(this.rol);
        usuario.setTelefono(this.telefono);
        usuario.setPassword(this.password);
        usuario.setIdentificacion(this.identificacion);
        usuario.setCargo(this.cargo);
        usuario.setFoto(this.foto != null ? Base64.getDecoder().decode(this.foto) : null);

        return usuario;

       
    }

   

}
