package com.trabajo.juan.umovil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Clase MisionVision
 */
public class MisionVision extends AppCompatActivity {

    //----------
    //Atributos
    //----------

    /**
     * Atributos necesarios para el funcionamientos de los procesos de la clase.
     */
    private TextView lblTitulo;
    private TextView lblMision;
    private TextView lblVision;

    //-----------
    //Constructor
    //-----------

    /**
     * Método constructor que inicializa las variables de la clase MisionVision.
     * @param savedInstanceState - Permite la visualización de la vista MisionVision.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mision_vision);

        lblTitulo = (TextView)findViewById(R.id.lblTitulo);
        lblMision = (TextView)findViewById(R.id.lblMision);
        lblVision = (TextView)findViewById(R.id.lblVision);

        lblTitulo.setText(getIntent().getExtras().getString("id_universidad"));
        lblMision.setText(getIntent().getExtras().getString("id_mision"));
        lblVision.setText(getIntent().getExtras().getString("id_vision"));
    }
}