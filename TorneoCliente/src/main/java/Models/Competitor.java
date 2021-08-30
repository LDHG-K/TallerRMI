/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
//import java.time.LocalDate;
//import java.util.Date;
import java.sql.Date;

/**
 *
 * @author Luis
 */
public class Competitor implements Serializable{

    private static final long serialVersionUID = 6529685098267757690L;

    private long id;
    private String apodo;
    private Date fechaInscripcion;
    private Date fechaCaducidad;

    public Competitor(long id, String apodo, Date fechaInscripcion, Date fechaCaducidad) {
        this.id = id;
        this.apodo = apodo;
        this.fechaInscripcion = fechaInscripcion;
        this.fechaCaducidad = fechaCaducidad;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    
    
}
