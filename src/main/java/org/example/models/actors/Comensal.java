package org.example.models.actors;

import lombok.Getter;
import lombok.Setter;
import org.example.models.core.Persona;
import org.example.models.Restaurant;


@Getter
@Setter
public class Comensal extends Persona implements Runnable {

    private int mesaAsignada;

    public Comensal(String nombre, int id) {
        super(nombre, id);
    }

    @Override
    public void realizarAccion() {
        System.out.println("Comensal " + nombre + " está esperando una mesa.");
    }

    public void run() {
        try {
            realizarAccion(); // Esperando una mesa
            Restaurant.recepcionista.atenderComensal(this); // Recepcionista asigna una mesa
            System.out.println("Comensal " + nombre + " está sentado en la mesa " + mesaAsignada + " y espera al mesero.");

            // Obtener el mesero asignado a la mesa
            Mesero meseroAsignado = Restaurant.obtenerMeseroPorMesa(mesaAsignada);

            meseroAsignado.atenderPedido(); // Mesero atiende el pedido
            System.out.println("Comensal " + nombre + " espera su comida.");

            // Simula tiempo comiendo
            Thread.sleep(2000);
            System.out.println("Comensal " + nombre + " terminó de comer.");

            meseroAsignado.limpiarMesa(); // Mesero limpia la mesa
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Comensal " + nombre + " fue interrumpido.");
        }
    }
}