package org.example.controllers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Patterns.MeseroObserver;
import org.example.models.actors.Comensal;
import org.example.models.actors.Mesero;
import org.example.views.MeseroView;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MeseroController implements MeseroObserver {
    private Mesero mesero;
    private MeseroView meseroView;

    public void iniciarAccion() {
        mesero.addObserver(this); // Registrar el controlador como observer
        new Thread(mesero).start();
    }

    public void realizarTarea(String tarea, int delay, Runnable accion) {
        new Thread(() -> {
            try {
                System.out.println("Mesero " + mesero.getNombre() + " " + tarea + " en la mesa " + mesero.getMesaAsignada());
                meseroView.moverAMesa(500, 600); // Mover a la mesa (valores de ejemplo)
                Thread.sleep(delay);
                accion.run(); // Ejecutar la acción específica
            } catch (InterruptedException e) {
                System.err.println("Error mientras " + tarea + ": " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    public void asignarMesa(int mesa, double x, double y) {
        mesero.setMesaAsignada(mesa); // Esto notificará automáticamente a los observers
        moverMeseroAMesa(x, y);
    }

    public void moverMeseroAMesa(double x, double y) {
        meseroView.moverAMesa(x, y);
    }

    public void levantarPedido() {
        realizarTarea("levanta pedido", 3000, mesero::atenderPedido);
    }

    public void limpiarMesa() {
        realizarTarea("limpia la mesa", 2000, mesero::limpiarMesa);
    }

    @Override
    public void onMesaAsignada(Mesero mesero, int mesa) {
        meseroView = ManagerController.getMeseroView();
        var mesaEntity = ManagerController.getMesaView().getMesasEntities()[mesa - 1];
        double mesaX = mesaEntity.getX();
        double mesaY = mesaEntity.getY();

        meseroView.moverAMesa(mesaX, mesaY);
    }
}
