package org.example.monitores;

import java.util.LinkedList;
import java.util.Queue;

public class ComidasMonitor {
    private final Queue<Integer> colaPedidos = new LinkedList<>(); // Cola de pedidos

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
        System.out.println("Chef tomó el pedido de la mesa " + idMesa);
        return idMesa;
    }
}