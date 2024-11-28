package org.example.views.components;

import com.almasb.fxgl.entity.component.Component;

public class MesaComponent extends Component {
    private int numeroMesa;
    private boolean ocupada; // Estado de la mesa: libre (false) u ocupada (true)

    public MesaComponent(int numeroMesa) {
        this.numeroMesa = numeroMesa;
        this.ocupada = false; // Por defecto, la mesa está libre
    }
    // Cambiar el estado de la mesa
    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;

        // Cambiar visualización según el estado
        if (ocupada) {
            entity.getViewComponent().setOpacity(0.5); // Semi-transparente si está ocupada
        } else {
            entity.getViewComponent().setOpacity(1.0); // Normal si está libre
        }
    }

    // Consultar si la mesa está ocupada
    public boolean isOcupada() {
        return ocupada;
    }
}
