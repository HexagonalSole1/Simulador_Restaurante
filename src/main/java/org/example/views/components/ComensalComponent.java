package org.example.views.components;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.dsl.FXGL;

public class ComensalComponent extends Component {

    private double destinoX;
    private double destinoY;
    private double velocidad = 100; // Velocidad en píxeles por segundo
    private boolean enMovimiento = false; // Indica si el comensal debe moverse

    // Establece la posición de destino (mesa) y activa el movimiento
    public void moveToMesa(double mesaX, double mesaY) {
        this.destinoX = mesaX;
        this.destinoY = mesaY;
        this.enMovimiento = true; // Activa el estado de movimiento
        System.out.println("Moviendo al comensal hacia la mesa en: (" + mesaX + ", " + mesaY + ")");
    }

    // Lógica para que el comensal salga del restaurante
    public void salirDelRestaurante() {
        System.out.println("El comensal ha salido del restaurante.");
        FXGL.getGameWorld().removeEntity(entity); // Elimina la entidad del juego
    }

    @Override
    public void onUpdate(double tpf) {
        // Solo se mueve si el estado enMovimiento está activo
        if (!enMovimiento) {
            return; // Si no está en movimiento, no hace nada
        }

        // Calcula la distancia hacia el destino
        double x = entity.getX();
        double y = entity.getY();

        double distanciaX = destinoX - x;
        double distanciaY = destinoY - y;

        // Si ya estamos cerca del destino, detiene el movimiento
        if (Math.abs(distanciaX) < 1 && Math.abs(distanciaY) < 1) {
            enMovimiento = false; // Detiene el movimiento
            System.out.println("El comensal ha llegado a la mesa en: (" + destinoX + ", " + destinoY + ")");
            return;
        }

        // Calcula la dirección normalizada
        double distanciaTotal = Math.sqrt(distanciaX * distanciaX + distanciaY * distanciaY);
        double direccionX = distanciaX / distanciaTotal;
        double direccionY = distanciaY / distanciaTotal;

        // Actualiza la posición del comensal según la velocidad y el tiempo por frame (tpf)
        double movimientoX = direccionX * velocidad * tpf;
        double movimientoY = direccionY * velocidad * tpf;

        entity.setPosition(x + movimientoX, y + movimientoY);
    }
}
