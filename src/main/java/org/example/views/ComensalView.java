package org.example.views;

import com.almasb.fxgl.entity.Entity;
import javafx.util.Duration;
import org.example.views.components.ComensalComponent;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Point2D;


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

    public void moverAMesa(double mesaX, double mesaY) {
        // Coordenadas actuales
        double startX = comensal.getX();
        double startY = comensal.getY();

        // Diferencia de las coordenadas
        double deltaX = mesaX - startX;
        double deltaY = mesaY - startY;

        // Pasos para el movimiento
        int steps = 100; // Número de actualizaciones
        double stepX = deltaX / steps;
        double stepY = deltaY / steps;

        // Temporizador para realizar el movimiento paso a paso
        FXGL.run(() -> {
            // Verifica si se alcanzó la posición final
            if (Math.abs(comensal.getX() - mesaX) < Math.abs(stepX) &&
                    Math.abs(comensal.getY() - mesaY) < Math.abs(stepY)) {
                comensal.setPosition(mesaX, mesaY); // Ajusta a la posición final
                return; // Detén el movimiento
            }

            // Actualiza la posición del comensal
            comensal.setPosition(
                    comensal.getX() + stepX,
                    comensal.getY() + stepY
            );
        }, Duration.millis(0.02)); // Intervalo en segundos (20 ms)
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
