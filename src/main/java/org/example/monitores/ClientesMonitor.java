package org.example.monitores;

import org.example.models.actors.Comensal;

import java.util.LinkedList;
import java.util.Queue;

public class ClientesMonitor {
    private final Queue<Comensal> colaClientes = new LinkedList<>(); // Cola para los clientes en espera

    // Agregar un cliente a la cola (llamado cuando no hay mesas disponibles)
    public synchronized void agregarCliente(Comensal cliente) {
        colaClientes.add(cliente); // Agrega el cliente a la cola
        System.out.println("Cliente " + cliente.getId() + " está esperando en la cola.");
        notifyAll(); // Notifica a otros hilos que hay un cliente esperando
    }

    // Obtener el siguiente cliente en la cola (llamado cuando hay una mesa disponible)
    public synchronized Comensal siguienteCliente() throws InterruptedException {
        while (colaClientes.isEmpty()) { // Si no hay clientes en la cola, espera
            System.out.println("No hay clientes en espera. Recepcionista espera nuevos clientes.");
            wait();
        }
        Comensal cliente = colaClientes.poll(); // Toma el primer cliente en la cola
        System.out.println("Cliente " + cliente.getId() + " salió de la cola para ser atendido.");
        return cliente;
    }
}