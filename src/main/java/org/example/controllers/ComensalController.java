package org.example.controllers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.models.actors.Comensal;
import org.example.views.ComensalView;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComensalController {

    private Comensal comensal;
    private ComensalView comensalView;

    // Iniciar la acción del comensal
    public void iniciarAccion() {
        System.out.println("Comensal " + comensal.getNombre() + " está esperando una mesa.");
        comensal.realizarAccion();
    }

    // Asignar una mesa al comensal y actualizar la vista
    public void asignarMesa(int mesa) {
        comensal.setMesaAsignada(mesa);
        System.out.println("Mesa " + mesa + " asignada al comensal " + comensal.getNombre());
    }

    // Mover gráficamente al comensal hacia la mesa asignada
    public void moverComensalAMesa(double x, double y) {
        comensalView.moverAMesa(x, y);
    }

    // Hacer que el comensal salga del restaurante
    public void salirDelRestaurante() {
        comensalView.salir();
    }

}
