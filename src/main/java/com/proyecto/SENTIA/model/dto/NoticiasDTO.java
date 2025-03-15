package com.proyecto.SENTIA.model.dto;

import java.time.LocalDateTime;

import com.proyecto.SENTIA.model.entity.Noticias;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoticiasDTO {

    private Long id;
    private String titulo;
    private String contenido;
    private String fechaPublicacion;

    public NoticiasDTO(Noticias noticias) {
        this.id = noticias.getId();
        this.titulo = noticias.getTitulo();
        this.contenido = noticias.getContenido();
        this.fechaPublicacion = noticias.getFechaPublicacion().toString();
    }

    public Noticias toEntity() {
        Noticias noticias = new Noticias();
        noticias.setId(this.id);
        noticias.setTitulo(this.titulo);
        noticias.setContenido(this.contenido);
        noticias.setFechaPublicacion(LocalDateTime.parse(this.fechaPublicacion));
        return noticias;
    }
}