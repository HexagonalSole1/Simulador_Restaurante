package org.example.models.actors;

import lombok.Getter;
import lombok.Setter;
import org.example.models.core.Persona;
import org.example.monitores.ComidasMonitor;
import org.example.monitores.MesaMonitor;

@Getter
@Setter
public class Mesero extends Persona {
    private final int mesaAsignada; // Mesa exclusiva para este mesero
    private final MesaMonitor mesaMonitor;
    private final ComidasMonitor comidasMonitor;

    public Mesero(String nombre, int id, int mesaAsignada, MesaMonitor mesaMonitor, ComidasMonitor comidasMonitor) {
        super(nombre, id);
        this.mesaAsignada = mesaAsignada;
        this.mesaMonitor = mesaMonitor;
        this.comidasMonitor = comidasMonitor;
    }

    // Atender pedido para la mesa asignada
    public synchronized void atenderPedido() throws InterruptedException {
        System.out.println("Mesero " + getNombre() + " está tomando el pedido de la mesa " + mesaAsignada);
        comidasMonitor.nuevoPedido(mesaAsignada); // Envía el pedido a la cocina
    }

    // Limpiar la mesa asignada
    public synchronized void limpiarMesa() throws InterruptedException {
        System.out.println("Mesero " + getNombre() + " está limpiando la mesa " + mesaAsignada);
        mesaMonitor.liberarMesa(mesaAsignada); // Libera la mesa
    }

    @Override
    public void realizarAccion() {
        System.out.println("Mesero " + getNombre() + " está atendiendo a un comensal en la mesa : " + mesaAsignada);

    }
}