package com.trabajo.juan.umovil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Clase AcercaDe
 */
public class AcercaDe extends AppCompatActivity {

    //-----------
    //Constructor
    //-----------

    /**
     * Método constructor que inicializa las variables de la clase AcercaDe.
     * @param savedInstanceState - Permite la visualización de la vista AcercaDe.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acerca_de);
    }
}
