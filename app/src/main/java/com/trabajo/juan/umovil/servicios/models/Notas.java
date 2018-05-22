package com.trabajo.juan.umovil.servicios.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by juan on 13/11/17.
 */

/**
 * Clase que contiene el modelo del servicio notas.
 */
public class Notas{

    //----------
    //Atributos
    //----------

    /**
     * Atributos necesarios para el funcionamientos de los procesos de la clase.
     */
    @SerializedName("codigo_estudiante")
    @Expose
    private Integer codigoEstudiante;
    @SerializedName("codigo_materia")
    @Expose
    private Integer codigoMateria;
    @SerializedName("materia")
    @Expose
    private String materia;
    @SerializedName("nota")
    @Expose
    private String nota;
    @SerializedName("faltas")
    @Expose
    private Integer faltas;

    //----------
    //Métodos
    //----------

    /**
     * Métodos de tipo get y set que permiten obtener y dar información de cada nota.
     */
    public Integer getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(Integer codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public Integer getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(Integer codigoMateria) {
        this.codigoMateria = codigoMateria;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Integer getFaltas() {
        return faltas;
    }

    public void setFaltas(Integer faltas) {
        this.faltas = faltas;
    }

}