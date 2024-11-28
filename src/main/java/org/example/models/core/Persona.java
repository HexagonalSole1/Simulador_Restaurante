package org.example.models.core;

public abstract class Persona {
    protected String nombre;
    protected int id;

    public Persona(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public abstract void realizarAccion(); // Método abstracto que será implementado por las subclases
}