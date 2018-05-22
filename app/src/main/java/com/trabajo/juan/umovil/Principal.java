package com.trabajo.juan.umovil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.trabajo.juan.umovil.adapter.ServicioAdapter;
import com.trabajo.juan.umovil.api.InicioApi;
import com.trabajo.juan.umovil.api.ServicioApi;
import com.trabajo.juan.umovil.configuracion.Configuracion;
import com.trabajo.juan.umovil.configuracion.Configuracion2;
import com.trabajo.juan.umovil.informacion.Articulo;
import com.trabajo.juan.umovil.informacion.CalcularNotas;
import com.trabajo.juan.umovil.informacion.Directorio;
import com.trabajo.juan.umovil.informacion.Localizacion;
import com.trabajo.juan.umovil.informacion.Oferta;
import com.trabajo.juan.umovil.models.Inicio;
import com.trabajo.juan.umovil.models.Servicios;
import com.trabajo.juan.umovil.servicios.HojaDeVida;
import com.trabajo.juan.umovil.servicios.MateriaCodigoEstudiante;
import com.trabajo.juan.umovil.servicios.MateriaEstudiante;
import com.trabajo.juan.umovil.servicios.ProgramaMateria;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Clase principal
 */
public class Principal extends AppCompatActivity {

    //----------
    //Constantes
    //----------

    /**
     * Constante necesaria para el funcionamientos de los procesos de la clase.
     */
    public final static String TAG = "Principal", MyPREFERENCES = "MyPrefs",Url = "urlKey";

    //----------
    //Atributos
    //----------

    /**
     * Atributos necesarios para el funcionamientos de los procesos de la clase.
     */
    private PresentacionManager presentacionManager;
    private SharedPreferences sharedpreferences;
    private FrameLayout lprincipal, linformacion, lservicios;
    private Retrofit retrofit;
    private CardView cv;
    private RecyclerView rv;
    private TextView mTextMessage, txtEslogan, txtInformacion, lblUsuario, lblBienvenido;
    private ImageView imgInicio, imgFlecha;
    private ArrayList<Inicio> parametrizacion;
    private List<Servicios> lista;
    private String s, u;


    //-----------
    //Constructor
    //-----------

    /**
     * Método constructor que inicializa las variables de la clase principal.
     * @param savedInstanceState - Permite la visualización de la vista principal.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        cv = (CardView) findViewById(R.id.card_view_servicio);
        rv = (RecyclerView) findViewById(R.id.recycle_servicios);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        u = sharedpreferences.getString("urlKey","");
        s = sharedpreferences.getString("urlServicios", "");
        String n = sharedpreferences.getString("usuarioKey", "");

        lblUsuario = (TextView)findViewById(R.id.lblUsuario);
        lblBienvenido = (TextView)findViewById(R.id.lblBienvenido);
        mTextMessage = (TextView) findViewById(R.id.txtTitulo);
        txtEslogan = (TextView)findViewById(R.id.txtEslogan);
        txtInformacion = (TextView)findViewById(R.id.txtInformacion);
        imgFlecha = (ImageView)findViewById(R.id.imgFlecha);
        imgInicio= (ImageView)findViewById(R.id.imgInicio);
        parametrizacion = new ArrayList<>();
        lista = new ArrayList<>();

        lprincipal = (FrameLayout)findViewById(R.id.layout_principal);
        linformacion = (FrameLayout)findViewById(R.id.layout_informacion);
        lservicios = (FrameLayout)findViewById(R.id.layout_servicios);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        try {
            retrofit = new Retrofit.Builder()
                    .baseUrl(u)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            obtenerDatos();
            cargarServicios();
        }catch (Exception e)
        {
            Intent intent = new Intent(Principal.this, Configuracion.class);
            startActivity(intent);

            Toast.makeText(this, "No tienes acceso a la información", Toast.LENGTH_SHORT).show();
        }

        if(!s.equals(""))
        {
            lblBienvenido.setVisibility(View.VISIBLE);
            lblUsuario.setVisibility(View.VISIBLE);
            lblUsuario.setText(n);
        }
    }

    //----------
    //Métodos
    //----------

    /**
     * Método que permite ejecutar los eventos del menú principal.
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mostrarPrincipal();
                    return true;
                case R.id.navigation_dashboard:
                    mostrarInformacion();
                    return true;
                case R.id.navigation_notifications:
                    int est = sharedpreferences.getInt("estadoKey",0);
                    if(est == 0)
                    {
                        Toast.makeText(Principal.this, "Revisa la url en configuración", Toast.LENGTH_SHORT).show();
                    }else {
                        mostrarServicios();
                    }
                    return true;
            }
            return false;
        }
    };

    /**
     * Método que permite visualizar la vista de inicio.
     */
    public void mostrarPrincipal()
    {
        lprincipal.setVisibility(View.VISIBLE);
        linformacion.setVisibility(View.GONE);
        lservicios.setVisibility(View.GONE);
    }

