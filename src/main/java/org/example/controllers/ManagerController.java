package org.example.controllers;

import org.example.config.Constants;
import org.example.models.actors.Chef;
import org.example.models.actors.Comensal;
import org.example.models.actors.Mesero;
import org.example.models.actors.Recepcionista;
import org.example.models.restaurant.Mesa;
import org.example.monitores.ClientesMonitor;
import org.example.monitores.ComidasMonitor;
import org.example.monitores.MesaMonitor;
import org.example.views.*;
import org.example.views.components.RecepcionistaComponent;

import java.util.ArrayList;
import java.util.List;

public class ManagerController {
    public static void initController() {

        //conifguracion inicial de mesas
        List<Mesa> mesas = new ArrayList<>();
        for (int i = 0; i < Constants.NUMERO_MESAS; i++) { // 5 mesas de ejemplo
            mesas.add(new Mesa(i, true,false, true,false, null, null));
        }

        // Monitores
        ClientesMonitor clientesMonitor = new ClientesMonitor();
        MesaMonitor mesaMonitor = new MesaMonitor(mesas); // Número de mesas definido en Constants
        ComidasMonitor comidasMonitor = new ComidasMonitor();

        // Modelos
        Recepcionista recepcionista = new Recepcionista("Laura", 1, mesaMonitor, clientesMonitor);
        Chef chef = new Chef("Naranjoso",1,comidasMonitor,3);

        // Vistas
        RecepcionistaView recepcionistaView = new RecepcionistaView(
                Constants.POSITION_INITIAL_RECEPCIONISTA_X,
                Constants.POSITION_INITIAL_RECEPCIONISTA_Y,
                mesaMonitor
        );
        ChefView chefView = new ChefView(
                Constants.POSITION_INITIAL_CHEF_X,
                Constants.POSITION_INITIAL_CHEF_Y
        );

        //Controlador
        ChefController chefController = new ChefController(chef,chefView,comidasMonitor);


        // Crear vista de la mesa con los valores iniciales
        MesaView mesaView = new MesaView(mesaMonitor, Constants.POSITION_INITIAL_MESAS_X, Constants.POSITION_INITIAL_MESAS_Y);

        // Sincronizar las mesas lógicas con el monitor
        for (Mesa mesa : mesas) { // Iterar sobre la lista de mesas creada anteriormente
            mesaMonitor.liberarMesa(mesa.getNumeroMesa());
        }


        // Controladores
        RecepcionistaController recepcionistaController = new RecepcionistaController(
                recepcionista,
                recepcionistaView,
                mesaMonitor,
                clientesMonitor
        );
        recepcionistaController.startAssigningGuests();


        for (int i = 0; i < Constants.NUMERO_MESEROS ; i++){
            Mesero mesero = new Mesero(("mesero" + (i+1)), (i+1), mesaMonitor, comidasMonitor);
            MeseroView meseroView = new MeseroView(
                    Constants.POSITION_INITIAL_MESERO_X+ (i % 5) * 200,
                    Constants.POSITION_INITIAL_MESERO_Y+ (i % 5) * 0
            );
            MeseroController meseroController = new MeseroController(
                    mesero,
                    meseroView,
                    mesaMonitor
            );
        }




        // Crear e inicializar x comensales
        for (int i = 0; i < Constants.NUMERO_COMENSALES; i++) {
            // Crear el modelo del comensal
            Comensal comensal = new Comensal("Comensal" + (i+1), (i+1));
            // Crear la vista para el comensal
            int posX = Constants.POSITION_INITIAL_COMENSAL_X + (i % 5) * 0; // Ajuste para distribuirlos horizontalmente
            int posY = Constants.POSITION_INITIAL_COMENSAL_Y + (i % 5) * 100;
            ComensalView comensalView = new ComensalView(posX, posY);

            // Asociar modelo y vista mediante el controlador
            ComensalController comensalController = ComensalController.builder()
                    .comensal(comensal)
                    .comensalView(comensalView)
                    .mesaMonitor(mesaMonitor)
                    .build();
            comensal.addObserver(comensalController);

            // Agregar el comensal al monitor de clientes
            clientesMonitor.agregarCliente(comensal);

            // (Opcional) Imprimir la creación
            System.out.println("Creado: " + comensal.getNombre() + " en posición (" + posX + ", " + posY + ")");
        }



    }



}
