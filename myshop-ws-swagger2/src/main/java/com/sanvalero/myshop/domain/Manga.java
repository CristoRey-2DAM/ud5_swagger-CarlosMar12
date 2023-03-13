package com.sanvalero.myshop.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "mangas")
public class Manga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column
    private String genero;
    
    @Column
    private String frecPublicacion;
    
    @Column
    private String  Anime;

    @Column
    public String codManga;
    
    @Column
    public String estiloDibujo;
     
     
//    @OneToMany(mappedBy = "order")
//    private List<OrderDetail> details;
//
//    public Manga() {
//        details = new ArrayList<>();
//    }
//
//    public void addDetail(OrderDetail detail) {
//        details.add(detail);
//    }
}
