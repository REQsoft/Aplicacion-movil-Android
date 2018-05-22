package com.trabajo.juan.umovil;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends AppCompatActivity {

    //----------
    //Constantes
    //----------

    /**
     * Constante necesaria para el funcionamientos de los procesos de la clase.
     */
    public static final long SPLASH_SCREEN_DELAY = 4000;

    //-----------
    //Constructor
    //-----------

    /**
     * Método constructor que inicializa las variables de la clase splashscreen.
     * @param savedInstanceState - Permite la visualización de la vista splashscreen.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SplashScreen.this.startActivity(new Intent(SplashScreen.this, Presentacion.class));
                SplashScreen.this.finish();
            }
        },SPLASH_SCREEN_DELAY);
    }
}