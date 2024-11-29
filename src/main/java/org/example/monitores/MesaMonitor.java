package org.example.monitores;

import org.example.models.restaurant.Mesa;
import org.example.models.actors.Mesero;

import java.util.ArrayList;
import java.util.List;

public class MesaMonitor {
    private final List<Mesa> mesas; // Lista de mesas
    private final List<Mesero> observadores = new ArrayList<>(); // Observadores (Meseros)

    public MesaMonitor(List<Mesa> mesas) {
        this.mesas = mesas; // Inicializa la lista de mesas
    }

    // Método sincronizado para ocupar una mesa
    public synchronized Mesa ocuparMesa() throws InterruptedException {
        while (true) {
            for (Mesa mesa : mesas) {
                if (!mesa.estaOcupada()) {
                    mesa.setOcupada(true);
                    return mesa;
                }
            }
            wait();
        }
    }

    // Método sincronizado para liberar una mesa
    public synchronized void liberarMesa(int idMesa) {
        if (idMesa >= 0 && idMesa < mesas.size()) {
            mesas.get(idMesa).setOcupada(false);
            notifyAll();
        } else {
            System.err.println("ID de mesa inválido: " + idMesa);
        }
    }

    // Método para agregar un mesero observador
    public void addMeseroObserver(Mesero mesero) {
        if (!observadores.contains(mesero)) {
            observadores.add(mesero);
        }
    }

    // Método para notificar a los meseros que una mesa está ocupada
    private void notifyMeseros(Mesa mesa) {
        for (Mesero mesero : observadores) {
            try {
                mesero.setMesaAsignada(mesa.getNumeroMesa()); // Asignar la mesa al mesero
                System.out.println("Notificando al mesero " + mesero.getNombre() + " para atender la mesa " + mesa.getNumeroMesa());
            } catch (Exception e) {
                System.err.println("Error al notificar al mesero: " + e.getMessage());
            }
        }
    }


    // Método privado para verificar si hay mesas disponibles
    private boolean hayMesasDisponibles() {
        for (Mesa mesa : mesas) {
            if (!mesa.estaOcupada()) {
                return true; // Hay al menos una mesa libre
            }
        }
        return false; // No hay mesas libres
    }

    // Método para obtener el número de mesas
    public int getNumeroMesas() {
        return mesas.size();
    }

    public void marcarMesaComoAtendida(int numeroMesa) {

    }
}
