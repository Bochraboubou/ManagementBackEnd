package com.example.managementbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Data
public class MaterielleLivreeMC  {
    @EmbeddedId
    private MaterielLivreeMCId id;
    @NotNull
    private  float quantiteeLivreeMC;
    @NotNull
    private float prix;

    @JsonIgnore
    @ManyToOne
    @MapsId("bonLivraisonMC_id") //This is the name of attr in Article realiseePK class
    @JoinColumn(name = "bonLivraisonMC_id")
    private BonLivraisonMC bonLivraisonMC;
    @JsonIgnore
    @ManyToOne
    @MapsId("article_id")
    @JoinColumn(name = "article_id")
    private Article article;

}
