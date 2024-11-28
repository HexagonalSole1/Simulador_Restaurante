package org.example;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import org.example.config.GameConfig;
import org.example.config.InitGame;
import org.example.views.RestaurantView;

public class Main extends GameApplication {


    @Override
    protected void initSettings(GameSettings settings) {
        GameConfig.applySettings(settings);
    }

    @Override
    protected void initGame() {
        InitGame _initGame = new InitGame();
        _initGame.runGame();

    }
    public static void main(String[] args) {
        launch(args);  // Inicia la aplicación FXGL
    }
}
