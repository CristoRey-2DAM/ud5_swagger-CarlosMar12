package com.sanvalero.myshop.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * Un producto del catálogo
 * @author Santiago Faci
 * @version Curso 2020-2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "editoriales")
public class Editorial {

    @Schema(description = "Identificador de la editorial", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Nombre de la editorial", example = "Shueshia", required = true)
    @NotBlank
    @Column
    private String name;

    @Schema(description = "Numero de empleados", example = "20")
    @Column
    private int numEmpleados;

    @Schema(description = "Numero de sucursales", example = "12", required = true)
    @NotBlank
    @Column
    private int numSucursales;

    @Schema(description = "Numero de series publicadas por la editorial", example = "1", defaultValue = "1")
    @Column
    @Min(value = 1)
    private int numSeries;

    @Schema(description = "Codigo del editor", example = "33AB3")
    @Column
    private String codEditor;
}
