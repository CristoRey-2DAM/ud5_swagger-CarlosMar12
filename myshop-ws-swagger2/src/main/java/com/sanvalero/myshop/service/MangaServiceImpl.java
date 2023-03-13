package com.sanvalero.myshop.service;


import com.sanvalero.myshop.domain.Manga;
import com.sanvalero.myshop.exception.MangaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Set;

import com.sanvalero.myshop.repository.MangaRepository;
import java.util.Optional;

/**
 * Implementación del Service de pedidos
 * @author Santiago Faci
 * @version Curso 2020-2021
 */
@Service
public class MangaServiceImpl implements MangaService {

     @Autowired
    private MangaRepository mangaRepository;
    
    
    @Override
    public Set<Manga> findAll() {
        return mangaRepository.findAll();
    }

    @Override
    public Set<Manga> findByCodManga(String codManga) {
        return mangaRepository.findByCodManga(codManga);
    }

    @Override
    public Optional<Manga> findById(long id) {
         return mangaRepository.findById(id);
    }

    @Override
    public Manga addManga(Manga manga) {
         return mangaRepository.save(manga);
    }

    @Override
    public Manga modifyManga(long id, Manga newManga) {
       Manga manga = mangaRepository.findById(id)
                .orElseThrow(() -> new MangaNotFoundException(id));
        newManga.setId(manga.getId());
        return mangaRepository.save(newManga);
    }

    @Override
    public void deleteManga(long id) {
        mangaRepository.findById(id)
                .orElseThrow(() -> new MangaNotFoundException(id));
        mangaRepository.deleteById(id);
    }

    


   
}
