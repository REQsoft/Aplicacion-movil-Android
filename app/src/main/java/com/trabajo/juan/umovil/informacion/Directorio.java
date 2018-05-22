package com.trabajo.juan.umovil.informacion;

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
import com.trabajo.juan.umovil.adapter.ArticuloAdapter;
import com.trabajo.juan.umovil.adapter.DirectorioAdapter;
import com.trabajo.juan.umovil.api.DirectorioApi;
import com.trabajo.juan.umovil.models.Directorios;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Directorio extends AppCompatActivity {

    //----------
    //Constantes
    //----------

    /**
     * Constante necesaria para el funcionamientos de los procesos de la clase.
     */
    public final static String TAG1 = "Directorio", MyPREFERENCES = "MyPrefs", Url = "urlKey";

    //----------
    //Atributos
    //----------

    /**
     * Atributos necesarios para el funcionamientos de los procesos de la clase.
     */
    private Retrofit retrofit;
    private RecyclerView rv;
    private CardView cv;
    private List<Directorios> lista;
    private SharedPreferences sharedpreferences;
    private String u;

    //-----------
    //Constructor
    //-----------

    /**
     * Método constructor que inicializa las variables de la clase directorio.
     * @param savedInstanceState - Permite la visualización de la vista directorio.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.directorio);
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

        cv = (CardView) findViewById(R.id.card_view_directorio);
        rv = (RecyclerView) findViewById(R.id.recycle_directorio);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
        lista = new ArrayList<>();
        cargarDirectorios();
    }

    /**
     * Método que permite cargar la información de los directorios desde el repositorio.
     */
    public void cargarDirectorios() {
        DirectorioApi service = retrofit.create(DirectorioApi.class);
        Call<List<Directorios>> directorioCall = service.obtenerListaDirectorios();

        directorioCall.enqueue(new Callback<List<Directorios>>() {
            @Override
            public void onResponse(Call<List<Directorios>> call, Response<List<Directorios>> response) {
                if (response.isSuccessful()) {
                    List<Directorios> listaDirectorio = response.body();
                    Directorios directorio = null;
                    for (int i = 0; i < listaDirectorio.size(); i++) {
                        directorio = new Directorios();
                        String dependencia = listaDirectorio.get(i).getDependencia();
                        String extension = listaDirectorio.get(i).getExtension();
                        String linea = listaDirectorio.get(i).getLineaDirecta();
                        directorio.setDependencia(dependencia);
                        directorio.setExtension(extension);
                        directorio.setLineaDirecta(linea);
                        lista.add(directorio);
                    }
                    DirectorioAdapter recyclerAdapter = new DirectorioAdapter(lista);
                    RecyclerView.LayoutManager recyce = new GridLayoutManager(Directorio.this, 1);
                    rv.setLayoutManager(recyce);
                    rv.setItemAnimator(new DefaultItemAnimator());
                    rv.setAdapter(recyclerAdapter);
                } else {
                    Log.e(TAG1, "onResponse " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Directorios>> call, Throwable t) {
                Log.e(TAG1, "OnFailure " + t.getMessage());
            }
        });
    }
}