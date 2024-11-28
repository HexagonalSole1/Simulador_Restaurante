package org.example.config;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import org.example.controllers.ManagerController;
import org.example.views.RestaurantView;
import org.example.views.entities.EntityManager;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;
import static com.almasb.fxgl.dsl.FXGLForKtKt.loopBGM;

public class InitGame {
    EntityManager entityManager;
    RestaurantView restaurantView;

    private void initializeBackground() {
        getGameScene().setBackgroundRepeat("fondo.png");
    }

    private void startSoundtrack(){
        loopBGM("soundtrack.mp3");
    }
    public void runGame() {
        // Iniciar la simulación
        initializeBackground();
        //startSoundtrack();

        ManagerController.initController();

      /*  restaurantView = new RestaurantView();
        restaurantView.iniciarSimulacion();  // Llamamos a iniciar la simulación*/

    }
}
