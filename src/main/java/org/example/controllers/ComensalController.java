package org.example.controllers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Patterns.ComensalObserver;
import org.example.models.actors.Comensal;
import org.example.models.restaurant.Mesa;
import org.example.monitores.MesaMonitor;
import org.example.utils.LoggerDepuracionFXGL;
import org.example.views.ComensalView;
import org.example.views.MesaView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComensalController implements ComensalObserver {

    private Comensal comensal;
    private ComensalView comensalView;
    private MesaView mesaView;
    private MesaMonitor mesaMonitor;

    // Pool de hilos para manejar operaciones concurrentes
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public void iniciarAccion() {
        comensal.addObserver(this); // Registrar el controlador como observer
        executorService.execute(comensal); // Ejecutar el comensal en el pool
    }

    public void asignarMesa(int mesa, double x, double y) {
        executorService.execute(() -> {
            comensal.setMesaAsignada(mesa); // Notifica automáticamente a los observers
            moverComensalAMesa(x, y);
        });
    }

    public void moverComensalAMesa(double x, double y) {
        executorService.execute(() -> comensalView.moverAMesa(x, y));
    }

    public void salirDelRestaurante() {
        executorService.execute(() -> {
            comensalView.salir();
            LoggerDepuracionFXGL.log("Comensal " + comensal.getNombre() + " ha salido del restaurante.");
        });
    }

    @Override
    public void onMesaAsignada(Comensal comensal, int mesa) {
        executorService.execute(() -> {
            Mesa mesaUbi = mesaMonitor.encontrarMesaPorId(mesa);

                Boolean isComensalArrived = comensalView.moverAMesa(mesaUbi.getPosX(), mesaUbi.getPosY());
                if (isComensalArrived) {
                    synchronized (mesaUbi) { // Bloqueo en la mesa para garantizar seguridad
                        mesaUbi.setIsPresentComensal(true);
                    }
                    LoggerDepuracionFXGL.log("Comensal llegó a la mesa");
                } else {
                    LoggerDepuracionFXGL.log("Comensal no pudo llegar a la mesa");
                }
        });
    }

    // Método para cerrar el pool de hilos de manera segura
    public void shutdown() {
        executorService.shutdown();
    }
}
