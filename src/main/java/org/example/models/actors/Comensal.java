package org.example.models.actors;

import lombok.Getter;
import lombok.Setter;
import org.example.models.core.Persona;
import org.example.utils.LoggerDepuracionFXGL;

@Getter
@Setter
public class Comensal extends Persona implements Runnable {

    private int mesaAsignada;

    public Comensal(String nombre, int id) {
        super(nombre, id);
    }
    @Override
    public void realizarAccion() {
        LoggerDepuracionFXGL.log("Comensal " + nombre + " está esperando una mesa.");
    }

    @Override
    public void run() {
        try {
            realizarAccion();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LoggerDepuracionFXGL.log("Comensal " + nombre + " fue interrumpido.");
        }
    }
}
