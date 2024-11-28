package org.example.models.actors;

import org.example.models.core.Persona;
import org.example.monitores.MesaMonitor;

public class Recepcionista extends Persona {
    private final MesaMonitor mesaMonitor; // Monitor para gestionar las mesas

    public Recepcionista(String nombre, int id, MesaMonitor mesaMonitor) {
        super(nombre, id);
        this.mesaMonitor = mesaMonitor; // Asocia el monitor de mesas
    }

    // Método para atender a un comensal
    public void atenderComensal(Comensal comensal) throws InterruptedException {
        realizarAccion();


        int idMesa = mesaMonitor.ocuparMesa();
        System.out.println("Recepcionista " + getNombre() + ": Mesa " + (idMesa + 1) + " asignada al comensal " + comensal.getNombre());

        // Simula tiempo para asignar la mesa
        Thread.sleep(1000);

        // Notifica al comensal que tiene una mesa asignada
        comensal.setMesaAsignada(idMesa);
    }

    @Override
    public void realizarAccion() {
        System.out.println("Recepcionista " + nombre + " está verificando disponibilidad de mesas.");
    }
}