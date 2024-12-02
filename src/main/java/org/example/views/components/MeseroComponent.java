package org.example.views.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;
import org.example.views.ComidaView;

public class MeseroComponent extends Component {
    private double velocidad = 150; // Velocidad del mesero (píxeles por segundo)
    private Point2D destino; // Posición hacia donde se dirige el mesero
    private boolean ocupado = false; // Estado del mesero
    private String tareaActual = ""; // Identifica la tarea actual del mesero

    // Método para entregar comida a una mesa
    public void entregarComida(ComidaView comidaView, double mesaX, double mesaY) {
        System.out.println("Mesero lleva la comida a la mesa...");

        // Mover el objeto comida hacia la mesa
        comidaView.moverHacia(mesaX, mesaY);

        // Simular el tiempo de entrega
        FXGL.runOnce(() -> {
            System.out.println("Comida entregada en la mesa.");
            comidaView.entregar(); // Eliminar la comida de la escena
        }, javafx.util.Duration.seconds(2)); // Tiempo de entrega simulado
    }

    // Método para atender a un comensal
    public void atenderComensal(double x, double y) {
        destino = new Point2D(x, y); // Establecer el destino
        ocupado = true; // Indicar que el mesero está ocupado
        tareaActual = "atenderComensal"; // Establecer la tarea actual
        System.out.println("Mesero se dirige a atender al comensal en la posición (" + x + ", " + y + ").");
    }

    // Método para llevar un pedido a la cocina
    public void llevarPedidoACocina(double x, double y) {
        destino = new Point2D(x, y); // Establecer el destino
        ocupado = true; // Indicar que el mesero está ocupado
        tareaActual = "llevarPedidoACocina"; // Establecer la tarea actual
        System.out.println("Mesero se dirige a la cocina con el pedido en la posición (" + x + ", " + y + ").");
    }

    // Método para verificar si el mesero ha llegado al destino
    public boolean haLlegadoAlDestino() {
        return destino == null; // Si el destino es null, significa que llegó
    }

    // Método para regresar a la posición inicial
    public void regresarAPosicionInicial(double x, double y) {
        destino = new Point2D(x, y); // Establecer el destino
        ocupado = false; // Indicar que el mesero está disponible
        tareaActual = "regresar"; // Establecer la tarea actual
        System.out.println("Mesero regresa a la posición inicial en (" + x + ", " + y + ").");
    }

    @Override
    public void onUpdate(double tpf) {
        if (destino != null) {
            // Mover al mesero hacia el destino
            entity.translateTowards(destino, velocidad * tpf);

            // Verificar si llegó al destino
            if (entity.getPosition().distance(destino) < 5) {
                destino = null; // Reiniciar el destino
                switch (tareaActual) {
                    case "atenderComensal":
                        System.out.println("Mesero llegó al comensal y está atendiendo.");
                        break;
                    case "llevarPedidoACocina":
                        System.out.println("Mesero entregó el pedido a la cocina.");
                        break;
                    case "regresar":
                        System.out.println("Mesero regresó a la posición inicial.");
                        break;
                    default:
                        System.out.println("Mesero completó una tarea desconocida.");
                        break;
                }
                ocupado = false; // Liberar al mesero después de completar la tarea
            }
        }
    }

    // Consultar si el mesero está ocupado
    public boolean isOcupado() {
        return ocupado;
    }
}
