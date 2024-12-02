package org.example.monitores;

import org.example.models.restaurant.Mesa;

import java.util.LinkedList;
import java.util.Queue;

public class ComidasMonitor {
    private final Queue<Integer> colaPedidos = new LinkedList<>(); // Cola de pedidos
    private final Queue<Integer> colaComidas = new LinkedList<>(); // Cola de comidas listas para entregar

    // Agregar un nuevo pedido a la cola (llamado por Mesero)
    public synchronized void nuevoPedido(int idMesa) {
        colaPedidos.add(idMesa); // Agrega el pedido a la cola
        System.out.println("Nuevo pedido recibido para la mesa " + idMesa);
        notifyAll(); // Notifica a los chefs que hay un nuevo pedido
    }

    // Tomar un pedido de la cola (llamado por Chef)
    public synchronized int tomarPedido() throws InterruptedException {
        while (colaPedidos.isEmpty()) {
            System.out.println("Chef está esperando pedidos...");
            wait(); // Espera hasta que haya un pedido en la cola
        }
        int idMesa = colaPedidos.poll(); // Toma el primer pedido disponible
        comidaLista(idMesa);
        System.out.println("Chef tomó el pedido de la mesa " + idMesa);
        return idMesa;
    }

    // Registrar una comida lista en la cola de comidas (llamado por Chef)
    public synchronized void comidaLista(int idMesa) {
        colaComidas.add(idMesa); // Agrega la comida lista a la cola
        System.out.println("Comida para la mesa " + idMesa + " está lista para ser entregada.");
        notifyAll(); // Notifica a los meseros que hay una comida lista
    }

    // Tomar una comida lista específica de la cola (llamado por Mesero)
    public synchronized int tomarComidaEspecifica(Mesa mesa) throws InterruptedException {
        while (!colaComidas.contains(mesa.getNumeroMesa())) {
            System.out.println("Mesero está esperando que la comida para la mesa " + mesa + " esté lista...");
            wait(); // Espera hasta que haya un cambio en la cola
        }

        // Remover la comida específica de la cola
        colaComidas.remove(mesa.getNumeroMesa());
        System.out.println("Mesero tomó la comida lista para la mesa " + mesa);
        return mesa.getNumeroMesa();
    }
}
