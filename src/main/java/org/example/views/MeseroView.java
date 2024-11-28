package org.example.views;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.dsl.FXGL;
import org.example.views.components.MeseroComponent;

public class MeseroView {

    private Entity mesero;

    public MeseroView(double startX, double startY) {
        this.mesero = FXGL.entityBuilder()
                .at(startX, startY)
                .view("mesera.png")
                .scale(0.5,0.5)
                .with(new MeseroComponent())
                .buildAndAttach();
    }

    public void atenderComensal(double comensalX, double comensalY) {
        mesero.getComponent(MeseroComponent.class).atenderComensal(comensalX, comensalY);
    }

    public void regresarAPosicion(double posX, double posY) {
        mesero.getComponent(MeseroComponent.class).regresarAPosicionInicial(posX, posY);
    }
}

