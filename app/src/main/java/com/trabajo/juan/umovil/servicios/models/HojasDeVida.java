package com.trabajo.juan.umovil.servicios.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by juan on 11/11/17.
 */

/**
 * Clase que contiene el modelo del servicio hojas de vida.
 */
public class HojasDeVida {

    //----------
    //Atributos
    //----------

    /**
     * Atributos necesarios para el funcionamientos de los procesos de la clase.
     */
    @SerializedName("identificacion")
    @Expose
    private String identificacion;
    @SerializedName("nombres")
    @Expose
    private String nombres;
    @SerializedName("apellidos")
    @Expose
    private String apellidos;
    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("telofono")
    @Expose
    private String telofono;
    @SerializedName("estado_civil")
    @Expose
    private String estadoCivil;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("tipo_sangre")
    @Expose
    private String tipoSangre;

    //----------
    //Métodos
    //----------

    /**
     * Métodos de tipo get y set que permiten obtener y dar información de cada hoja de vida.
     */
    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelofono() {
        return telofono;
    }

    public void setTelofono(String telofono) {
        this.telofono = telofono;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }
}