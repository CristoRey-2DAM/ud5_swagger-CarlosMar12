package com.sanvalero.myshop.controller;



import static com.sanvalero.myshop.controller.Response.NOT_FOUND;
import com.sanvalero.myshop.domain.Manga;
import com.sanvalero.myshop.exception.MangaNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sanvalero.myshop.service.MangaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Set;

/**
 * Controlador para pedidos
 * @author Santiago Faci
 * @version Curso 2020-2021
 */
@RestController
@Tag(name = "Mangas", description = "Catálogo de mangas")
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private MangaService mangaService;

       @Operation(summary = "Obtiene el listado de mangas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de mangas",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Manga.class)))),
    })
    @GetMapping(value = "/mangas", produces = "application/json")
    public ResponseEntity<Set<Manga>> getMangas(@RequestParam(value = "codManga", defaultValue = "") String codManga) {
        logger.info("inicio getMangas");
        Set<Manga> mangas = null;
        if (codManga.equals(""))
            mangas = mangaService.findAll();
        else
            mangas = mangaService.findByCodManga(codManga);

        logger.info("fin getManga");
        return new ResponseEntity<>(mangas, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un manga determinado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el manga", content = @Content(schema = @Schema(implementation = Manga.class))),
            @ApiResponse(responseCode = "404", description = "El manga no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/mangas/{id}", produces = "application/json")
    public ResponseEntity<Manga> getManga(@PathVariable long id) {
       Manga product = mangaService.findById(id)
                .orElseThrow(() -> new MangaNotFoundException(id));

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Operation(summary = "Registra un nuevo manga")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se registra el manga", content = @Content(schema = @Schema(implementation = Manga.class)))
    })
    @PostMapping(value = "/mangas", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Manga> addManga(@RequestBody Manga manga) {
        Manga addedManga = mangaService.addManga(manga);
        return new ResponseEntity<>(addedManga, HttpStatus.CREATED);
    }

    @Operation(summary = "Modifica un manga en el catálogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica el manga", content = @Content(schema = @Schema(implementation = Manga.class))),
            @ApiResponse(responseCode = "404", description = "El manga no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/mangas/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Manga> modifyManga(@PathVariable long id, @RequestBody Manga newManga) {
        Manga manga = mangaService.modifyManga(id, newManga);
        return new ResponseEntity<>(manga, HttpStatus.OK);
    }

    @Operation(summary = "Elimina un manga")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina el manga", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "El manga no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/mangas/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteManga(@PathVariable long id) {
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