    /**
     * Método que permite visualiar la vista de información.
     */
    public void mostrarInformacion()
    {
        lprincipal.setVisibility(View.GONE);
        linformacion.setVisibility(View.VISIBLE);
        lservicios.setVisibility(View.GONE);
    }

    /**
     * Método que permite visualizar la vista de servicios.
     */
    public void mostrarServicios()
    {
        String n = sharedpreferences.getString("codKey", "");
        if(n.equals(""))
        {
            Intent intent = new Intent(Principal.this, Configuracion2.class);
            startActivity(intent);
        }
        else if(u.equals(""))
        {
            Toast.makeText(this, "No tienes acceso a la información", Toast.LENGTH_SHORT).show();
        }
        else {
            lprincipal.setVisibility(View.GONE);
            linformacion.setVisibility(View.GONE);
            lservicios.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Método que permite cargar la información de la vista inicio desde el repositorio.
     */
    private void obtenerDatos() {
        InicioApi service = retrofit.create(InicioApi.class);
        Call<List<Inicio>> inicioCall = service.obtenerInformacionInicio();

        inicioCall.enqueue(new Callback<List<Inicio>>() {
            @Override
            public void onResponse(Call<List<Inicio>> call, Response<List<Inicio>> response) {
                if(response.isSuccessful())
                {
                    Inicio inicio = new Inicio();
                    List<Inicio> listaInicio = response.body();

                    String datos[] = listaInicio.get(0).getImagen().split("/");
                    String ruta = datos[5]+"/"+datos[6];
                    String nombre = listaInicio.get(0).getNombre();
                    String mision = listaInicio.get(0).getMision();
                    String vision = listaInicio.get(0).getVision();
                    String estado_directorio = listaInicio.get(0).getEstadoDirectorio();
                    String estado_articulo = listaInicio.get(0).getEstadoArticulo();
                    String estado_localizacion = listaInicio.get(0).getEstadoLocalizacion();
                    String estado_hoja_de_vida = listaInicio.get(0).getHojaDeVida();
                    String estado_oferta_academica = listaInicio.get(0).getOfertaAcademica();
                    String estado_notas = listaInicio.get(0).getNotaSemestre();
                    String estado_informacion_materia = listaInicio.get(0).getInformacionMateria();
                    String estado_lista_estudiante = listaInicio.get(0).getListaEstudiantes();

                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString("directorioKey", estado_directorio);
                    editor.putString("articuloKey", estado_articulo);
                    editor.putString("localizacionKey", estado_localizacion);
                    editor.putString("hojaDeVidaKey", estado_hoja_de_vida);
                    editor.putString("ofertaKey", estado_oferta_academica);
                    editor.putString("notasKey", estado_notas);
                    editor.putString("informacionMateriaKey", estado_informacion_materia);
                    editor.putString("listaEstudianteKey", estado_lista_estudiante);
                    editor.commit();

                    inicio.setNombre(nombre);
                    inicio.setMision(mision);
                    inicio.setVision(vision);
                    parametrizacion.add(inicio);

                    imgFlecha.setVisibility(View.GONE);
                    mTextMessage.setVisibility(View.VISIBLE);
                    mTextMessage.setText(listaInicio.get(0).getNombre());
                    txtEslogan.setText(listaInicio.get(0).getLema());
                    Glide.with(Principal.this).load(u+"media/"+ruta).into(imgInicio);
                    txtInformacion.setText(listaInicio.get(0).getInformacion());
                }
            }

            @Override
            public void onFailure(Call<List<Inicio>> call, Throwable t) {
                Log.e(TAG,t.getMessage());
            }
        });
    }

    /**
     * Método que permite cargar los servicios desde el repositorio.
     */
    public void cargarServicios()
    {
        final ServicioApi service = retrofit.create(ServicioApi.class);
        Call<List<Servicios>> servicioCall = service.obtenerListaServicios();

        servicioCall.enqueue(new Callback<List<Servicios>>() {
            @Override
            public void onResponse(Call<List<Servicios>> call, Response<List<Servicios>> response) {
                if (response.isSuccessful()) {
                    List<Servicios> listaServicio = response.body();
                    Servicios servicio = null;
                    String service = s;
                    if(!service.equals("")) {
                        String[] servicios = service.split(",");
                        for (int i = 0; i < listaServicio.size(); i++) {
                            servicio = new Servicios();
                            String nombre = listaServicio.get(i).getNombre();
                            if (servicios.length == 1) {
                                if (servicios[0].equals(nombre)) {
                                    servicio.setNombre(nombre);
                                    lista.add(servicio);
                                }
                            } else if (servicios.length == 2) {
                                if (servicios[0].equals(nombre)) {
                                    servicio.setNombre(servicios[0]);
                                    lista.add(servicio);
                                }
                                if (servicios[1].equals(nombre)) {
                                    servicio.setNombre(servicios[1]);
                                    lista.add(servicio);
                                }
                            } else if (servicios.length == 3) {
                                if (servicios[0].equals(nombre)) {
                                    servicio.setNombre(servicios[0]);
                                    lista.add(servicio);
                                }
                                if (servicios[1].equals(nombre)) {
                                    servicio.setNombre(servicios[1]);
                                    lista.add(servicio);
                                }
                                if (servicios[2].equals(nombre)) {
                                    servicio.setNombre(servicios[2]);
                                    lista.add(servicio);
                                }
                            }
                        }
                    }
                    ServicioAdapter recyclerAdapter = new ServicioAdapter(lista, Principal.this);
                    RecyclerView.LayoutManager recyce = new GridLayoutManager(Principal.this, 1);
                    rv.setLayoutManager(recyce);
                    rv.setItemAnimator(new DefaultItemAnimator());
                    rv.setAdapter(recyclerAdapter);
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
     * Método que permite capturar el nombre del servicio desde el adapter.
     * @param pNombre - Nombre del servicio que llega como parámetro.
     */
    public void capturarNombreServicio(String pNombre)
    {
        ejecutarServicio(pNombre);
    }

    /**
     * Método que permite ejecutar un servicio dado su nombre por parámetro.
     * @param pNombre - Nombre del servicio que llega como parámetro.
     */
    public void ejecutarServicio(String pNombre) {
        String h = sharedpreferences.getString("hojaDeVidaKey", "");
        String n = sharedpreferences.getString("notasKey", "");
        String i = sharedpreferences.getString("informacionMateriaKey", "");
        String l = sharedpreferences.getString("listaEstudianteKey", "");

        if (pNombre.equals("Listado Estudiantes"))
        {
            if(l.equals("inactivo")) {
                Toast.makeText(this, "El servicio se encuentra inactivo", Toast.LENGTH_SHORT).show();
            }
            else {
                listarEstudiante();
            }
        }
        else if (pNombre.equals("Hoja de Vida Estudiantes"))
        {
            if(h.equals("inactivo")) {
                Toast.makeText(this, "El servicio se encuentra inactivo", Toast.LENGTH_SHORT).show();
            }
            else {
                darHojaDeVida();
            }
        }
        else if (pNombre.equals("Consulta Calificaciones"))
        {
            if(n.equals("inactivo")) {
                Toast.makeText(this, "El servicio se encuentra inactivo", Toast.LENGTH_SHORT).show();
            }
            else {
                consultarNotas();
            }
        }
        else if (pNombre.equals("Consulta Espacio Académico"))
        {
            if(i.equals("inactivo")) {
                Toast.makeText(this, "El servicio se encuentra inactivo", Toast.LENGTH_SHORT).show();
            }
            else {
                consultarMaterias();
            }
        }
    }

    /**
     * Método que permite obtener la lista de estudiantes.
     */
    public void listarEstudiante()
    {
        Intent intent = new Intent(Principal.this, MateriaEstudiante.class);
        startActivity(intent);
    }

    /**
     * Método que permite obtener la hoja de vida de cada estudiante.
     */
    public void darHojaDeVida()
    {
        Intent intent = new Intent(Principal.this, HojaDeVida.class);
        startActivity(intent);
    }

    /**
     * Método que permite consultar las notas de cada estudiante.
     */

    public void consultarNotas()
    {
        Intent intent = new Intent(Principal.this, MateriaCodigoEstudiante.class);
        startActivity(intent);
    }

    /**
     * Método que permite consultar las materias de cada programa.
     */
    public void consultarMaterias()
    {
        Intent intent = new Intent(Principal.this, ProgramaMateria.class);
        startActivity(intent);
    }

    /**
     * Método que permite adaptar el menú superior a la vista principal.
     * @param menu - Contexto de la clase Menu para ser utilizado.
     * @return - true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Método que permite ejecutar los eventos de cada botón del menú superior.
     * @param item - Contexto de la clase MenuIntem para ser utilizado.
     * @return super.onOptionsItemSelected(item) - Dependiendo el item seleccionado,
     *           retorna la acción configurada.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(Principal.this, Configuracion.class);
            startActivity(intent);
        }
        else if (id == R.id.action_settings2) {
            Intent intent = new Intent(Principal.this, Configuracion2.class);
            startActivity(intent);
        }
        else if (id == R.id.action_settings3) {
            presentacionManager = new PresentacionManager(this);
            if (!presentacionManager.Check()) {
                presentacionManager.setFirst(true);
                Intent intent = new Intent(Principal.this, Presentacion.class);
                startActivity(intent);
            }
        }
        else if (id == R.id.action_settings4) {
            Intent intent = new Intent(Principal.this, AcercaDe.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Método que permite ejecutar los eventos de los botones de la vista información.
     * @param view - Contexto de la clase View para ser utilizado.
     */
    public void onClick(View view)
    {
        String d = sharedpreferences.getString("directorioKey", "");
        String a = sharedpreferences.getString("articuloKey", "");
        String l = sharedpreferences.getString("localizacionKey", "");
        String o = sharedpreferences.getString("ofertaKey", "");
        int est = sharedpreferences.getInt("estadoKey",0);

        if(view.getId() == R.id.btnCalcularNotas)
        {
            startActivity(new Intent(Principal.this, CalcularNotas.class));
        }
        else if(est != 0) {
            if(view.getId() == R.id.btnArticulos)
            {

                if (u.equals("")) {
                    Toast.makeText(this, "No tienes acceso a la información", Toast.LENGTH_SHORT).show();
                } else if (a.equals("inactivo")) {
                    Toast.makeText(this, "El servicio se encuentra inactivo", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Principal.this, Articulo.class);
                    startActivity(intent);
                }
            }
            else  if(view.getId() == R.id.btnDirectorio)
            {
                if(u.equals(""))
                {
                    Toast.makeText(this, "No tienes acceso a la información", Toast.LENGTH_SHORT).show();
                }
                else if(d.equals("inactivo"))
                {
                    Toast.makeText(this, "El servicio se encuentra inactivo", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(Principal.this, Directorio.class);
                    startActivity(intent);
                }
            }
            else if(view.getId() == R.id.btnLocalizacion)
            {
                if(u.equals(""))
                {
                    Toast.makeText(this, "No tienes acceso a la información", Toast.LENGTH_SHORT).show();
                }
                else if(l.equals("inactivo"))
                {
                    Toast.makeText(this, "El servicio se encuentra inactivo", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(Principal.this, Localizacion.class);
                    startActivity(intent);
                }
            }
            else if(view.getId() == R.id.btnOferta)
            {
                if(u.equals(""))
                {
                    Toast.makeText(this, "No tienes acceso a la información", Toast.LENGTH_SHORT).show();
                }
                else if(o.equals("inactivo"))
                {
                    Toast.makeText(this, "El servicio se encuentra inactivo", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(Principal.this, Oferta.class);
                    startActivity(intent);
                }
            }
            else if(view.getId() == R.id.btnMisionVision)
            {
                if(u.equals(""))
                {
                    Toast.makeText(this, "No tienes acceso a la información", Toast.LENGTH_SHORT).show();
                }
                else if(l.equals("inactivo"))
                {
                    Toast.makeText(this, "El servicio se encuentra inactivo", Toast.LENGTH_SHORT).show();
                }else
                {
                    Intent intent = new Intent(Principal.this, MisionVision.class);
                    intent.putExtra("id_universidad", parametrizacion.get(0).getNombre());
                    intent.putExtra("id_mision", parametrizacion.get(0).getMision());
                    intent.putExtra("id_vision", parametrizacion.get(0).getVision());
                    startActivity(intent);
                }
            }
        }
        else
        {
            Toast.makeText(this, "Revisa la url en configuración", Toast.LENGTH_SHORT).show();
        }
    }
}