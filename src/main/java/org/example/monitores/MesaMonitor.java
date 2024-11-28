package org.example.monitores;

import org.example.models.restaurant.Mesa;

import java.util.ArrayList;
import java.util.List;

public class MesaMonitor {
    private final List<Mesa> mesas; // Lista para manejar las mesas como objetos

    public MesaMonitor(int numMesas) {
        this.mesas = new ArrayList<>();
        for (int i = 1; i <= numMesas; i++) {
            // Inicializa las mesas como libres, limpias y con posiciones por defecto
            mesas.add(new Mesa(i, true, true, 0.0, 0.0));
        }
    }

    // Método sincronizado para ocupar una mesa
    public synchronized int ocuparMesa() throws InterruptedException {
        while (!hayMesasDisponibles()) { // Si no hay mesas disponibles, espera
            wait();
        }

        for (Mesa mesa : mesas) {
            if (mesa.getDisponibilidad()) { // Encuentra la primera mesa libre
                mesa.setDisponibilidad(false); // Ocupa la mesa
                System.out.println("Mesa " + mesa.getNumeroMesa() + " ocupada.");
                notifyAll(); // Notifica a otros hilos que el estado de las mesas ha cambiado
                return mesa.getNumeroMesa(); // Devuelve el número de la mesa ocupada
            }
        }

        return -1; // Nunca debería llegar aquí
    }

    // Método sincronizado para liberar una mesa
    public synchronized void liberarMesa(int numeroMesa) {
        for (Mesa mesa : mesas) {
            if (mesa.getNumeroMesa() == numeroMesa) {
                mesa.setDisponibilidad(true); // Libera la mesa
                System.out.println("Mesa " + numeroMesa + " liberada.");
                notifyAll(); // Notifica a otros hilos que el estado de las mesas ha cambiado
                return;
            }
        }

        System.out.println("ID de mesa inválido: " + numeroMesa);
    }

    // Método sincronizado para buscar una mesa por su ID
    public synchronized Mesa buscarMesaPorId(int idMesa) {
        for (Mesa mesa : mesas) {
            if (mesa.getNumeroMesa() == idMesa) {
                return mesa; // Retorna la mesa si se encuentra
            }
        }
        System.out.println("Mesa con ID " + idMesa + " no encontrada.");
        return null; // Retorna null si no se encuentra la mesa
    }

    // Método para verificar si hay mesas disponibles
    private boolean hayMesasDisponibles() {
        for (Mesa mesa : mesas) {
            if (mesa.getDisponibilidad()) {
                return true; // Hay al menos una mesa libre
            }
        }
        return false; // No hay mesas libres
    }

    // Método para obtener el número total de mesas
    public int getNumeroMesas() {
        return mesas.size();
    }
}
