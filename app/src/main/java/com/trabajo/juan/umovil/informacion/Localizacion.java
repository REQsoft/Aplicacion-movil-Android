package com.trabajo.juan.umovil.informacion;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.trabajo.juan.umovil.R;
import com.trabajo.juan.umovil.api.LocalizacionApi;
import com.trabajo.juan.umovil.models.Localizaciones;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Clase Localizacion
 */
public class Localizacion extends FragmentActivity implements OnMapReadyCallback {

    //----------
    //Constantes
    //----------

    /**
     * Constante necesaria para el funcionamientos de los procesos de la clase.
     */
    public final static String TAG1 = "Localización", MyPREFERENCES = "MyPrefs", Url = "urlKey";

    //----------
    //Atributos
    //----------

    /**
     * Atributos necesarios para el funcionamientos de los procesos de la clase.
     */
    private GoogleMap mMap;
    private SharedPreferences sharedpreferences;
    private Retrofit retrofit;
    private ListView listaUbicaciones;
    private List<Localizaciones> lista;
    private String[] des;

    //-----------
    //Constructor
    //-----------

    /**
     * Método constructor que inicializa las variables de la clase localizacion.
     * @param savedInstanceState - Permite la visualización de la vista localizacion.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.localizacion);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String u = sharedpreferences.getString("urlKey","");
        try {

            retrofit = new Retrofit.Builder()
                    .baseUrl(u)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }catch (Exception e)
        {
            Toast.makeText(this, "No tienes acceso a la información", Toast.LENGTH_SHORT).show();
        }
        listaUbicaciones = (ListView)findViewById(R.id.listaUbicaciones);
        lista = new ArrayList<>();
        cargarLocalizacion();
    }

    /**
     * Método que permite cargar la información de las ubicaciones desde el repositorio.
     */
    private void cargarLocalizacion() {
        LocalizacionApi service = retrofit.create(LocalizacionApi.class);
        Call<List<Localizaciones>> localizacionCall = service.obtenerInformacionLocalizaciones();

        localizacionCall.enqueue(new Callback<List<Localizaciones>>() {
            @Override
            public void onResponse(Call<List<Localizaciones>> call, Response<List<Localizaciones>> response) {
                if (response.isSuccessful()) {
                    List<Localizaciones> listaLocalizacion = response.body();
                    Localizaciones localizacion = null;
                    des = new String[listaLocalizacion.size()];
                    for (int i = 0; i < listaLocalizacion.size(); i++) {
                        localizacion = new Localizaciones();
                        String descripcion = listaLocalizacion.get(i).getDescripcion();
                        String latitud = listaLocalizacion.get(i).getLatitud();
                        String longitud = listaLocalizacion.get(i).getLongitud();
                        localizacion.setDescripcion(descripcion);
                        localizacion.setLatitud(latitud);
                        localizacion.setLongitud(longitud);
                        lista.add(localizacion);
                        des[i]=listaLocalizacion.get(i).getDescripcion();
                    }
                    ArrayAdapter adapter = new ArrayAdapter(Localizacion.this, android.R.layout.simple_list_item_1, des);
                    listaUbicaciones.setAdapter(adapter);
                } else {
                    Log.e(TAG1, "onResponse " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Localizaciones>> call, Throwable t) {
                Log.e(TAG1, "OnFailure " + t.getMessage());
            }
        });
    }

    /**
     * Método que permite obtener la lista de ubicaciones.
     * @return lista - Lista de ubicaciones cargadas.
     */
    public List<Localizaciones> darListaUbiaciones()
    {
        return lista;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney = new LatLng( 4.740675384778373, -74.1796875);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.740675384778373, -74.1796875), 4.0f));
        listaUbicaciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mMap.clear();
                LatLng sydney = new LatLng( darLatitud(position), darLongitud(position));
                mMap.addMarker(new MarkerOptions().position(sydney).title(darDesripcion(position)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(darLatitud(position), darLongitud(position)), 20.0f));
            }
        });
    }

    /**
     * Método que permite obtener la descripción dada su posición.
     * @param pPos - Parámetro que entra con la posición en la lista de cada ubicación.
     * @return descripcion - Cadena obtenida con la posición dada por parámetro.
     */
    public String darDesripcion(int pPos)
    {
        String descripcion = "";
        descripcion = darListaUbiaciones().get(pPos).getDescripcion();
        return  descripcion;
    }

    /**
     * Método que permite obtener la latitud dada su posición.
     * @param pPos - Parámetro que entra con la posición en la lista de cada ubicación.
     * @return latitud - Valor obtenido con la posición dada por parámetro.
     */
    public Double darLatitud(int pPos)
    {
        Double latitud= 0.0;
        latitud = Double.parseDouble(darListaUbiaciones().get(pPos).getLatitud());
        return  latitud;
    }

    /**
     * Método que permite obtener la longitud dada su posición.
     * @param pPos - Parámetro que entra con la posición en la lista de cada ubicación.
     * @return longitud - Valor obtenido con la posición dada por parámetro.
     */
    public Double darLongitud(int pPos)
    {
        Double longitud= 0.0;
        longitud = Double.parseDouble(darListaUbiaciones().get(pPos).getLongitud());
        return  longitud;
    }
}