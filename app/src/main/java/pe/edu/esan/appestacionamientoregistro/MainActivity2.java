package pe.edu.esan.appestacionamientoregistro;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends ActionBarActivity {

    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;

    //Texto para que se vea en la consola para verificacion del codigo
    private final String TAG= "APP";
    //Cuadros de textos editables
    EditText et1,et2;
    //Cadena de texto que obtiene el lenguaje del celular

    //Boton del login para acceder al app
    Button botonacceder; //SOLO SE USA PARA CAMBIAR LA FUENTE
    //Cadena de texto cuyo valor es el url del php para el login
    // La respuesta del JSON es


    CheckBox cb1;
    //Variable Boton
    Button bstart;
    //Variable de cuadro de texto no editable





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Se guarda el estado del fragmento actual
        super.onCreate(savedInstanceState);
        //Se obtiene la pantalla del movil y se oculta el teclado de la pantalla
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //Manda el contenido al fragmento con la vista del layout correspondiente
        setContentView(R.layout.activity_main);
        //Obtiene el servicio de WiFi
        WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        //Activa el WiFi
        wifi.setWifiEnabled(true);
        //Se le asigna el id a la variable segun el layout
        bstart = (Button) findViewById(R.id.button);
        //Se le asigna el id a la variable segun el layout
        cb1 = (CheckBox) findViewById(R.id.cb1);
        //Se le da una cadena de texto al cuadro de texto

        //Se da un intento de obtencion de variables del main


        //Metodo que se activa cuando se da click al checkbox
        cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Si el checkbox es chequeado
                if (cb1.isChecked()) {
                    loginPrefsEditor.putBoolean("saveLogin", true);
                    loginPrefsEditor.putString("username", et1.getText().toString());
                    loginPrefsEditor.putString("password", et2.getText().toString());
                    loginPrefsEditor.commit();
                } else {
                    //Caso contrario
                    loginPrefsEditor.putBoolean("saveLogin", false);
                    loginPrefsEditor.commit();
                }
            }
        });


        //Declaracion de variable
        SpannableString s = new SpannableString("ESAN");
        s.setSpan(new TypefaceSpan("HelveticaNeue-Roman.ttf"), 0 , s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Update the action bar title with the TypefaceSpan instance
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(s);

        //Creacion de variable cuyo valor se le asigna como el soporte de la barra de actividades
        android.support.v7.app.ActionBar abLogin = getSupportActionBar();

        //Declaracion de variables de cuadros de texto con sus elementos determinados en el layout
        et1 = (EditText) findViewById(R.id.et1);

        //Declaracion de variables de cuadros de texto con sus elementos determinados en el layout
        et2 = (EditText) findViewById(R.id.et2);


        botonacceder = (Button) findViewById(R.id.button); //SOLO ES USADO PARA LA FUENTE

        //Asingacion de valores de texto a los cuadros de texto




        //ESTO ES PARA LA FUENTE
        String font_path = "font/HelveticaNeue-Light.ttf"; //ruta de la fuente
        Typeface TF = Typeface.createFromAsset(getAssets(),font_path);//llamanos a la CLASS TYPEFACE y la definimos con un CREATE desde ASSETS con la ruta STRING

        //Se le asigna una fuente al cuadro de texto
        et1.setTypeface(TF);

        //Se le asigna una fuente al cuadro de texto
        et2.setTypeface(TF);

        //Se le asigna una fuente al texto del boton
        botonacceder.setTypeface(TF);


        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);

        if(saveLogin==true){
            et1.setText(loginPreferences.getString("username", ""));
            et2.setText(loginPreferences.getString("password", ""));
            cb1.setChecked(true);
        }




        //Cuando el boton es presionado se ejecuta la clase
        bstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(et1.getText().toString().equals("parking")&&et2.getText().toString().equals("esanmmxv")){
                    Intent i = new Intent(getApplicationContext(), MainActivity3.class);
                    startActivity(i);
                    finish();

                }else{
                    Toast.makeText(MainActivity2.this, "Contraseña incorrecta", Toast.LENGTH_LONG).show();

                }


            }
        });


        //Cuando el texto es seleccionado se abre la actividad de Olvido

    }




    private boolean isNetworkAvailable() {
        //Verifica la conexion a internet
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }




}
