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
import com.trabajo.juan.umovil.servicios.adapter.ProgramaMateriaAdapter;
import com.trabajo.juan.umovil.servicios.api.MateriaApi;
import com.trabajo.juan.umovil.servicios.models.Materias;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Clase ProgramaMateria
 */
public class ProgramaMateria extends AppCompatActivity {

    //----------
    //Constantes
    //----------

    /**
     * Constante necesaria para el funcionamientos de los procesos de la clase.
     */
    public final static String TAG1 = "ProgramaMateria", MyPREFERENCES = "MyPrefs", Url = "urlKey";

    //----------
    //Atributos
    //----------

    /**
     * Atributos necesarios para el funcionamientos de los procesos de la clase.
     */
    private Retrofit retrofit;
    private RecyclerView rv;
    private CardView cv;
    private List<Materias> lista;
    private SharedPreferences sharedpreferences;
    private String u;

    //-----------
    //Constructor
    //-----------

    /**
     * Método constructor que inicializa las variables de la clase programaMateria.
     * @param savedInstanceState - Permite la visualización de la vista programaMateria.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.programa_materia);

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

        cv = (CardView) findViewById(R.id.card_view_programa_materia);
        rv = (RecyclerView) findViewById(R.id.recycle_programa_materia);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
        lista = new ArrayList<>();
        cargarMaterias();
    }

    /**
     * Método que permite cargar la información de las materias desde el repositorio.
     */
    private void cargarMaterias() {
        MateriaApi service = retrofit.create(MateriaApi.class);
        Call<List<Materias>> programaCall = service.obtenerListaMaterias();

        programaCall.enqueue(new Callback<List<Materias>>() {
            @Override
            public void onResponse(Call<List<Materias>> call, Response<List<Materias>> response) {
                if (response.isSuccessful()) {
                    List<Materias> listaPrograma = response.body();
                    Materias programa = null;
                    String aux = "";
                    for (int i = 0; i < listaPrograma.size(); i++) {
                        programa = new Materias();
                        String p1 = listaPrograma.get(i).getPrograma();
                        if (!p1.equals(aux)) {
                            programa.setPrograma(p1);
                            aux = p1;
                            Log.i("eee: ", aux);
                            lista.add(programa);
                        }
                    }
                    ProgramaMateriaAdapter recyclerAdapter = new ProgramaMateriaAdapter(lista,ProgramaMateria.this);
                    RecyclerView.LayoutManager recyce = new GridLayoutManager(ProgramaMateria.this, 1);
                    rv.setLayoutManager(recyce);
                    rv.setItemAnimator(new DefaultItemAnimator());
                    rv.setAdapter(recyclerAdapter);
                } else {
                    Log.e(TAG1, "onResponse " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Materias>> call, Throwable t) {
                Log.e(TAG1, "OnFailure " + t.getMessage());
            }
        });
    }

    /**
     * Método que permite obtener las materias por programa.
     * @param pMateria - Parámetro que ingresa con el nombre de la materia seleccionada.
     */
    public void darProgramas(String pMateria) {
        Intent intent = new Intent(ProgramaMateria.this, Materia.class);
        intent.putExtra("programa", pMateria);
        startActivity(intent);
    }
}