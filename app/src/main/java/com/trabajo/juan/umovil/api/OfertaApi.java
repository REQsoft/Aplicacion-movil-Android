package com.trabajo.juan.umovil.api;

import com.trabajo.juan.umovil.models.Ofertas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by juan on 4/11/17.
 */

/**
 * Clase que contiene el acceso a la información de las Ofertas.
 */
public interface OfertaApi {
    @GET("servicio/1/")
    Call<List<Ofertas>> obtenerInformacionOfertas();
}
