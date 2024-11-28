package org.example.views.components;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Point2D;

public class ComidaComponent extends Component {

    private String estado; // Estado de la comida: en preparación, lista, entregada
    private int idMesa;    // ID de la mesa asociada

    public ComidaComponent(int idMesa) {
        this.idMesa = idMesa;
        this.estado = "en preparación"; // Estado inicial
    }

    // Obtener el estado de la comida
    public String getEstado() {
        return estado;
    }

    // Cambiar el estado de la comida
    public void setEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
        System.out.println("Estado de la comida cambiado a: " + nuevoEstado);
    }

    // Mover la comida hacia un destino (animación)
    public void moverHacia(double x, double y) {
        Point2D destino = new Point2D(x, y);
        entity.translateTowards(destino, 100); // Velocidad de movimiento
    }

    // Método que se ejecuta al entregar la comida
    public void entregar() {
        setEstado("entregada");
        FXGL.runOnce(() -> {
            System.out.println("Comida entregada en la mesa " + idMesa);
            entity.removeFromWorld(); // Elimina la comida de la escena
        }, javafx.util.Duration.seconds(1)); // Simula el tiempo de entrega
    }

    @Override
    public void onUpdate(double tpf) {
        // Lógica para actualizaciones por frame (si es necesario)
    }
}
