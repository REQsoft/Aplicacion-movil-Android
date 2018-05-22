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
import com.trabajo.juan.umovil.api.ArticuloApi;
import com.trabajo.juan.umovil.models.Articulos;
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
public class Articulo extends AppCompatActivity {

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
    private List<Articulos> lista;
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
        setContentView(R.layout.articulo);

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

        cv = (CardView)findViewById(R.id.card_view_articulo);
        rv = (RecyclerView) findViewById(R.id.recycle_articulo);
        ImageLoaderConfiguration config=new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
        lista = new ArrayList<>();
        cargarArticulos();
    }

    /**
     * Método que permite cargar la información de los artículos desde el repositorio.
     */
    public void cargarArticulos()
    {
        ArticuloApi service = retrofit.create(ArticuloApi.class);
        Call<List<Articulos>> articuloCall = service.obtenerListaArticulos();

        articuloCall.enqueue(new Callback<List<Articulos>>() {
            @Override
            public void onResponse(Call<List<Articulos>> call, Response<List<Articulos>> response) {
                if (response.isSuccessful()) {
                    List<Articulos> listaArticulo = response.body();
                    Articulos articulo = null;
                    for (int i = 0; i < listaArticulo.size(); i++) {
                        articulo = new Articulos();
                        String datos[] = listaArticulo.get(i).getFoto().split("/");
                        String ruta = datos[5]+"/"+datos[6];
                        String descripcion = listaArticulo.get(i).getDescripcion();
                        String nombre = listaArticulo.get(i).getFecha();
                        String imagen = u+"media/"+ruta;
                        articulo.setDescripcion(descripcion);
                        articulo.setFecha(nombre);
                        articulo.setFoto(imagen);
                        lista.add(articulo);
                        Log.i(TAG1, ""+listaArticulo.get(i).getDescripcion()+" "+listaArticulo.get(i).getFecha()+" "+listaArticulo.get(i).getFoto());
                    }
                    ArticuloAdapter recyclerAdapter = new ArticuloAdapter(lista, ImageLoader.getInstance());
                    RecyclerView.LayoutManager recyce = new GridLayoutManager(Articulo.this, 1);
                    rv.setLayoutManager(recyce);
                    rv.setItemAnimator(new DefaultItemAnimator());
                    rv.setAdapter(recyclerAdapter);
                } else {
                    Log.e(TAG1, "onResponse " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Articulos>> call, Throwable t) {
                Log.e(TAG1, "OnFailure " + t.getMessage());
            }
        });
    }
}