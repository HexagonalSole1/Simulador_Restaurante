package org.example.views.components;

import com.almasb.fxgl.entity.component.Component;
import org.example.models.restaurant.Comida;
import org.example.monitores.ComidasMonitor;
import org.example.views.ComidaView;

public class ChefComponent extends Component {
    private final ComidasMonitor comidasMonitor; // Monitor de comidas
    private boolean ocupado; // Estado del chef
    private final int tiempoPreparacion; // Tiempo base para preparar un pedido (en segundos)

    // Constructor
    public ChefComponent(ComidasMonitor comidasMonitor, int tiempoPreparacion) {
        this.comidasMonitor = comidasMonitor;
        this.tiempoPreparacion = tiempoPreparacion;
        this.ocupado = false;
    }

    // Método para iniciar el trabajo del chef
    public void iniciarTrabajo() {
        new Thread(() -> {
            while (true) {
                try {
                    // Tomar un pedido del monitor
                    int idMesa = comidasMonitor.tomarPedido();
                    prepararOrden(idMesa);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("ChefComponent interrumpido.");
                    break;
                }
            }
        }).start();
    }

    public ComidaView prepararComida(int idMesa, double startX, double startY) {
        System.out.println("Chef está preparando ramen para la mesa " + idMesa);

        // Crear el modelo de la comida
        Comida comida = new Comida(idMesa);
        comida.setEstado("lista"); // Cambiar el estado a "lista"

        // Crear la vista del ramen
        return new ComidaView(startX, startY);
    }

    // Simula la preparación de una orden
    private void prepararOrden(int idMesa) {
        ocupado = true;
        System.out.println("Chef está preparando el pedido de la mesa " + idMesa);

        // Simula el tiempo de preparación
        try {
            Thread.sleep(tiempoPreparacion * 1000); // Tiempo en milisegundos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Preparación interrumpida.");
        }

        System.out.println("Chef finalizó el pedido de la mesa " + idMesa);
        ocupado = false;
    }

    // Devuelve el estado actual del chef
    public boolean isOcupado() {
        return ocupado;
    }
}
