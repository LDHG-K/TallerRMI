/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author usuario
 */
public class Competitor implements Serializable{
    
private static final long serialVersionUID = 6529685098267757690L;
    
    private long id;
    private String apodo;
    private Date fechaInscripcion;
    private Date fechaCaducidad;

    public Competitor( String apodo, Date fechaCaducidad) {
        
        this.apodo = apodo;
        long d = new Date().getTime();
        this.fechaInscripcion = new java.sql.Date(d);
        this.fechaCaducidad = fechaCaducidad;
    }
    
    public Competitor(long id, String apodo,Date fechaDeInscripcion, Date fechaCaducidad) {
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

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(java.sql.Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(java.sql.Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    
    public void verficarInvariante(){
        assert apodo == null && !apodo.equals("") : "El participante debe tener un apodo";
        assert id>0 : "id debe ser mayor a 0";
        assert fechaCaducidad.after(new Date()) : "No se puede añadir una fecha de caducidad atrazada";
    }
}