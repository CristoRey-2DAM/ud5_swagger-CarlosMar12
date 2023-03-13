package com.sanvalero.myshop.service;



import com.sanvalero.myshop.domain.Manga;
import java.util.Optional;
import java.util.Set;

/**
 * Service de pedidos
 * @author Santiago Faci
 * @version Curso 2020-2021
 */
public interface MangaService {

    Set<Manga> findAll();
    Set<Manga> findByCodManga(String codManga);
    Optional<Manga> findById(long id);
    Manga addManga(Manga manga);
    Manga modifyManga(long id, Manga newManga);
    void deleteManga(long id);
}
