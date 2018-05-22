package com.trabajo.juan.umovil.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.trabajo.juan.umovil.R;
import com.trabajo.juan.umovil.models.Articulos;

import java.util.List;

/**
 * Created by juan on 29/10/17.
 */

/**
 * Clase ArticuloAdapter
 */
public class ArticuloAdapter extends RecyclerView.Adapter<ArticuloAdapter.MyHolder>{

    //----------
    //Atributos
    //----------

    /**
     * Atributos necesarios para el funcionamientos de los procesos de la clase.
     */
    private List<Articulos> list;
    private ImageLoader imageLoader;

    //-----------
    //Constructor
    //-----------

    /**
     * Método constructor que inicializa las variables de la clase ArticuloAdapter.
     */
    public ArticuloAdapter(List<Articulos> list, ImageLoader imageLoader) {
        this.list = list;
        this.imageLoader=imageLoader;
    }

    /**
     * Método que permite adecuar la clase con la vista adapter.
     * @param parent - Contexto que perimite interacción con la vista articulo.
     * @param viewType - Contexto que perimite interacción con la vista articulo.
     * @return myHolder - La vista que contiene el adapter.
     */
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.articulo_adapter,parent,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    /**
     * Método que permite adecuar la información en el adapter.
     * @param holder - Parámetro que permite hacer la relación de la información con el adapter dada su posición.
     * @param position - Parámetro que contiene la posición de cada item.
     */
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Articulos articulo = list.get(position);
        holder.descripcion.setText(articulo.getDescripcion());
        holder.fecha.setText(articulo.getFecha());
        String image1 = articulo.getFoto();
        imageLoader.displayImage(image1, holder.imagen);
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
        TextView descripcion, fecha;
        ImageView imagen;

        public MyHolder(View itemView) {
            super(itemView);
            descripcion = (TextView) itemView.findViewById(R.id.txtDescripcion);
            fecha = (TextView) itemView.findViewById(R.id.txtFecha);
            imagen = (ImageView) itemView.findViewById(R.id.imgArticulo);
        }
    }
}