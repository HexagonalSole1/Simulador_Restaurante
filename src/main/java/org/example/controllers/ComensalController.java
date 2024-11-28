package org.example.controllers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Patterns.ComensalObserver;
import org.example.models.actors.Comensal;
import org.example.utils.LoggerDepuracionFXGL;
import org.example.views.ComensalView;
import org.example.views.MesaView;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComensalController implements ComensalObserver {

    private Comensal comensal;
    private ComensalView comensalView;
    private MesaView mesaView;


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
        // Simular que el comensal sale después de cierto tiempo
        new Thread(() -> {
            try {
                Thread.sleep(5000); // Esperar 5 segundos (simulando que el comensal come)
                salirDelRestaurante(); // Llamar al método de salida
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Error al esperar la salida del comensal: " + e.getMessage());
            }
        }).start();

    }

    public void salirDelRestaurante() {
        comensalView.salir();
        LoggerDepuracionFXGL.log("Comensal " + comensal.getNombre() + " ha salido del restaurante.");
    }

    @Override
    public void onMesaAsignada(Comensal comensal, int mesa) {
        mesaView= ManagerController.getMesaView();
        var mesaEntity = ManagerController.getMesaView().getMesasEntities()[mesa - 1];
        double mesaX = mesaEntity.getX();
        double mesaY = mesaEntity.getY();

        comensalView.moverAMesa(mesaX, mesaY);
    }


}