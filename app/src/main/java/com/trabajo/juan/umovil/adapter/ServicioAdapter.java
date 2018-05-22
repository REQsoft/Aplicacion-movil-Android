package com.trabajo.juan.umovil.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trabajo.juan.umovil.Principal;
import com.trabajo.juan.umovil.R;
import com.trabajo.juan.umovil.models.Servicios;

import java.util.List;

/**
 * Created by juan on 4/11/17.
 */

/**
 * Clase ServicioAdapter
 */
public class ServicioAdapter extends RecyclerView.Adapter<ServicioAdapter.MyHolder>{

    //----------
    //Atributos
    //----------

    /**
     * Atributos necesarios para el funcionamientos de los procesos de la clase.
     */
    private Principal principal;
    private List<Servicios> list;

    //-----------
    //Constructor
    //-----------

    /**
     * Método constructor que inicializa las variables de la clase ServicioAdapter.
     */
    public ServicioAdapter(List<Servicios> list, Principal pPrincipal) {
        principal = new Principal();
        principal = pPrincipal;
        this.list = list;
    }

    /**
     * Método que permite adecuar la clase con la vista adapter.
     * @param parent - Contexto que perimite interacción con la vista servicio.
     * @param viewType - Contexto que perimite interacción con la vista servicio.
     * @return myHolder - La vista que contiene el adapter.
     */
    @Override
    public ServicioAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.servicio_adapter,parent,false);
        ServicioAdapter.MyHolder myHolder = new ServicioAdapter.MyHolder(view);
        return myHolder;
    }

    /**
     * Método que permite adecuar la información en el adapter.
     * @param holder - Parámetro que permite hacer la relación de la información con el adapter dada su posición.
     * @param position - Parámetro que contiene la posición de cada item.
     */
    @Override
    public void onBindViewHolder(ServicioAdapter.MyHolder holder, int position) {
        Servicios servicio = list.get(position);
        holder.nombre.setText(servicio.getNombre());
    }

    /**
     * Método que retorna la cantidad de elementos que tiene la lista.
     * @return Cantidad de elementos que contiene la lista.
     */
    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * Clase que permite identificar y adecuar los elementos al adapter.
     */
    class MyHolder extends RecyclerView.ViewHolder{
        TextView nombre;

        public MyHolder(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.txtNombre);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    principal.capturarNombreServicio(nombre.getText().toString());
                }
            });
        }
    }
}