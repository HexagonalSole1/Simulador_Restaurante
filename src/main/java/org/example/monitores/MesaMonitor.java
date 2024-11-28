package org.example.monitores;

import java.util.Arrays;

public class MesaMonitor {
    private final boolean[] mesas; // Array para indicar el estado de las mesas (true = ocupada, false = libre)

    public MesaMonitor(int numMesas) {
        this.mesas = new boolean[numMesas]; // Inicializa todas las mesas como libres
        Arrays.fill(mesas, false); // Todas las mesas están libres al inicio
    }

    // Método sincronizado para ocupar una mesa
    public synchronized int ocuparMesa() throws InterruptedException {
        while (!hayMesasDisponibles()) { // Si no hay mesas disponibles, espera
            wait();
        }

        for (int i = 0; i < mesas.length; i++) {
            if (!mesas[i]) { // Encuentra la primera mesa libre
                mesas[i] = true; // Ocupa la mesa
                System.out.println("Mesa " + (i + 1) + " no esta ocupada.");
                notifyAll(); // Notifica a otros hilos que el estado de las mesas ha cambiado
                return i; // Devuelve el ID de la mesa ocupada
            }
        }

        return -1; // Nunca debería llegar aquí
    }

    // Método sincronizado para liberar una mesa
    public synchronized void liberarMesa(int idMesa) {
        if (idMesa >= 0 && idMesa < mesas.length) {
            mesas[idMesa] = false; // Marca la mesa como libre
            System.out.println("Mesa " + (idMesa + 1) + " ha sido liberada.");
            notifyAll(); // Notifica que hay una mesa disponible
        } else {
            System.err.println("Intento de liberar una mesa inválida: " + idMesa);
        }
    }


    // Método privado para verificar si hay mesas disponibles
    private boolean hayMesasDisponibles() {
        for (boolean mesa : mesas) {
            if (!mesa) {
                return true; // Hay al menos una mesa libre
            }
        }
        return false; // No hay mesas libres
    }

    // Método para obtener el número de mesas
    public int getNumeroMesas() {
        return mesas.length;
    }
}