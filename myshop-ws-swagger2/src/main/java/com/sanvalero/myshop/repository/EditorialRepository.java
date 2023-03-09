/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanvalero.myshop.repository;

import com.sanvalero.myshop.domain.Editorial;
import java.util.Set;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos
 */
@Repository
public interface EditorialRepository extends CrudRepository<Editorial, Long>{

    Set<Editorial> findAll();
    Editorial findById_Editorial(int id);
    Editorial deleteEditorial(int id);

    public Object findById(int id);
}
