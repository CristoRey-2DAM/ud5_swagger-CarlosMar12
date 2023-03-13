package com.sanvalero.myshop.controller;

import com.sanvalero.myshop.domain.Editorial;
import com.sanvalero.myshop.exception.EditorialNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.sanvalero.myshop.controller.Response.NOT_FOUND;
import com.sanvalero.myshop.service.EditorialService;

/**
 * Controlador para productos
 * @author Santiago Faci
 * @version Curso 2020-2021
 */
@RestController
@Tag(name = "Editoriales", description = "Catálogo de editoriales")
public class EditorialController {

    private final Logger logger = LoggerFactory.getLogger(EditorialController.class);

    @Autowired
    private EditorialService editorialService;

    @Operation(summary = "Obtiene el listado de editoriales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de productos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Editorial.class)))),
    })
    @GetMapping(value = "/editoriales", produces = "application/json")
    public ResponseEntity<Set<Editorial>> getEditoriales(@RequestParam(value = "codEditor", defaultValue = "") String codEditor) {
        logger.info("inicio getEditoriales");
        Set<Editorial> editoriales = null;
        if (codEditor.equals(""))
            editoriales = editorialService.findAll();
        else
            editoriales = editorialService.findByCodEditor(codEditor);

        logger.info("fin getEditoriales");
        return new ResponseEntity<>(editoriales, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene una editorial determinada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe la editorial", content = @Content(schema = @Schema(implementation = Editorial.class))),
            @ApiResponse(responseCode = "404", description = "La editorial no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/editoriales/{id}", produces = "application/json")
    public ResponseEntity<Editorial> getEditorial(@PathVariable long id) {
        Editorial product = editorialService.findById(id)
                .orElseThrow(() -> new EditorialNotFoundException(id));

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Operation(summary = "Registra una nueva editorial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se registra la editorial", content = @Content(schema = @Schema(implementation = Editorial.class)))
    })
    @PostMapping(value = "/editoriales", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Editorial> addEditorial(@RequestBody Editorial editorial) {
        Editorial addedEditorial = editorialService.addEditorial(editorial);
        return new ResponseEntity<>(addedEditorial, HttpStatus.CREATED);
    }

    @Operation(summary = "Modifica una editorial en el catálogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica la editorial", content = @Content(schema = @Schema(implementation = Editorial.class))),
            @ApiResponse(responseCode = "404", description = "La editorial no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/editoriales/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Editorial> modifyEditorial(@PathVariable long id, @RequestBody Editorial newEditorial) {
        Editorial editorial = editorialService.modifyEditorial(id, newEditorial);
        return new ResponseEntity<>(editorial, HttpStatus.OK);
    }

    @Operation(summary = "Elimina una Editorial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina la editorial", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "La editorial no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/editoriales/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteEditorial(@PathVariable long id) {
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
