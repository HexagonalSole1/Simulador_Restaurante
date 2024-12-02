package org.example.models.actors;

import lombok.Getter;
import lombok.Setter;
import org.example.models.core.Persona;
import org.example.models.restaurant.Mesa;
import org.example.monitores.ComidasMonitor;
import org.example.monitores.MesaMonitor;

@Getter
@Setter
public class Mesero extends Persona {
    private final MesaMonitor mesaMonitor;
    private final ComidasMonitor comidasMonitor;

    public Mesero(String nombre, int id, MesaMonitor mesaMonitor, ComidasMonitor comidasMonitor) {
        super(nombre, id);
        this.mesaMonitor = mesaMonitor;
        this.comidasMonitor = comidasMonitor;
    }

    // Atender pedido para la mesa asignada
    public synchronized void atenderPedido(Mesa mesa) throws InterruptedException {
        System.out.println("Mesero " + getNombre() + " está tomando el pedido de la mesa " + mesa.getNumeroMesa());
        comidasMonitor.nuevoPedido(mesa.getNumeroMesa()); // Envía el pedido a la cocina
    }

    // Limpiar la mesa asignada
    public synchronized void limpiarMesa(MesaMonitor mesa) throws InterruptedException {
        System.out.println("Mesero " + getNombre() + " está limpiando la mesa " + mesaMonitor.encontrarMesaPorId(mesa.getNumeroMesas()).getNumeroMesa());
        mesaMonitor.liberarMesa(mesa.getNumeroMesas()); // Libera la mesa
    }
    public synchronized int dejarPedidoListoEnMesa(Mesa mesaDisponible) throws InterruptedException {
        return comidasMonitor.tomarComidaEspecifica(mesaDisponible); // Libera la mesa
    }

    public synchronized int seEntregoComidaEnLaMesa(Mesa mesaDisponible) throws InterruptedException {
        return mesaDisponible.getNumeroMesa(); // Libera la mesa
    }



    @Override
    public void realizarAccion() {
        System.out.println("Mesero " + getNombre() + " está atendiendo a un comensal");

    }
}