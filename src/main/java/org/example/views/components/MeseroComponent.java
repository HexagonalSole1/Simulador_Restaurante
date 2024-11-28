package org.example.views.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;
import org.example.views.ComidaView;


public class MeseroComponent extends Component {
    private double velocidad = 150; // Velocidad del mesero (píxeles por segundo)
    private Point2D destino; // Posición hacia donde se dirige el mesero
    private boolean ocupado = false; // Estado del mesero
    public void entregarComida(ComidaView comidaView, double mesaX, double mesaY) {
        System.out.println("Mesero lleva el ramen a la mesa...");

        // Mover el ramen hacia la mesa
        comidaView.moverHacia(mesaX, mesaY);

        // Entregar el ramen
        FXGL.runOnce(() -> {
            System.out.println("Ramen entregado en la mesa.");
            comidaView.entregar(); // Eliminar la comida de la escena
        }, javafx.util.Duration.seconds(2)); // Tiempo de entrega simulado
    }
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
