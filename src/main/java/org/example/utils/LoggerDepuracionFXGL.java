package org.example.utils;

import com.almasb.fxgl.logging.Logger;

public class LoggerDepuracionFXGL {
    private static final Logger logger = Logger.get(LoggerDepuracionFXGL.class);

    /**
     * Método estático para registrar mensajes de depuración en FXGL.
     *
     * @param message El mensaje a registrar
     */
    public static void log(String message) {
        logger.info(message); // Usa el nivel "info" para depuración estándar
    }

    /**
     * Método para registrar mensajes de advertencia.
     *
     * @param message El mensaje de advertencia
     */
    public static void warn(String message) {
        logger.warning(message); // Usa el nivel "warning" para advertencias
    }


}
