package org.example.controllers;

import com.almasb.fxgl.dsl.FXGL;
import org.example.config.Constants;
import org.example.models.actors.Comensal;
import org.example.models.actors.Recepcionista;
import org.example.monitores.ClientesMonitor;
import org.example.monitores.ComidasMonitor;
import org.example.monitores.MesaMonitor;
import org.example.utils.LoggerDepuracionFXGL;
import org.example.views.ComensalView;
import org.example.views.components.RecepcionistaComponent;

import java.util.stream.IntStream;

public class ManagerController {
    public static void initController() {
        // Monitores
        ClientesMonitor clientesMonitor = new ClientesMonitor();
        MesaMonitor mesaMonitor = new MesaMonitor(Constants.NUMERO_MESAS); // 10 mesas disponibles
        ComidasMonitor comidasMonitor = new ComidasMonitor();

        // Modelos
        Recepcionista recepcionista = new Recepcionista("Laura", 1, mesaMonitor,clientesMonitor);

        // Crear 10 comensales con lambda
        IntStream.range(0, 10).forEach(i -> {
            String nombreComensal = "Comensal" + (i + 1);
            Comensal comensal = new Comensal(nombreComensal, i + 1); // Crear comensal
            ComensalView comensalView = new ComensalView(
                    Constants.POSITION_INITIAL_COMENSAL_X + i * 50, // Espaciar en X
                    Constants.POSITION_INITIAL_COMENSAL_Y
            ); // Crear vista del comensal
            ComensalController comensalController = new ComensalController(comensal, comensalView);
            comensalController.iniciarAccion(); // Iniciar la acción del comensal
        });


    }
}
