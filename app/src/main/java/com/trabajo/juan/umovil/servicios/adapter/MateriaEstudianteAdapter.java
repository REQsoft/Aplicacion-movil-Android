package com.trabajo.juan.umovil.servicios.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.trabajo.juan.umovil.R;
import com.trabajo.juan.umovil.servicios.MateriaEstudiante;
import com.trabajo.juan.umovil.servicios.models.Estudiantes;
import java.util.List;

/**
 * Created by juan on 11/11/17.
 */

/**
 * Clase MateriaEstudianteAdapter
 */
public class MateriaEstudianteAdapter extends RecyclerView.Adapter<MateriaEstudianteAdapter.MyHolder> {

    //----------
    //Atributos
    //----------

    /**
     * Atributos necesarios para el funcionamientos de los procesos de la clase.
     */
    private List<Estudiantes> list;
    private MateriaEstudiante materia;

    //-----------
    //Constructor
    //-----------

    /**
     * Método constructor que inicializa las variables de la clase EstudianteAdapter.
     */
    public MateriaEstudianteAdapter(List<Estudiantes> list, MateriaEstudiante materia1) {
        materia = new MateriaEstudiante();
        materia = materia1;
        this.list = list;
    }

    /**
     * Método que permite adecuar la clase con la vista adapter.
     * @param parent - Contexto que perimite interacción con la vista materiaEstudiante.
     * @param viewType - Contexto que perimite interacción con la vista materiaEstudiante.
     * @return myHolder - La vista que contiene el adapter.
     */
    @Override
    public MateriaEstudianteAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.materia_estudiante_adapter, parent, false);
        MateriaEstudianteAdapter.MyHolder myHolder = new MateriaEstudianteAdapter.MyHolder(view);
        return myHolder;
    }

    /**
     * Método que permite adecuar la información en el adapter.
     * @param holder - Parámetro que permite hacer la relación de la información con el adapter dada su posición.
     * @param position - Parámetro que contiene la posición de cada item.
     */
    @Override
    public void onBindViewHolder(MateriaEstudianteAdapter.MyHolder holder, final int position) {
        Estudiantes estudiantes = list.get(position);
        holder.materiaEstudiante.setText(estudiantes.getMateria());
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
        TextView materiaEstudiante;

        public MyHolder(final View itemView) {
            super(itemView);
            materiaEstudiante = (TextView) itemView.findViewById(R.id.lblMateriaEstudiante);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    materia.darEstudiantes(materiaEstudiante.getText().toString());
                }
            });
        }
    }
}