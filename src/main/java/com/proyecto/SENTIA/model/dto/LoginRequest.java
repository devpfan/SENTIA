package com.proyecto.SENTIA.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class LoginRequest {

    private String identificacion;
    private String password;

    // Getters y setters
    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

        
}
