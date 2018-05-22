package com.trabajo.juan.umovil.servicios;

import android.content.Context;
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
import com.trabajo.juan.umovil.servicios.adapter.MateriaAdapter;
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
 * Clase Articulo
 */
public class Materia extends AppCompatActivity {

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
    private List<Materias> lista;
    private SharedPreferences sharedpreferences;
    private String u;

    //-----------
    //Constructor
    //-----------

    /**
     * Método constructor que inicializa las variables de la clase articulo.
     * @param savedInstanceState - Permite la visualización de la vista articulo.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.materia);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        u = sharedpreferences.getString("urlKey","");
        try {
            retrofit = new Retrofit.Builder()
                    .baseUrl(u)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }catch (Exception e)
        {
            Toast.makeText(this, "No tienes acceso a la información", Toast.LENGTH_SHORT).show();
        }

        cv = (CardView) findViewById(R.id.card_view_materia);
        rv = (RecyclerView) findViewById(R.id.recycle_materia);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
        lista = new ArrayList<>();
        cargarMateria();
    }

    /**
     * Método que permite cargar la información de las materias desde el repositorio.
     */
    public void cargarMateria() {
        MateriaApi service = retrofit.create(MateriaApi.class);
        Call<List<Materias>> materiaCall = service.obtenerListaMaterias();

        materiaCall.enqueue(new Callback<List<Materias>>() {
            @Override
            public void onResponse(Call<List<Materias>> call, Response<List<Materias>> response) {
                if (response.isSuccessful()) {
                    List<Materias> listaMateria = response.body();
                    Materias materia = null;
                    String p = getIntent().getExtras().getString("programa");
                    for (int i = 0; i < listaMateria.size(); i++) {
                        materia = new Materias();
                        String codigo = String.valueOf(listaMateria.get(i).getCodigo());
                        String nombre = listaMateria.get(i).getNombres();
                        String tipo = listaMateria.get(i).getTipo();
                        String categoria = listaMateria.get(i).getCategoria();
                        Integer creditos = listaMateria.get(i).getCreditos();
                        String programa = listaMateria.get(i).getPrograma();
                        if(p.equals(programa)) {
                            materia.setCodigo(Integer.parseInt(codigo));
                            materia.setNombres(nombre);
                            materia.setTipo(tipo);
                            materia.setCategoria(categoria);
                            materia.setCreditos(creditos);
                            materia.setPrograma(programa);
                            lista.add(materia);
                            Log.i(TAG1, listaMateria.get(i).getPrograma());
                        }
                        Log.i(TAG1, listaMateria.get(i).getPrograma());
                    }
                    MateriaAdapter recyclerAdapter = new MateriaAdapter(lista, Materia.this);
                    RecyclerView.LayoutManager recyce = new GridLayoutManager(Materia.this, 1);
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
}