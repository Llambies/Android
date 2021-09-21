package com.example.admallla.shumo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private String ETIQUETA_LOG = "<<<<" ;

    private Intent elIntentDelServicio = null;

    Button b ;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b =findViewById(R.id.button);
        t =findViewById(R.id.textView);
    }


    public void recibir(View view){
        b.setBackgroundColor(0xFF00FF00);
        Context c = this;
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    CheckUrl check = new CheckUrl(c);
                    try {
                        String s = check.doInBackground("http://192.168.1.134:8080/medida","GET");
                        Log.d("",s);
                        t.setText(s);

                    }catch (Exception e){
                        Log.d("",e.toString());
                    }
                    //Your code goes here
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();


    }

    public void postear(View view){
        b.setBackgroundColor(0xFF00FF00);
        Context c = this;
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    CheckUrl check = new CheckUrl(c);
                    try {
                        String s = check.doInBackground("http://192.168.1.134:8080/medida/","POST");
                        Log.d("",s);
                        t.setText("Posteado " +s);

                    }catch (Exception e){
                        Log.d("",e.toString());
                    }
                    //Your code goes here
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }


    public void encender(View v){
        Log.d(ETIQUETA_LOG, " boton arrancar servicio Pulsado" );

        if ( this.elIntentDelServicio != null ) {
            // ya estaba arrancado
            Log.d(ETIQUETA_LOG,"ya estaba arrancado");

            return;
        }

        Log.d(ETIQUETA_LOG, " MainActivity.constructor : voy a arrancar el servicio");

        this.elIntentDelServicio = new Intent(this, ServicioEscuharBeacons.class);

        this.elIntentDelServicio.putExtra("tiempoDeEspera", (long) 5000);
        startService( this.elIntentDelServicio );
    }

    public void apagar(View v){
        if ( this.elIntentDelServicio == null ) {
            // no estaba arrancado
            Log.d(ETIQUETA_LOG,"no estaba arrancado");
            return;
        }

        stopService( this.elIntentDelServicio );

        this.elIntentDelServicio = null;

        Log.d(ETIQUETA_LOG, " boton detener servicio Pulsado" );

    }


}