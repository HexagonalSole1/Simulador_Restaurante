package org.example.views.entities;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.TransformComponent;
import javafx.animation.Interpolator;
import javafx.geometry.Point2D;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGLForKtKt.animationBuilder;

public class EntityUtils {


    public static void rotateEntity(Entity entity, double angle){
        TransformComponent transform = entity.getTransformComponent();
        transform.setRotationZ(angle);
    }

    public static void scaleEntity(Entity entity, double scaleX, double scaleY){
        TransformComponent transform = entity.getTransformComponent();
        transform.setScaleX(scaleX);
        transform.setScaleY(scaleY);
    }
}