package org.example.views;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.views.components.RecepcionistaComponent;

import java.util.ArrayList;
import java.util.List;

public class RestaurantView {

    private List<ComensalView> comensales;  // Lista de comensales
    private List<MeseroView> meseros;  // Lista de meseros
    private RecepcionistaView recepcionistaView;  // Vista del recepcionista

    public RestaurantView() {
        this.comensales = new ArrayList<>();
        this.meseros = new ArrayList<>();
        this.recepcionistaView = new RecepcionistaView(100, 200);
        // Añadir el recepcionista al mundo
        FXGL.getGameWorld().addEntity(recepcionistaView.getEntity());
    }

    public void agregarComensal(double startX, double startY, double mesaX, double mesaY) {
        // Crear un comensal y asignar mesa
        ComensalView comensal = new ComensalView(startX, startY);
        comensal.moverAMesa(mesaX, mesaY);
        comensales.add(comensal);

        // Asignar mesa si está disponible
        if (recepcionistaView.verificarDisponibilidad()) {
            recepcionistaView.asignarMesa();
            System.out.println("Mesa asignada al comensal.");
        } else {
            System.out.println("Esperando por mesa...");
        }
    }

    public void meseroAtiende(int meseroIndex, double comensalX, double comensalY) {
        // Llamar a un mesero para que atienda a un comensal
        if (meseroIndex < meseros.size()) {
            meseros.get(meseroIndex).atenderComensal(comensalX, comensalY);
            System.out.println("Mesero " + meseroIndex + " atiende al comensal en la mesa.");
        } else {
            System.out.println("No hay mesero para atender.");
        }
    }



    public void agregarMesero(MeseroView mesero) {
        meseros.add(mesero);
    }

    // Este método es donde puedes iniciar la simulación, agregando comensales y meseros
    public void iniciarSimulacion() {
        // Agregar meseros al restaurante
        agregarMesero(new MeseroView(2, 1)); // Mesero 1 en la posición (2, 1)
        agregarMesero(new MeseroView(4, 1)); // Mesero 2 en la posición (4, 1)

        // Agregar comensales al restaurante
        agregarComensal(50, 100, 300, 200);  // Comensal 1 en la posición (50, 100)
        agregarComensal(150, 100, 350, 200); // Comensal 2 en la posición (150, 100)

        // Simular que un mesero atiende a un comensal
        meseroAtiende(0, 300, 200);  // El mesero 0 atiende al comensal en la mesa
    }
}
