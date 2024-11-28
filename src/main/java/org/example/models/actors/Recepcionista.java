package org.example.models.actors;

import org.example.models.core.Persona;
import org.example.monitores.ClientesMonitor;
import org.example.monitores.MesaMonitor;
import org.example.utils.LoggerDepuracionFXGL;

public class Recepcionista extends Persona {
    private final MesaMonitor mesaMonitor; // Monitor para gestionar las mesas
    private final ClientesMonitor clientesMonitor; // Monitor para gestionar la cola de clientes

    public Recepcionista(String nombre, int id, MesaMonitor mesaMonitor, ClientesMonitor clientesMonitor) {
        super(nombre, id);
        this.mesaMonitor = mesaMonitor; // Asocia el monitor de mesas
        this.clientesMonitor = clientesMonitor; // Asocia el monitor de clientes
    }

    // Método para atender clientes de la cola y asignarles mesa
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
                Thread.currentThread().interrupt(); // Restaurar estado de interrupción
            }
        }).start(); // Ejecutar en un hilo separado
    }

    // Método para asignar una mesa al comensal
    private void atenderComensal(Comensal comensal) throws InterruptedException {
        realizarAccion();

        int idMesa = mesaMonitor.ocuparMesa(); // Asigna una mesa al comensal
        comensal.setMesaAsignada(idMesa);
        LoggerDepuracionFXGL.log("Recepcionista " + getNombre() + ": Mesa " + idMesa + " asignada al comensal " + comensal.getNombre());

        // Simula tiempo para asignar la mesa
        Thread.sleep(1000);

        // Notifica al comensal que tiene una mesa asignada
        comensal.setMesaAsignada(idMesa);
    }

    @Override
    public void realizarAccion() {
        LoggerDepuracionFXGL.log("Recepcionista " + nombre + " está verificando disponibilidad de mesas.");
    }
}
