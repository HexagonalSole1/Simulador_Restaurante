package org.example.controllers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.models.actors.Comensal;
import org.example.utils.LoggerDepuracionFXGL;
import org.example.views.ComensalView;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComensalController {

    private Comensal comensal;
    private ComensalView comensalView;

    public void iniciarAccion() {
        new Thread(comensal).start();
    }

    public void asignarMesa(int mesa, double x, double y) {
        comensal.setMesaAsignada(mesa);
        LoggerDepuracionFXGL.log("Mesa " + mesa + " asignada al comensal " + comensal.getNombre());
        moverComensalAMesa(x, y);
    }

    public void moverComensalAMesa(double x, double y) {
        comensalView.moverAMesa(x, y);
    }

    public void salirDelRestaurante() {
        comensalView.salir();
        LoggerDepuracionFXGL.log("Comensal " + comensal.getNombre() + " ha salido del restaurante.");
    }
}
