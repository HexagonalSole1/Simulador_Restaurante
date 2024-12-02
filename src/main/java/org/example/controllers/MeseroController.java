
package org.example.controllers;

import org.example.config.Constants;
import org.example.models.actors.Mesero;
import org.example.models.restaurant.Mesa;
import org.example.monitores.MesaMonitor;
import org.example.utils.LoggerDepuracionFXGL;
import org.example.views.MeseroView;
public class MeseroController {
    private final Mesero mesero;
    private final MeseroView meseroView;
    private final MesaMonitor mesaMonitor;
    private volatile boolean running = true; // Bandera para controlar el bucle

    public MeseroController(Mesero mesero, MeseroView meseroView, MesaMonitor mesaMonitor) {
        this.mesero = mesero;
        this.meseroView = meseroView;
        this.mesaMonitor = mesaMonitor;

        new Thread(() -> {
            try {
                atendCLient();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }


    private void atendCLient() throws InterruptedException {
        while (running) {
            Mesa mesaDisponible = mesaMonitor.esperarMesaOcupada(); // Espera una mesa ocupada
            Boolean isArrivedToComensal = meseroView.atenderComensal(mesaDisponible.getPosX(), mesaDisponible.getPosY());

            if (isArrivedToComensal){
                 meseroView.llevarPedidoACocina(Constants.POSITION_INITIAL_CHEF_X,Constants.POSITION_INITIAL_COMENSAL_Y);
                 mesero.atenderPedido(mesaDisponible);

                mesero.dejarPedidoListoEnMesa(mesaDisponible);
                meseroView.atenderComensal(mesaDisponible.getPosX(), mesaDisponible.getPosY());
                mesero.seEntregoComidaEnLaMesa(mesaDisponible);

                 meseroView.regresarAPosicion(Constants.POSITION_INITIAL_MESERO_X,Constants.POSITION_INITIAL_CHEF_Y);
            }
            // Detener el hilo después de atender el pedido
            System.out.println("Mesero ha terminado de atender al cliente.");
            //detenerMesero(); // Sale del bucle
        }
    }

    public void detenerMesero() {
        running = false; // Detener el bucle
    }








}
