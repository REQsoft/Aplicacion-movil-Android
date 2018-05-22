package com.trabajo.juan.umovil.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by juan on 29/10/17.
 */

/**
 * Clase que contiene el modelo del Inicio.
 */
public class Inicio {

    //----------
    //Atributos
    //----------

    /**
     * Atributos necesarios para el funcionamientos de los procesos de la clase.
     */
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("lema")
    @Expose
    private String lema;
    @SerializedName("imagen")
    @Expose
    private String imagen;
    @SerializedName("mision")
    @Expose
    private String mision;
    @SerializedName("vision")
    @Expose
    private String vision;
    @SerializedName("informacion")
    @Expose
    private String informacion;
    @SerializedName("estado_directorio")
    @Expose
    private String estadoDirectorio;
    @SerializedName("estado_articulo")
    @Expose
    private String estadoArticulo;
    @SerializedName("estado_localizacion")
    @Expose
    private String estadoLocalizacion;
    @SerializedName("hoja_de_vida")
    @Expose
    private String hojaDeVida;
    @SerializedName("oferta_academica")
    @Expose
    private String ofertaAcademica;
    @SerializedName("nota_semestre")
    @Expose
    private String notaSemestre;
    @SerializedName("informacion_materia")
    @Expose
    private String informacionMateria;
    @SerializedName("lista_estudiantes")
    @Expose
    private String listaEstudiantes;

    //----------
    //Métodos
    //----------

    /**
     * Métodos de tipo get y set que permiten obtener y dar información del inicio.
     */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLema() {
        return lema;
    }

    public void setLema(String lema) {
        this.lema = lema;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getMision() {
        return mision;
    }

    public void setMision(String mision) {
        this.mision = mision;
    }

    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public String getEstadoDirectorio() {
        return estadoDirectorio;
    }

    public void setEstadoDirectorio(String estadoDirectorio) {
        this.estadoDirectorio = estadoDirectorio;
    }

    public String getEstadoArticulo() {
        return estadoArticulo;
    }

    public void setEstadoArticulo(String estadoArticulo) {
        this.estadoArticulo = estadoArticulo;
    }

    public String getEstadoLocalizacion() {
        return estadoLocalizacion;
    }

    public void setEstadoLocalizacion(String estadoLocalizacion) {
        this.estadoLocalizacion = estadoLocalizacion;
    }

    public String getHojaDeVida() {
        return hojaDeVida;
    }

    public void setHojaDeVida(String hojaDeVida) {
        this.hojaDeVida = hojaDeVida;
    }

    public String getOfertaAcademica() {
        return ofertaAcademica;
    }

    public void setOfertaAcademica(String ofertaAcademica) {
        this.ofertaAcademica = ofertaAcademica;
    }

    public String getNotaSemestre() {
        return notaSemestre;
    }

    public void setNotaSemestre(String notaSemestre) {
        this.notaSemestre = notaSemestre;
    }

    public String getInformacionMateria() {
        return informacionMateria;
    }

    public void setInformacionMateria(String informacionMateria) {
        this.informacionMateria = informacionMateria;
    }

    public String getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setListaEstudiantes(String listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }

}