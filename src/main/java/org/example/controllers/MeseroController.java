package org.example.controllers;

import org.example.config.Constants;
import org.example.models.actors.Mesero;
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
    public void levantarPedido() {
        new Thread(() -> {
            try {
                // Mueve al mesero a la mesa asignada
                System.out.println("Mesero " + mesero.getNombre() + " se mueve hacia la mesa " + mesero.getMesaAsignada());


                // Simula el tiempo para tomar el pedido
                Thread.sleep(3000);

                // Levanta el pedido usando el modelo del mesero
                mesero.atenderPedido();
                System.out.println("Mesero " + mesero.getNombre() + " ha levantado el pedido en la mesa " + mesero.getMesaAsignada());
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
                System.out.println("Mesero " + mesero.getNombre() + " se mueve hacia la mesa " + mesero.getMesaAsignada());
                meseroView.atenderComensal(500,600);

                // Simula el tiempo para limpiar la mesa
                Thread.sleep(2000);

                // Limpia la mesa usando el modelo del mesero
                mesero.limpiarMesa();
                System.out.println("Mesero " + mesero.getNombre() + " ha limpiado la mesa " + mesero.getMesaAsignada());
            } catch (InterruptedException e) {
                System.err.println("Error mientras el mesero limpiaba la mesa: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
