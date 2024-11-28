package org.example.config;

import com.almasb.fxgl.app.GameSettings;

public class GameConfig {
    public static void applySettings(GameSettings settings) {
        settings.setWidth(Constants.WINDOW_WIDTH);
        settings.setHeight(Constants.WINDOW_HEIGTH);
        settings.setTitle("Simulador de Restaurante"); // Título de la ventana
        settings.setVersion("1.0");
    }
}
