package org.example.views;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Point2D;

public class ComidaView {

    private Entity comidaEntity;

    public ComidaView(double startX, double startY) {
        // Crear la entidad visual del ramen
        this.comidaEntity = FXGL.entityBuilder()
                .at(startX, startY) // Posición inicial
                .view("ramen.png") // Imagen del ramen
                .scale(0.5, 0.5)   // Escala de la imagen
                .buildAndAttach();
    }

    // Mover la comida hacia una posición destino (por ejemplo, la mesa)
    public void moverHaciaCliente(double x, double y) {

        comidaEntity.translateTowards(new Point2D(x, y), 100); // Velocidad de movimiento
    }

    // Eliminar la comida de la escena (por ejemplo, cuando se entrega)
    public void entregar() {
        FXGL.runOnce(() -> comidaEntity.removeFromWorld(), javafx.util.Duration.seconds(1)); // Simula el tiempo de entrega
    }

    public Entity getEntity() {
        return comidaEntity;
    }
}
