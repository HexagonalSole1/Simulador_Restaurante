package org.example.controllers;

import org.example.models.actors.Chef;
import org.example.monitores.ComidasMonitor;
import org.example.utils.LoggerDepuracionFXGL;
import org.example.views.ChefView;

public class ChefController {
    private final Chef chef;
    private final ChefView chefView;
    private final ComidasMonitor comidasMonitor;


    public ChefController(Chef chef, ChefView chefView, ComidasMonitor comidasMonitor) {
        this.chef = chef;
        this.chefView = chefView;
        this.comidasMonitor = comidasMonitor;
        new Thread(() -> {
            try {
                cook();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    private void cook() throws InterruptedException {

        chef.iniciarTrabajo();
    }

}
