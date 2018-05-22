package com.trabajo.juan.umovil.servicios.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trabajo.juan.umovil.R;
import com.trabajo.juan.umovil.servicios.HojaDeVida;
import com.trabajo.juan.umovil.servicios.models.HojasDeVida;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juan on 11/11/17.
 */

/**
 * Clase HojaDeVidaAdapter
 */
public class HojaDeVidaAdapter extends RecyclerView.Adapter<HojaDeVidaAdapter.MyHolder>{

    //----------
    //Atributos
    //----------

    /**
     * Atributos necesarios para el funcionamientos de los procesos de la clase.
     */
    private HojaDeVida principal;
    private List<HojasDeVida> list;

    //-----------
    //Constructor
    //-----------

    /**
     * Método constructor que inicializa las variables de la clase HojaDeVidaAdapter.
     */
    public HojaDeVidaAdapter(List<HojasDeVida> list, HojaDeVida pPrincipal) {
        principal = new HojaDeVida();
        principal = pPrincipal;
        this.list = list;
    }

    /**
     * Método que permite adecuar la clase con la vista adapter.
     * @param parent - Contexto que perimite interacción con la vista hojaDeVida.
     * @param viewType - Contexto que perimite interacción con la vista hojaDeVida.
     * @return myHolder - La vista que contiene el adapter.
     */
    @Override
    public HojaDeVidaAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hoja_de_vida_adapter,parent,false);
        HojaDeVidaAdapter.MyHolder myHolder = new HojaDeVidaAdapter.MyHolder(view);
        return myHolder;
    }

    /**
     * Método que permite adecuar la información en el adapter.
     * @param holder - Parámetro que permite hacer la relación de la información con el adapter dada su posición.
     * @param position - Parámetro que contiene la posición de cada item.
     */
    @Override
    public void onBindViewHolder(HojaDeVidaAdapter.MyHolder holder, int position) {
        HojasDeVida hojaDeVida = list.get(position);
        holder.identificacion.setText(String.valueOf(hojaDeVida.getIdentificacion()));
        holder.nombre.setText(hojaDeVida.getNombres());
        holder.apellido.setText(hojaDeVida.getApellidos());
        holder.direccion.setText(hojaDeVida.getDireccion());
        holder.telefono.setText(hojaDeVida.getTelofono());
        holder.estadoCivil.setText(hojaDeVida.getEstadoCivil());
        holder.email.setText(hojaDeVida.getEmail());
        holder.tipoSangre.setText(hojaDeVida.getTipoSangre());
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
        TextView identificacion, nombre, apellido, direccion, telefono, estadoCivil, email, tipoSangre;

        public MyHolder(View itemView) {
            super(itemView);
            identificacion = (TextView) itemView.findViewById(R.id.lblIdentificacion);
            nombre = (TextView) itemView.findViewById(R.id.lblNombresH);
            apellido = (TextView) itemView.findViewById(R.id.lblApellidosH);
            direccion = (TextView) itemView.findViewById(R.id.lblDireccion);
            telefono = (TextView) itemView.findViewById(R.id.lblTelefono);
            estadoCivil = (TextView) itemView.findViewById(R.id.lblEstadoCivil);
            email = (TextView) itemView.findViewById(R.id.lblEmail);
            tipoSangre = (TextView) itemView.findViewById(R.id.lblSangre);
        }
    }

    /**
     * Método que permite filtrar información en la lista de hojas de vida.
     * @param listaHojas - Parámetro que contiene la lista filtrada con la información necesaria.
     */
    public void setFilter(List<HojasDeVida> listaHojas)
    {
        this.list = new ArrayList<>();
        this.list.addAll(listaHojas);
        notifyDataSetChanged();
    }
}