package com.trabajo.juan.umovil.servicios;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.trabajo.juan.umovil.R;
import com.trabajo.juan.umovil.servicios.adapter.MateriaEstudianteAdapter;
import com.trabajo.juan.umovil.servicios.api.EstudianteApi;
import com.trabajo.juan.umovil.servicios.models.Estudiantes;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Clase MateriaEstudiante
 */
public class MateriaEstudiante extends AppCompatActivity {

    //----------
    //Constantes
    //----------

    /**
     * Constante necesaria para el funcionamientos de los procesos de la clase.
     */
    public final static String TAG1 = "Artículos", MyPREFERENCES = "MyPrefs", Url = "urlKey";

    //----------
    //Atributos
    //----------

    /**
     * Atributos necesarios para el funcionamientos de los procesos de la clase.
     */
    private Retrofit retrofit;
    private RecyclerView rv;
    private CardView cv;
    private List<Estudiantes> lista;
    private SharedPreferences sharedpreferences;
    private String u;

    //-----------
    //Constructor
    //-----------

    /**
     * Método constructor que inicializa las variables de la clase materiaEstudiante.
     * @param savedInstanceState - Permite la visualización de la vista materiaEstudiante.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.materia_estudiante);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        u = sharedpreferences.getString("urlKey", "");
        try {
            retrofit = new Retrofit.Builder()
                    .baseUrl(u)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        } catch (Exception e) {
            Toast.makeText(this, "No tienes acceso a la información", Toast.LENGTH_SHORT).show();
        }

        cv = (CardView) findViewById(R.id.card_view_materia_estudiante);
        rv = (RecyclerView) findViewById(R.id.recycle_materia_estudiante);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
        lista = new ArrayList<>();
        cargarOfertas();
    }

    /**
     * Método que permite cargar la información de los estudiantes desde el repositorio.
     */
    private void cargarOfertas() {
        EstudianteApi service = retrofit.create(EstudianteApi.class);
        Call<List<Estudiantes>> ofertaCall = service.obtenerListaEstudiantes();

        ofertaCall.enqueue(new Callback<List<Estudiantes>>() {
            @Override
            public void onResponse(Call<List<Estudiantes>> call, Response<List<Estudiantes>> response) {
                if (response.isSuccessful()) {
                    List<Estudiantes> listaEstudiante = response.body();
                    Estudiantes estudiante = null;
                    String aux = "";
                    for (int i = 0; i < listaEstudiante.size(); i++) {
                        estudiante = new Estudiantes();
                        String materia = listaEstudiante.get(i).getMateria();
                        if(!materia.equals(aux)) {
                            estudiante.setMateria(materia);
                            aux = materia;
                            lista.add(estudiante);
                        }
                    }
                    MateriaEstudianteAdapter recyclerAdapter = new MateriaEstudianteAdapter(lista, MateriaEstudiante.this);
                    RecyclerView.LayoutManager recyce = new GridLayoutManager(MateriaEstudiante.this, 1);
                    rv.setLayoutManager(recyce);
                    rv.setItemAnimator(new DefaultItemAnimator());
                    rv.setAdapter(recyclerAdapter);
                } else {
                    Log.e(TAG1, "onResponse " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Estudiantes>> call, Throwable t) {
                Log.e(TAG1, "OnFailure " + t.getMessage());
            }
        });
    }

    /**
     * Método que permite obtener los estudiantes por materia.
     * @param pMateria - Parámetro que ingresa con el nombre de la materia seleccionada.
     */
    public void darEstudiantes(String pMateria)
    {
        Intent intent = new Intent(MateriaEstudiante.this, Estudiante.class);
        intent.putExtra("materia", pMateria);
        startActivity(intent);
    }
}