package org.example.monitores;

import org.example.models.restaurant.Mesa;

import java.util.List;

public class MesaMonitor {
    private final List<Mesa> mesas; // Lista de mesas

    public MesaMonitor(List<Mesa> mesas) {
        this.mesas = mesas; // Inicializa la lista de mesas
    }

    // Método sincronizado para ocupar una mesa
    public synchronized Mesa ocuparMesa() throws InterruptedException {
        while (!hayMesasDisponibles()) { // Si no hay mesas disponibles, espera
            wait();
        }

        for (Mesa mesa : mesas) {
            if (mesa.getDisponibilidad()) { // Encuentra la primera mesa libre
                mesa.setDisponibilidad(false); // Ocupa la mesa
                System.out.println("Mesa " + mesa.getNumeroMesa() + " está ocupada.");
                notifyAll(); // Notifica a otros hilos que el estado de las mesas ha cambiado
                return mesa; // Devuelve la mesa ocupada
            }
        }

        return null; // Nunca debería llegar aquí
    }

    // Método sincronizado para liberar una mesa
    public synchronized void liberarMesa(int numeroMesa) {
        for (Mesa mesa : mesas) {
            if (mesa.getNumeroMesa() == numeroMesa) {
                mesa.setDisponibilidad(true); // Libera la mesa
                mesa.setIsClean(true); // Marca la mesa como limpia
                System.out.println("Mesa " + numeroMesa + " liberada y limpia.");
                notifyAll(); // Notifica a otros hilos que el estado de las mesas ha cambiado
                return;
            }
        }

        System.out.println("ID de mesa inválido: " + numeroMesa);
    }

    // Método privado para verificar si hay mesas disponibles
    private boolean hayMesasDisponibles() {
        for (Mesa mesa : mesas) {
            if (mesa.getDisponibilidad()) {
                return true; // Hay al menos una mesa libre
            }
        }
        return false; // No hay mesas libres
    }

    // Método para obtener el número de mesas
    public int getNumeroMesas() {
        return mesas.size();
    }
}
