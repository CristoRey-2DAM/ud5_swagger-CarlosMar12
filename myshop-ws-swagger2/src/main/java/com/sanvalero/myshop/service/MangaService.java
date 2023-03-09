/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanvalero.myshop.service;


import com.sanvalero.myshop.domain.Manga;
import java.util.Set;

/**
 *
 * @author Carlos
 */
public interface MangaService {
     Set<Manga> findAll();
    Set<Manga> findById(int Id);
   Manga addManga(Manga manga);
    Manga modifyManga(int id, Manga newManga);
    void deleteManga(int id);
}
