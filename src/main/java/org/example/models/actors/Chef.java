package org.example.models.actors;

import org.example.models.core.Persona;
import org.example.monitores.ComidasMonitor;
import org.example.utils.LoggerDepuracionFXGL;

public class Chef extends Persona {
    private final ComidasMonitor comidasMonitor;
    private boolean ocupado; // Estado del chef
    private int tiempoPreparacion; // Tiempo base para preparar una comida (en segundos)

    // Constructor
    public Chef(String nombre, int id, ComidasMonitor comidasMonitor, int tiempoPreparacion) {
        super(nombre, id);
        this.comidasMonitor = comidasMonitor;
        this.tiempoPreparacion = tiempoPreparacion;
        this.ocupado = false;
    }

    // Método para iniciar el ciclo de trabajo del chef
    public void iniciarTrabajo() {
        new Thread(() -> {
            while (true) {
                try {
                    // Tomar un pedido del monitor
                    int idMesa = comidasMonitor.tomarPedido();
                    prepararOrden(idMesa); // Procesar el pedido
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Chef " + getNombre() + " fue interrumpido.");
                    break;
                }
            }
        }).start();
    }

    // Simula la preparación de una orden
    private void prepararOrden(int idMesa) {
        ocupado = true;
        System.out.println("Chef " + getNombre() + " está preparando el pedido de la mesa " + idMesa);

        // Simula el tiempo de preparación
        try {
            Thread.sleep(tiempoPreparacion * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Preparación interrumpida para el Chef " + getNombre());
        }

        System.out.println("Chef " + getNombre() + " finalizó el pedido de la mesa " + idMesa);
        ocupado = false;
    }

    // Obtener estado del chef
    public boolean isOcupado() {
        return ocupado;
    }

    @Override
    public void realizarAccion() {
        LoggerDepuracionFXGL.log("Chef " + nombre + " está verificando disponibilidad de mesas.");
    }
}
