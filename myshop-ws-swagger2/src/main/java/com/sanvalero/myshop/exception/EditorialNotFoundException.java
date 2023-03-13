package com.sanvalero.myshop.exception;

/**
 * Excepción de producto no encontrado
 * @author Santiago Faci
 * @version Curso 2020-2021
 */
public class EditorialNotFoundException extends RuntimeException {

    public EditorialNotFoundException() {
        super();
    }

    public EditorialNotFoundException(String message) {
        super(message);
    }

    public EditorialNotFoundException(long id) {
        super("Product not found: " + id);
    }
}
