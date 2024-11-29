package org.example.views.components;

import com.almasb.fxgl.entity.component.Component;
import org.example.monitores.MesaMonitor;

public class RecepcionistaComponent extends Component {
    private final MesaMonitor mesaMonitor; // Monitor para verificar mesas

    public RecepcionistaComponent(MesaMonitor mesaMonitor) {
        this.mesaMonitor = mesaMonitor;
    }



    // Verifica si hay mesas disponibles
    public boolean verificarDisponibilidadMesas() {
        return mesaMonitor.getNumeroMesas() > 0;
    }

    public int asignarMesa() {
        try {
            return mesaMonitor.ocuparMesa().getNumeroMesa(); // Ocupar una mesa
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return -1; // Indica que no se pudo asignar
        }
    }

    // Liberar una mesa (opcional)
   /* public void liberarMesa(int idMesa) {
        mesaMonitor.liberarMesa(idMesa);
    }*/
}