package com.trabajo.juan.umovil.servicios.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by juan on 13/11/17.
 */

/**
 * Clase que contiene el modelo del servicio materias.
 */
public class Materias {

    //----------
    //Atributos
    //----------

    /**
     * Atributos necesarios para el funcionamientos de los procesos de la clase.
     */
    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("nombres")
    @Expose
    private String nombres;
    @SerializedName("tipo")
    @Expose
    private String tipo;
    @SerializedName("categoria")
    @Expose
    private String categoria;
    @SerializedName("creditos")
    @Expose
    private Integer creditos;
    @SerializedName("programa")
    @Expose
    private String programa;

    //----------
    //Métodos
    //----------

    /**
     * Métodos de tipo get y set que permiten obtener y dar información de cada materia.
     */
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }
}