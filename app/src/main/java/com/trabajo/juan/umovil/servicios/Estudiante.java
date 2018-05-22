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
import com.trabajo.juan.umovil.servicios.adapter.EstudianteAdapter;
import com.trabajo.juan.umovil.servicios.api.EstudianteApi;
import com.trabajo.juan.umovil.servicios.models.Estudiantes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Estudiante extends AppCompatActivity implements SearchView.OnQueryTextListener{

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
    private List<Estudiantes> lista;
    private SharedPreferences sharedpreferences;
    private String u;
    private EstudianteAdapter recyclerAdapter;

    //-----------
    //Constructor
    //-----------

    /**
     * Método constructor que inicializa las variables de la clase estudiante.
     * @param savedInstanceState - Permite la visualización de la vista estudiante.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estudiante);

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

        cv = (CardView) findViewById(R.id.card_view_estudiante);
        rv = (RecyclerView) findViewById(R.id.recycle_estudiante);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
        lista = new ArrayList<>();
        cargarEstudiante();
    }

    /**
     * Método que permite cargar la información de los estudiantes desde el repositorio.
     */
    public void cargarEstudiante() {
        EstudianteApi service = retrofit.create(EstudianteApi.class);
        Call<List<Estudiantes>> estudianteCall = service.obtenerListaEstudiantes();

        estudianteCall.enqueue(new Callback<List<Estudiantes>>() {
            @Override
            public void onResponse(Call<List<Estudiantes>> call, Response<List<Estudiantes>> response) {
                if (response.isSuccessful()) {
                    List<Estudiantes> listaEstudiante = response.body();
                    Estudiantes estudiante = null;
                    String m = getIntent().getExtras().getString("materia");
                    for (int i = 0; i < listaEstudiante.size(); i++) {
                        estudiante = new Estudiantes();
                        String codigo = String.valueOf(listaEstudiante.get(i).getCodigo());
                        String nombres = listaEstudiante.get(i).getNombres();
                        String apellidos = listaEstudiante.get(i).getApellidos();
                        int semestre = listaEstudiante.get(i).getSemestre();
                        String programa = listaEstudiante.get(i).getPrograma();
                        String materia = listaEstudiante.get(i).getMateria();
                        if(m.equals(materia)) {
                            estudiante.setCodigo(Integer.parseInt(codigo));
                            estudiante.setNombres(nombres);
                            estudiante.setApellidos(apellidos);
                            estudiante.setSemestre(semestre);
                            estudiante.setPrograma(programa);
                            lista.add(estudiante);
                        }
                    }
                    recyclerAdapter = new EstudianteAdapter(lista, Estudiante.this);
                    RecyclerView.LayoutManager recyce = new GridLayoutManager(Estudiante.this, 1);
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
            List<Estudiantes> listaFiltrada = filter(lista, newText);
            recyclerAdapter.setFilter(listaFiltrada);
        }catch (Exception e)
        {
            //e.printStackTrace();
            Log.e("Filtrar: ", e.getMessage());
        }

        return true;
    }

    /**
     * Método que permite filtrar la lista de estudiantes con la información necesaria.
     * @param estudiante - Parámetro que entra con la lista de estudiantes.
     * @param texto - Parámetro que entra como texto que se va a comparar para filtrar.
     * @return listaFiltrada - Lista que contiene la información filtrada.
     */
    private List<Estudiantes> filter(List<Estudiantes> estudiante, String texto)
    {
        List<Estudiantes> listaFiltrada = new ArrayList<>();

        try
        {
            texto = texto.toLowerCase();

            for(Estudiantes est: estudiante)
            {
                String codigo = String.valueOf(est.getCodigo());
                String nombres = est.getNombres().toLowerCase();
                String apellidos = est.getApellidos().toLowerCase();
                String semestre = String.valueOf(est.getSemestre());

                if(codigo.contains(texto))
                {
                    listaFiltrada.add(est);
                }
                else if(nombres.contains(texto))
                {
                    listaFiltrada.add(est);
                }
                else if(apellidos.contains(texto))
                {
                    listaFiltrada.add(est);
                }
                else if(semestre.contains(texto))
                {
                    listaFiltrada.add(est);
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return listaFiltrada;
    }
}