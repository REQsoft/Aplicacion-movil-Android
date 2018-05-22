package com.trabajo.juan.umovil.servicios.api;

import com.trabajo.juan.umovil.servicios.models.Notas;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by juan on 13/11/17.
 */

/**
 * Clase que contiene el acceso al servicio de obtener notas.
 */
public interface NotaApi
{
    @GET("servicio/4/")
    Call<List<Notas>> obtenerListaNotas();
}