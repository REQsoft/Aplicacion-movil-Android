package com.trabajo.juan.umovil.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.trabajo.juan.umovil.R;
import com.trabajo.juan.umovil.models.Directorios;
import java.util.List;

/**
 * Created by juan on 29/10/17.
 */

/**
 * Clase DirectorioAdapter
 */
public class DirectorioAdapter extends RecyclerView.Adapter<DirectorioAdapter.MyHolder> {

    //----------
    //Atributos
    //----------

    /**
     * Atributos necesarios para el funcionamientos de los procesos de la clase.
     */
    private List<Directorios> list;

    //-----------
    //Constructor
    //-----------

    /**
     * Método constructor que inicializa las variables de la clase DirectorioAdapter.
     */

    public DirectorioAdapter(List<Directorios> list) {
        this.list = list;
    }

    /**
     * Método que permite adecuar la clase con la vista adapter.
     * @param parent - Contexto que perimite interacción con la vista directorio.
     * @param viewType - Contexto que perimite interacción con la vista directorio.
     * @return myHolder - La vista que contiene el adapter.
     */
    @Override
    public DirectorioAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.directorio_adapter, parent, false);
        DirectorioAdapter.MyHolder myHolder = new DirectorioAdapter.MyHolder(view);
        return myHolder;
    }

    /**
     * Método que permite adecuar la información en el adapter.
     * @param holder - Parámetro que permite hacer la relación de la información con el adapter dada su posición.
     * @param position - Parámetro que contiene la posición de cada item.
     */
    @Override
    public void onBindViewHolder(DirectorioAdapter.MyHolder holder, int position) {
        Directorios directorio = list.get(position);
        holder.dependencia.setText(directorio.getDependencia());
        holder.extension.setText(directorio.getExtension());
        holder.linea.setText(directorio.getLineaDirecta());
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
        TextView dependencia, extension, linea;

        public MyHolder(View itemView) {
            super(itemView);
            dependencia = (TextView) itemView.findViewById(R.id.txtDependencia);
            extension = (TextView) itemView.findViewById(R.id.txtExtension);
            linea = (TextView) itemView.findViewById(R.id.txtLinea);
        }
    }
}