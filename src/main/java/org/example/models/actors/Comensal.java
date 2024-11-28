package org.example.models.actors;

import lombok.Getter;
import lombok.Setter;
import org.example.Patterns.ComensalObserver;
import org.example.models.core.Persona;
import org.example.utils.LoggerDepuracionFXGL;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Comensal extends Persona implements Runnable {

    private int mesaAsignada;
    private final List<ComensalObserver> observers = new ArrayList<>(); // Lista de observers


    // Agregar un observer
    public void addObserver(ComensalObserver observer) {
        observers.add(observer);
    }

    // Remover un observer
    public void removeObserver(ComensalObserver observer) {
        observers.remove(observer);
    }

    // Notificar a todos los observers
    private void notifyObservers() {
        for (ComensalObserver observer : observers) {
            observer.onMesaAsignada(this, mesaAsignada);
        }
    }

    // Sobrescribir el setter para notificar cambios
    public void setMesaAsignada(int mesaAsignada) {
        this.mesaAsignada = mesaAsignada; // Asigna el valor
        notifyObservers(); // Notifica a los observers
    }

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
