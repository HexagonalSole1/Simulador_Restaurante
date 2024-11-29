package org.example.views.components;

import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import org.example.models.restaurant.Mesa;
import org.example.monitores.MesaMonitor;
import org.example.views.ComidaView;

import javax.swing.*;


public class MeseroComponent extends Component {
    private Point2D destino; // Posición de la mesa o cliente asignado
    private double velocidad = 120; // Velocidad del mesero

    // Asignar destino para atender
    public void moverAMesa(double mesaX, double mesaY) {
        ComponentInputMap mesero = null;
        JComponent component = mesero.getComponent();
        if (component != null) {
            System.out.println("Configurando destino del mesero hacia: (" + mesaX + ", " + mesaY + ")");
            component.move((int) mesaX, (int) mesaY); // Configura el destino
        } else {
            System.err.println("No se encontró el componente MeseroComponent para el mesero.");
        }
    }

    public void moverAHacia(double x, double y) {
        destino = new Point2D(x, y); // Asegúrate de que `destino` se configura
        System.out.println("Destino configurado: " + destino); // Depuración
    }



    @Override
    public void onUpdate(double tpf) {
        if (destino != null) {
            System.out.println("Moviendo hacia destino: " + destino); // Depuración
            entity.translateTowards(destino, velocidad * tpf); // Mover entidad hacia el destino

            // Verificar si se ha alcanzado el destino
            if (entity.getPosition().distance(destino) < 5) { // Umbral de 5 píxeles
                destino = null; // Resetear el destino
                System.out.println("Mesero llegó al destino.");
            }
        } else {
            System.out.println("No hay destino configurado para el mesero.");
        }
    }


    public void regresarAPosicion(double x, double y) {
        moverAHacia(2000, 100); // Usar la misma lógica para regresar a la posición inicial
    }
}

