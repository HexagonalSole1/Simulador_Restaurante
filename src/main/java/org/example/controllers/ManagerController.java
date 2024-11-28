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
    private static MesaView mesaView; // Variable estática para compartir
    public static void initController() {
        // Monitores
        ClientesMonitor clientesMonitor = new ClientesMonitor();
        MesaMonitor mesaMonitor = new MesaMonitor(Constants.NUMERO_MESAS); // Número de mesas definido en Constants
        ComidasMonitor comidasMonitor = new ComidasMonitor();

        // Modelos
        Recepcionista recepcionista = new Recepcionista("Laura", 1, mesaMonitor, clientesMonitor);
        Mesero mesero = new Mesero("Naransojo", 1, 1, mesaMonitor, comidasMonitor);

        // Vistas
        RecepcionistaView recepcionistaView = new RecepcionistaView(
                Constants.POSITION_INITIAL_RECEPCIONISTA_X,
                Constants.POSITION_INITIAL_RECEPCIONISTA_Y
        );
        ChefView chefView = new ChefView(
                Constants.POSITION_INITIAL_CHEF_X,
                Constants.POSITION_INITIAL_CHEF_Y
        );
        MeseroView meseroView = new MeseroView(
                Constants.POSITION_INITIAL_MESERO_X,
                Constants.POSITION_INITIAL_MESERO_Y
        );

        // Crear vista de la mesa con los valores iniciales
        MesaView mesaView = new MesaView(mesaMonitor, Constants.POSITION_INITIAL_MESAS_X, Constants.POSITION_INITIAL_MESAS_Y);
        ManagerController.setMesaView(mesaView);


        // Sincronizar las mesas lógicas con el monitor
        for (Mesa mesa : mesaView.getMesas()) {
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

        // Crear e inicializar 10 comensales
        for (int i = 1; i <= 10; i++) {
            // Crear el modelo del comensal
            Comensal comensal = new Comensal("Comensal" + i, i);

            // Crear la vista para el comensal
            int posX = Constants.POSITION_INITIAL_COMENSAL_X + (i % 5) * 0; // Ajuste para distribuirlos horizontalmente
            int posY = Constants.POSITION_INITIAL_COMENSAL_Y + (i % 5) * 100;
            ComensalView comensalView = new ComensalView(posX, posY);

            // Asociar modelo y vista mediante el controlador
            ComensalController comensalController = ComensalController.builder()
                    .comensal(comensal)
                    .comensalView(comensalView)
                    .build();
            comensal.addObserver(comensalController);

            // Agregar el comensal al monitor de clientes
            clientesMonitor.agregarCliente(comensal);

            // (Opcional) Imprimir la creación
            System.out.println("Creado: " + comensal.getNombre() + " en posición (" + posX + ", " + posY + ")");
        }
    }

    public static void setMesaView(MesaView mesaView) {
        ManagerController.mesaView = mesaView;
    }

    public static MesaView getMesaView() {
        return mesaView;
    }

}
