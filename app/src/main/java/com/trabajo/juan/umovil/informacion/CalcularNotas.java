package com.trabajo.juan.umovil.informacion;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.trabajo.juan.umovil.R;

/**
 * Clase CalcularNotas
 */
public class CalcularNotas extends AppCompatActivity {

    //----------
    //Atributos
    //----------

    /**
     * Atributos necesarios para el funcionamientos de los procesos de la clase.
     */
    private Spinner spinner;
    private RadioButton rd2, rd3, rd4, rd5;
    private LinearLayout layout_cortes, layout_notas, ln3, ln4, ln5, lp3, lp4, lp5;
    private EditText n1, n2, n3, n4, n5, p1, p2, p3, p4, p5, notaA;
    private TextView lnota;
    private int estado, cantidad;
    private Double nota;

    //-----------
    //Constructor
    //-----------

    /**
     * Método constructor que inicializa las variables de la clase calcularNotas.
     * @param savedInstanceState - Permite la visualización de la vista calcularNotas.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_notas);

        spinner = (Spinner)findViewById(R.id.spinner_tipo);
        layout_cortes = (LinearLayout)findViewById(R.id.layout_corte);
        layout_notas = (LinearLayout)findViewById(R.id.layout_notas);
        ln3 = (LinearLayout) findViewById(R.id.lnota3);
        ln4 = (LinearLayout) findViewById(R.id.lnota4);
        ln5 = (LinearLayout) findViewById(R.id.lnota5);
        lp3 = (LinearLayout) findViewById(R.id.lporcentaje3);
        lp4 = (LinearLayout) findViewById(R.id.lporcentaje4);
        lp5 = (LinearLayout) findViewById(R.id.lporcentaje5);
        rd2 = (RadioButton)findViewById(R.id.rd_c2);
        rd3 = (RadioButton)findViewById(R.id.rd_c3);
        rd4 = (RadioButton)findViewById(R.id.rd_c4);
        rd5 = (RadioButton)findViewById(R.id.rd_c5);
        n1 = (EditText)findViewById(R.id.edNota1);
        n2 = (EditText)findViewById(R.id.edNota2);
        n3 = (EditText)findViewById(R.id.edNota3);
        n4 = (EditText)findViewById(R.id.edNota4);
        n5 = (EditText)findViewById(R.id.edNota5);
        p1 = (EditText)findViewById(R.id.edPorcentaje1);
        p2 = (EditText)findViewById(R.id.edPorcentaje2);
        p3 = (EditText)findViewById(R.id.edPorcentaje3);
        p4 = (EditText)findViewById(R.id.edPorcentaje4);
        p5 = (EditText)findViewById(R.id.edPorcentaje5);
        notaA = (EditText)findViewById(R.id.edNotaP);
        lnota = (TextView)findViewById(R.id.lblAcumulado);

        estado = 2;
        cantidad = 0;
        nota = 0.0;

        cambiarTipo();
        cargarTipo();
        visualizarCortes();
        validarNotasCorte();
        validarNotasAcumuladas();
    }

    /**
     * Método que permite validar que las notas de corte ingresadas se encuentren en el rago de 0 a 5.
     */
    public void validarNotasCorte()
    {
        n1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try
                {
                    if(Double.parseDouble(n1.getText().toString())>5) {
                        n1.setText("");
                        Toast.makeText(CalcularNotas.this, "Solo números de 0 a 5", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        n2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try
                {
                    if(Double.parseDouble(n2.getText().toString())>5) {
                        n2.setText("");
                        Toast.makeText(CalcularNotas.this, "Solo números de 0 a 5", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        n3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try
                {
                    if(Double.parseDouble(n3.getText().toString())>5) {
                        n3.setText("");
                        Toast.makeText(CalcularNotas.this, "Solo números de 0 a 5", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        n4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try
                {
                    if(Double.parseDouble(n4.getText().toString())>5) {
                        n4.setText("");
                        Toast.makeText(CalcularNotas.this, "Solo números de 0 a 5", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        n5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try
                {
                    if(Double.parseDouble(n5.getText().toString())>5) {
                        n5.setText("");
                        Toast.makeText(CalcularNotas.this, "Solo números de 0 a 5", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        p1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try
                {
                    if(Double.parseDouble(p1.getText().toString())>100) {
                        p1.setText("");
                        Toast.makeText(CalcularNotas.this, "Solo números de 0 a 100", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        p2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try
                {
                    if(Double.parseDouble(p2.getText().toString())>100) {
                        p2.setText("");
                        Toast.makeText(CalcularNotas.this, "Solo números de 0 a 100", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        p3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try
                {
                    if(Double.parseDouble(p3.getText().toString())>100) {
                        p3.setText("");
                        Toast.makeText(CalcularNotas.this, "Solo números de 0 a 100", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        p4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try
                {
                    if(Double.parseDouble(p4.getText().toString())>100) {
                        p4.setText("");
                        Toast.makeText(CalcularNotas.this, "Solo números de 0 a 100", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        p5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try
                {
                    if(Double.parseDouble(p5.getText().toString())>100) {
                        p5.setText("");
                        Toast.makeText(CalcularNotas.this, "Solo números de 0 a 100", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * Método que permite validar que las notas acumuladas ingresadas se encuentren en el rago de 0 a 5.
     */
    public void validarNotasAcumuladas()
    {
        notaA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try
                {
                    if(Double.parseDouble(notaA.getText().toString())>5) {
                        notaA.setText("");
                        Toast.makeText(CalcularNotas.this, "Solo números de 0 a 5", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * Método que permite llenar la lista desplegable con información.
     */
    public void cargarTipo()
    {
        Spinner spinner_animales = (Spinner) findViewById(R.id.spinner_tipo);
        ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource( this, R.array.tipo , android.R.layout.simple_spinner_item);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_animales.setAdapter(spinner_adapter);
    }

    /**
     * Método que permite interactuar con la lista desplegable y ejecutar procesos.
     */
    public void cambiarTipo()
    {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0)
                {
                    layout_cortes.setVisibility(View.GONE);
                    layout_notas.setVisibility(View.GONE);
                }
                else if(position == 1)
                {
                    layout_cortes.setVisibility(View.VISIBLE);
                    layout_notas.setVisibility(View.GONE);
                }
                else if(position == 2)
                {
                    layout_cortes.setVisibility(View.GONE);
                    layout_notas.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * Método que permite visualizar u ocultar componentes al interactuar con los radioButton.
     */
    public void visualizarCortes()
    {
        rd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ln3.setVisibility(View.GONE);
                ln4.setVisibility(View.GONE);
                ln5.setVisibility(View.GONE);
                lp3.setVisibility(View.GONE);
                lp4.setVisibility(View.GONE);
                lp5.setVisibility(View.GONE);
                cambiarEstado(2);
            }
        });

        rd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ln3.setVisibility(View.VISIBLE);
                ln4.setVisibility(View.GONE);
                ln5.setVisibility(View.GONE);
                lp3.setVisibility(View.VISIBLE);
                lp4.setVisibility(View.GONE);
                lp5.setVisibility(View.GONE);
                cambiarEstado(3);
            }
        });

        rd4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ln3.setVisibility(View.VISIBLE);
                ln4.setVisibility(View.VISIBLE);
                ln5.setVisibility(View.GONE);
                lp3.setVisibility(View.VISIBLE);
                lp4.setVisibility(View.VISIBLE);
                lp5.setVisibility(View.GONE);
                cambiarEstado(4);
            }
        });

        rd5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ln3.setVisibility(View.VISIBLE);
                ln4.setVisibility(View.VISIBLE);
                ln5.setVisibility(View.VISIBLE);
                lp3.setVisibility(View.VISIBLE);
                lp4.setVisibility(View.VISIBLE);
                lp5.setVisibility(View.VISIBLE);
                cambiarEstado(5);
            }
        });
    }

    /**
     * Método que permite limpiar información de los componentes de las notas por corte.
     */
    public void limpiarNotasCorte()
    {
        n1.setText("");
        n2.setText("");
        n3.setText("");
        n4.setText("");
        n5.setText("");
        p1.setText("");
        p2.setText("");
        p3.setText("");
        p4.setText("");
        p5.setText("");
    }

    /**
     * Método que permite limpiar información de los componentes de las notas acumuladas.
     */
    public void limpiarNotasAcumuladas()
    {
        notaA.setText("");
        lnota.setText("0");
        cantidad = 0;
        nota = 0.0;
    }

    /**
     * Método que permite calcular notas por corte.
     */
    public void calcularNotaCorte()
    {
        if(estado == 2)
        {
            String nota1 = n1.getText().toString();
            String nota2 = n2.getText().toString();
            String porcentaje1 = p1.getText().toString();
            String porcentaje2 = p2.getText().toString();
            if(nota1.equals("")||nota2.equals("")||
                    porcentaje1.equals("")||porcentaje2.equals(""))
            {
                Toast.makeText(this, "Campos Vacíos", Toast.LENGTH_SHORT).show();
            }
            else if(!nota1.equals("")&&!nota2.equals("")&&
                    !porcentaje1.equals("")&&!porcentaje2.equals(""))
            {
                Double cn1 = Double.parseDouble(nota1);
                Double cn2 = Double.parseDouble(nota2);
                Double cp1 = Double.parseDouble(porcentaje1);
                Double cp2 = Double.parseDouble(porcentaje2);
                if((cp1+cp2)>100)
                {
                    Toast.makeText(this, "No superar porcentaje de 100%", Toast.LENGTH_SHORT).show();
                }else if((cp1+cp2)!=100)
                {
                    Toast.makeText(this, "Suma de porcentajes debe 100%", Toast.LENGTH_SHORT).show();
                }
                else {
                    Double total = ((cn1 * cp1) / 100) + ((cn2 * cp2) / 100);

                    String val = String.format("%.2f", total);
                    String cal = val.replace(",", ".");
                    calcularNota(cal);
                }
            }
        }
        else if(estado == 3)
        {
            String nota1 = n1.getText().toString();
            String nota2 = n2.getText().toString();
            String nota3 = n3.getText().toString();
            String porcentaje1 = p1.getText().toString();
            String porcentaje2 = p2.getText().toString();
            String porcentaje3 = p3.getText().toString();
            if(nota1.equals("")||nota2.equals("")||nota3.equals("")||
                    porcentaje1.equals("")||porcentaje2.equals(""))
            {
                Toast.makeText(this, "Campos Vacíos", Toast.LENGTH_SHORT).show();
            }
            else if(!nota1.equals("")&&!nota2.equals("")&&!nota3.equals("")&&
                    !porcentaje1.equals("")&&!porcentaje2.equals("")&&!porcentaje3.equals(""))
            {
                Double cn1 = Double.parseDouble(nota1);
                Double cn2 = Double.parseDouble(nota2);
                Double cn3 = Double.parseDouble(nota3);
                Double cp1 = Double.parseDouble(porcentaje1);
                Double cp2 = Double.parseDouble(porcentaje2);
                Double cp3 = Double.parseDouble(porcentaje3);

                if((cp1+cp2+cp3)>100)
                {
                    Toast.makeText(this, "No superar porcentaje de 100%", Toast.LENGTH_SHORT).show();
                }else if((cp1+cp2+cp3)!=100)
                {
                    Toast.makeText(this, "Suma de porcentajes debe 100%", Toast.LENGTH_SHORT).show();
                }else {
                    Double total = ((cn1 * cp1) / 100) + ((cn2 * cp2) / 100) +
                            ((cn3 * cp3) / 100);

                    String val = String.format("%.2f", total);
                    String cal = val.replace(",", ".");
                    calcularNota(cal);
                }
            }
        }
        else if(estado == 4)
        {
            String nota1 = n1.getText().toString();
            String nota2 = n2.getText().toString();
            String nota3 = n3.getText().toString();
            String nota4 = n4.getText().toString();
            String porcentaje1 = p1.getText().toString();
            String porcentaje2 = p2.getText().toString();
            String porcentaje3 = p3.getText().toString();
            String porcentaje4 = p4.getText().toString();
            if(nota1.equals("")||nota2.equals("")||nota3.equals("")||nota4.equals("")||
                    porcentaje1.equals("")||porcentaje2.equals("")||porcentaje4.equals(""))
            {
                Toast.makeText(this, "Campos Vacíos", Toast.LENGTH_SHORT).show();
            }
            else if(!nota1.equals("")&&!nota2.equals("")&&!nota3.equals("")&&!nota4.equals("")&&
                    !porcentaje1.equals("")&&!porcentaje2.equals("")&&!porcentaje3.equals("")&&!porcentaje4.equals(""))
            {
                Double cn1 = Double.parseDouble(nota1);
                Double cn2 = Double.parseDouble(nota2);
                Double cn3 = Double.parseDouble(nota3);
                Double cn4 = Double.parseDouble(nota4);
                Double cp1 = Double.parseDouble(porcentaje1);
                Double cp2 = Double.parseDouble(porcentaje2);
                Double cp3 = Double.parseDouble(porcentaje3);
                Double cp4 = Double.parseDouble(porcentaje4);

                if((cp1+cp2+cp3+cp4)>100)
                {
                    Toast.makeText(this, "No superar porcentaje de 100%", Toast.LENGTH_SHORT).show();
                }else if((cp1+cp2+cp3+cp4)!=100)
                {
                    Toast.makeText(this, "Suma de porcentajes debe 100%", Toast.LENGTH_SHORT).show();
                }else {
                    Double total = ((cn1 * cp1) / 100) + ((cn2 * cp2) / 100) +
                            ((cn3 * cp3) / 100) + ((cn4 * cp4) / 100);

                    String val = String.format("%.2f", total);
                    String cal = val.replace(",", ".");
                    calcularNota(cal);
                }
            }
        }

        else if(estado == 5)
        {
            String nota1 = n1.getText().toString();
            String nota2 = n2.getText().toString();
            String nota3 = n3.getText().toString();
            String nota4 = n4.getText().toString();
            String nota5 = n5.getText().toString();
            String porcentaje1 = p1.getText().toString();
            String porcentaje2 = p2.getText().toString();
            String porcentaje3 = p3.getText().toString();
            String porcentaje4 = p4.getText().toString();
            String porcentaje5 = p5.getText().toString();
            if(nota1.equals("")||nota2.equals("")||nota3.equals("")||nota4.equals("")||nota5.equals("")||
                    porcentaje1.equals("")||porcentaje2.equals("")||porcentaje4.equals("")||porcentaje5.equals(""))
            {
                Toast.makeText(this, "Campos Vacíos", Toast.LENGTH_SHORT).show();
            }
            else if(!nota1.equals("")&&!nota2.equals("")&&!nota3.equals("")&&!nota4.equals("")&&!nota5.equals("")&&
                    !porcentaje1.equals("")&&!porcentaje2.equals("")&&!porcentaje3.equals("")&&!porcentaje4.equals("")&&
                    !porcentaje5.equals(""))
            {
                Double cn1 = Double.parseDouble(nota1);
                Double cn2 = Double.parseDouble(nota2);
                Double cn3 = Double.parseDouble(nota3);
                Double cn4 = Double.parseDouble(nota4);
                Double cn5 = Double.parseDouble(nota5);
                Double cp1 = Double.parseDouble(porcentaje1);
                Double cp2 = Double.parseDouble(porcentaje2);
                Double cp3 = Double.parseDouble(porcentaje3);
                Double cp4 = Double.parseDouble(porcentaje4);
                Double cp5 = Double.parseDouble(porcentaje5);

                if((cp1+cp2+cp3+cp4+cp5)>100)
                {
                    Toast.makeText(this, "No superar porcentaje de 100%", Toast.LENGTH_SHORT).show();
                }else if((cp1+cp2+cp3+cp4+cp5)!=100)
                {
                    Toast.makeText(this, "Suma de porcentajes debe 100%", Toast.LENGTH_SHORT).show();
                }else {
                    Double total = ((cn1 * cp1) / 100) + ((cn2 * cp2) / 100) +
                            ((cn3 * cp3) / 100) + ((cn4 * cp4) / 100) + ((cn5 * cp5) / 100);

                    String val = String.format("%.2f", total);
                    String cal = val.replace(",", ".");
                    calcularNota(cal);
                }
            }
        }
    }

    /**
     * Método que permite cambiar un estado dependiendo el radioButton seleccionado.
     * @param pEstado - Parámetro que define el estado con el contexto de cada radioButton.
     */
    public void cambiarEstado(int pEstado)
    {
        estado = pEstado;
    }

    /**
     * Método que permite calcular notas acumuladas.
     */
    public void calcularNotaAcumulada() {
        cantidad += 1;
        String notaAcumulada = notaA.getText().toString();

        if (notaAcumulada.equals("")) {
            Toast.makeText(this, "Campo Vacío", Toast.LENGTH_SHORT).show();
        } else {
            nota += Double.parseDouble(notaAcumulada);

            Double total = (nota) / cantidad;

            String val = String.format("%.2f", total);
            String cal = val.replace(",", ".");
            notaA.setText("");
            lnota.setText(cal);
        }
    }

    /**
     * Método que permite mostrar un alertDialog con la información de cada nota calculada.
     * @param texto - Parámetro que contiene la descripción del alertDialog.
     * @param titulo - Parámetro que contiene el titulo del alertDialog.
     * @param icono - Parámetro que contiene el icono del alertDialog.
     * @return dialogo - Devuelve y muestra el alertDialog.
     */
    public Dialog dialogo(final String texto, String titulo, int icono)
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(CalcularNotas.this);
        dialog.setMessage(texto)
                .setIcon(icono)
                .setTitle(titulo)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return dialog.show();
    }

    /**
     * Método que permite calcular las notas por corte.
     * @param pNota - Parámetro que llega con la nota calculada.
     */
    public void calcularNota(String pNota)
    {
        if(Double.parseDouble(pNota)>=0 && Double.parseDouble(pNota)<=2.9)
        {
            dialogo("Su nota final es: "+pNota, "Promedio Notas", R.drawable.triste);
        }
        else if(Double.parseDouble(pNota)>=3.0 && Double.parseDouble(pNota)<=3.5)
        {
            dialogo("Su nota final es: "+pNota, "Promedio Notas", R.drawable.confundido);
        }
        else if(Double.parseDouble(pNota)>=3.6 && Double.parseDouble(pNota)<=4.4)
        {
            dialogo("Su nota final es: "+pNota, "Promedio Notas", R.drawable.feliz);
        }
        else if(Double.parseDouble(pNota)>=4.5 && Double.parseDouble(pNota)<=5)
        {
            dialogo("Su nota final es: "+pNota, "Promedio Notas", R.drawable.feliz1);
        }
    }

    /**
     * Método que permite ejecutar los procesos para cada botón.
     * @param v - Contexto de la clase View para ser usada.
     */
    public void calcular(View v)
    {
        if(v.getId() == R.id.btnCalcularC)
        {
            calcularNotaCorte();
        }
        else if(v.getId() == R.id.btnLimpiarC)
        {
            limpiarNotasCorte();
        }
        else if(v.getId() == R.id.imgCalcular)
        {
            calcularNotaAcumulada();
        }
        else if(v.getId() == R.id.btnLimpiarN)
        {
            limpiarNotasAcumuladas();
        }
    }
}