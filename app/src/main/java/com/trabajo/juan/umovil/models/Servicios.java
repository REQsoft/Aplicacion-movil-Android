package com.trabajo.juan.umovil.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by juan on 4/11/17.
 */

/**
 * Clase que contiene el modelo de los Servicios.
 */
public class Servicios {

    //----------
    //Atributos
    //----------

    /**
     * Atributos necesarios para el funcionamientos de los procesos de la clase.
     */
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("rol")
    @Expose
    private String rol;

    //----------
    //Métodos
    //----------

    /**
     * Métodos de tipo get y set que permiten obtener y dar información de cada servicio.
     */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}