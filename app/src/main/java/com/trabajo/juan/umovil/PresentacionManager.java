package com.trabajo.juan.umovil;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mario on 11/26/2017.
 */

/**
 * Clase PresentacionManager
 */
public class PresentacionManager {

    //----------
    //Atributos
    //----------

    /**
     * Atributos necesarios para el funcionamientos de los procesos de la clase.
     */
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context context;

    //-----------
    //Constructor
    //-----------

    /**
     * Método constructor que inicializa las variables de la clase presentacionManager.
     * @param context - Permite generarla relación con la clase presentación
     *                  y poder ejecutar procesos.
     */
    public PresentacionManager(Context context)
    {
        this.context = context;
        pref = context.getSharedPreferences("first",0);
        editor = pref.edit();
    }

    /**
     * Método que permite cambiar el estado de visualización de la vista presentación.
     * @param isFirst - Parámetro que permite atrapar el valor de visualización de la vista presentación.
     */
    public void setFirst(boolean isFirst)
    {
        editor.putBoolean("check", isFirst);
        editor.commit();
    }

    /**
     * Método que permite verificar si es posible visualiza la vista presentación.
     * @return true - Si es posible visualizar.
     *         false - No es posible visualizar.
     */
    public boolean Check()
    {
        return pref.getBoolean("check", true);
    }
}