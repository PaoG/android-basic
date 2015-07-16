package com.example.pao.juegodememoria;

import android.app.Activity;
import android.os.*;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import java.util.Arrays;
import java.util.Collections;


public class MemoryActivity extends Activity {
    private static final int[] CARTA_RESOURCES= new int[]{
            R.drawable.brasilbandera,
            R.drawable.brasiletra,
            R.drawable.chilebandera,
            R.drawable.chileletra,
            R.drawable.perubandera,
            R.drawable.peruletra,
            R.drawable.usabandera,
            R.drawable.usaletra
    };

    private final Handler handler=new Handler();
    private Carta [] cartas;
    private boolean touchActivo=true;
    private Carta visible=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final TableLayout tabla= new TableLayout(this); //Table
        final int tam=4;
        cartas= new Carta[tam*tam];
        for(int i=0; i<cartas.length; i++)
        {
            cartas[i]= new Carta(CARTA_RESOURCES[i/2]);
        }

        Collections.shuffle(Arrays.asList(cartas));

        for (int y=0; y<tam; y++)
        {
            final TableRow fila= new TableRow(this);
            for(int x=0; x< tam; x++)
            {
                fila.addView(cartas[(y*tam)+x].boton);

            }
            tabla.addView(fila);
        }
        setContentView(tabla);
    }


    private class Carta implements View.OnClickListener
    {
        private final ImageButton boton;
        private final int imagen;
        private boolean caraVisible=false;
        Carta(final int imagen)
        {
            this.imagen=imagen;
            this.boton= new ImageButton(MemoryActivity.this);
            this.boton.setLayoutParams(new TableRow.LayoutParams(250, 350));
            this.boton.setScaleType(ImageView.ScaleType.FIT_XY);
            this.boton.setImageResource(R.drawable.linea);
            this.boton.setOnClickListener(this);

        }
        void setCaraVisible(final boolean caraVisible)
        {
            this.caraVisible=caraVisible;
            boton.setImageResource(caraVisible ? imagen : R.drawable.linea);

        }

        public void onClick(View arg0)
        {

            if(!caraVisible&&touchActivo)
            {
                onCartaDescubierta(this);
            }
        }

        public void onCartaDescubierta(final Carta celda)
        {
            if(visible==null)
            {
                visible=celda;
                visible.setCaraVisible(true);
            } else {
                if (visible.imagen == celda.imagen) {

                    celda.setCaraVisible(true);
                    celda.boton.setEnabled(false);
                    visible.boton.setEnabled(false);
                    visible = null;

                } else {

                    celda.setCaraVisible(true);
                    touchActivo = false;
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            celda.setCaraVisible(false);
                            visible.setCaraVisible(false);
                            visible = null;
                            touchActivo = true;

                        }
                    }, 1000);
                }
            }
        }

    }
}
