/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanvalero.myshop.service;

import com.sanvalero.myshop.domain.Manga;
import com.sanvalero.myshop.exception.MangaNotFoundException;
import com.sanvalero.myshop.repository.MangaRepository;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Carlos
 */
public class MangaServiceImpl implements MangaService{

    @Autowired
    private MangaRepository mangaRepository;
    
    
    @Override
    public Set<Manga> findAll() {
         return mangaRepository.findAll();
    }

    @Override
    public Set<Manga> findById(int Id) {
        return (Set<Manga>) mangaRepository.findById_Manga(Id);
    }

    @Override
    public Manga addManga(Manga manga) {
        return mangaRepository.save(manga);
    }

    @Override
    public Manga modifyManga(int id, Manga newManga) {
        Manga manga = (Manga) mangaRepository.findById_Manga(id);
         if(manga == null){
             throw new MangaNotFoundException(id);
         }else{
        newManga.setId_Manga(manga.getId_Manga());
        return mangaRepository.save(newManga);
         }
    }

    @Override
    public void deleteManga(int id) {
        Manga manga = (Manga) mangaRepository.findById_Manga(id);
         if(manga == null){
             throw new MangaNotFoundException(id);
         }else{
        mangaRepository.deleteManga(id);
         }
    }
    
}
