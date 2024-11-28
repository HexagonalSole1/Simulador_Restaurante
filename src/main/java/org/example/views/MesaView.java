package org.example.views;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.paint.Color;
import org.example.models.restaurant.Mesa;
import org.example.monitores.MesaMonitor;
import org.example.views.components.MesaComponent;

public class MesaView {
    private Entity[] mesasEntities;
    private Mesa[] mesas; // Arreglo para almacenar las mesas lógicas

    public MesaView(MesaMonitor mesaMonitor, double xInicial, double yInicial) {
        int numeroMesas = mesaMonitor.getNumeroMesas();
        mesasEntities = new Entity[numeroMesas];
        mesas = new Mesa[numeroMesas];

        // Definir separación entre mesas
        double separacionX = 200; // Distancia horizontal entre mesas
        double separacionY = 150; // Distancia vertical entre mesas
        int columnas = 3; // Número de mesas por fila

        for (int i = 0; i < numeroMesas; i++) {
            // Calcular la posición de la mesa en una rejilla
            double x = xInicial + (i % columnas) * separacionX;
            double y = yInicial + (i / columnas) * separacionY; // División entera para cambiar de fila

            // Crear instancia de Mesa y añadirla al monitor
            Mesa mesa = new Mesa(i + 1, true, true, x, y);
            mesas[i] = mesa;

            // Crear la representación visual de la mesa
            crearView(mesaMonitor, mesa, x, y);
        }
    }

    private void crearView(MesaMonitor mesaMonitor, Mesa mesa, double x, double y) {
        // Crear la entidad visual para la mesa
        Entity mesaEntity = FXGL.entityBuilder()
                .at(x, y) // Posición inicial de la mesa
                .viewWithBBox("mesa.png") // Imagen de la mesa
                .scale(0.5, 0.5)
                .with(new MesaComponent(mesa.getNumeroMesa())) // Agregar componente para manejar estados
                .buildAndAttach();

        // Crear el texto que identifica a la mesa
        var textoMesa = FXGL.getUIFactoryService().newText("Mesa " + mesa.getNumeroMesa(), Color.WHITE, 16);
        textoMesa.setTranslateX(x + 100); // Ajustar posición para centrar en la mesa
        textoMesa.setTranslateY(y + 100); // Centrar verticalmente sobre la mesa
        FXGL.getGameScene().addUINode(textoMesa);

        // Guardar la entidad de la mesa
        mesasEntities[mesa.getNumeroMesa() - 1] = mesaEntity;

        // Registrar la mesa en el monitor
        mesaMonitor.liberarMesa(mesa.getNumeroMesa()); // Inicialmente está disponible

        // Mensaje de depuración
        System.out.println("Mesa " + mesa.getNumeroMesa() + " creada en posición (" + x + ", " + y + ")");
    }

    public Entity[] getMesasEntities() {
        return mesasEntities;
    }

    public Mesa[] getMesas() {
        return mesas;
    }
}
