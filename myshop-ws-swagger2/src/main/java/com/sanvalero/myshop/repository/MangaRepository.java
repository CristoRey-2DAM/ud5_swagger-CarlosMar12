/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanvalero.myshop.repository;


import com.sanvalero.myshop.domain.Manga;
import java.util.Set;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos
 */
@Repository
public interface MangaRepository extends CrudRepository<Manga, Long>{
    Set<Manga> findAll();
    Manga findById_Manga(int id);
    Manga deleteManga(int id);
}
