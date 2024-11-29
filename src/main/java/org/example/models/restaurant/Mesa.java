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
    private int numeroMesa;       // Número de la mesa
    private Boolean disponible;   // Estado de disponibilidad
    private Boolean limpia;       // Estado de limpieza
    private Double posX;          // Posición X
    private Double posY;          // Posición Y

    // Métodos adicionales para gestionar el estado de la mesa
    public void ocupar() {
        this.disponible = false;
        this.limpia = false; // Suponemos que la mesa ya no está limpia al ocuparla
    }

    public void liberar() {
        this.disponible = true;
        this.limpia = false; // Suponemos que la mesa necesita limpieza al liberarse
    }

    public void limpiar() {
        this.limpia = true; // Marca la mesa como limpia
    }

    public boolean estaOcupada() {
        return !this.disponible; // La mesa está ocupada si no está disponible
    }

    public void setOcupada(boolean ocupada) {
        this.disponible = !ocupada; // Si está ocupada, no está disponible
        if (ocupada) {
            this.limpia = false; // Suponemos que al ocupar, la mesa ya no está limpia
        }
    }

}
