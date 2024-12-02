package org.example.views;

import com.almasb.fxgl.entity.Entity;
import org.example.views.components.ComensalComponent;

import static com.almasb.fxgl.dsl.FXGL.*;

public class ComensalView {

    private Entity comensal;

    public ComensalView(double startX, double startY) {
        // Crear la entidad del comensal con su imagen y componente
        this.comensal = entityBuilder()
                .at(startX, startY) // Posición inicial
                .view("cliente.png") // Imagen
                .scale(0.5,0.5)
                .with(new ComensalComponent()) // Agrega el componente
                .buildAndAttach();
    }

    public boolean moverAMesa(double mesaX, double mesaY) {
        ComensalComponent component = comensal.getComponent(ComensalComponent.class);
        if (component != null) {
            component.moverAHaciaMesa(mesaX, mesaY); // Iniciar el movimiento

            // Esperar hasta que el comensal llegue al destino
            while (!component.estaEnDestino()) {
                try {
                    Thread.sleep(100); // Breve espera antes de verificar nuevamente
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Movimiento interrumpido.");
                    return false;
                }
            }

            return true; // El comensal llegó al destino
        } else {
            System.err.println("No se encontró el componente ComensalComponent para el comensal.");
        }
        return false;
    }



    public void salir() {
        // Hacer que el comensal salga del restaurante
        comensal.removeFromWorld();
    }

    public double[] obtenerPosicion() {
        return new double[] { comensal.getX(), comensal.getY() };
    }

    public Entity getEntity() {
        return comensal;
    }
}