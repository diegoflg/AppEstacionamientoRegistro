package pe.edu.esan.appestacionamientoregistro;
//Imports que necesita la aplicacion
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class MainActivity3 extends ActionBarActivity {

    //Declaracion de variables

    //Para el semaforo
    private ImageView sem1,sem2,sem3,sem4,sem5,sem6,sem7,sem8,sem9;

    //Dato del color activo del semaforo
    private String libres;
    private String libres2;
    private String libres3;
    String horatot="";
    String diatot="";

    //Dialogo de progreso de carga
    private ProgressDialog pDialog;
    private ProgressDialog pDialog2;
    private ProgressDialog pDialog3;
    private ProgressDialog pDialog4;
    private ProgressDialog pDialog5;

    // JSON parser class
    JSONParser jsonParser = new JSONParser();
    /*CADENAS DE TEXTO PRIVADAS ESTATICAS FINALES CUYOS VALORES SON LAS URLs DE LOS PHP PARA REGISTRAR LOS DATOS*/
    private static final String REGISTER_URL = "http://estacionamientos.esan.edu.pe/cas/register.php";
    private static final String REGISTER_URL2 = "http://estacionamientos.esan.edu.pe/cas/register2.php";
    private static final String REGISTER_URL3 = "http://estacionamientos.esan.edu.pe/cas/register3.php";
    //Cadenas de texto privadas cuyos valores son los textos obtenidos para resultados satisfactorios
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    //Cadena de texto cuyo valor es la URL del php para obtener los datos
    private static String url_all_empresas = "http://estacionamientos.esan.edu.pe/esconnect/get_all_empresas.php";
    //Cadenas de texto privadas cuyos valores son los nombres de los campos en la base de datos
    private static final String TAG_PRODUCTS = "users";
    private static final String TAG_NOMBRE = "username";
    private static final String TAG_NOMBRE2 = "username2";
    private static final String TAG_NOMBRE3 = "username3";
    //Cadenas privadas de texto cuyos valores iniciales son waa
    private String estado="waa";
    private String estado2="waa";
    private String estado3="waa";
    //Nuevo array JSON cuyo valor inicial es nulo
    JSONArray products = null;
    //Nueva variable JSONParser(clase)
    JSONParser jParser = new JSONParser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Se le asigna el layout que se visualizara con la actividad
        setContentView(R.layout.lay_registroesta);

        //Se le da valor a la vista imagen con el id del layout
        sem1=(ImageView)findViewById(R.id.sema1);
        sem2=(ImageView)findViewById(R.id.sema2);
        sem3=(ImageView)findViewById(R.id.sema3);
        sem4=(ImageView)findViewById(R.id.sema7);
        sem5=(ImageView)findViewById(R.id.sema9);
        sem6=(ImageView)findViewById(R.id.sema8);
        sem7=(ImageView)findViewById(R.id.sema4);
        sem8=(ImageView)findViewById(R.id.sema6);
        sem9=(ImageView)findViewById(R.id.sema5);

        //Se comprueba la conexion a internet

        if (isNetworkAvailable() == true){
            //Si hay conexion a internet se ejecuta el async task LoadAllProducts
            //Que obtendra el estado actual de cada estacionamiento y cambiara los colores del semaforo con respecto a estos
            try{
                new LoadAllProducts().execute();

            }catch (Exception e){

            }

            try{
                new LoadTIME1().execute();

            }catch (Exception e){

            }




        }





        sem1.setOnClickListener(new View.OnClickListener() {//se detecta si se hizo click a este boton(bombilla roja del semaforo)
            @Override
            public void onClick(View v) {
                if((diatot.contains("Monday") || diatot.contains("Tuesday") || diatot.contains("Wednesday") || diatot.contains("Thursday") || diatot.contains("Friday"))){

                    if(horatot.contains("23:33") || horatot.contains("23:34")|| horatot.contains("23:35")|| horatot.contains("23:36")|| horatot.contains("17:56")|| horatot.contains("17:57")|| horatot.contains("17:58")|| horatot.contains("17:59")){

                    }else{
                        showPopup(MainActivity3.this,1);


                    }


                }

                if(diatot.contains("Saturday")){

                    if(horatot.contains("23:33") || horatot.contains("23:34")|| horatot.contains("23:35")|| horatot.contains("23:36")|| horatot.contains("06:26") ||horatot.contains("06:27")|| horatot.contains("06:28")|| horatot.contains("06:29")){

                    }else{
                        showPopup(MainActivity3.this,1);


                    }


                }

                if(diatot.contains("Sunday")){

                    if(horatot.contains("23:33") || horatot.contains("23:34")|| horatot.contains("23:35")|| horatot.contains("23:36") ){

                    }else{
                        showPopup(MainActivity3.this,1);


                    }


                }




            }
        });

        sem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if((diatot.contains("Monday") || diatot.contains("Tuesday") || diatot.contains("Wednesday") || diatot.contains("Thursday") || diatot.contains("Friday"))){

                    if(horatot.contains("23:33") || horatot.contains("23:34")|| horatot.contains("23:35")|| horatot.contains("23:36")|| horatot.contains("17:56")|| horatot.contains("17:57")|| horatot.contains("17:58")|| horatot.contains("17:59")){

                    }else{
                        showPopup(MainActivity3.this,2);


                    }


                }

                if(diatot.contains("Saturday")){

                    if(horatot.contains("23:33") || horatot.contains("23:34")|| horatot.contains("23:35")|| horatot.contains("23:36")|| horatot.contains("06:26") ||horatot.contains("06:27")|| horatot.contains("06:28")|| horatot.contains("06:29")){

                    }else{
                        showPopup(MainActivity3.this,2);


                    }


                }

                if(diatot.contains("Sunday")){

                    if(horatot.contains("23:33") || horatot.contains("23:34")|| horatot.contains("23:35")|| horatot.contains("23:36") ){

                    }else{
                        showPopup(MainActivity3.this,2);


                    }


                }





            }
        });

        sem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((diatot.contains("Monday") || diatot.contains("Tuesday") || diatot.contains("Wednesday") || diatot.contains("Thursday") || diatot.contains("Friday"))){

                    if(horatot.contains("23:33") || horatot.contains("23:34")|| horatot.contains("23:35")|| horatot.contains("23:36")|| horatot.contains("17:56")|| horatot.contains("17:57")|| horatot.contains("17:58")|| horatot.contains("17:59")){

                    }else{
                        showPopup(MainActivity3.this,3);


                    }


                }

                if(diatot.contains("Saturday")){

                    if(horatot.contains("23:33") || horatot.contains("23:34")|| horatot.contains("23:35")|| horatot.contains("23:36")|| horatot.contains("06:26") ||horatot.contains("06:27")|| horatot.contains("06:28")|| horatot.contains("06:29")){

                    }else{
                        showPopup(MainActivity3.this,3);


                    }


                }

                if(diatot.contains("Sunday")){

                    if(horatot.contains("23:33") || horatot.contains("23:34")|| horatot.contains("23:35")|| horatot.contains("23:36") ){

                    }else{
                        showPopup(MainActivity3.this,3);


                    }


                }









            }
        });

        sem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((diatot.contains("Monday") || diatot.contains("Tuesday") || diatot.contains("Wednesday") || diatot.contains("Thursday") || diatot.contains("Friday"))){

                    if(horatot.contains("23:33") || horatot.contains("23:34")|| horatot.contains("23:35")|| horatot.contains("23:36")|| horatot.contains("17:56")|| horatot.contains("17:57")|| horatot.contains("17:58")|| horatot.contains("17:59")){

                    }else{

                        if((diatot.contains("Monday") || diatot.contains("Tuesday") || diatot.contains("Wednesday") || diatot.contains("Thursday") || diatot.contains("Friday"))){
                            //Si la hora contiene 18, 19, 20, 21, 22 o 23 (se le pone dos puntos para que coja la hora y no lo confunda con los minutos)
                            if(horatot.contains("18:") || horatot.contains("19:") || horatot.contains("20:")||
                                    horatot.contains("21:") || horatot.contains("22:") || horatot.contains("23:0") || horatot.contains("23:1") || horatot.contains("23:2")){



                                showPopup(MainActivity3.this,4);




                            }else{

                                if(!estado2.equals("rojo")){
                                    showPopup(MainActivity3.this,4);
                                }


                            }



                        }


                    }


                }

                if(diatot.contains("Saturday")){

                    if(horatot.contains("23:33") || horatot.contains("23:34")|| horatot.contains("23:35")|| horatot.contains("23:36")|| horatot.contains("06:26") ||horatot.contains("06:27")|| horatot.contains("06:28")|| horatot.contains("06:29")){

                    }else{
                        if( diatot.contains("Saturday")){



                            if( horatot.contains("06:3")|| horatot.contains("06:4")|| horatot.contains("06:5") || horatot.contains("07:") ||
                                    horatot.contains("08:") || horatot.contains("09:") || horatot.contains("10:") ||
                                    horatot.contains("11:") || horatot.contains("12:") || horatot.contains("13:") ||
                                    horatot.contains("14:") || horatot.contains("15:") || horatot.contains("16:") ||
                                    horatot.contains("17:") || horatot.contains("18:") ||
                                    horatot.contains("19:") || horatot.contains("20:")
                                    )
                            {


                                showPopup(MainActivity3.this,4);




                            }
                            else{

                                if(!estado2.equals("rojo")){
                                    showPopup(MainActivity3.this,4);
                                }


                            }



                        }


                    }


                }

                if(diatot.contains("Sunday")){

                    if(horatot.contains("23:33") || horatot.contains("23:34")|| horatot.contains("23:35")|| horatot.contains("23:36") ){

                    }else{
                        if(diatot.contains("Sunday")){



                            if(!estado2.equals("rojo")){
                                showPopup(MainActivity3.this,4);
                            }



                        }


                    }


                }







            }
        });

        sem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((diatot.contains("Monday") || diatot.contains("Tuesday") || diatot.contains("Wednesday") || diatot.contains("Thursday") || diatot.contains("Friday"))){

                    if(horatot.contains("23:33") || horatot.contains("23:34")|| horatot.contains("23:35")|| horatot.contains("23:36")|| horatot.contains("17:56")|| horatot.contains("17:57")|| horatot.contains("17:58")|| horatot.contains("17:59")){

                    }else{

                        if((diatot.contains("Monday") || diatot.contains("Tuesday") || diatot.contains("Wednesday") || diatot.contains("Thursday") || diatot.contains("Friday"))){
                            //Si la hora contiene 18, 19, 20, 21, 22 o 23 (se le pone dos puntos para que coja la hora y no lo confunda con los minutos)
                            if(horatot.contains("18:") || horatot.contains("19:") || horatot.contains("20:")||
                                    horatot.contains("21:") || horatot.contains("22:") || horatot.contains("23:0") || horatot.contains("23:1") || horatot.contains("23:2")){



                                showPopup(MainActivity3.this,5);




                            }else{

                                if(!estado2.equals("rojo")){
                                    showPopup(MainActivity3.this,5);
                                }


                            }



                        }


                    }


                }

                if(diatot.contains("Saturday")){

                    if(horatot.contains("23:33") || horatot.contains("23:34")|| horatot.contains("23:35")|| horatot.contains("23:36")|| horatot.contains("06:26") ||horatot.contains("06:27")|| horatot.contains("06:28")|| horatot.contains("06:29")){

                    }else{
                        if( diatot.contains("Saturday")){



                            if( horatot.contains("06:3")|| horatot.contains("06:4")|| horatot.contains("06:5") || horatot.contains("07:") ||
                                    horatot.contains("08:") || horatot.contains("09:") || horatot.contains("10:") ||
                                    horatot.contains("11:") || horatot.contains("12:") || horatot.contains("13:") ||
                                    horatot.contains("14:") || horatot.contains("15:") || horatot.contains("16:") ||
                                    horatot.contains("17:") || horatot.contains("18:") ||
                                    horatot.contains("19:") || horatot.contains("20:")
                                    )
                            {


                                showPopup(MainActivity3.this,5);




                            }
                            else{

                                if(!estado2.equals("rojo")){
                                    showPopup(MainActivity3.this,5);
                                }


                            }



                        }


                    }


                }

                if(diatot.contains("Sunday")){

                    if(horatot.contains("23:33") || horatot.contains("23:34")|| horatot.contains("23:35")|| horatot.contains("23:36") ){

                    }else{
                        if(diatot.contains("Sunday")){



                            if(!estado2.equals("rojo")){
                                showPopup(MainActivity3.this,5);
                            }



                        }


                    }


                }










            }
        });

        sem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((diatot.contains("Monday") || diatot.contains("Tuesday") || diatot.contains("Wednesday") || diatot.contains("Thursday") || diatot.contains("Friday"))){

                    if(horatot.contains("23:33") || horatot.contains("23:34")|| horatot.contains("23:35")|| horatot.contains("23:36")|| horatot.contains("17:56")|| horatot.contains("17:57")|| horatot.contains("17:58")|| horatot.contains("17:59")){

                    }else{

                        if((diatot.contains("Monday") || diatot.contains("Tuesday") || diatot.contains("Wednesday") || diatot.contains("Thursday") || diatot.contains("Friday"))){
                            //Si la hora contiene 18, 19, 20, 21, 22 o 23 (se le pone dos puntos para que coja la hora y no lo confunda con los minutos)
                            if(horatot.contains("18:") || horatot.contains("19:") || horatot.contains("20:")||
                                    horatot.contains("21:") || horatot.contains("22:") || horatot.contains("23:0") || horatot.contains("23:1") || horatot.contains("23:2")){



                                showPopup(MainActivity3.this,6);




                            }else{

                                if(!estado2.equals("rojo")){
                                    showPopup(MainActivity3.this,6);
                                }


                            }



                        }


                    }


                }

                if(diatot.contains("Saturday")){

                    if(horatot.contains("23:33") || horatot.contains("23:34")|| horatot.contains("23:35")|| horatot.contains("23:36")|| horatot.contains("06:26") ||horatot.contains("06:27")|| horatot.contains("06:28")|| horatot.contains("06:29")){

                    }else{
                        if( diatot.contains("Saturday")){



                            if( horatot.contains("06:3")|| horatot.contains("06:4")|| horatot.contains("06:5") || horatot.contains("07:") ||
                                    horatot.contains("08:") || horatot.contains("09:") || horatot.contains("10:") ||
                                    horatot.contains("11:") || horatot.contains("12:") || horatot.contains("13:") ||
                                    horatot.contains("14:") || horatot.contains("15:") || horatot.contains("16:") ||
                                    horatot.contains("17:") || horatot.contains("18:") ||
                                    horatot.contains("19:") || horatot.contains("20:")
                                    )
                            {


                                showPopup(MainActivity3.this,6);




                            }
                            else{

                                if(!estado2.equals("rojo")){
                                    showPopup(MainActivity3.this,6);
                                }


                            }



                        }


                    }


                }

                if(diatot.contains("Sunday")){

                    if(horatot.contains("23:33") || horatot.contains("23:34")|| horatot.contains("23:35")|| horatot.contains("23:36") ){

                    }else{
                        if(diatot.contains("Sunday")){



                            if(!estado2.equals("rojo")){
                                showPopup(MainActivity3.this,6);
                            }



                        }


                    }


                }











            }
        });


        sem7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((diatot.contains("Monday") || diatot.contains("Tuesday") || diatot.contains("Wednesday") || diatot.contains("Thursday") || diatot.contains("Friday"))){

                    if(horatot.contains("23:33") || horatot.contains("23:34")|| horatot.contains("23:35")|| horatot.contains("23:36")|| horatot.contains("17:56")|| horatot.contains("17:57")|| horatot.contains("17:58")|| horatot.contains("17:59")){

                    }else{
                        if((diatot.contains("Monday") || diatot.contains("Tuesday") || diatot.contains("Wednesday") || diatot.contains("Thursday") || diatot.contains("Friday"))){
                            //Si la hora contiene 18, 19, 20, 21, 22 o 23 (se le pone dos puntos para que coja la hora y no lo confunda con los minutos)
                            if(horatot.contains("18:") || horatot.contains("19:") || horatot.contains("20:")||
                                    horatot.contains("21:") || horatot.contains("22:") || horatot.contains("23:0") || horatot.contains("23:1") || horatot.contains("23:2")){


                                showPopup(MainActivity3.this,7);


                            }else{

                                if(!estado3.equals("rojo")){
                                    showPopup(MainActivity3.this,7);
                                }


                            }



                        }


                    }


                }

                if(diatot.contains("Saturday")){

                    if(horatot.contains("23:33") || horatot.contains("23:34")|| horatot.contains("23:35")|| horatot.contains("23:36")|| horatot.contains("06:26") ||horatot.contains("06:27")|| horatot.contains("06:28")|| horatot.contains("06:29")){

                    }else{
                        if(!estado3.equals("rojo")){
                            showPopup(MainActivity3.this,7);
                        }


                    }


                }

                if(diatot.contains("Sunday")){

                    if(horatot.contains("23:33") || horatot.contains("23:34")|| horatot.contains("23:35")|| horatot.contains("23:36") ){

                    }else{
                        if(!estado3.equals("rojo")){
                            showPopup(MainActivity3.this,7);
                        }


                    }


                }











            }
        });

        sem8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((diatot.contains("Monday") || diatot.contains("Tuesday") || diatot.contains("Wednesday") || diatot.contains("Thursday") || diatot.contains("Friday"))){

                    if(horatot.contains("23:33") || horatot.contains("23:34")|| horatot.contains("23:35")|| horatot.contains("23:36")|| horatot.contains("17:56")|| horatot.contains("17:57")|| horatot.contains("17:58")|| horatot.contains("17:59")){

                    }else{
                        if((diatot.contains("Monday") || diatot.contains("Tuesday") || diatot.contains("Wednesday") || diatot.contains("Thursday") || diatot.contains("Friday"))){
                            //Si la hora contiene 18, 19, 20, 21, 22 o 23 (se le pone dos puntos para que coja la hora y no lo confunda con los minutos)
                            if(horatot.contains("18:") || horatot.contains("19:") || horatot.contains("20:")||
                                    horatot.contains("21:") || horatot.contains("22:") || horatot.contains("23:0") || horatot.contains("23:1") || horatot.contains("23:2")){


                                showPopup(MainActivity3.this,8);


                            }else{

                                if(!estado3.equals("rojo")){
                                    showPopup(MainActivity3.this,8);
                                }


                            }



                        }


                    }


                }

                if(diatot.contains("Saturday")){

                    if(horatot.contains("23:33") || horatot.contains("23:34")|| horatot.contains("23:35")|| horatot.contains("23:36")|| horatot.contains("06:26") ||horatot.contains("06:27")|| horatot.contains("06:28")|| horatot.contains("06:29")){

                    }else{
                        if(!estado3.equals("rojo")){
                            showPopup(MainActivity3.this,8);
                        }


                    }


                }

                if(diatot.contains("Sunday")){

                    if(horatot.contains("23:33") || horatot.contains("23:34")|| horatot.contains("23:35")|| horatot.contains("23:36") ){

                    }else{
                        if(!estado3.equals("rojo")){
                            showPopup(MainActivity3.this,8);
                        }


                    }


                }







            }
        });

        sem9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((diatot.contains("Monday") || diatot.contains("Tuesday") || diatot.contains("Wednesday") || diatot.contains("Thursday") || diatot.contains("Friday"))){

                    if(horatot.contains("23:33") || horatot.contains("23:34")|| horatot.contains("23:35")|| horatot.contains("23:36")|| horatot.contains("17:56")|| horatot.contains("17:57")|| horatot.contains("17:58")|| horatot.contains("17:59")){

                    }else{
                        if((diatot.contains("Monday") || diatot.contains("Tuesday") || diatot.contains("Wednesday") || diatot.contains("Thursday") || diatot.contains("Friday"))){
                            //Si la hora contiene 18, 19, 20, 21, 22 o 23 (se le pone dos puntos para que coja la hora y no lo confunda con los minutos)
                            if(horatot.contains("18:") || horatot.contains("19:") || horatot.contains("20:")||
                                    horatot.contains("21:") || horatot.contains("22:") || horatot.contains("23:0") || horatot.contains("23:1") || horatot.contains("23:2")){


                                showPopup(MainActivity3.this,9);


                            }else{

                                if(!estado3.equals("rojo")){
                                    showPopup(MainActivity3.this,9);
                                }


                            }



                        }


                    }


                }

                if(diatot.contains("Saturday")){

                    if(horatot.contains("23:33") || horatot.contains("23:34")|| horatot.contains("23:35")|| horatot.contains("23:36")|| horatot.contains("06:26") ||horatot.contains("06:27")|| horatot.contains("06:28")|| horatot.contains("06:29")){

                    }else{
                        if(!estado3.equals("rojo")){
                            showPopup(MainActivity3.this,9);
                        }


                    }


                }

                if(diatot.contains("Sunday")){

                    if(horatot.contains("23:33") || horatot.contains("23:34")|| horatot.contains("23:35")|| horatot.contains("23:36") ){

                    }else{
                        if(!estado3.equals("rojo")){
                            showPopup(MainActivity3.this,9);
                        }


                    }


                }







            }
        });
        //Se crea y da valor a un numero para que sea el tiempo del handle en milisegundos
        final int delay = 10000; //milliseconds
        //Se crea una variable handle nueva
        final Handler hT = new Handler();


        hT.postDelayed(new Runnable() {
            @Override
            public void run() {

                try{
                    new LoadTIME2().execute();
                    //Se ejecuta el async task LoafTIME2 que obtiene la hora actual de un servidor en internet


                    Log.v("asd", "apagon");
                }catch (Exception e){

                }



                hT.postDelayed(this, delay);


            }
        }, delay);


    }

    private class LoadTIME2 extends AsyncTask<Void, Void, Void> {
        //Sacado de: http://www.survivingwithandroid.com/2014/04/parsing-html-in-android-with-jsoup.html
        //Pagina web real: http://www.timeanddate.com/worldclock/peru/lima
        //HTML DE WEB: view-source:http://www.timeanddate.com/worldclock/peru/lima



        //Se crea una cadena de texto cuyo valor es la URL de la pagina web
        String url = "http://www.timeanddate.com/worldclock/peru/lima";
        //Se crea una variable de tipo Documento y se le da como valor inicial nulo
        Document doc = null;
        //Se crean dos cadenas de texto para la hora y el dia
        String horac;
        String diac;

        @Override
        protected void onPreExecute() {
            //Metodo antes de ejecutar la accion
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {
            //Metodo que ocurre en segundo plano
            // TODO Auto-generated method stub

            //Intenta
            try {
                // obtener el documento de la pagina conectandose con el Jsoup
                //TIEMPO HH:MM
                doc = Jsoup.connect(url).get();
                //Se crea un elemento llamado hora cuyo valor sera el dato(hora y minutos) dentro del parametro dado
                Elements hora = doc.select("span[id=fshrmin]");
                //Se le da valor a la cadena de texto convirtiendo el dato encontrado a texto
                horac = hora.text();


                //Se crea un elemento cuyo valor sera el dato (fecha en ingles) dentro del parametro dado
                Elements dia = doc.select("span[id=ctdat]");
                //Se le da valor a la cadena de texto convirtiendo el dato encontrado a texto
                diac = dia.text();
                horatot=horac;
                diatot=diac;
                Log.v("asd",horac);

                /*
                Elements topicList = doc.select("h2.topic");
                Log.i("TIEMPO", "META: " + metaElem);
                Log.i("TIEMPO", "TOPICLIST : " + topicList);
                Elements links = doc.select("a[href]"); // a with href
                Element masthead = doc.select("div.masthead").first();
                // div with class=masthead
                Elements resultLinks = doc.select("h3.r > a"); // direct a after h3
                Log.i("TIEMPO", "AHREF: " + links);
                Log.i("TIEMPO", "MASTHEAD: " + masthead);
                Log.i("TIEMPO", "ResultLinks : " + resultLinks);
                */


            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.i("TIEMPO", "ERROR");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //Metodo que ocurre despues de terminada la accion
            // TODO Auto-generated method stub
            super.onPostExecute(result);


            //Se verifica que el texto contenga los valores dado en parametros:
            //(Si contiene Lunes, Martes, Miercoles, Jueves o Viernes)

            try{

                if((diac.contains("Monday") || diac.contains("Tuesday") || diac.contains("Wednesday") || diac.contains("Thursday") || diac.contains("Friday")|| diac.contains("Sunday")|| diac.contains("Saturday"))){
                    //Si la hora contiene 18, 19, 20, 21, 22 o 23 (se le pone dos puntos para que coja la hora y no lo confunda con los minutos)
                    if(horac.contains("23:34")||horac.contains("23:35")||horac.contains("23:36")){

                        Log.v("estados", estado+estado2+estado3);


                        if(estado.equals("verde") && estado2.equals("rojo") && estado3.equals("rojo")){


                        }else{

                            Log.v("asd","entrogogo");
                            libres = "verde";
                            libres2 = "rojo";
                            libres3 = "rojo";
                            new CreateUserv2().execute();


                        }


                    }


                }

                if((diac.contains("Monday") || diac.contains("Tuesday") || diac.contains("Wednesday") || diac.contains("Thursday") || diac.contains("Friday"))){
                    //Si la hora contiene 18, 19, 20, 21, 22 o 23 (se le pone dos puntos para que coja la hora y no lo confunda con los minutos)
                    if(horac.contains("17:57") ||horac.contains("17:58")||horac.contains("17:59")){

                        Log.v("estados", estado+estado2+estado3);


                        if(estado2.equals("verde") && estado3.equals("verde")){


                        }else{

                            Log.v("asd","entrogogo");
                            libres = estado;
                            libres2 = "verde";
                            libres3 = "verde";
                            new CreateUserv2().execute();


                        }


                    }


                }

                if(diac.contains("Saturday")){
                    //Si la hora contiene 18, 19, 20, 21, 22 o 23 (se le pone dos puntos para que coja la hora y no lo confunda con los minutos)
                    if(horac.contains("06:27") ||horac.contains("06:28")||horac.contains("06:29")){

                        Log.v("estados", estado+estado2+estado3);


                        if(estado2.equals("verde")){


                        }else{

                            Log.v("asd","entrogogo");
                            libres = estado;
                            libres2 = "verde";
                            libres3 = "rojo";
                            new CreateUserv2().execute();


                        }


                    }

                    if(horac.contains("20:57") ||horac.contains("20:58")||horac.contains("20:59")){



                        if(estado2.equals("rojo")){


                        }else{

                            Log.v("asd","entrogogo");
                            libres = estado;
                            libres2 = "rojo";
                            libres3 = estado;
                            new CreateUserv2().execute();


                        }


                    }


                }

            }catch (Exception e){

            }

        }
    }

    private class LoadTIME1 extends AsyncTask<Void, Void, Void> {
        //Sacado de: http://www.survivingwithandroid.com/2014/04/parsing-html-in-android-with-jsoup.html
        //Pagina web real: http://www.timeanddate.com/worldclock/peru/lima
        //HTML DE WEB: view-source:http://www.timeanddate.com/worldclock/peru/lima



        //Se crea una cadena de texto cuyo valor es la URL de la pagina web
        String url = "http://www.timeanddate.com/worldclock/peru/lima";
        //Se crea una variable de tipo Documento y se le da como valor inicial nulo
        Document doc = null;
        //Se crean dos cadenas de texto para la hora y el dia
        String horac;
        String diac;

        @Override
        protected void onPreExecute() {
            //Metodo antes de ejecutar la accion
            super.onPreExecute();

            //Se da como valor a la variable un nuevo dialogo de progreso en la actividad
            pDialog2 = new ProgressDialog(MainActivity3.this);
            //Se le asigna el mensaje a mostrar
            pDialog2.setMessage("Cargando...");
            //Se le da valor falso

            pDialog2.setIndeterminate(false);
            //Se le da valor cancelable verdadero
            pDialog2.setCancelable(false);
            //Se muestra el dialogo de progreso
            pDialog2.show();

        }

        @Override
        protected Void doInBackground(Void... params) {
            //Metodo que ocurre en segundo plano
            // TODO Auto-generated method stub

            //Intenta
            try {
                // obtener el documento de la pagina conectandose con el Jsoup
                //TIEMPO HH:MM
                doc = Jsoup.connect(url).get();
                //Se crea un elemento llamado hora cuyo valor sera el dato(hora y minutos) dentro del parametro dado
                Elements hora = doc.select("span[id=fshrmin]");
                //Se le da valor a la cadena de texto convirtiendo el dato encontrado a texto
                horac = hora.text();

                //Se crea un elemento cuyo valor sera el dato (fecha en ingles) dentro del parametro dado
                Elements dia = doc.select("span[id=ctdat]");
                //Se le da valor a la cadena de texto convirtiendo el dato encontrado a texto
                diac = dia.text();
                horatot=horac;
                diatot=diac;
                Log.v("asd",horac);

                /*
                Elements topicList = doc.select("h2.topic");
                Log.i("TIEMPO", "META: " + metaElem);
                Log.i("TIEMPO", "TOPICLIST : " + topicList);
                Elements links = doc.select("a[href]"); // a with href
                Element masthead = doc.select("div.masthead").first();
                // div with class=masthead
                Elements resultLinks = doc.select("h3.r > a"); // direct a after h3
                Log.i("TIEMPO", "AHREF: " + links);
                Log.i("TIEMPO", "MASTHEAD: " + masthead);
                Log.i("TIEMPO", "ResultLinks : " + resultLinks);
                */


            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.i("TIEMPO", "ERROR");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //Metodo que ocurre despues de terminada la accion
            // TODO Auto-generated method stub
            super.onPostExecute(result);


            //Se verifica que el texto contenga los valores dado en parametros:
            //(Si contiene Lunes, Martes, Miercoles, Jueves o Viernes)

            try{

                if((diac.contains("Monday") || diac.contains("Tuesday") || diac.contains("Wednesday") || diac.contains("Thursday") || diac.contains("Friday")|| diac.contains("Sunday")|| diac.contains("Saturday"))){
                    //Si la hora contiene 18, 19, 20, 21, 22 o 23 (se le pone dos puntos para que coja la hora y no lo confunda con los minutos)
                    if(horac.contains("23:34")||horac.contains("23:35")||horac.contains("23:36")){

                        Log.v("estados", estado+estado2+estado3);


                        if(estado.equals("verde") && estado2.equals("rojo") && estado3.equals("rojo")){


                        }else{

                            Log.v("asd","entrogogo");
                            libres = "verde";
                            libres2 = "rojo";
                            libres3 = "rojo";
                            new CreateUserv2().execute();


                        }


                    }


                }

                if((diac.contains("Monday") || diac.contains("Tuesday") || diac.contains("Wednesday") || diac.contains("Thursday") || diac.contains("Friday"))){
                    //Si la hora contiene 18, 19, 20, 21, 22 o 23 (se le pone dos puntos para que coja la hora y no lo confunda con los minutos)
                    if(horac.contains("17:57") ||horac.contains("17:58")||horac.contains("17:59")){

                        Log.v("estados", estado+estado2+estado3);


                        if(estado2.equals("verde") && estado3.equals("verde")){


                        }else{

                            Log.v("asd","entrogogo");
                            libres = estado;
                            libres2 = "verde";
                            libres3 = "verde";
                            new CreateUserv2().execute();


                        }


                    }


                }

                if(diac.contains("Saturday")){
                    //Si la hora contiene 18, 19, 20, 21, 22 o 23 (se le pone dos puntos para que coja la hora y no lo confunda con los minutos)
                    if(horac.contains("06:27") ||horac.contains("06:28")||horac.contains("06:29")){

                        Log.v("estados", estado+estado2+estado3);


                        if(estado2.equals("verde")){


                        }else{

                            Log.v("asd","entrogogo");
                            libres = estado;
                            libres2 = "verde";
                            libres3 = "rojo";
                            new CreateUserv2().execute();


                        }


                    }

                    if(horac.contains("20:57") ||horac.contains("20:58")||horac.contains("20:59")){



                        if(estado2.equals("rojo")){


                        }else{

                            Log.v("asd","entrogogo");
                            libres = estado;
                            libres2 = "rojo";
                            libres3 = estado;
                            new CreateUserv2().execute();


                        }


                    }


                }

            }catch (Exception e){

            }

            pDialog2.dismiss();

        }
    }



    class CreateUser extends AsyncTask<String, String, String> {//Metodo que guarda el estado en la base de datos


        @Override
        protected void onPreExecute() {
            //Metodo antes de ser ejecutada la accion


            super.onPreExecute();
            //Se le da valor a la variable con un nuevo dialogo de progreso en esta actividad
            pDialog3 = new ProgressDialog(MainActivity3.this);
            //Se le da el mensaje a mostrar
            pDialog3.setMessage("Registrando cambio...");
            //Se le da falso a su indeterminado
            pDialog3.setIndeterminate(false);
            //Se le permite ser cancelado
            pDialog3.setCancelable(false);
            //Se muiestra el dialogo de progreso
            pDialog3.show();

        }

        @Override
        protected String doInBackground(String... args) {
            //Metodo que se hace en segundo plano

            // TODO Auto-generated method stub
            // Check for success tag


            int compro=0;

            while(compro==0){

                int success;
                String username = libres;
                try {
                    // Building Parameters
                    List params = new ArrayList();
                    params.add(new BasicNameValuePair("username", username));


                    Log.d("request!", "starting");

                    //Posting user data to script
                    JSONObject json = jsonParser.makeHttpRequest(
                            REGISTER_URL, "POST", params);

                    // full json response
                    Log.d("Registering attempt", json.toString());

                    // json success element
                    success = json.getInt(TAG_SUCCESS);
                    if (success == 1) {
                        Log.d("User Created!", json.toString());
                        compro=compro+1;
                        return json.getString(TAG_MESSAGE);

                    }else{
                        Log.d("Registering Failure!", json.getString(TAG_MESSAGE));
                        return json.getString(TAG_MESSAGE);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }


            return null;

        }

        protected void onPostExecute(String file_url) {
            //Metodo que se hace terminada la ejecucion de la accion

            // dismiss the dialog once product deleted
            pDialog3.dismiss();
            if (file_url != null){
                Toast.makeText(MainActivity3.this, file_url, Toast.LENGTH_LONG).show();
            }

            try{
                new LoadAllProducts().execute();

            }catch (Exception e){

            }



        }
    }

    class CreateUserv2 extends AsyncTask<String, String, String> {//Metodo que guarda el estado en la base de datos


        @Override
        protected void onPreExecute() {
            //Metodo antes de ser ejecutada la accion


            super.onPreExecute();
            //Se le da valor a la variable con un nuevo dialogo de progreso en esta actividad
            pDialog3 = new ProgressDialog(MainActivity3.this);
            //Se le da el mensaje a mostrar
            pDialog3.setMessage("Registrando cambio...");
            //Se le da falso a su indeterminado
            pDialog3.setIndeterminate(false);
            //Se le permite ser cancelado
            pDialog3.setCancelable(false);
            //Se muiestra el dialogo de progreso
            pDialog3.show();

        }

        @Override
        protected String doInBackground(String... args) {
            //Metodo que se hace en segundo plano

            // TODO Auto-generated method stub
            // Check for success tag


            int compro=0;

            while(compro==0){

                int success;
                String username = libres;
                try {
                    // Building Parameters
                    List params = new ArrayList();
                    params.add(new BasicNameValuePair("username", username));


                    Log.d("request!", "starting");

                    //Posting user data to script
                    JSONObject json = jsonParser.makeHttpRequest(
                            REGISTER_URL, "POST", params);

                    // full json response
                    Log.d("Registering attempt", json.toString());

                    // json success element
                    success = json.getInt(TAG_SUCCESS);
                    if (success == 1) {
                        Log.d("User Created!", json.toString());
                        compro=compro+1;
                        return json.getString(TAG_MESSAGE);

                    }else{
                        Log.d("Registering Failure!", json.getString(TAG_MESSAGE));
                        return json.getString(TAG_MESSAGE);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }


            return null;

        }

        protected void onPostExecute(String file_url) {
            //Metodo que se hace terminada la ejecucion de la accion

            // dismiss the dialog once product deleted
            pDialog3.dismiss();
            if (file_url != null){
                Toast.makeText(MainActivity3.this, file_url, Toast.LENGTH_LONG).show();
            }

            new CreateUser2v2().execute();



        }
    }



    class CreateUser2 extends AsyncTask<String, String, String> {//Metodo que guarda el estado en la base de datos


        @Override
        protected void onPreExecute() {
            //Metodo antes de ser ejecutada la accion


            super.onPreExecute();
            //Se da valor a la variable con un nuevo dialogo de progreso en la actividad
            pDialog4 = new ProgressDialog(MainActivity3.this);
            //Se le asigna el mensaje a mostrar
            pDialog4.setMessage("Registrando cambio...");
            //Se le da falso al indeterminado
            pDialog4.setIndeterminate(false);
            //Se permite cancelar
            pDialog4.setCancelable(false);
            //Se muestra el dialogo de progreso
            pDialog4.show();

        }

        @Override
        protected String doInBackground(String... args) {
            //Metodo que se hace en segundo plano

            int compro=0;

            while(compro==0){


                int success;
                String username2 = libres2;
                try {
                    // Building Parameters
                    List params = new ArrayList();
                    params.add(new BasicNameValuePair("username2", username2));


                    Log.d("request!", "starting");

                    //Posting user data to script
                    JSONObject json = jsonParser.makeHttpRequest(
                            REGISTER_URL2, "POST", params);

                    // full json response
                    Log.d("Registering attempt", json.toString());

                    // json success element
                    success = json.getInt(TAG_SUCCESS);
                    if (success == 1) {
                        Log.d("User Created!", json.toString());
                        compro=compro+1;
                        return json.getString(TAG_MESSAGE);
                    }else{
                        Log.d("Registering Failure!", json.getString(TAG_MESSAGE));
                        return json.getString(TAG_MESSAGE);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }



            return null;

        }

        protected void onPostExecute(String file_url) {
            //Metodo que se hace terminada la ejecucion de la accion

            // dismiss the dialog once product deleted
            pDialog4.dismiss();
            if (file_url != null){
                //Se muestra un mensaje en pantalla durante 3.5 segundos
                Toast.makeText(MainActivity3.this, file_url, Toast.LENGTH_LONG).show();
            }
            try{
                new LoadAllProducts().execute();

            }catch (Exception e){

            }



        }
    }

    class CreateUser2v2 extends AsyncTask<String, String, String> {//Metodo que guarda el estado en la base de datos


        @Override
        protected void onPreExecute() {
            //Metodo antes de ser ejecutada la accion


            super.onPreExecute();
            //Se da valor a la variable con un nuevo dialogo de progreso en la actividad
            pDialog4 = new ProgressDialog(MainActivity3.this);
            //Se le asigna el mensaje a mostrar
            pDialog4.setMessage("Registrando cambio...");
            //Se le da falso al indeterminado
            pDialog4.setIndeterminate(false);
            //Se permite cancelar
            pDialog4.setCancelable(false);
            //Se muestra el dialogo de progreso
            pDialog4.show();

        }

        @Override
        protected String doInBackground(String... args) {
            //Metodo que se hace en segundo plano

            int compro=0;

            while(compro==0){


                int success;
                String username2 = libres2;
                try {
                    // Building Parameters
                    List params = new ArrayList();
                    params.add(new BasicNameValuePair("username2", username2));


                    Log.d("request!", "starting");

                    //Posting user data to script
                    JSONObject json = jsonParser.makeHttpRequest(
                            REGISTER_URL2, "POST", params);

                    // full json response
                    Log.d("Registering attempt", json.toString());

                    // json success element
                    success = json.getInt(TAG_SUCCESS);
                    if (success == 1) {
                        Log.d("User Created!", json.toString());
                        compro=compro+1;
                        return json.getString(TAG_MESSAGE);
                    }else{
                        Log.d("Registering Failure!", json.getString(TAG_MESSAGE));
                        return json.getString(TAG_MESSAGE);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }



            return null;

        }

        protected void onPostExecute(String file_url) {
            //Metodo que se hace terminada la ejecucion de la accion

            // dismiss the dialog once product deleted
            pDialog4.dismiss();
            if (file_url != null){
                //Se muestra un mensaje en pantalla durante 3.5 segundos
                Toast.makeText(MainActivity3.this, file_url, Toast.LENGTH_LONG).show();
            }
            new CreateUser3v3().execute();



        }
    }


    class CreateUser3 extends AsyncTask<String, String, String> {//Metodo que guarda el estado en la base de datos


        @Override
        protected void onPreExecute() {
            //Metodo antes de ser ejecutada la accion


            super.onPreExecute();
            //Se da valor al dialogo de progreso con uno nuevo en la actividad
            pDialog5 = new ProgressDialog(MainActivity3.this);
            //Se le asigna el mensaje a mostrar
            pDialog5.setMessage("Registrando cambio...");
            //Se le da valor falso indeterminado
            pDialog5.setIndeterminate(false);
            //Se da valor verdadero al cancelable
            pDialog5.setCancelable(false);
            //Se muestra el dialogo de progreso
            pDialog5.show();

        }

        @Override
        protected String doInBackground(String... args) {
            //Metodo que se hace en segundo plano

            // TODO Auto-generated method stub
            // Check for success tag
            int compro=0;

            while(compro==0){


                int success;
                String username3 = libres3;
                try {
                    // Building Parameters
                    List params = new ArrayList();
                    params.add(new BasicNameValuePair("username3", username3));


                    Log.d("request!", "starting");

                    //Posting user data to script
                    JSONObject json = jsonParser.makeHttpRequest(
                            REGISTER_URL3, "POST", params);

                    // full json response
                    Log.d("Registering attempt", json.toString());

                    // json success element
                    success = json.getInt(TAG_SUCCESS);
                    if (success == 1) {
                        Log.d("User Created!", json.toString());
                        compro=compro+1;
                        return json.getString(TAG_MESSAGE);
                    }else{
                        Log.d("Registering Failure!", json.getString(TAG_MESSAGE));
                        return json.getString(TAG_MESSAGE);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }




            return null;

        }

        protected void onPostExecute(String file_url) {
            //Metodo que se hace terminada la ejecucion de la accion

            // dismiss the dialog once product deleted
            pDialog5.dismiss();
            if (file_url != null){
                //Se muestra un mensaje en pantalla durante 3.5 segundos
                Toast.makeText(MainActivity3.this, file_url, Toast.LENGTH_LONG).show();
            }
            try{
                new LoadAllProducts().execute();

            }catch (Exception e){

            }



        }
    }

    class CreateUser3v3 extends AsyncTask<String, String, String> {//Metodo que guarda el estado en la base de datos


        @Override
        protected void onPreExecute() {
            //Metodo antes de ser ejecutada la accion


            super.onPreExecute();
            //Se da valor al dialogo de progreso con uno nuevo en la actividad
            pDialog5 = new ProgressDialog(MainActivity3.this);
            //Se le asigna el mensaje a mostrar
            pDialog5.setMessage("Registrando cambio...");
            //Se le da valor falso indeterminado
            pDialog5.setIndeterminate(false);
            //Se da valor verdadero al cancelable
            pDialog5.setCancelable(false);
            //Se muestra el dialogo de progreso
            pDialog5.show();

        }

        @Override
        protected String doInBackground(String... args) {
            //Metodo que se hace en segundo plano

            // TODO Auto-generated method stub
            // Check for success tag
            int compro=0;

            while(compro==0){


                int success;
                String username3 = libres3;
                try {
                    // Building Parameters
                    List params = new ArrayList();
                    params.add(new BasicNameValuePair("username3", username3));


                    Log.d("request!", "starting");

                    //Posting user data to script
                    JSONObject json = jsonParser.makeHttpRequest(
                            REGISTER_URL3, "POST", params);

                    // full json response
                    Log.d("Registering attempt", json.toString());

                    // json success element
                    success = json.getInt(TAG_SUCCESS);
                    if (success == 1) {
                        Log.d("User Created!", json.toString());
                        compro=compro+1;
                        return json.getString(TAG_MESSAGE);
                    }else{
                        Log.d("Registering Failure!", json.getString(TAG_MESSAGE));
                        return json.getString(TAG_MESSAGE);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }




            return null;

        }

        protected void onPostExecute(String file_url) {
            //Metodo que se hace terminada la ejecucion de la accion

            // dismiss the dialog once product deleted
            pDialog5.dismiss();
            if (file_url != null){
                //Se muestra un mensaje en pantalla durante 3.5 segundos
                Toast.makeText(MainActivity3.this, file_url, Toast.LENGTH_LONG).show();
            }

            try{
                new LoadAllProducts().execute();

            }catch (Exception e){

            }




        }
    }


    //Clase que ocurre en segundo plano que carga los datos de la base a la app
    class LoadAllProducts extends AsyncTask<String, String, String> {
        //Antes de ejecutar
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Se da como valor a la variable un nuevo dialogo de progreso en la actividad
            pDialog = new ProgressDialog(MainActivity3.this);
            //Se le asigna el mensaje a mostrar
            pDialog.setMessage("Cargando...");
            //Se le da valor falso
            pDialog.setIndeterminate(false);
            //Se le da valor cancelable verdadero
            pDialog.setCancelable(false);
            //Se muestra el dialogo de progreso
            pDialog.show();

        }

        protected String doInBackground(String... args) {

            int compro2=0;
            //Segundo plano
            //Se crea una nueva lista

            while(compro2==0){



            List params = new ArrayList();
            //Se crea una nueva variable para obtener los datos del php
            JSONObject json = jParser.makeHttpRequest(url_all_empresas, "GET", params);

            try {
                int success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    products = json.getJSONArray(TAG_PRODUCTS);
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);
                        estado=c.getString(TAG_NOMBRE);
                        estado2=c.getString(TAG_NOMBRE2);
                        estado3=c.getString(TAG_NOMBRE3);
                        Log.d("estado registro", c.getString(TAG_NOMBRE));
                        compro2=compro2+1;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            }

            return null;
        }
        protected void onPostExecute(String file_url) {
            //Despues de ser ejecutada la accion

            //El dialogo de progreso desaparece
            pDialog.dismiss();

            //Obtiene el color elegido del semaforo para que los otros dos se apaguen
            if(estado.equals("rojo")){
                sem1.setImageResource(R.drawable.rojoprendido);
                sem2.setImageResource(R.drawable.amarilloapagado);
                sem3.setImageResource(R.drawable.verdeapagado);

                //Obtiene el color elegido del semaforo para que los otros dos se apaguen
            }
            if(estado.equals("amarillo")){
                sem1.setImageResource(R.drawable.rojoapagado);
                sem2.setImageResource(R.drawable.amarilloprendido);
                sem3.setImageResource(R.drawable.verdeapagado);

                //Obtiene el color elegido del semaforo para que los otros dos se apaguen
            }
            if(estado.equals("verde")){
                sem1.setImageResource(R.drawable.rojoapagado);
                sem2.setImageResource(R.drawable.amarilloapagado);
                sem3.setImageResource(R.drawable.verdeprendido);

            }

            if(estado2.equals("rojo")){
                sem4.setImageResource(R.drawable.rojoprendido);
                sem5.setImageResource(R.drawable.amarilloapagado);
                sem6.setImageResource(R.drawable.verdeapagado);

                //Obtiene el color elegido del semaforo para que los otros dos se apaguen
            }
            if(estado2.equals("amarillo")){
                sem4.setImageResource(R.drawable.rojoapagado);
                sem5.setImageResource(R.drawable.amarilloprendido);
                sem6.setImageResource(R.drawable.verdeapagado);

                //Obtiene el color elegido del semaforo para que los otros dos se apaguen
            }
            if(estado2.equals("verde")){
                sem4.setImageResource(R.drawable.rojoapagado);
                sem5.setImageResource(R.drawable.amarilloapagado);
                sem6.setImageResource(R.drawable.verdeprendido);

            }

            if(estado3.equals("rojo")){
                sem7.setImageResource(R.drawable.rojoprendido);
                sem8.setImageResource(R.drawable.amarilloapagado);
                sem9.setImageResource(R.drawable.verdeapagado);

                //Obtiene el color elegido del semaforo para que los otros dos se apaguen
            }
            if(estado3.equals("amarillo")){
                sem7.setImageResource(R.drawable.rojoapagado);
                sem8.setImageResource(R.drawable.amarilloprendido);
                sem9.setImageResource(R.drawable.verdeapagado);

                //Obtiene el color elegido del semaforo para que los otros dos se apaguen
            }
            if(estado3.equals("verde")){
                sem7.setImageResource(R.drawable.rojoapagado);
                sem8.setImageResource(R.drawable.amarilloapagado);
                sem9.setImageResource(R.drawable.verdeprendido);

            }

        }
    }



    //Metodo que permite mostar un PopUp
    private void showPopup(final Activity context, final int opc) {
        //Creacion de nueva variable de medida
        DisplayMetrics displaymetrics = new DisplayMetrics();
        //Se obtiene datos de medida de pantalla del celular
        MainActivity3.this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        //Se crea un entero y se le da valor con el alto de la pantalla en pixeles
        int height = displaymetrics.heightPixels;
        //Se crea un entero y se le da valor con el ancho de la pantalla en pixeles
        double width = displaymetrics.widthPixels;

        Log.v("tamano",String.valueOf(height));
        Log.v("tamano",String.valueOf(width));

        //Se crean variables de numeros reales que se multiplican por otros para encajar bien el popup
        double popupHeight = height*0.52;
        double popupWidth = width*0.625;


        //Se le da el layout respectivo
        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.popup3);
        //Se infla el el layout
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Se da valor al view con los datos anteriores
        View layout = layoutInflater.inflate(R.layout.popup3, viewGroup);

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        //Se le da la vista de contendio al popup
        popup.setContentView(layout);
        //Se le asigna un ancho al popup
        popup.setWidth((int) Math.round(popupHeight));
        //Se le asigna el alto al popup
        popup.setHeight((int) Math.round(popupWidth));
        //Se enfoca el popup
        popup.setFocusable(true);
        //No se permite dar clic fuera del popup
        popup.setOutsideTouchable(false);

        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(layout, Gravity.CENTER, 0, 0);

        // FUENTE PARA TEXTO EN POPUP Y BOTONES:
        String font_pathPP = "font/HelveticaNeue-Light.ttf"; //ruta de la fuente
        Typeface TPP = Typeface.createFromAsset(MainActivity3.this.getAssets(),font_pathPP);//llamanos a la CLASS TYPEFACE y la definimos
        // con un CREATE desde ASSETS con la ruta STRING

        // Getting a reference to Close button, and close the popup when clicked.
        Button close = (Button) layout.findViewById(R.id.call3);
        Button call = (Button) layout.findViewById(R.id.close3);
        //Se crea una variable de tipo vista de imagen y se le da el valor del id respectivo del layout
        final ImageView popim=(ImageView)layout.findViewById(R.id.pop3im);
        //Se les asigna el tipo de fuente a los textos de los botones
        call.setTypeface(TPP);
        close.setTypeface(TPP);

        //Se crea una variable de cuadro de texto final cuyo valor es el id del mismo en el layout respectivo
        final TextView tv1 = (TextView) layout.findViewById(R.id.pop3tv1);
        //Se le asigna el tipo de fuente al cuadro de texto
        tv1.setTypeface(TPP);




        //Seguns sea la opcion escogida del color de semaforo
        switch(opc) {
            //Se mostrara en el cuadro de texto el texto entre comillas segun el caso
            //Se cambia la imagen segun el caso
            case 1:
                tv1.setText("¿Está seguro que desea cambiar el estado del estacionamiento de ESAN a rojo?");
                popim.setImageResource(R.drawable.rojoprendido);
                break;

            case 2:
                tv1.setText("¿Está seguro que desea cambiar el estado del estacionamiento de ESAN a amarillo?");
                popim.setImageResource(R.drawable.amarilloprendido);

                break;

            case 3:
                tv1.setText("¿Está seguro que desea cambiar el estado del estacionamiento de ESAN a verde?");
                popim.setImageResource(R.drawable.verdeprendido);

                break;

            case 4:
                tv1.setText("¿Es seguro que desea cambiar el estado del estacionamiento de Alonso de Molina a rojo?");
                popim.setImageResource(R.drawable.rojoprendido);

                break;

            case 5:
                tv1.setText("¿Está seguro que desea cambiar el estado del estacionamiento de Alonso de Molina a amarillo?");
                popim.setImageResource(R.drawable.amarilloprendido);

                break;

            case 6:
                tv1.setText("¿Está seguro que desea cambiar el estado del estacionamiento de Alonso de Molina a verde?");
                popim.setImageResource(R.drawable.verdeprendido);
                break;

            case 7:
                tv1.setText("¿Está seguro que desea cambiar el estado del estacionamiento de El Polo a rojo?");
                popim.setImageResource(R.drawable.rojoprendido);

                break;

            case 8:
                tv1.setText("¿Está seguro que desea cambiar el estado del estacionamiento de El Polo a amarillo?");
                popim.setImageResource(R.drawable.amarilloprendido);

                break;

            case 9:
                tv1.setText("¿Está seguro que desea cambiar el estado del estacionamiento de El Polo a verde?");
                popim.setImageResource(R.drawable.verdeprendido);

                break;
        }



        close.setOnClickListener(new View.OnClickListener() {
            //Cuando se da click al boton Cerrar
            @Override
            public void onClick(View v) {
                //Se cierra el pop up
                popup.dismiss();
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            //Cuando se ca click al boton aceptar
            @Override
            public void onClick(View v) {
                //Se cierra el pop up
                popup.dismiss();


                switch(opc) {
                    //Se verifica la conexion a internet en todos los casos
                    //En caso de ser verdadera la conexion, se cambian las imagenes/color de semaforos
                    //Caso contrario muestra un mensaje de No hay conexion
                    case 1:
                        if (isNetworkAvailable() == true) {

                            libres = "rojo";
                            new CreateUser().execute();// se ejecuta este metodo, que guarda el estado "rojo" en la base de datos


                            //Si no existe conexion a internet
                        } else {
                            Toast.makeText(MainActivity3.this, "No hay conexio,n a internet", Toast.LENGTH_LONG).show();
                            Log.d("internet", "no hay");
                        }

                        break;

                    case 2:

                        if (isNetworkAvailable() == true) {

                            libres = "amarillo";
                            new CreateUser().execute();

                            //Si no existe conexion a internet
                        } else {
                            Toast.makeText(MainActivity3.this, "No hay coneccion a internet", Toast.LENGTH_LONG).show();
                            Log.d("internet", "no hay");
                        }

                        break;

                    case 3:

                        if (isNetworkAvailable() == true) {

                            libres = "verde";
                            new CreateUser().execute();

                            //Si no existe conexion a internet
                        } else {
                            Toast.makeText(MainActivity3.this, "No hay coneccion a internet", Toast.LENGTH_LONG).show();
                            Log.d("internet", "no hay");
                        }



                        break;

                    case 4:
                        if(isNetworkAvailable()==true){

                            libres2="rojo";
                            new CreateUser2().execute();

                            //Si no existe conexion a internet
                        }else{
                            Toast.makeText(MainActivity3.this, "No hay coneccion a internet", Toast.LENGTH_LONG).show();
                            Log.d("internet", "no hay");
                        }



                        break;

                    case 5:
                        if(isNetworkAvailable()==true){

                            libres2="amarillo";
                            new CreateUser2().execute();

                            //Si no existe conexion a internet
                        }else{
                            Toast.makeText(MainActivity3.this, "No hay coneccion a internet", Toast.LENGTH_LONG).show();
                            Log.d("internet", "no hay");
                        }



                        break;

                    case 6:

                        if(isNetworkAvailable()==true){

                            libres2="verde";
                            new CreateUser2().execute();

                            //Si no existe conexion a internet
                        }else{
                            Toast.makeText(MainActivity3.this, "No hay coneccion a internet", Toast.LENGTH_LONG).show();
                            Log.d("internet", "no hay");
                        }



                        break;

                    case 7:

                        if(isNetworkAvailable()==true){

                            libres3="rojo";
                            new CreateUser3().execute();

                            //Si no existe conexion a internet
                        }else{
                            Toast.makeText(MainActivity3.this, "No hay coneccion a internet", Toast.LENGTH_LONG).show();
                            Log.d("internet", "no hay");
                        }



                        break;

                    case 8:

                        if(isNetworkAvailable()==true){

                            libres3="amarillo";
                            new CreateUser3().execute();

                            //Si no existe conexion a internet
                        }else{
                            Toast.makeText(MainActivity3.this, "No hay coneccion a internet", Toast.LENGTH_LONG).show();
                            Log.d("internet", "no hay");
                        }



                        break;

                    case 9:




                        if(isNetworkAvailable()==true){

                            libres3="verde";
                            new CreateUser3().execute();

                            //Si no existe conexion a internet
                        }else{
                            Toast.makeText(MainActivity3.this, "No hay coneccion a internet", Toast.LENGTH_LONG).show();
                            Log.d("internet", "no hay");
                        }



                        break;


                }
            }
        });


    }

    private boolean isNetworkAvailable() {
        //Obtiene verdadero o falso segun la verificaion de conexion a internet

        ConnectivityManager cm = (ConnectivityManager) MainActivity3.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}
