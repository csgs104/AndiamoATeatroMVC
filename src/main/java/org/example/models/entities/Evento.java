package org.example.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evento {
    private int id;
    private String nome;
    private String genere;
    private double prezzo;
    private String durata;
    private Date data;
    private int sala;
}