package com.example.admallla.shumo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class BeaconsActivity extends AppCompatActivity {

    private final String ETIQUETA_LOG = "BeaconsActivity";
    private Intent elIntentDelServicio = null;

    Context mContext;
    Activity mActivity;

    EditText e;
    TextView t;

    ServicioEscuharBeacons servicio;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_beacons);
        mContext = this.getBaseContext();
        mActivity = this;


        e = findViewById(R.id.dispositivoBuscado);
        t = findViewById(R.id.textView2);


        servicio = new ServicioEscuharBeacons();


    }


    public void encender(View v) {
        Log.d(ETIQUETA_LOG, " boton arrancar servicio Pulsado");

        if (this.elIntentDelServicio != null) {
            // ya estaba arrancado
            Log.d(ETIQUETA_LOG, "ya estaba arrancado");

            return;
        }

        Log.d(ETIQUETA_LOG, " MainActivity.constructor : voy a arrancar el servicio");

        this.elIntentDelServicio = new Intent(this, ServicioEscuharBeacons.class);
        this.elIntentDelServicio.putExtra("tiempoDeEspera", (long) 5000);
        this.elIntentDelServicio.putExtra("dispositivoBuscado", e.getText().toString());

        startService(this.elIntentDelServicio);


    }

    public void apagar(View v) {
        if (this.elIntentDelServicio == null) {
            // no estaba arrancado
            Log.d(ETIQUETA_LOG, "no estaba arrancado");
            return;
        }

        stopService(this.elIntentDelServicio);

        this.elIntentDelServicio = null;

        Log.d(ETIQUETA_LOG, " boton detener servicio Pulsado");

    }


    // --------------------------------------------------------------
    // --------------------------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void botonBuscarDispositivosBTLEPulsado(View v) {
        Log.d(ETIQUETA_LOG, " boton buscar dispositivos BTLE Pulsado");
        servicio.buscarTodosLosDispositivosBTLE();
    } // ()


    // --------------------------------------------------------------
    // --------------------------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void botonBuscarNuestroDispositivoBTLEPulsado(View v) {
        Log.d(ETIQUETA_LOG, " boton nuestro dispositivo BTLE Pulsado");
        //this.buscarEsteDispositivoBTLE( Utilidades.stringToUUID( "EPSG-GTI-PROY-3A" ) );
        //this.buscarEsteDispositivoBTLE( "EPSG-GTI-PROY-3A" );
        servicio.buscarEsteDispositivoBTLE(e.getText().toString());

    } // ()


    // --------------------------------------------------------------
    // --------------------------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void botonDetenerBusquedaDispositivosBTLEPulsado(View v) {
        Log.d(ETIQUETA_LOG, " boton detener busqueda dispositivos BTLE Pulsado");
        servicio.detenerBusquedaDispositivosBTLE();
    } // ()


}
