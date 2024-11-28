package org.example.views;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.paint.Color;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.monitores.MesaMonitor;
import org.example.views.components.MesaComponent;


public class MesaView {
    private Entity[] mesasEntities;

    public MesaView(MesaMonitor mesaMonitor, double xInicial, double yInicial) {
        int numeroMesas = mesaMonitor.getNumeroMesas();
        mesasEntities = new Entity[numeroMesas];

        // Definir separación entre mesas
        double separacionX = 200; // Distancia horizontal entre mesas
        double separacionY = 150; // Distancia vertical entre mesas
        int columnas = 3; // Número de mesas por fila

        for (int i = 0; i < numeroMesas; i++) {
            // Calcular la posición de la mesa en una rejilla
            double x = xInicial + (i % columnas) * separacionX;
            double y = yInicial + (i / columnas) * separacionY; // División entera para cambiar de fila

            crearView(i + 1, x, y);
        }
    }

    private void crearView(int numeroMesa, double x, double y) {
        // Crear la entidad visual para la mesa
        Entity mesaEntity = FXGL.entityBuilder()
                .at(x, y) // Posición inicial de la mesa
                .viewWithBBox("mesa.png") // Imagen de la mesa
                .scale(0.5,0.5)
                .with(new MesaComponent(numeroMesa)) // Agregar componente para manejar estados
                .buildAndAttach();

        // Crear el texto que identifica a la mesa
        var textoMesa = FXGL.getUIFactoryService().newText("Mesa " + numeroMesa, Color.WHITE, 16);
        textoMesa.setTranslateX(x + 100); // Ajustar posición para centrar en la mesa
        textoMesa.setTranslateY(y + 100); // Centrar verticalmente sobre la mesa
        FXGL.getGameScene().addUINode(textoMesa);

        // Guardar la entidad de la mesa (opcional, si necesitas referencia)
        mesasEntities[numeroMesa - 1] = mesaEntity;

        // Mensaje de depuración
        System.out.println("Mesa " + numeroMesa + " creada en posición (" + x + ", " + y + ")");
    }

    public Entity[] getMesasEntities() {
        return mesasEntities;
    }
}

