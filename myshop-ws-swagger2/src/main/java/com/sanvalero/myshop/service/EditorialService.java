package com.sanvalero.myshop.service;

import com.sanvalero.myshop.domain.Editorial;

import java.util.Optional;
import java.util.Set;

/**
 * Service de productos
 * @author Santiago Faci
 * @version Curso 2020-2021
 */
public interface EditorialService {

    Set<Editorial> findAll();
    Set<Editorial> findByCodEditor(String codEditor);
    Optional<Editorial> findById(long id);
    Editorial addEditorial(Editorial editorial);
    Editorial modifyEditorial(long id, Editorial newEditorial);
    void deleteEditorial(long id);
}
