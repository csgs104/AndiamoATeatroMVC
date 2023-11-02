package org.example.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sala {
    private int id;
    private String nome;
    private int n_posti;
    private int sede;
}