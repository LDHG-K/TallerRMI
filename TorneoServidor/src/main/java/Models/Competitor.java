/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Luis
 */
public class Competitor {

    private int id;
    private String apodo;
    private LocalDate fechaInscripcion;
    private LocalDate fechaCaducidad;

    public Competitor(int id, String apodo, LocalDate fechaInscripcion, LocalDate fechaCaducidad) {
        this.id = id;
        this.apodo = apodo;
        this.fechaInscripcion = fechaInscripcion;
        this.fechaCaducidad = fechaCaducidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    
    
}
