package org.example.views.components;

import com.almasb.fxgl.entity.component.Component;

public class RecepcionistaComponent extends Component {

    private boolean hayMesasDisponibles;

    public RecepcionistaComponent() {
        // Asumimos que al principio hay mesas disponibles
        this.hayMesasDisponibles = true;
    }
    public boolean verificarDisponibilidadMesas() {
        return hayMesasDisponibles;
    }

    public void asignarMesa() {
        if (hayMesasDisponibles) {
            // Aquí podrías cambiar la lógica según el número de mesas disponibles
            hayMesasDisponibles = false;
        } else {
            // Si no hay mesas disponibles, los comensales deben esperar
            System.out.println("Esperando disponibilidad de mesas...");
        }
    }

    public void liberarMesa() {
        hayMesasDisponibles = true;
    }
}