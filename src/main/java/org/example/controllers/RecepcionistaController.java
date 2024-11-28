package org.example.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.models.actors.Comensal;
import org.example.models.actors.Recepcionista;
import org.example.monitores.ClientesMonitor;
import org.example.monitores.MesaMonitor;
import org.example.views.RecepcionistaView;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecepcionistaController {
    private Recepcionista recepcionista;
    private RecepcionistaView recepcionistaView;
    private MesaMonitor mesaMonitor;
    private ClientesMonitor clientesMonitor;

    // Crear un ExecutorService para manejar la concurrencia
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    // Método para iniciar la asignación de clientes concurrentemente
    public void startAssigningGuests() {
        executorService.submit(() -> {
            while (true) { // Ciclo infinito para siempre atender clientes
                try {
                    // Tomar el siguiente cliente de la cola
                    Comensal cliente = clientesMonitor.siguienteCliente();

                    // Simular la asignación de una mesa al cliente
                    int idMesaOcupar = mesaMonitor.ocuparMesa().getNumeroMesa();
                    if (idMesaOcupar != -1) {
                        System.out.println("Recepcionista asignando cliente " + cliente.getNombre() + " a la mesa " + (idMesaOcupar + 1));
                        cliente.setMesaAsignada(idMesaOcupar + 1); // Base-1
                    } else {
                        System.out.println("No hay mesas disponibles para el cliente: " + cliente.getNombre());
                    }


                    Thread.sleep(3000); // Simulación de tiempo para asignar la mesa
                } catch (InterruptedException e) {
                    System.err.println("Recepcionista interrumpido: " + e.getMessage());
                    Thread.currentThread().interrupt();
                    break; // Salir del ciclo si el hilo es interrumpido
                }
            }
        });
    }

    // Método para detener el ExecutorService
    public void stopAssigningGuests() {
        executorService.shutdownNow(); // Detener el ExecutorService
    }
}