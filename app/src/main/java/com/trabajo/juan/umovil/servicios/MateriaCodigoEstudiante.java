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
import com.trabajo.juan.umovil.servicios.adapter.MateriaCodigoEstudianteAdapter;
import com.trabajo.juan.umovil.servicios.api.NotaApi;
import com.trabajo.juan.umovil.servicios.models.Notas;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Clase MateriaCodigoEstudiante
 */
public class MateriaCodigoEstudiante extends AppCompatActivity {

    //----------
    //Constantes
    //----------

    /**
     * Constante necesaria para el funcionamientos de los procesos de la clase.
     */
    public final static String TAG1 = "MateriaCódigoEstudiante", MyPREFERENCES = "MyPrefs", Url = "urlKey";

    //----------
    //Atributos
    //----------

    /**
     * Atributos necesarios para el funcionamientos de los procesos de la clase.
     */
    private Retrofit retrofit;
    private RecyclerView rv;
    private CardView cv;
    private List<Notas> lista;
    private SharedPreferences sharedpreferences;
    private String u;

    //-----------
    //Constructor
    //-----------

    /**
     * Método constructor que inicializa las variables de la clase materiaCodigoEstudiante.
     * @param savedInstanceState - Permite la visualización de la vista materiaCodigoEstudiante.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.materia_codigo_estudiante);

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

        cv = (CardView) findViewById(R.id.card_view_materia_codigo_estudiante);
        rv = (RecyclerView) findViewById(R.id.recycle_materia_codigo_estudiante);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
        lista = new ArrayList<>();
        cargarNotas();
    }

    /**
     * Método que permite cargar la información de las notas desde el repositorio.
     */
    private void cargarNotas() {
        NotaApi service = retrofit.create(NotaApi.class);
        Call<List<Notas>> notaCall = service.obtenerListaNotas();

        notaCall.enqueue(new Callback<List<Notas>>() {
            @Override
            public void onResponse(Call<List<Notas>> call, Response<List<Notas>> response) {
                if (response.isSuccessful()) {
                    List<Notas> listaNota = response.body();
                    Notas notas = null;
                    for (int i = 0; i < listaNota.size(); i++) {
                        notas = new Notas();
                        String codigoE = String.valueOf(listaNota.get(i).getCodigoEstudiante());
                        String codigoM = String.valueOf(listaNota.get(i).getCodigoMateria());
                        String materia = listaNota.get(i).getMateria();
                        String nota = listaNota.get(i).getNota();
                        int faltas = listaNota.get(i).getFaltas();
                        String n = sharedpreferences.getString("codKey", "");
                        if(n.equals(codigoE)) {
                            notas.setCodigoMateria(Integer.parseInt(codigoM));
                            notas.setMateria(materia);
                            notas.setNota(nota);
                            notas.setFaltas(faltas);
                            lista.add(notas);
                        }
                    }
                    MateriaCodigoEstudianteAdapter recyclerAdapter = new MateriaCodigoEstudianteAdapter(lista, MateriaCodigoEstudiante.this);
                    RecyclerView.LayoutManager recyce = new GridLayoutManager(MateriaCodigoEstudiante.this, 1);
                    rv.setLayoutManager(recyce);
                    rv.setItemAnimator(new DefaultItemAnimator());
                    rv.setAdapter(recyclerAdapter);
                } else {
                    Log.e(TAG1, "onResponse " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Notas>> call, Throwable t) {
                Log.e(TAG1, "OnFailure " + t.getMessage());
            }
        });
    }
}