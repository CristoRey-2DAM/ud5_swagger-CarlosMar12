package com.sanvalero.myshop.service;

import com.sanvalero.myshop.domain.Editorial;
import com.sanvalero.myshop.exception.EditorialNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import com.sanvalero.myshop.repository.EditorialRepository;

/**
 * Implementación del Service de productos
 * @author Santiago Faci
 * @version Curso 2020-2021
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
    public Optional<Editorial> findById(long id) {
        return editorialRepository.findById(id);
    }

    @Override
    public Set<Editorial> findByCodEditor(String codEditor) {
          return editorialRepository.findByCodEditor(codEditor);
    }

    @Override
    public Editorial addEditorial(Editorial editorial) {
        return editorialRepository.save(editorial);
    }

    @Override
    public Editorial modifyEditorial(long id, Editorial newEditorial) {
        Editorial editorial = editorialRepository.findById(id)
                .orElseThrow(() -> new EditorialNotFoundException(id));
        newEditorial.setId(editorial.getId());
        return editorialRepository.save(newEditorial);
    }

    @Override
    public void deleteEditorial(long id) {
        editorialRepository.findById(id)
                .orElseThrow(() -> new EditorialNotFoundException(id));
        editorialRepository.deleteById(id);
    }
}
