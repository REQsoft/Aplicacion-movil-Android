package com.trabajo.juan.umovil.api;

import com.trabajo.juan.umovil.models.Articulos;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by juan on 29/10/17.
 */

/**
 * Clase que contiene el acceso a la información de los Artículos.
 */
public interface ArticuloApi {
    @GET("articulo/?format=json")
    Call<List<Articulos>> obtenerListaArticulos();
}
