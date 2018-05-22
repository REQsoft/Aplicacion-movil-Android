package com.trabajo.juan.umovil.api;

import com.trabajo.juan.umovil.models.Servicios;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by juan on 4/11/17.
 */

/**
 * Clase que contiene el acceso a la información de los Servicios.
 */
public interface ServicioApi {
    @GET("servicios")
    Call<List<Servicios>> obtenerListaServicios();
}