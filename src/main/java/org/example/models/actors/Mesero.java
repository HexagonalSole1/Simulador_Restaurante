package org.example.models.actors;

import lombok.Getter;
import lombok.Setter;
import org.example.Patterns.MeseroObserver;
import org.example.models.core.Persona;
import org.example.monitores.ComidasMonitor;
import org.example.monitores.MesaMonitor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Mesero extends Persona implements Runnable {
    private int mesaAsignada;
    private final List<MeseroObserver> observers = new ArrayList<>();
    private final MesaMonitor mesaMonitor;
    private final ComidasMonitor comidasMonitor;

    public Mesero(String nombre, int id, int mesaAsignada, MesaMonitor mesaMonitor, ComidasMonitor comidasMonitor) {
        super(nombre, id);
        this.mesaAsignada = mesaAsignada;
        this.mesaMonitor = mesaMonitor;
        this.comidasMonitor = comidasMonitor;
    }

    public void addObserver(MeseroObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (MeseroObserver observer : observers) {
            observer.onMesaAsignada(this, mesaAsignada);
        }
    }

    public void setMesaAsignada(int mesaAsignada) {
        this.mesaAsignada = mesaAsignada;
        notifyObservers();
    }

    public synchronized void atenderPedido() {
        System.out.println("Mesero " + getNombre() + " está tomando el pedido de la mesa " + mesaAsignada);
        comidasMonitor.nuevoPedido(mesaAsignada);
    }

    public synchronized void limpiarMesa() {
        System.out.println("Mesero " + getNombre() + " está limpiando la mesa " + mesaAsignada);
        mesaMonitor.liberarMesa(mesaAsignada);
    }

    @Override
    public void run() {
        try {
            realizarAccion();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Mesero " + getNombre() + " fue interrumpido.");
        }
    }

    @Override
    public void realizarAccion() {

    }
}
