package org.example.models.restaurant;

public class Comida {

    private final int idMesa; // ID de la mesa asociada
    private String estado;    // Estado de la comida: "en preparación", "lista", "entregada"

    public Comida(int idMesa) {
        this.idMesa = idMesa;
        this.estado = "en preparación"; // Estado inicial
    }

    public int getIdMesa() {
        return idMesa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
        System.out.println("Estado de la comida cambiado a: " + estado);
    }
}
