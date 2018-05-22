package com.trabajo.juan.umovil.api;

import com.trabajo.juan.umovil.models.Inicio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by juan on 29/10/17.
 */

/**
 * Clase que contiene el acceso a la información de la vista de Inicio.
 */
public interface InicioApi {
    @GET("parametrizacion/?format=json")
    Call<List<Inicio>> obtenerInformacionInicio();
}