/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanvalero.myshop.controller;

import static com.sanvalero.myshop.controller.Response.NOT_FOUND;
import com.sanvalero.myshop.domain.Editorial;

import com.sanvalero.myshop.exception.EditorialNotFoundException;

import com.sanvalero.myshop.service.EditorialService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Carlos
 */
@RestController
@Tag(name = "editoriales", description = "Catálogo de editoriales")
public class EditorialController {

    private final Logger logger = LoggerFactory.getLogger(EditorialController.class);

    @Autowired
    private EditorialService editorialService;

    @Operation(summary = "Obtiene el listado de editoriales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de Editoriales",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Editorial.class)))),
    })
    @GetMapping(value = "/editoriales", produces = "application/json")
    public ResponseEntity<Set<Editorial>> getEditoriles(@RequestParam(value = "name", defaultValue = "") int id) {
        logger.info("inicio getEditoriales");
        Set<Editorial> editorials = null;
        if (id == 0)
            editorials = editorialService.findAll();
        else
            editorials = editorialService.findById(id);

        logger.info("fin getEditoriales");
        return new ResponseEntity<>(editorials, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene una Editorial determinada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe la Editorial", content = @Content(schema = @Schema(implementation = Editorial.class))),
            @ApiResponse(responseCode = "404", description = "La Editorial no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/editoriales/{id}", produces = "application/json")
    public ResponseEntity<Editorial> getEditorial(@PathVariable int id) {
        Editorial editorial = (Editorial) editorialService.findById(id); 

        return new ResponseEntity<>(editorial, HttpStatus.OK);
    }

    @Operation(summary = "Registra una nueva Editorial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se registra la Editorial", content = @Content(schema = @Schema(implementation = Editorial.class)))
    })
    @PostMapping(value = "/editoriales", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Editorial> addEditorial(@RequestBody Editorial editorial) {
        Editorial addedEditorial = editorialService.addEditorial(editorial);
        return new ResponseEntity<>(addedEditorial, HttpStatus.CREATED);
    }

    @Operation(summary = "Modifica una Editorial en el catálogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica la Editorial", content = @Content(schema = @Schema(implementation = Editorial.class))),
            @ApiResponse(responseCode = "404", description = "La Editorial no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/editoriales/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Editorial> modifyEditorial(@PathVariable int id, @RequestBody Editorial newEditorial) {
        Editorial editorial = editorialService.modifyEditorial(id, newEditorial);
        return new ResponseEntity<>(editorial, HttpStatus.OK);
    }

    @Operation(summary = "Elimina una Editorial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina la Editorial", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "La Editorial no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/editoriales/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteEditorial(@PathVariable int id) {
        editorialService.deleteEditorial(id);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

   

    @ExceptionHandler(EditorialNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(EditorialNotFoundException pnfe) {
        Response response = Response.errorResonse(NOT_FOUND, pnfe.getMessage());
        logger.error(pnfe.getMessage(), pnfe);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
