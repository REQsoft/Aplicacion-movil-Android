package com.trabajo.juan.umovil.servicios.api;

import com.trabajo.juan.umovil.servicios.models.Estudiantes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by juan on 5/11/17.
 */

/**
 * Clase que contiene el acceso al servicio de listar estudiantes.
 */
public interface EstudianteApi
{
    @GET("servicio/2/")
    Call<List<Estudiantes>> obtenerListaEstudiantes();
}