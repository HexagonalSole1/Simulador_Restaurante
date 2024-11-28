package org.example.views;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.dsl.FXGL;
import org.example.monitores.ComidasMonitor;
import org.example.views.components.ChefComponent;

public class ChefView {
    private Entity chefEntity; // La entidad visual del chef

    public ChefView(double startX, double startY) {
        this(startX, startY, new ComidasMonitor(), 5); // Crear con valores predeterminados
    }

    public ChefView(double startX, double startY, ComidasMonitor comidasMonitor, int tiempoPreparacion) {
        // Crear la entidad del chef con su imagen, escala y componente lógico
        this.chefEntity = FXGL.entityBuilder()
                .at(startX, startY) // Posición inicial
                .view("chef.png") // Imagen del chef
                .scale(0.5, 0.5) // Escalar al 50% (ajustable)
                .with(new ChefComponent(comidasMonitor, tiempoPreparacion)) // Agregar el componente de lógica
                .buildAndAttach();
    }

    // Método para mover al chef a una nueva posición
    public void moverChef(double nuevaX, double nuevaY) {
        chefEntity.setPosition(nuevaX, nuevaY);
        System.out.println("Chef se movió a: (" + nuevaX + ", " + nuevaY + ")");
    }

    // Obtener la entidad del chef (por si se necesita acceder en otros componentes)
    public Entity getChefEntity() {
        return chefEntity;
    }
}
