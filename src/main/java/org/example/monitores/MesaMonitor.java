package org.example.monitores;

import org.example.models.restaurant.Mesa;

import java.util.List;

public class MesaMonitor {
    private final List<Mesa> mesas; // Lista de mesas

    public MesaMonitor(List<Mesa> mesas) {
        this.mesas = mesas; // Inicializa la lista de mesas
    }

    // Método para encontrar una mesa por ID
    public synchronized Mesa encontrarMesaPorId(int idMesa) {
        for (Mesa mesa : mesas) {
            if (mesa.getNumeroMesa() == idMesa) { // Compara el ID de la mesa
                return mesa; // Devuelve la mesa encontrada
            }
        }
        System.out.println("Mesa con ID " + idMesa + " no encontrada.");
        return null; // Devuelve null si no encuentra la mesa
    }

    // Método sincronizado para ocupar una mesa disponible
    public synchronized Mesa ocuparMesa() throws InterruptedException {
        while (!hayMesasDisponibles()) { // Si no hay mesas disponibles, espera
            wait();
        }

        for (Mesa mesa : mesas) {
            if (mesa.getDisponibilidad()) { // Encuentra la primera mesa libre
                mesa.setDisponibilidad(false); // Ocupa la mesa
                System.out.println("Mesa " + (mesa.getNumeroMesa() + 1) + " está ocupada.");
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
                mesa.setDisponibilidad(true);
                mesa.setIsClean(true);
                System.out.println("Mesa " + numeroMesa + " liberada y limpia.");
                notifyAll(); // Notifica a los hilos en espera
                return;
            }
        }
        System.out.println("ID de mesa inválido: " + numeroMesa);
    }

    // Método sincronizado para esperar hasta que haya una mesa ocupada que necesite atención
    public synchronized Mesa esperarMesaOcupada() throws InterruptedException {
        while (!hayMesasOcupadas()) { // Si no hay mesas ocupadas, espera
            wait(); // Libera el bloqueo y espera una notificación
        }

        // Busca y devuelve la primera mesa ocupada que no ha sido atendida
        for (Mesa mesa : mesas) {
            if (!mesa.getDisponibilidad() && !mesa.getIsAtendida() && mesa.getIsPresentComensal()) { // Mesa ocupada pero no atendida
                mesa.setIsAtendida(true); // Marca la mesa como atendida
                System.out.println("Mesa " + (mesa.getNumeroMesa() + 1) + " necesita atención.");
                notifyAll(); // Notifica a otros hilos que el estado de las mesas ha cambiado
                return mesa;
            }
        }

        return null; // Esto no debería ocurrir si la lógica está correcta
    }

    // Método privado para verificar si hay mesas ocupadas
    private synchronized boolean hayMesasOcupadas() {
        for (Mesa mesa : mesas) {
            if (!mesa.getDisponibilidad() && !mesa.getIsAtendida() && mesa.getIsPresentComensal()) {
                return true; // Hay al menos una mesa ocupada que necesita atención
            }
        }
        return false; // No hay mesas ocupadas
    }

    // Método privado para verificar si hay mesas disponibles
    private synchronized boolean hayMesasDisponibles() {
        for (Mesa mesa : mesas) {
            if (mesa.getDisponibilidad()) {
                return true; // Hay al menos una mesa libre
            }
        }
        return false; // No hay mesas libres
    }

    // Método para notificar cuando cambia el estado de las mesas
    public synchronized void notificarCambioMesa() {
        notifyAll(); // Notifica a todos los hilos en espera
    }

    // Método para obtener el número total de mesas
    public int getNumeroMesas() {
        return mesas.size();
    }
}
