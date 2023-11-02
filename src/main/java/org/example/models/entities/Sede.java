package org.example.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sede {
    private int id;
    private String nome;
    private String indirizzo;
    private String comune;
    private boolean al_chiuso;
}