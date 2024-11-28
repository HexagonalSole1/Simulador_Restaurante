package org.example.views;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.dsl.FXGL;
import org.example.views.components.ComensalComponent;

public class ComensalView {

    private Entity comensal;

    public ComensalView(double startX, double startY) {
        // Crear la entidad del comensal con su imagen y componente
        this.comensal = FXGL.entityBuilder()
                .at(startX, startY) // Posición inicial
                .view("cliente.png") // Imagen
                .scale(0.5,0.5)
                .with(new ComensalComponent()) // Agrega el componente
                .buildAndAttach();
    }

    public void moverAMesa(double mesaX, double mesaY) {
        // Mover al comensal a la mesa
        comensal.getComponent(ComensalComponent.class).moveToMesa(mesaX, mesaY);
    }

    public void salir() {
        // Hacer que el comensal salga del restaurante
        comensal.getComponent(ComensalComponent.class).salirDelRestaurante();
    }

    // Método para obtener la posición actual del comensal
    public double[] obtenerPosicion() {
        return new double[] { comensal.getX(), comensal.getY() };
    }

    // Método para obtener la entidad del comensal (si se necesita para interactuar directamente)
    public Entity getEntity() {
        return comensal;
    }
}
