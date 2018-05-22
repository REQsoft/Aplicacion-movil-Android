package com.trabajo.juan.umovil.configuracion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.trabajo.juan.umovil.Principal;
import com.trabajo.juan.umovil.R;
import com.trabajo.juan.umovil.api.ServicioApi;
import com.trabajo.juan.umovil.api.UsuarioApi;
import com.trabajo.juan.umovil.models.Servicios;
import com.trabajo.juan.umovil.models.Usuarios;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Configuracion2 extends AppCompatActivity {

    //----------
    //Constantes
    //----------

    /**
     * Constante necesaria para el funcionamientos de los procesos de la clase.
     */
    public static final String CODIGO = "codKey", USUARIO = "usuarioKey", SERVICIOS = "urlServicios",
            TAG = "Configuración 2", MyPREFERENCES = "MyPrefs", Url = "urlKey";


    //----------
    //Atributos
    //----------

    /**
     * Atributos necesarios para el funcionamientos de los procesos de la clase.
     */
    private Retrofit retrofit;
    private EditText usuario;
    private Button btnInicioSesion;
    private String u;
    private List<Servicios> lista_servicios;
    private List<Usuarios> lista_usuarios;
    private SharedPreferences sharedpreferences;

    //-----------
    //Constructor
    //-----------

    /**
     * Método constructor que inicializa las variables de la clase Configuracion2.
     * @param savedInstanceState - Permite la visualización de la vista Configuracion2.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuracion2);
        lista_servicios = new ArrayList<>();
        lista_usuarios = new ArrayList<>();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        u = sharedpreferences.getString("urlKey", "");
        try {
            retrofit = new Retrofit.Builder()
                    .baseUrl(u)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            cargarUsuarios();
            cargarServicios();
        } catch (Exception e) {
            Toast.makeText(Configuracion2.this, "No tienes acceso a la información", Toast.LENGTH_SHORT).show();
        }
        guardar();
        cargar();
    }

    /**
     * Método que permite almacenar en un archivo SharePreference la información ingresada en la caja de texto.
     */
    public void guardar() {

        usuario = (EditText) findViewById(R.id.editUsuario);
        btnInicioSesion = (Button) findViewById(R.id.btnInicioSesion);

        btnInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = usuario.getText().toString();

                if (!n.equals("")) {

                    String texto = listarServicios(n);

                    if(texto.equals(""))
                    {
                        Toast.makeText(Configuracion2.this, "El usuario no existe", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(CODIGO, n);
                        editor.putString(SERVICIOS, texto);
                        editor.commit();
                        Intent i = new Intent(Configuracion2.this, Principal.class);
                        startActivity(i);
                    }

                }
                else
                {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(CODIGO, n);
                    editor.putString(SERVICIOS, "");
                    editor.commit();
                    Intent i = new Intent(Configuracion2.this, Principal.class);
                    startActivity(i);
                }
            }
        });
    }

    /**
     * Método que permite devolver información de un archivo SharePreference en la caja de texto.
     */
    public void cargar() {
        String n = sharedpreferences.getString("codKey", "");
        usuario.setText(n);

    }

    /**
     * Método que permite cargar la información de los servicios desde el repositorio.
     */
    public void cargarServicios() {
        ServicioApi service = retrofit.create(ServicioApi.class);
        Call<List<Servicios>> servicioCall = service.obtenerListaServicios();
        servicioCall.enqueue(new Callback<List<Servicios>>() {
            @Override
            public void onResponse(Call<List<Servicios>> call, Response<List<Servicios>> response) {
                if (response.isSuccessful()) {
                    List<Servicios> listaServicios = response.body();
                    Servicios servicio = null;
                    for (int i = 0; i < listaServicios.size(); i++) {
                        servicio = new Servicios();
                        String nombre = listaServicios.get(i).getNombre();
                        String rol = listaServicios.get(i).getRol();
                        servicio.setNombre(nombre);
                        servicio.setRol(rol);
                        lista_servicios.add(servicio);
                    }
                } else {
                    Log.e(TAG, "onResponse " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Servicios>> call, Throwable t) {
                Log.e(TAG, "OnFailure " + t.getMessage());
            }
        });
    }

    /**
     * Método que permite cargar la información de los usuarios desde el repositorio.
     */
    public void cargarUsuarios() {
        UsuarioApi service = retrofit.create(UsuarioApi.class);
        Call<List<Usuarios>> servicioCall = service.obtenerListaUsuarios();
        servicioCall.enqueue(new Callback<List<Usuarios>>() {
            @Override
            public void onResponse(Call<List<Usuarios>> call, Response<List<Usuarios>> response) {
                if (response.isSuccessful()) {
                    List<Usuarios> listaUsuarios = response.body();
                    Usuarios usuario = null;
                    for (int i = 0; i < listaUsuarios.size(); i++) {
                        usuario = new Usuarios();

                        String codigo = listaUsuarios.get(i).getCodigo();
                        String nombre = listaUsuarios.get(i).getUsuario();
                        String rol = listaUsuarios.get(i).getRol();

                        usuario.setCodigo(codigo);
                        usuario.setUsuario(nombre);
                        usuario.setRol(rol);
                        lista_usuarios.add(usuario);
                    }
                } else {
                    Log.e(TAG, "onResponse " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Usuarios>> call, Throwable t) {
                Log.e(TAG, "OnFailure " + t.getMessage());
            }
        });
    }

    /**
     * Método que permite devolver la lista de usuarios.
     * @return lista_usuarios - Contiene la lista de usuarios cargados.
     */
    public List<Usuarios> darUsuarios() {
        return lista_usuarios;
    }

    /**
     * Método que permite devolver la lista de servicios.
     * @return lista_servicios - Contiene la lista de servicios cargados.
     */
    public List<Servicios> darServicios() {
        return lista_servicios;
    }

    /**
     * Método que permite lista los servicios dado como parámetro el código del usuario.
     * @param pCod - Parámetro que llega con el código del usuario.
     * @return servicios - Servicios que contiene el código del usuario ingresado.
     */
    public String listarServicios(String pCod)
    {
        String servicios = "";
        int pos = buscarUsuario(pCod);
        if(pos != -1) {
            String nombre = lista_usuarios.get(pos).getUsuario();
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(USUARIO, nombre);
            editor.commit();
            String rol = lista_usuarios.get(pos).getRol();
            for (int i = 0; i < darServicios().size(); i++) {
                String rol_usuario = darServicios().get(i).getRol();
                String sRol[] = rol_usuario.split(",");

                if (rol.equalsIgnoreCase(rol_usuario)) {
                    servicios += darServicios().get(i).getNombre()+",";
                }

                if(sRol.length == 3)
                {
                    if(sRol[0].equalsIgnoreCase(rol) ||
                            sRol[1].replace(" ", "").equalsIgnoreCase(rol) ||
                            sRol[2].replace(" ", "").equalsIgnoreCase(rol))
                    {
                        servicios += darServicios().get(i).getNombre()+",";
                    }
                }

                if(sRol.length == 2)
                {
                    if(sRol[0].equalsIgnoreCase(rol) ||
                            sRol[1].replace(" ", "").equalsIgnoreCase(rol))
                    {
                        servicios += darServicios().get(i).getNombre()+",";
                    }
                }
            }
        }
        return servicios;
    }

    /**
     * Método que busca usuario en la lista dado su código.
     * @param pCod - Parámetro que llega con el código del usuario.
     * @return posicion - Retorna la posición del usuario en la lista.
     */
    public int buscarUsuario(String pCod) {
        boolean encontro = false;
        int posicion = -1;

        for(int i = 0; i<darUsuarios().size() && encontro == false;i++)
        {
            String cod = darUsuarios().get(i).getCodigo();
            if(cod.equals(pCod))
            {
                posicion = i;
                encontro = true;
            }
        }
        return posicion;
    }
}