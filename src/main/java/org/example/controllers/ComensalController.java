package org.example.controllers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Patterns.ComensalObserver;
import org.example.models.actors.Comensal;
import org.example.utils.LoggerDepuracionFXGL;
import org.example.views.ComensalView;
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
    public void onMesaAsignada(Comensal comensal, int mesa) {
        LoggerDepuracionFXGL.log("Controller: El comensal " + comensal.getNombre() + " fue asignado a la mesa " + mesa);
        comensalView.moverAMesa(500,600);
        // Aquí puedes realizar cualquier acción adicional
    }
}
