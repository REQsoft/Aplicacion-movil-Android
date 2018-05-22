package com.trabajo.juan.umovil.servicios;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.trabajo.juan.umovil.R;
import com.trabajo.juan.umovil.servicios.adapter.HojaDeVidaAdapter;
import com.trabajo.juan.umovil.servicios.api.HojaDeVidaApi;
import com.trabajo.juan.umovil.servicios.models.HojasDeVida;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HojaDeVida extends AppCompatActivity implements SearchView.OnQueryTextListener{

    //----------
    //Constantes
    //----------

    /**
     * Constante necesaria para el funcionamientos de los procesos de la clase.
     */
    public final static String TAG1 = "Estudiante", MyPREFERENCES = "MyPrefs", Url = "urlKey";

    //----------
    //Atributos
    //----------

    /**
     * Atributos necesarios para el funcionamientos de los procesos de la clase.
     */
    private Retrofit retrofit;
    private RecyclerView rv;
    private CardView cv;
    private List<HojasDeVida> lista;
    private SharedPreferences sharedpreferences;
    private String u;
    private HojaDeVidaAdapter recyclerAdapter;

    //-----------
    //Constructor
    //-----------

    /**
     * Método constructor que inicializa las variables de la clase estudiante.
     * @param savedInstanceState - Permite la visualización de la vista estudiante.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hoja_de_vida);

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

        cv = (CardView) findViewById(R.id.card_view_hoja_de_vida);
        rv = (RecyclerView) findViewById(R.id.recycle_hoja_de_vida);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
        lista = new ArrayList<>();
        cargarHojaDeVida();
    }

    /**
     * Método que permite cargar la información de las hojas de vida desde el repositorio.
     */
    public void cargarHojaDeVida() {
        HojaDeVidaApi service = retrofit.create(HojaDeVidaApi.class);
        Call<List<HojasDeVida>> hojasDeVidaCall = service.obtenerListaHojasDeVida();

        hojasDeVidaCall.enqueue(new Callback<List<HojasDeVida>>() {
            @Override
            public void onResponse(Call<List<HojasDeVida>> call, Response<List<HojasDeVida>> response) {
                if (response.isSuccessful()) {
                    List<HojasDeVida> listaHojaDeVida = response.body();
                    HojasDeVida hojaDeVida = null;
                    for (int i = 0; i < listaHojaDeVida.size(); i++) {
                        hojaDeVida = new HojasDeVida();
                        String identificacion = String.valueOf(listaHojaDeVida.get(i).getIdentificacion());
                        String nombres = listaHojaDeVida.get(i).getNombres();
                        String apellidos = listaHojaDeVida.get(i).getApellidos();
                        String direccion = listaHojaDeVida.get(i).getDireccion();
                        String telofono = listaHojaDeVida.get(i).getTelofono();
                        String estadoCivil = listaHojaDeVida.get(i).getEstadoCivil();
                        String email = listaHojaDeVida.get(i).getTelofono();
                        String tipoSangre = listaHojaDeVida.get(i).getTipoSangre();
                        hojaDeVida.setIdentificacion(identificacion);
                        hojaDeVida.setNombres(nombres);
                        hojaDeVida.setApellidos(apellidos);
                        hojaDeVida.setDireccion(direccion);
                        hojaDeVida.setTelofono(telofono);
                        hojaDeVida.setEstadoCivil(estadoCivil);
                        hojaDeVida.setEmail(email);
                        hojaDeVida.setTipoSangre(tipoSangre);
                        lista.add(hojaDeVida);
                    }
                    recyclerAdapter = new HojaDeVidaAdapter(lista, HojaDeVida.this);
                    RecyclerView.LayoutManager recyce = new GridLayoutManager(HojaDeVida.this, 1);
                    rv.setLayoutManager(recyce);
                    rv.setItemAnimator(new DefaultItemAnimator());
                    rv.setAdapter(recyclerAdapter);
                } else {
                    Log.e(TAG1, "onResponse " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<HojasDeVida>> call, Throwable t) {
                Log.e(TAG1, "OnFailure " + t.getMessage());
            }
        });
    }

    /**
     * Método que permite implementar el botón de búsqueda para filtrar información
     * @param menu - Contexto de la clase Menu para ser usada.
     * @return true - Si se implemento correctamente el botón.
     */
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_buscador, menu);
        MenuItem item = menu.findItem(R.id.buscador);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {

                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                recyclerAdapter.setFilter(lista);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    /**
     * Método que permite actualizar la lista filtrada al momento de cambiar el contenido de la caja de texto.
     * @param newText - Parámetro que entra como el nuevo texto que va a ser utilizado para filtrar la información.
     * @return true - Si la lista fue filtrada correctamente.
     */
    @Override
    public boolean onQueryTextChange(String newText) {

        try{
            List<HojasDeVida> listaFiltrada = filter(lista, newText);
            recyclerAdapter.setFilter(listaFiltrada);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * Método que permite filtrar la lista de hojas de vida con la información necesaria.
     * @param hojasDeVidas - Parámetro que entra con la lista de hojas de vida.
     * @param texto - Parámetro que entra como texto que se va a comparar para filtrar.
     * @return listaFiltrada - Lista que contiene la información filtrada.
     */
    private List<HojasDeVida> filter(List<HojasDeVida> hojasDeVidas, String texto)
    {
        List<HojasDeVida>listaFiltrada = new ArrayList<>();

        try
        {
            texto = texto.toLowerCase();

            for(HojasDeVida hojas: hojasDeVidas)
            {
                String identificacion = hojas.getIdentificacion().toLowerCase();
                String nombres = hojas.getNombres().toLowerCase();
                String apellidos = hojas.getApellidos().toLowerCase();

                if(identificacion.contains(texto))
                {
                    listaFiltrada.add(hojas);
                }
                else if(nombres.contains(texto))
                {
                    listaFiltrada.add(hojas);
                }
                else if(apellidos.contains(texto))
                {
                    listaFiltrada.add(hojas);
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return listaFiltrada;
    }
}