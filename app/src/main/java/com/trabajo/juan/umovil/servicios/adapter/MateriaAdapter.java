package com.trabajo.juan.umovil.servicios.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.trabajo.juan.umovil.R;
import com.trabajo.juan.umovil.servicios.Materia;
import com.trabajo.juan.umovil.servicios.models.Materias;
import java.util.List;

/**
 * Created by juan on 13/11/17.
 */

/**
 * Clase MateriaAdapter
 */
public class MateriaAdapter extends RecyclerView.Adapter<MateriaAdapter.MyHolder>{
    //----------
    //Atributos
    //----------

    /**
     * Atributos necesarios para el funcionamientos de los procesos de la clase.
     */
    private Materia principal;
    private List<Materias> list;

    //-----------
    //Constructor
    //-----------

    /**
     * Método constructor que inicializa las variables de la clase MateriaAdapter.
     */
    public MateriaAdapter(List<Materias> list, Materia pPrincipal) {
        principal = new Materia();
        principal = pPrincipal;
        this.list = list;
    }

    /**
     * Método que permite adecuar la clase con la vista adapter.
     * @param parent - Contexto que perimite interacción con la vista materia.
     * @param viewType - Contexto que perimite interacción con la vista materia.
     * @return myHolder - La vista que contiene el adapter.
     */
    @Override
    public MateriaAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.materia_adapter,parent,false);
        MateriaAdapter.MyHolder myHolder = new MateriaAdapter.MyHolder(view);
        return myHolder;
    }

    /**
     * Método que permite adecuar la información en el adapter.
     * @param holder - Parámetro que permite hacer la relación de la información con el adapter dada su posición.
     * @param position - Parámetro que contiene la posición de cada item.
     */
    @Override
    public void onBindViewHolder(MateriaAdapter.MyHolder holder, int position) {
        Materias materia = list.get(position);
        holder.codigo.setText(String.valueOf(materia.getCodigo()));
        holder.nombre.setText(materia.getNombres());
        holder.tipo.setText(materia.getTipo());
        holder.categoria.setText(materia.getCategoria());
        holder.creditos.setText(String.valueOf(materia.getCreditos()));
        holder.programa.setText(materia.getPrograma());
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
        TextView codigo, nombre, tipo, categoria, creditos, programa;

        public MyHolder(View itemView) {
            super(itemView);
            codigo = (TextView) itemView.findViewById(R.id.lblCodigoM);
            nombre = (TextView) itemView.findViewById(R.id.lblNombreM);
            tipo = (TextView) itemView.findViewById(R.id.lblTipoM);
            categoria = (TextView) itemView.findViewById(R.id.lblCategoriaM);
            creditos = (TextView) itemView.findViewById(R.id.lblCreditosM);
            programa = (TextView) itemView.findViewById(R.id.lblProgramaM);
        }
    }
}