package org.example.models.entities;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Biglietto {
    private int id;
    private int utente;
    private int evento;
    private int posto;
}