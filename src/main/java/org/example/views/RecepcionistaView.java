package org.example.views;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.dsl.FXGL;
import org.example.views.components.RecepcionistaComponent;

public class RecepcionistaView {

    private Entity recepcionista;

    public RecepcionistaView(double startX, double startY) {
        // Crear la entidad para el recepcionista
        this.recepcionista = FXGL.entityBuilder()
                .at(startX, startY) // Ubicación inicial del recepcionista
                .view("recepcionista.png") // Asignar imagen del recepcionista
                .with(new RecepcionistaComponent()) // Añadir el componente de lógica
                .buildAndAttach();
    }

    // Método para verificar disponibilidad de mesas
    public boolean verificarDisponibilidad() {
        return recepcionista.getComponent(RecepcionistaComponent.class).verificarDisponibilidadMesas();
    }

    // Método para asignar mesa a un comensal
    public void asignarMesa() {
        recepcionista.getComponent(RecepcionistaComponent.class).asignarMesa();
    }

    // Método para liberar una mesa cuando el comensal se va
    public void liberarMesa() {
        recepcionista.getComponent(RecepcionistaComponent.class).liberarMesa();
    }

    public Entity getEntity() {
        return recepcionista;
    }
}
