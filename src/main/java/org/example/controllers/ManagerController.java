package org.example.controllers;

import org.example.config.Constants;
import org.example.models.actors.Comensal;
import org.example.models.actors.Recepcionista;
import org.example.models.restaurant.Mesa;
import org.example.monitores.ComidasMonitor;
import org.example.monitores.MesaMonitor;
import org.example.views.ComensalView;
import org.example.views.MesaView;
import org.example.views.RecepcionistaView;
import org.example.views.components.RecepcionistaComponent;

public class ManagerController {
    public static void initController(){

        //monitores
        MesaMonitor mesaMonitor = new MesaMonitor(Constants.NUMERO_MESAS); // 10 mesas disponibles
        ComidasMonitor comidasMonitor = new ComidasMonitor();

        //modelos
        Recepcionista recepcionista = new Recepcionista("Laura", 1, mesaMonitor);
        Comensal comensal =new Comensal("Pablo",1);
        Mesa mesa = new Mesa();
        //views
        RecepcionistaView recepcionista1 = new RecepcionistaView(Constants.POSITION_INITIAL_RECEPCIONISTA_X,Constants.POSITION_INITIAL_RECEPCIONISTA_Y);
        ComensalView comensalView = new ComensalView(Constants.POSITION_INITIAL_COMENSAL_X,Constants.POSITION_INITIAL_COMENSAL_Y);
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