package org.example.controllers;

import org.example.config.Constants;
import org.example.models.Restaurant;
import org.example.models.actors.Comensal;
import org.example.utils.DistribucionPoisson;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RestaurantController {

    public void iniciarSimulacion(int numeroMeseros, double tasaPoisson) {
        // Inicializa los meseros (1 mesero por mesa)
        Restaurant.inicializarMeseros(numeroMeseros);

        ExecutorService poolComensales = Executors.newCachedThreadPool();

        int idComensal = 1;
        for (int i = 0; i < Constants.NUMERO_COMENSALES; i++) {
            poolComensales.submit(new Comensal("Comensal " + idComensal, idComensal++));
            try {
                Thread.sleep(DistribucionPoisson.generarIntervaloPoisson(tasaPoisson) * 1000); // Intervalo Poisson
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        poolComensales.shutdown();
        System.out.println("Simulación completada. Todos los comensales han sido atendidos.");
    }
}