package com.trabajo.juan.umovil.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by juan on 4/11/17.
 */

/**
 * Clase que contiene el modelo de los Ofertas.
 */
public class Ofertas {

    //----------
    //Atributos
    //----------

    /**
     * Atributos necesarios para el funcionamientos de los procesos de la clase.
     */
    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("formacion")
    @Expose
    private String formacion;
    @SerializedName("titulo")
    @Expose
    private String titulo;
    @SerializedName("lugar")
    @Expose
    private String lugar;
    @SerializedName("metodologia")
    @Expose
    private String metodologia;
    @SerializedName("creditos")
    @Expose
    private Integer creditos;
    @SerializedName("periodicidad")
    @Expose
    private String periodicidad;
    @SerializedName("duracion")
    @Expose
    private String duracion;
    @SerializedName("jornada")
    @Expose
    private String jornada;
    @SerializedName("valor")
    @Expose
    private String valor;

    //----------
    //Métodos
    //----------

    /**
     * Métodos de tipo get y set que permiten obtener y dar información de cada oferta.
     */
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getFormacion() {
        return formacion;
    }

    public void setFormacion(String formacion) {
        this.formacion = formacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getMetodologia() {
        return metodologia;
    }

    public void setMetodologia(String metodologia) {
        this.metodologia = metodologia;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public String getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(String periodicidad) {
        this.periodicidad = periodicidad;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
