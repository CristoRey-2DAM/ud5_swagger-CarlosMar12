/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanvalero.myshop.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *
 * @author Carlos
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "mangas")
public class Manga{
    
    @Schema(description = "Numero identificador del manga", example = "666", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int Id_Manga;
    
    
    @Schema(description = "Genero del manga", example = "Shonen", required = true)
    @NotBlank
    @Column
    public String Genero;
    
    @Schema(description = "Frecuencia de publicacion", example = "Mensual")
    @NotBlank
    @Column
    public String frecuencia_de_Publicacion;
    
    @Schema(description = "Numero temporadas del Anime", example = "2Temp")
    @NotBlank
    @Column
    public String Anime;
    
    @Schema(description = "Codigo identificador del manga", example = "13221")
    @NotBlank
    @Column
    public String Cod_Manga;
    
    @Schema(description = "Estilo de Dibujo", example = "Blanco y Negro")
    @NotBlank
    @Column
    public String estilo_de_Dibujo;
  

}
