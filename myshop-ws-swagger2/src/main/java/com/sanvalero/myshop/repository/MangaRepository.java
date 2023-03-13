package com.sanvalero.myshop.repository;

import com.sanvalero.myshop.domain.Manga;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Repositorio de pedidos
 * @author Santiago Faci
 * @version Curso 2020-2021
 */
@Repository
public interface MangaRepository extends CrudRepository<Manga, Long> {

    Set<Manga> findAll();
    Manga findByGenero(String genero);
    Set<Manga> findByCodManga(String codManga);
}
