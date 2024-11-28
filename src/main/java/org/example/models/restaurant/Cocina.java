package org.example.models.restaurant;

import org.example.models.actors.Comensal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Cocina {
    private final ExecutorService poolCocineros;

    public Cocina(int cantidadCocineros) {
        this.poolCocineros = Executors.newFixedThreadPool(cantidadCocineros);
    }

    public void prepararPedido(Comensal comensal) {
        poolCocineros.submit(() -> {
            try {
                System.out.println("Cocinero: Preparando pedido para el comensal " + comensal.getId());
                Thread.sleep(2000); // Simula tiempo de preparación
                System.out.println("Cocinero: Pedido listo para el comensal " + comensal.getId());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
}