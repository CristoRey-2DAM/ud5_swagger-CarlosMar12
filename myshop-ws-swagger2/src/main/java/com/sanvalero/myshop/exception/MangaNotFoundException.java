/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanvalero.myshop.exception;


/**
 *
 * @author Carlos
 */
public class MangaNotFoundException extends RuntimeException {

    public MangaNotFoundException() {
        super();
    }
    public MangaNotFoundException(int id) {
        super("Manga not found: " + id);
    }
}