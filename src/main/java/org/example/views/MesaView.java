package org.example.views;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.paint.Color;

public class MesaView {
    private Entity mesaEntity;

    // Constructor vacío para inicialización posterior
    public MesaView() {}

    // Método para crear una mesa visual
    public void crearMesa(int numeroMesa, double x, double y) {
        this.mesaEntity = FXGL.entityBuilder()
                .at(x, y)
                .viewWithBBox("mesa.png") // Imagen de la mesa
                .buildAndAttach();

        // Crear texto con el número de la mesa y agregarlo a la escena
        var textoMesa = FXGL.getUIFactoryService().newText("Mesa " + numeroMesa, Color.WHITE, 16); // Tamaño del texto: 16
        textoMesa.setTranslateX(x + 10); // Posición en X
        textoMesa.setTranslateY(y - 10); // Posición en Y
        FXGL.getGameScene().addUINode(textoMesa); // Agregar el texto a la escena
    }

    public Entity getEntity() {
        return mesaEntity;
    }
}
