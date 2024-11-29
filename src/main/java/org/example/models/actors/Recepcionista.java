package org.example.models.actors;

import org.example.models.core.Persona;
import org.example.monitores.ClientesMonitor;
import org.example.monitores.MesaMonitor;
import org.example.utils.LoggerDepuracionFXGL;

public class Recepcionista extends Persona {
    private final MesaMonitor mesaMonitor;
    private final ClientesMonitor clientesMonitor;

    public Recepcionista(String nombre, int id, MesaMonitor mesaMonitor, ClientesMonitor clientesMonitor) {
        super(nombre, id);
        this.mesaMonitor = mesaMonitor;
        this.clientesMonitor = clientesMonitor;
    }

    public void atenderClientes() {
        new Thread(() -> {
            try {
                while (true) {
                    // Obtener el siguiente cliente de la cola
                    Comensal comensal = clientesMonitor.siguienteCliente();

                    // Asignar mesa al cliente
                    atenderComensal(comensal);
                }
            } catch (InterruptedException e) {
                LoggerDepuracionFXGL.log("Recepcionista interrumpida: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    private void atenderComensal(Comensal comensal) throws InterruptedException {
        LoggerDepuracionFXGL.log("Recepcionista " + getNombre() + ": Atendiendo al comensal " + comensal.getNombre());

        int idMesa = mesaMonitor.ocuparMesa().getNumeroMesa();
        comensal.setMesaAsignada(idMesa);
        LoggerDepuracionFXGL.log("Recepcionista " + getNombre() + ": Mesa " + idMesa + " asignada al comensal " + comensal.getNombre());

        Thread.sleep(1000);
    }

    @Override
    public void realizarAccion() {
        LoggerDepuracionFXGL.log("Recepcionista " + nombre + " está verificando disponibilidad de mesas.");
    }
}

