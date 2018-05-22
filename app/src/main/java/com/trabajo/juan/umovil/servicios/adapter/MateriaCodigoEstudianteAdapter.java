package com.trabajo.juan.umovil.servicios.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.trabajo.juan.umovil.R;
import com.trabajo.juan.umovil.servicios.MateriaCodigoEstudiante;
import com.trabajo.juan.umovil.servicios.models.Notas;

import java.util.List;

/**
 * Created by juan on 13/11/17.
 */

/**
 * Clase MateriaCodigoEstudianteAdapter
 */
public class MateriaCodigoEstudianteAdapter extends RecyclerView.Adapter<MateriaCodigoEstudianteAdapter.MyHolder> {

    //----------
    //Atributos
    //----------

    /**
     * Atributos necesarios para el funcionamientos de los procesos de la clase.
     */
    private List<Notas> list;
    private MateriaCodigoEstudiante materia;

    //-----------
    //Constructor
    //-----------

    /**
     * Método constructor que inicializa las variables de la clase MateriaCodigoEstudianteAdapter.
     */
    public MateriaCodigoEstudianteAdapter(List<Notas> list, MateriaCodigoEstudiante materia1) {
        materia = new MateriaCodigoEstudiante();
        materia = materia1;
        this.list = list;
    }

    /**
     * Método que permite adecuar la clase con la vista adapter.
     * @param parent - Contexto que perimite interacción con la vista materiaCodigoEstudiante.
     * @param viewType - Contexto que perimite interacción con la vista materiaCodigoEstudiante.
     * @return myHolder - La vista que contiene el adapter.
     */
    @Override
    public MateriaCodigoEstudianteAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.materia_codigo_estudiante_adapter, parent, false);
        MateriaCodigoEstudianteAdapter.MyHolder myHolder = new MateriaCodigoEstudianteAdapter.MyHolder(view);
        return myHolder;
    }

    /**
     * Método que permite adecuar la información en el adapter.
     * @param holder - Parámetro que permite hacer la relación de la información con el adapter dada su posición.
     * @param position - Parámetro que contiene la posición de cada item.
     */
    @Override
    public void onBindViewHolder(MateriaCodigoEstudianteAdapter.MyHolder holder, final int position) {
        Notas notas = list.get(position);
        holder.codigo_materia.setText(String.valueOf(notas.getCodigoMateria()));
        holder.materia.setText(notas.getMateria());
        holder.nota.setText(notas.getNota());
        holder.faltas.setText(String.valueOf(notas.getFaltas()));
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
        TextView codigo_materia, materia, nota, faltas;

        public MyHolder(final View itemView) {
            super(itemView);
            codigo_materia = (TextView) itemView.findViewById(R.id.txtCodigoMateria);
            materia = (TextView)itemView.findViewById(R.id.txtMateriaN);
            nota = (TextView) itemView.findViewById(R.id.txtNotaN);
            faltas = (TextView) itemView.findViewById(R.id.txtFaltasN);
        }
    }
}