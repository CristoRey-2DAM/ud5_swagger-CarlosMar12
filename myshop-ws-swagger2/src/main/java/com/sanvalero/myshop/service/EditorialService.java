package com.sanvalero.myshop.service;

import com.sanvalero.myshop.domain.Editorial;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author Carlos
 */
public interface EditorialService {

    Set<Editorial> findAll();
    Set<Editorial> findById(int Id);
    Editorial addEditorial(Editorial editorial);
    Editorial modifyEditorial(int id, Editorial newEditorial);
    void deleteEditorial(int id);
}
