package com.trabajo.juan.umovil.configuracion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.trabajo.juan.umovil.Principal;
import com.trabajo.juan.umovil.R;
import com.trabajo.juan.umovil.adapter.ServicioAdapter;
import com.trabajo.juan.umovil.api.InicioApi;
import com.trabajo.juan.umovil.api.ServicioApi;
import com.trabajo.juan.umovil.models.Inicio;
import com.trabajo.juan.umovil.models.Servicios;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Clase Configuracion
 */
public class Configuracion extends AppCompatActivity{

    //----------
    //Constantes
    //----------

    /**
     * Constante necesaria para el funcionamientos de los procesos de la clase.
     */
    public static final String MyPREFERENCES = "MyPrefs", Url = "urlKey", TAG = "Configuración";

    //----------
    //Atributos
    //----------

    /**
     * Atributos necesarios para el funcionamientos de los procesos de la clase.
     */
    private Retrofit retrofit;
    private EditText url;
    private Button btnGuardar;
    private SharedPreferences sharedpreferences;

    //-----------
    //Constructor
    //-----------

    /**
     * Método constructor que inicializa las variables de la clase Configuracion.
     * @param savedInstanceState - Permite la visualización de la vista Configuracion.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuracion);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        guardar();
        cargar();
    }

    /**
     * Método que permite almacenar en un archivo SharePreference la información ingresada en la caja de texto.
     */
    public void guardar() {
        url = (EditText) findViewById(R.id.editURL);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String u = url.getText().toString();
                try {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(u)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    obtenerDatos();

                }catch (Exception e)
                {
                    Intent intent = new Intent(Configuracion.this, Configuracion.class);
                    startActivity(intent);

                    Toast.makeText(Configuracion.this, "No tienes acceso a la información", Toast.LENGTH_SHORT).show();
                }
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(Url, u);
                editor.commit();
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }

    /**
     * Método que permite devolver información de un archivo SharePreference en la caja de texto.
     */
    public void cargar() {
        String u = sharedpreferences.getString("urlKey", "");
        url.setText(u);
    }

    /**
     * Método que permite evaluar si la url es correcta.
     */
    public void obtenerDatos() {
        final SharedPreferences.Editor editor = sharedpreferences.edit();
        InicioApi service = retrofit.create(InicioApi.class);
        Call<List<Inicio>> inicioCall = service.obtenerInformacionInicio();

        inicioCall.enqueue(new Callback<List<Inicio>>() {
            @Override
            public void onResponse(Call<List<Inicio>> call, Response<List<Inicio>> response) {
                if (response.isSuccessful()) {
                    editor.putInt("estadoKey", 1);
                    editor.commit();
                }
            }

            @Override
            public void onFailure(Call<List<Inicio>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                editor.putInt("estadoKey", 0);
                editor.commit();
            }
        });
    }
}