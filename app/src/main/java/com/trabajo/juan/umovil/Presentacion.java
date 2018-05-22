package com.trabajo.juan.umovil;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Presentacion extends AppCompatActivity{

    private ViewPager viewPager;
    private PresentacionManager presentacionManager;
    private ViewPagerAdapter viewPagerAdapter;
    private TextView[] dots;
    private Button next, skip;
    private LinearLayout dotsLayout;
    private int[] layouts;

    //-----------
    //Constructor
    //-----------

    /**
     * Método constructor que inicializa las variables de la clase Presentacion.
     * @param savedInstanceState - Permite la visualización de la vista Presentacion.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presentacionManager = new PresentacionManager(this);
        if (!presentacionManager.Check()) {
            presentacionManager.setFirst(false);
            Intent i = new Intent(Presentacion.this, Principal.class);
            startActivity(i);
            finish();
        }

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_presentacion);

        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        skip = (Button) findViewById(R.id.btn_skip);
        next = (Button) findViewById(R.id.btn_next);
        viewPagerAdapter = new ViewPagerAdapter();

        layouts = new int[]{R.layout.activity_screen_1, R.layout.activity_screen_2,
                R.layout.activity_screen_3, R.layout.activity_screen_4};

        addBottomDots(0);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(viewListener);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presentacionManager.setFirst(false);
                Intent i = new Intent(Presentacion.this, Principal.class);
                startActivity(i);
                finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current = getItem(1);
                if(current<layouts.length)
                {
                    viewPager.setCurrentItem(current);
                }
                else
                {
                    presentacionManager.setFirst(false);
                    Intent i = new Intent(Presentacion.this, Principal.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }

    /**
     * Método que permite la los botones de la clase presentación y sus vistas.
     * @param position - Parámetro que llega con la posición que debe tener cada punto de las vistas.
     */
    private void addBottomDots(int position) {
        dots = new TextView[layouts.length];
        int[] colorActive = getResources().getIntArray(R.array.dot_activate);
        int[] colorInactive = getResources().getIntArray(R.array.dot_inactivate);
        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorInactive[position]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[position].setTextColor(colorActive[position]);
        }
    }

    /**
     * Método que permite obtener el item de la clase presentacion.
     * @param i - Parámetro que permite evaluar el tope de las vistas.
     * @return item dependiendo la posición.
     */
    private int getItem(int i)
    {
        return viewPager.getCurrentItem() + i;
    }

    /**
     * Método que permite ejecutar los eventos de visualización de cada vista.
     */
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            if(position==layouts.length-1)
            {
                next.setText("Continuar");
                skip.setVisibility(View.GONE);
            }
            else
            {
                next.setText("Siguiente");
                skip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    /**
     * Método que permite visualizar cada vista en la clase presentación.
     */
    public class ViewPagerAdapter extends PagerAdapter
    {
        private LayoutInflater layoutInflater;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = layoutInflater.inflate(layouts[position], container,false);
            container.addView(v);
            return v;
        }

        @Override
        public int getCount() {
             return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View v = (View)object;
            container.removeView(v);
        }
    }

    /**
     * Método que permite capturar el evento de la flecha hacia atrás.
     * @param keyCode - Parámetro que llega como entero para comparar con el botón de flecha atrás.
     * @param event - Contexto de la clase KeyEvent para ser utilizado.
     * @return true - Si el botón fue presionado.
     *         false - Si el botón no fue presionado.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            presentacionManager.setFirst(false);
        }
        return super.onKeyDown(keyCode, event);
    }
}