package org.example.models.restaurant;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mesa {
    private int numeroMesa;
    private Boolean Disponibilidad;
    private Boolean isAtendida;
    private Boolean isClean;
    private Boolean isPresentComensal;
    private Double posX;
    private Double posY;
}