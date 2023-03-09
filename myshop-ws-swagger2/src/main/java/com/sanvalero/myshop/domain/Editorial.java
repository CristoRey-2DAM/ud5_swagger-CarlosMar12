/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanvalero.myshop.domain;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import javax.validation.constraints.NotBlank;

import javax.persistence.*;

/**
 *
 * @author Carlos
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "editoriales")
public class Editorial {
    
    @Schema(description = "Numero identificador de la editorial", example = "666", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    
    
    @Schema(description = "Nombre de la editorial", example = "Shueshia", required = true)
    @NotBlank
    @Column
    private String name;
    
    
    @Schema(description = "Numero de empleados", example = "100", defaultValue = "123")
    @NotBlank
    @Column
    private int NumEmpleados;
    
    
    @Schema(description = "Numero de Sucursales", example = "12", defaultValue = "4")
    @NotBlank
    @Column
    private int NumSucursales;
    
    
    @Schema(description = "Numero de Series publicadas por la editorial", example = "25", defaultValue = "14")
    @NotBlank
    @Column
    private int Num_SeriesPublicadas;
    
    @Schema(description = "Codigo del Editor de la Editorial", example = "334456", required = true)
    @NotBlank
    @Column
    private String cod_Editor;
}
