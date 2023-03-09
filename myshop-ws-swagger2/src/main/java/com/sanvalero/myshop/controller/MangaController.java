/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanvalero.myshop.controller;

import static com.sanvalero.myshop.controller.Response.NOT_FOUND;

import com.sanvalero.myshop.domain.Manga;

import com.sanvalero.myshop.exception.MangaNotFoundException;

import com.sanvalero.myshop.service.MangaService;
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
@Tag(name = "mangas", description = "Catálogo de mangas")
public class MangaController {
     private final Logger logger = LoggerFactory.getLogger(MangaController.class);

    @Autowired
    private MangaService mangaService;

    @Operation(summary = "Obtiene el listado de mangas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de mangas",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Manga.class)))),
    })
    @GetMapping(value = "/mangas", produces = "application/json")
    public ResponseEntity<Set<Manga>> getManga(@RequestParam(value = "name", defaultValue = "") int id) {
        logger.info("inicio getManga");
        Set<Manga> manga = null;
        if (id == 0)
            manga = mangaService.findAll();
        else
            manga = mangaService.findById(id);

        logger.info("fin getMangas");
        return new ResponseEntity<>(manga, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un Manga determinada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el Manga", content = @Content(schema = @Schema(implementation = Manga.class))),
            @ApiResponse(responseCode = "404", description = "El Manga no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/mangas/{id}", produces = "application/json")
    public ResponseEntity<Manga> getManga_ID(@PathVariable int id) {
        Manga manga = (Manga) mangaService.findById(id); 

        return new ResponseEntity<>(manga, HttpStatus.OK);
    }

    @Operation(summary = "Registra un nuevo Manga")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se registra el Manga", content = @Content(schema = @Schema(implementation = Manga.class)))
    })
    @PostMapping(value = "/mangas", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Manga> addManga(@RequestBody Manga manga) {
        Manga addedManga = mangaService.addManga(manga);
        return new ResponseEntity<>(addedManga, HttpStatus.CREATED);
    }

    @Operation(summary = "Modifica un Manga en el catálogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica el Manga", content = @Content(schema = @Schema(implementation = Manga.class))),
            @ApiResponse(responseCode = "404", description = "El Manga no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/mangas/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Manga> modifyManga(@PathVariable int id, @RequestBody Manga newManga) {
        Manga manga = mangaService.modifyManga(id, newManga);
        return new ResponseEntity<>(manga, HttpStatus.OK);
    }

    @Operation(summary = "Elimina un Manga")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina el Manga", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "El Manga no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/mangas/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteManga(@PathVariable int id) {
        mangaService.deleteManga(id);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

   

    @ExceptionHandler(MangaNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(MangaNotFoundException pnfe) {
        Response response = Response.errorResonse(NOT_FOUND, pnfe.getMessage());
        logger.error(pnfe.getMessage(), pnfe);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
