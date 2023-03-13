package com.sanvalero.myshop.repository;

import com.sanvalero.myshop.domain.Editorial;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Repositorio de productos
 * @author Santiago Faci
 * @version Curso 2020-2021
 */
@Repository
public interface EditorialRepository extends CrudRepository<Editorial, Long> {

    Set<Editorial> findAll();
    Editorial findByName(String name);
    Set<Editorial> findByCodEditor(String codEditor);

}
