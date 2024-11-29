package org.example.views;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.dsl.FXGL;

import java.util.ArrayList;
import java.util.List;

public class RestaurantView {

    private List<ComensalView> comensales;  // Lista de comensales
    private List<MeseroView> meseros;  // Lista de meseros
    private RecepcionistaView recepcionistaView;  // Vista del recepcionista

    // Constructor que recibe el RecepcionistaView ya inicializado
    public RestaurantView(RecepcionistaView recepcionistaView) {
        this.comensales = new ArrayList<>();
        this.meseros = new ArrayList<>();
        this.recepcionistaView = recepcionistaView;

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
            meseros.get(meseroIndex).moverAMesa(comensalX, comensalY);
            System.out.println("Mesero " + meseroIndex + " atiende al comensal en la mesa.");
        } else {
            System.out.println("No hay mesero para atender.");
        }
    }

    public void agregarMesero(MeseroView mesero) {
        meseros.add(mesero);
    }

    // Este método es donde puedes iniciar la simulación, agregando comensales y meseros

}