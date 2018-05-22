package com.trabajo.juan.umovil.servicios.api;

import com.trabajo.juan.umovil.servicios.models.HojasDeVida;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by juan on 11/11/17.
 */

/**
 * Clase que contiene el acceso al servicio de obtener hojas de vida.
 */
public interface HojaDeVidaApi
{
    @GET("servicio/3/")
    Call<List<HojasDeVida>> obtenerListaHojasDeVida();
}
