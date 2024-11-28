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

    public void moverAMesa(double mesaX, double mesaY) {
        // Mover al comensal a la mesa
        comensal.translate(mesaX, mesaY); // Suponiendo que translate cambia su posición
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
