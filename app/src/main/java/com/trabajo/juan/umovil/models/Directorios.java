package com.trabajo.juan.umovil.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by juan on 29/10/17.
 */

/**
 * Clase que contiene el modelo de los Directorios.
 */
public class Directorios {

    //----------
    //Atributos
    //----------

    /**
     * Atributos necesarios para el funcionamientos de los procesos de la clase.
     */
    @SerializedName("dependencia")
    @Expose
    private String dependencia;
    @SerializedName("extension")
    @Expose
    private String extension;
    @SerializedName("linea_directa")
    @Expose
    private String lineaDirecta;

    //----------
    //Métodos
    //----------

    /**
     * Métodos de tipo get y set que permiten obtener y dar información de cada directorio.
     */
    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getLineaDirecta() {
        return lineaDirecta;
    }

    public void setLineaDirecta(String lineaDirecta) {
        this.lineaDirecta = lineaDirecta;
    }
}
