package org.example.controllers;

import org.example.config.Constants;
import org.example.models.actors.Comensal;
import org.example.models.actors.Mesero;
import org.example.models.actors.Recepcionista;
import org.example.models.restaurant.Mesa;
import org.example.monitores.ClientesMonitor;
import org.example.monitores.ComidasMonitor;
import org.example.monitores.MesaMonitor;
import org.example.views.*;
import org.example.views.components.RecepcionistaComponent;

public class ManagerController {
    public static void initController(){

        //monitores
        ClientesMonitor clientesMonitor = new ClientesMonitor();
        MesaMonitor mesaMonitor = new MesaMonitor(Constants.NUMERO_MESAS); // 10 mesas disponibles
        ComidasMonitor comidasMonitor = new ComidasMonitor();

        //modelos
        Recepcionista recepcionista = new Recepcionista("Laura", 1, mesaMonitor, clientesMonitor);
        Comensal comensal =new Comensal("Pablo",1);
        Mesero mesero = new Mesero("Naransojo", 1, 1, mesaMonitor, comidasMonitor );

        //views
        RecepcionistaView recepcionista1 = new RecepcionistaView(Constants.POSITION_INITIAL_RECEPCIONISTA_X,Constants.POSITION_INITIAL_RECEPCIONISTA_Y);
        ComensalView comensalView = new ComensalView(Constants.POSITION_INITIAL_COMENSAL_X,Constants.POSITION_INITIAL_COMENSAL_Y);
        ChefView chefView = new ChefView(Constants.POSITION_INITIAL_CHEF_X, Constants.POSITION_INITIAL_CHEF_Y);
        MeseroView meseroView = new MeseroView(Constants.POSITION_INITIAL_MESERO_X, Constants.POSITION_INITIAL_MESERO_Y);
        // Crear vista de la mesa con los valores iniciales
        MesaView mesaView = new MesaView(mesaMonitor,
                Constants.POSITION_INITIAL_MESAS_X,
                Constants.POSITION_INITIAL_MESAS_Y
        );


        //controladores

        ComensalController comensalController= ComensalController.builder().comensal(comensal).comensalView(comensalView).build();


        //hola
/*
        comensalView.moverAMesa(300,300);
*/


    }
}