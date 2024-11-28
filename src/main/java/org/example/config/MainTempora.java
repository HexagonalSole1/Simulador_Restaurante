package org.example.config;

import org.example.controllers.RestaurantController;

public class MainTempora {

    public static void main(String[] args) {
        RestaurantController controller = new RestaurantController();
        controller.iniciarSimulacion(5, 0.5); // Simula con 5 meseros y una tasa de Poisson de 0.5
    }

}
