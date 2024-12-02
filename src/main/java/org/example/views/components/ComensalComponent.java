package org.example.views.components;

import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;

public class ComensalComponent extends Component {
    private Point2D destino; // Posición de la mesa asignada
    private double velocidad = 100; // Velocidad del comensal

    // Asignar una mesa y establecer su destino
    public void moverAHaciaMesa(double x, double y) {
        destino = new Point2D(x, y);
    }

    // Verificar si el comensal llegó al destino
    public boolean estaEnDestino() {
        return destino == null; // Si el destino es null, ya llegó
    }

    @Override
    public void onUpdate(double tpf) {
        if (destino != null) {
            entity.translateTowards(destino, velocidad * tpf); // Mover hacia la mesa
            if (entity.getPosition().distance(destino) < 5) { // Llega al destino
                destino = null; // Detener el movimiento
                System.out.println("Comensal llegó a la mesa.");
            }
        }
    }
}
