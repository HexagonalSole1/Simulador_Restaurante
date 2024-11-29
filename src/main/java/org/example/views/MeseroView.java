package org.example.views;

import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import org.example.views.components.MeseroComponent;


public class MeseroView {

    private final Entity mesero;

    public MeseroView(double startX, double startY) {
        this.mesero = FXGL.entityBuilder()
                .at(startX, startY)
                .view("mesera.png")
                .scale(0.5, 0.5)
                .with(new MeseroComponent())
                .buildAndAttach();
    }

    public void moverAMesa(double mesaX, double mesaY) {
        Animation<?> animation = FXGL.animationBuilder()
                .duration(Duration.seconds(2))
                .interpolator(Interpolators.EXPONENTIAL.EASE_OUT())
                .translate(mesero)
                .to(new Point2D(mesaX, mesaY))
                .build(); // Construir la animación

        animation.start(); // Iniciar la animación
    }

    public void regresarAPosicionInicial(double startX, double startY) {
        moverAMesa(startX, startY);
    }
}
