/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructural;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;


/**
 *
 * @author usuario
 */
public class Competitor implements Serializable{
    
private static final long serialVersionUID = 6529685098267757690L;
    
    private long id;
    private String apodo;
    private java.sql.Date fechaInscripcion;
    private java.sql.Date fechaCaducidad;

    public Competitor(long id, String apodo, java.sql.Date fechaCaducidad) {
        this.id = id;
        this.apodo = apodo;
        this.fechaInscripcion = Date.valueOf(LocalDate.now());
        this.fechaCaducidad = fechaCaducidad;
    }
    
    public Competitor(long id, String apodo,java.sql.Date fechaDeInscripcion, java.sql.Date fechaCaducidad) {
        this.id = id;
        this.apodo = apodo;
        this.fechaInscripcion = fechaDeInscripcion;
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

    public java.sql.Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(java.sql.Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public java.sql.Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(java.sql.Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    
    public void verficarInvariante(){
        assert apodo == null && !apodo.equals("") : "El participante debe tener un apodo";
        assert id>0 : "id debe ser mayor a 0";
        assert fechaCaducidad.after(Date.valueOf(LocalDate.now())) : "No se puede añadir una fecha de caducidad atrazada";
    }
}