package com.sanvalero.myshop.exception;

/**
 * Excepción de producto no encontrado
 * @author Santiago Faci
 * @version Curso 2020-2021
 */
public class MangaNotFoundException extends RuntimeException {

    public MangaNotFoundException() {
        super();
    }

    public MangaNotFoundException(String message) {
        super(message);
    }

    public MangaNotFoundException(long id) {
        super("Product not found: " + id);
    }
}
