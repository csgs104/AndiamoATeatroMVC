package org.example.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Posto {
    private int id;
    private String fila;
    private int sedia;
    private int sala;
}