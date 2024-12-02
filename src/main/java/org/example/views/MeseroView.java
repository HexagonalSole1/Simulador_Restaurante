package org.example.views;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.dsl.FXGL;
import org.example.views.components.MeseroComponent;

public class MeseroView {

    private Entity mesero;

    public MeseroView(double startX, double startY) {
        this.mesero = FXGL.entityBuilder()
                .at(startX, startY)
                .view("mesera.png")
                .scale(0.5,0.5)
                .with(new MeseroComponent())
                .buildAndAttach();
    }

    public boolean atenderComensal(double comensalX, double comensalY) {
        MeseroComponent meseroComponent = mesero.getComponent(MeseroComponent.class);
        meseroComponent.atenderComensal(comensalX, comensalY);

        // Esperar hasta que el mesero llegue al destino
        while (!meseroComponent.haLlegadoAlDestino()) {
            try {
                Thread.sleep(100); // Espera 100 ms antes de verificar nuevamente
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Movimiento interrumpido.");
                return false;
            }
        }

        return true; // El mesero llegó al destino
    }

    public boolean llevarPedidoACocina(double cocinaX, double cocinaY) {
        MeseroComponent meseroComponent = mesero.getComponent(MeseroComponent.class);
        meseroComponent.llevarPedidoACocina(cocinaX, cocinaY);

        // Esperar hasta que el mesero llegue al destino
        while (!meseroComponent.haLlegadoAlDestino()) {
            try {
                Thread.sleep(100); // Espera 100 ms antes de verificar nuevamente
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Movimiento interrumpido.");
                return false;
            }
        }

        return true; // El mesero llegó al destino
    }

    public boolean regresarAPosicion(double posX, double posY) {
        MeseroComponent meseroComponent = mesero.getComponent(MeseroComponent.class);
        meseroComponent.regresarAPosicionInicial(posX, posY);
        // Esperar hasta que el mesero llegue al destino
        while (!meseroComponent.haLlegadoAlDestino()) {
            try {
                Thread.sleep(100); // Espera 100 ms antes de verificar nuevamente
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Movimiento interrumpido.");
                return false;
            }
        }

        return true; // El mesero llegó al destino
    }
}

