package com.trabajo.juan.umovil.servicios.api;

import com.trabajo.juan.umovil.servicios.models.Materias;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by juan on 11/11/17.
 */

/**
 * Clase que contiene el acceso al servicio de obtener materias.
 */
public interface MateriaApi
{
    @GET("servicio/5/")
    Call<List<Materias>> obtenerListaMaterias();
}