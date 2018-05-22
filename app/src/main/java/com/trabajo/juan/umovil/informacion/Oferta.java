package com.trabajo.juan.umovil.informacion;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.trabajo.juan.umovil.adapter.OfertaAdapter;
import com.trabajo.juan.umovil.api.OfertaApi;
import com.trabajo.juan.umovil.models.Ofertas;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Clase Oferta
 */
public class Oferta extends AppCompatActivity {

    //----------
    //Constantes
    //----------

    /**
     * Constante necesaria para el funcionamientos de los procesos de la clase.
     */
    public final static String TAG1 = "Oferta", MyPREFERENCES = "MyPrefs", Url = "urlKey";

    //----------
    //Atributos
    //----------

    /**
     * Atributos necesarios para el funcionamientos de los procesos de la clase.
     */
    private Retrofit retrofit;
    private RecyclerView rv;
    private CardView cv;
    private List<Ofertas> lista;
    private SharedPreferences sharedpreferences;
    private String u;

    //-----------
    //Constructor
    //-----------

    /**
     * Método constructor que inicializa las variables de la clase oferta.
     * @param savedInstanceState - Permite la visualización de la vista oferta.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oferta);

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

        cv = (CardView) findViewById(R.id.card_view_oferta);
        rv = (RecyclerView) findViewById(R.id.recycle_oferta);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
        lista = new ArrayList<>();
        cargarOfertas();
    }

    /**
     * Método que permite cargar la información de las ofertas desde el repositorio.
     */
    private void cargarOfertas() {
        OfertaApi service = retrofit.create(OfertaApi.class);
        Call<List<Ofertas>> ofertaCall = service.obtenerInformacionOfertas();

        ofertaCall.enqueue(new Callback<List<Ofertas>>() {
            @Override
            public void onResponse(Call<List<Ofertas>> call, Response<List<Ofertas>> response) {
                if (response.isSuccessful()) {
                    List<Ofertas> listaOferta = response.body();
                    Ofertas oferta = null;
                    for (int i = 0; i < listaOferta.size(); i++) {
                        oferta = new Ofertas();
                        int codigo = listaOferta.get(i).getCodigo();
                        String formacion = listaOferta.get(i).getFormacion();
                        String titulo = listaOferta.get(i).getTitulo();
                        String lugar = listaOferta.get(i).getLugar();
                        String metodologia = listaOferta.get(i).getMetodologia();
                        int creditos = listaOferta.get(i).getCreditos();
                        String periodicidad = listaOferta.get(i).getPeriodicidad();
                        String duracion = listaOferta.get(i).getDuracion();
                        String jornada = listaOferta.get(i).getJornada();
                        String valor = listaOferta.get(i).getValor();
                        oferta.setCodigo(codigo);
                        oferta.setFormacion(formacion);
                        oferta.setTitulo(titulo);
                        oferta.setLugar(lugar);
                        oferta.setMetodologia(metodologia);
                        oferta.setCreditos(creditos);
                        oferta.setPeriodicidad(periodicidad);
                        oferta.setDuracion(duracion);
                        oferta.setJornada(jornada);
                        oferta.setValor(valor);
                        lista.add(oferta);
                    }
                    OfertaAdapter recyclerAdapter = new OfertaAdapter(lista,  Oferta.this);
                    RecyclerView.LayoutManager recyce = new GridLayoutManager(Oferta.this, 1);
                    rv.setLayoutManager(recyce);
                    rv.setItemAnimator(new DefaultItemAnimator());
                    rv.setAdapter(recyclerAdapter);
                } else {
                    Log.e(TAG1, "onResponse " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Ofertas>> call, Throwable t) {
                Log.e(TAG1, "OnFailure " + t.getMessage());
            }
        });
    }

    /**
     * Método que permite obtener la lista de ofertas.
     * @return lista - Lista con las ofertas cargadas.
     */
    public List<Ofertas> darListaOferta()
    {
        return lista;
    }

    /**
     * Método que permite obtener la información de cada oferta en un alertDialog dada su posición.
     * @param pPosicion - Parámetro que contiene la posición de la oferta en la lista cargada.
     */
    public void dialogo(int pPosicion)
    {
        int pos = pPosicion;
        String titulo = darListaOferta().get(pos).getTitulo();
        String formacion = darListaOferta().get(pos).getFormacion();
        String lugar = darListaOferta().get(pos).getLugar();
        String metodologia = darListaOferta().get(pos).getMetodologia();
        String creditos = darListaOferta().get(pos).getCreditos().toString();
        String periodicidad = darListaOferta().get(pos).getPeriodicidad();
        String duracion = darListaOferta().get(pos).getDuracion();
        String jornada = darListaOferta().get(pos).getJornada();
        String valor =darListaOferta().get(pos).getValor();
        String texto = "Título: "+titulo+"\n"+"Formación: "+formacion+"\n"+
                        "Lugar: "+lugar+"\n"+"Metodología: "+metodologia+"\n"+
                        "Créditos: "+creditos+"\n"+"Periodicidad: "+periodicidad+"\n"+
                        "Duración: "+duracion+"\n"+"Jornada: "+jornada+"\n"+"Valor: "+valor;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Detalle Oferta");
        builder.setMessage(texto)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                });
        // Create the AlertDialog object and return it
        builder.create().show();
    }
}