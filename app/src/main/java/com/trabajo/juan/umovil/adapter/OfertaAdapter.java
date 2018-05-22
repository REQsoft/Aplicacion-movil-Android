package com.trabajo.juan.umovil.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.trabajo.juan.umovil.R;
import com.trabajo.juan.umovil.informacion.Oferta;
import com.trabajo.juan.umovil.models.Ofertas;
import java.util.List;

/**
 * Created by juan on 4/11/17.
 */

/**
 * Clase ArticuloAdapter
 */
public class OfertaAdapter extends RecyclerView.Adapter<OfertaAdapter.MyHolder> {

    //----------
    //Atributos
    //----------

    /**
     * Atributos necesarios para el funcionamientos de los procesos de la clase.
     */
    private List<Ofertas> list;
    private Oferta oferta;

    //-----------
    //Constructor
    //-----------

    /**
     * Método constructor que inicializa las variables de la clase OfertaAdapter.
     */
    public OfertaAdapter(List<Ofertas> list, Oferta oferta1) {
        oferta = new Oferta();
        oferta = oferta1;
        this.list = list;
    }

    /**
     * Método que permite adecuar la clase con la vista adapter.
     * @param parent - Contexto que perimite interacción con la vista oferta.
     * @param viewType - Contexto que perimite interacción con la vista oferta.
     * @return myHolder - La vista que contiene el adapter.
     */
    @Override
    public OfertaAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.oferta_adapter, parent, false);
        OfertaAdapter.MyHolder myHolder = new OfertaAdapter.MyHolder(view);
        return myHolder;
    }

    /**
     * Método que permite adecuar la información en el adapter.
     * @param holder - Parámetro que permite hacer la relación de la información con el adapter dada su posición.
     * @param position - Parámetro que contiene la posición de cada item.
     */
    @Override
    public void onBindViewHolder(OfertaAdapter.MyHolder holder, final int position) {
        Ofertas oferta = list.get(position);
        holder.programa.setText(oferta.getTitulo());
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
    class MyHolder extends RecyclerView.ViewHolder {
        TextView programa;

        public MyHolder(final View itemView) {
            super(itemView);
            programa = (TextView) itemView.findViewById(R.id.txtPrograma);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    oferta.dialogo(getAdapterPosition());
                }
            });
        }
    }
}