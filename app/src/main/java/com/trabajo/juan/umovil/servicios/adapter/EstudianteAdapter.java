package com.trabajo.juan.umovil.servicios.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.trabajo.juan.umovil.R;
import com.trabajo.juan.umovil.servicios.Estudiante;
import com.trabajo.juan.umovil.servicios.models.Estudiantes;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by juan on 11/11/17.
 */

/**
 * Clase EstudianteAdapter
 */
public class EstudianteAdapter extends RecyclerView.Adapter<EstudianteAdapter.MyHolder>{

    //----------
    //Atributos
    //----------

    /**
     * Atributos necesarios para el funcionamientos de los procesos de la clase.
     */
    private Estudiante principal;
    private List<Estudiantes> list;

    //-----------
    //Constructor
    //-----------

    /**
     * Método constructor que inicializa las variables de la clase EstudianteAdapter.
     */
    public EstudianteAdapter(List<Estudiantes> list, Estudiante pPrincipal) {
        principal = new Estudiante();
        principal = pPrincipal;
        this.list = list;
    }

    /**
     * Método que permite adecuar la clase con la vista adapter.
     * @param parent - Contexto que perimite interacción con la vista estudiante.
     * @param viewType - Contexto que perimite interacción con la vista estudiante.
     * @return myHolder - La vista que contiene el adapter.
     */
    @Override
    public EstudianteAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.estudiante_adapter,parent,false);
        EstudianteAdapter.MyHolder myHolder = new EstudianteAdapter.MyHolder(view);
        return myHolder;
    }

    /**
     * Método que permite adecuar la información en el adapter.
     * @param holder - Parámetro que permite hacer la relación de la información con el adapter dada su posición.
     * @param position - Parámetro que contiene la posición de cada item.
     */
    @Override
    public void onBindViewHolder(EstudianteAdapter.MyHolder holder, int position) {
        Estudiantes estudiante = list.get(position);
        holder.codigo.setText(String.valueOf(estudiante.getCodigo()));
        holder.nombre.setText(estudiante.getNombres());
        holder.apellido.setText(estudiante.getApellidos());
        holder.semestre.setText(String.valueOf(estudiante.getSemestre()));
        holder.programa.setText(estudiante.getPrograma());
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
        TextView codigo, nombre, apellido, semestre, programa;

        public MyHolder(View itemView) {
            super(itemView);
            codigo = (TextView) itemView.findViewById(R.id.lblCodigo);
            nombre = (TextView) itemView.findViewById(R.id.lblNombres);
            apellido = (TextView) itemView.findViewById(R.id.lblApellidos);
            semestre = (TextView) itemView.findViewById(R.id.lblSemestre);
            programa = (TextView) itemView.findViewById(R.id.lblPrograma);
        }
    }

    /**
     * Método que permite filtrar información en la lista de estudiantes.
     * @param listaEstudiante - Parámetro que contiene la lista filtrada con la información necesaria.
     */
    public void setFilter(List<Estudiantes> listaEstudiante)
    {
        this.list = new ArrayList<>();
        this.list.addAll(listaEstudiante);
        notifyDataSetChanged();
    }
}