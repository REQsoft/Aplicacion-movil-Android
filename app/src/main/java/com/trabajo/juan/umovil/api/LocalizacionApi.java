package com.trabajo.juan.umovil.api;

import com.trabajo.juan.umovil.models.Localizaciones;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by juan on 31/10/17.
 */

/**
 * Clase que contiene el acceso a la información de las Ubicaciones.
 */
public interface LocalizacionApi {
    @GET("localizacion/?format=json")
    Call<List<Localizaciones>> obtenerInformacionLocalizaciones();
}
