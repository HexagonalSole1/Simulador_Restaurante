package org.example.controllers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Patterns.ComensalObserver;
import org.example.models.actors.Comensal;
import org.example.models.restaurant.Mesa;
import org.example.utils.LoggerDepuracionFXGL;
import org.example.views.ComensalView;

import static org.example.models.Restaurant.mesaMonitor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComensalController implements ComensalObserver {

    private Comensal comensal;
    private ComensalView comensalView;

    public void iniciarAccion() {
        comensal.addObserver(this); // Registrar el controlador como observer
        new Thread(comensal).start();
    }

    public void asignarMesa(int mesa, double x, double y) {
        comensal.setMesaAsignada(mesa); // Esto notificará automáticamente a los observers
        moverComensalAMesa(x, y);
    }

    public void moverComensalAMesa(double x, double y) {
        comensalView.moverAMesa(x, y);
    }

    public void salirDelRestaurante() {
        comensalView.salir();
        LoggerDepuracionFXGL.log("Comensal " + comensal.getNombre() + " ha salido del restaurante.");
    }

    @Override
    public void onMesaAsignada(Comensal comensal, int mesaId) {
        LoggerDepuracionFXGL.log("Controller: El comensal " + comensal.getNombre() + " fue asignado a la mesa " + mesaId);

        // Obtener la posición de la mesa asignada
        Mesa mesa = mesaMonitor.buscarMesaPorId(mesaId);
        if (mesa != null) {
            double posX = mesa.getPosX();
            double posY = mesa.getPosY();

            // Mover la vista del comensal a la posición de la mesa
            comensalView.moverAMesa(posX, posY);

            LoggerDepuracionFXGL.log("Comensal " + comensal.getNombre() + " movido a posición de la mesa (" + posX + ", " + posY + ")");
        } else {
            LoggerDepuracionFXGL.log("Error: Mesa con ID " + mesaId + " no encontrada.");
        }
    }

}
