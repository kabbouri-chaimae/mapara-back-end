package com.para_space.parapharma_pace.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Produits" )
@Data @NoArgsConstructor
@AllArgsConstructor @ToString

public class Produit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduit;


    private String titre;
    private String description;
    private String Code_barres;
    private double prix;

    private String photo;
    private int quantitelivre;
    private int quantite;

    @ManyToOne
    private  Category category;
}
