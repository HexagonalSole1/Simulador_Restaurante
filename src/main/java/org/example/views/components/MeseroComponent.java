package org.example.views.components;

import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;

public class MeseroComponent extends Component {

    public void atenderComensal(double x, double y) {
        entity.translateTowards(new Point2D(x, y), 150); // Velocidad más rápida
    }

    public void regresarAPosicionInicial(double x, double y) {
        entity.translateTowards(new Point2D(x, y), 150); // Vuelve a su posición
    }
}

