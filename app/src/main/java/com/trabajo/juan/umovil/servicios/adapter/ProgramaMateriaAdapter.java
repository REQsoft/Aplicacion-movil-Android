package com.trabajo.juan.umovil.servicios.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.trabajo.juan.umovil.R;
import com.trabajo.juan.umovil.servicios.ProgramaMateria;
import com.trabajo.juan.umovil.servicios.models.Materias;
import java.util.List;

/**
 * Created by juan on 13/11/17.
 */

/**
 * Clase ProgramaMateriaAdapter
 */
public class ProgramaMateriaAdapter extends RecyclerView.Adapter<ProgramaMateriaAdapter.MyHolder> {

    //----------
    //Atributos
    //----------

    /**
     * Atributos necesarios para el funcionamientos de los procesos de la clase.
     */
    private List<Materias> list;
    private ProgramaMateria materia;

    //-----------
    //Constructor
    //-----------

    /**
     * Método constructor que inicializa las variables de la clase ProgramaMateriaAdapter.
     */
    public ProgramaMateriaAdapter(List<Materias> list, ProgramaMateria materia1) {
        materia = new ProgramaMateria();
        materia = materia1;
        this.list = list;
    }

    /**
     * Método que permite adecuar la clase con la vista adapter.
     * @param parent - Contexto que perimite interacción con la vista programaMateria.
     * @param viewType - Contexto que perimite interacción con la vista programaMateria.
     * @return myHolder - La vista que contiene el adapter.
     */
    @Override
    public ProgramaMateriaAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.programa_materia_adapter, parent, false);
        ProgramaMateriaAdapter.MyHolder myHolder = new ProgramaMateriaAdapter.MyHolder(view);
        return myHolder;
    }

    /**
     * Método que permite adecuar la información en el adapter.
     * @param holder - Parámetro que permite hacer la relación de la información con el adapter dada su posición.
     * @param position - Parámetro que contiene la posición de cada item.
     */
    @Override
    public void onBindViewHolder(ProgramaMateriaAdapter.MyHolder holder, final int position) {
        Materias materias = list.get(position);
        holder.programaMateria.setText(materias.getPrograma());
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
        TextView programaMateria;

        public MyHolder(final View itemView) {
            super(itemView);
            programaMateria = (TextView) itemView.findViewById(R.id.lblProgramaMateria);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    materia.darProgramas(programaMateria.getText().toString());
                }
            });
        }
    }
}