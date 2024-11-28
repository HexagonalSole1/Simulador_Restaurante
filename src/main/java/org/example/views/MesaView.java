package org.example.views;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.paint.Color;
import org.example.monitores.MesaMonitor;

public class MesaView {
    private Entity[] mesasEntities;

    public MesaView(MesaMonitor mesaMonitor, double xInicial, double yInicial) {
        int numeroMesas = mesaMonitor.getNumeroMesas();
        mesasEntities = new Entity[numeroMesas];

        // Definir separación entre mesas
        double separacionX = 300; // Distancia horizontal entre mesas
        double separacionY = 250; // Distancia vertical entre mesas
        int columnas = 3; // Número de mesas por fila

        for (int i = 0; i < numeroMesas; i++) {
            // Calcular la posición de la mesa en una rejilla
            double x = xInicial + (i % columnas) * separacionX;
            double y = yInicial + (i / columnas) * separacionY; // División entera para cambiar de fila

            crearView(i + 1, x, y);
        }
    }

    private void crearView(int numeroMesa, double x, double y) {
        Entity mesaEntity = FXGL.entityBuilder()
                .at(x, y)
                .viewWithBBox("mesa.png")
                .buildAndAttach();

        var textoMesa = FXGL.getUIFactoryService().newText("Mesa " + numeroMesa, Color.WHITE, 16);
        textoMesa.setTranslateX(x + 10);
        textoMesa.setTranslateY(y - 10);
        FXGL.getGameScene().addUINode(textoMesa);

        mesasEntities[numeroMesa - 1] = mesaEntity;
    }

    public Entity[] getMesasEntities() {
        return mesasEntities;
    }
}

