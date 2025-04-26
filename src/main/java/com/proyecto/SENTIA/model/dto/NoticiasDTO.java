package com.proyecto.SENTIA.model.dto;

import java.time.LocalDateTime;
import java.util.Base64;

import com.proyecto.SENTIA.model.entity.Noticias;
import com.proyecto.SENTIA.model.entity.TipoNoticia;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoticiasDTO {

    private Long id;
    private String titulo;
    private String contenido;
    private int tipo;
    private String imagenBase64;
    private Long autorId; 
    private String autorNombre;
    private String autorApellido; 
    private String autorFotoBase64;
    private String fechaEscritura;
    private String fechaPublicacion;
    private boolean publicada;


    public NoticiasDTO(Noticias noticias) {
        this.id = noticias.getId();
        this.titulo = noticias.getTitulo();
        this.contenido = noticias.getContenido();
        this.tipo = noticias.getTipo().ordinal();
        this.imagenBase64 = noticias.getImagen() != null ? Base64.getEncoder().encodeToString(noticias.getImagen()) : null;
        this.autorId = noticias.getAutor().getId();
        this.autorNombre = noticias.getAutor().getNombre();
        this.autorApellido = noticias.getAutor().getApellido();
        this.autorFotoBase64 = noticias.getAutor().getFoto() != null ? Base64.getEncoder().encodeToString(noticias.getAutor().getFoto()) : null;
        this.fechaEscritura = noticias.getFechaEscritura().toString();
        this.fechaPublicacion = noticias.getFechaPublicacion() != null ? noticias.getFechaPublicacion().toString() : null;
        this.publicada = noticias.isPublicada();
    }

    public Noticias toEntity() {
        Noticias noticias = new Noticias();
        noticias.setId(this.id);
        noticias.setTitulo(this.titulo);
        noticias.setContenido(this.contenido);
        noticias.setTipo(TipoNoticia.values()[this.tipo]);
        noticias.setImagen(this.imagenBase64 != null ? Base64.getDecoder().decode(this.imagenBase64) : null);
        noticias.setFechaEscritura(LocalDateTime.parse(this.fechaEscritura));
        noticias.setFechaPublicacion(this.fechaPublicacion != null ? LocalDateTime.parse(this.fechaPublicacion) : null);
        noticias.setPublicada(this.publicada);
        return noticias;
    }
}