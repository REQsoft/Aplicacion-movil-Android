package com.trabajo.juan.umovil.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by juan on 4/11/17.
 */

/**
 * Clase que contiene el modelo de los Usuarios.
 */
public class Usuarios {

    //----------
    //Atributos
    //----------

    /**
     * Atributos necesarios para el funcionamientos de los procesos de la clase.
     */
    @SerializedName("codigo")
    @Expose
    private String codigo;
    @SerializedName("usuario")
    @Expose
    private String usuario;
    @SerializedName("rol")
    @Expose
    private String rol;

    //----------
    //Métodos
    //----------

    /**
     * Métodos de tipo get y set que permiten obtener y dar información de cada usuario.
     */
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}