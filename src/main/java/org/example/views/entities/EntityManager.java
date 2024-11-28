package org.example.views.entities;


import com.almasb.fxgl.entity.Entity;
import org.example.config.Constants;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameWorld;

public class EntityManager {
    public EntityManager(){}



    public Entity spawnClient(double x, double y){
        return getGameWorld().spawn(Constants.CLIENT_CONSTANT, x, y);
    }
    public static void deleteEntity(Entity entity){
        entity.removeFromWorld();
    }

}
