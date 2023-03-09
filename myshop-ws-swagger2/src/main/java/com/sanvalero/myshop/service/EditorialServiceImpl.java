/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanvalero.myshop.service;

import com.sanvalero.myshop.domain.Editorial;
import com.sanvalero.myshop.exception.EditorialNotFoundException;
import com.sanvalero.myshop.exception.ProductNotFoundException;
import com.sanvalero.myshop.repository.EditorialRepository;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Carlos
 */
@Service
public class EditorialServiceImpl implements EditorialService {

    @Autowired
    private EditorialRepository editorialRepository;
    
    @Override
    public Set<Editorial> findAll() {
        return editorialRepository.findAll();
    }

   

    @Override
    public Editorial addEditorial(Editorial editorial) {
        return editorialRepository.save(editorial);
    }

  

    @Override
    public void deleteEditorial(int id) {
         Editorial editorial = (Editorial) editorialRepository.findById(id);
         if(editorial == null){
             throw new EditorialNotFoundException(id);
         }else{
        editorialRepository.deleteEditorial(id);
         }
    }

    @Override
    public Set<Editorial> findById(int id) {
         return (Set<Editorial>) editorialRepository.findById(id);
    }

    @Override
    public Editorial modifyEditorial(int id, Editorial newEditorial) {
         Editorial editorial = (Editorial) editorialRepository.findById(id);
         if(editorial == null){
             throw new EditorialNotFoundException(id);
         }else{
        newEditorial.setId(editorial.getId());
        return editorialRepository.save(newEditorial);
         }
    }

  
   
}
