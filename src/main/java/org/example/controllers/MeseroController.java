package org.example.controllers;

import org.example.config.Constants;
import org.example.models.actors.Mesero;
import org.example.models.restaurant.Mesa;
import org.example.views.MeseroView;

public class MeseroController {
    private final Mesero mesero;
    private final MeseroView meseroView;

    private  ComensalController comensalController;

    public MeseroController(Mesero mesero, MeseroView meseroView) {
        this.mesero = mesero;
        this.meseroView = meseroView;
    }

    // Método para levantar un pedido en la mesa asignada

    // Modifica este método para que acepte un argumento de tipo Mesa
    public void levantarPedido(Mesa mesa) {
        new Thread(() -> {
            try {
                // Mueve al mesero hacia la mesa
                double mesaX = mesa.getPosX();
                double mesaY = mesa.getPosY();
                meseroView.atenderComensal(mesaX, mesaY); // Mueve la vista del mesero

                // Simula el tiempo para tomar el pedido
                Thread.sleep(3000); // El mesero tarda 3 segundos en tomar el pedido

                // Levanta el pedido usando el modelo del mesero
                mesero.atenderPedido();
                System.out.println("Mesero " + mesero.getNombre() + " ha levantado el pedido en la mesa " + mesa.getNumeroMesa());
            } catch (InterruptedException e) {
                System.err.println("Error mientras el mesero levantaba el pedido: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }).start();
    }


    // Método para limpiar la mesa asignada
    public void limpiarMesa() {
        new Thread(() -> {
            try {
                // Mueve al mesero a la mesa asignada
                double mesaX = mesero.getMesaAsignada();
                double mesaY = mesero.getMesaAsignada();
                meseroView.atenderComensal(mesaX, mesaY); // Mueve la vista del mesero

                // Simula el tiempo para limpiar la mesa
                Thread.sleep(2000);

                // Limpia la mesa usando el modelo del mesero
                mesero.limpiarMesa();
                System.out.println("Mesero " + mesero.getNombre() + " ha limpiado la mesa " + mesero.getMesaAsignada());

                // Regresa el mesero a su posición inicial
                meseroView.regresarAPosicion(0, 0); // Usa la posición que desees para el regreso
            } catch (InterruptedException e) {
                System.err.println("Error mientras el mesero limpiaba la mesa: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }).start();
    }

}
