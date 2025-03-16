package com.proyecto.SENTIA.model.dto;

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

        return usuario;

       
    }

   

}
