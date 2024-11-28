package org.example.views.components;

import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;

public class MeseroComponent extends Component {
    private double velocidad = 150; // Velocidad del mesero (píxeles por segundo)
    private Point2D destino; // Posición hacia donde se dirige el mesero
    private boolean ocupado = false; // Estado del mesero

    // Método para atender a un comensal
    public void atenderComensal(double x, double y) {
        destino = new Point2D(x, y); // Establecer el destino
        ocupado = true; // Indicar que el mesero está atendiendo
    }

    // Método para regresar a su posición inicial
    public void regresarAPosicionInicial(double x, double y) {
        destino = new Point2D(x, y); // Establecer el destino
        ocupado = false; // Indicar que está regresando y disponible
    }

    @Override
    public void onUpdate(double tpf) {
        if (destino != null) {
            // Mover hacia el destino
            entity.translateTowards(destino, velocidad * tpf);

            // Detenerse al llegar al destino
            if (entity.getPosition().distance(destino) < 5) {
                destino = null; // Reiniciar destino
                if (ocupado) {
                    System.out.println("Mesero llegó al comensal.");
                } else {
                    System.out.println("Mesero regresó a su posición inicial.");
                }
            }
        }
    }

    // Consultar si el mesero está ocupado
    public boolean isOcupado() {
        return ocupado;
    }
}
