package pe.edu.esan.appestacionamientoregistro;

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

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends ActionBarActivity {

    //Declaracion de variables

    //Para el semaforo
    private ImageView sem1,sem2,sem3,sem4,sem5,sem6,sem7,sem8,sem9;

    //Dato del color activo del semaforo
    private String libres;
    private String libres2;
    private String libres3;

    //Dialogo de progreso de carga
    private ProgressDialog pDialog;

    // JSON parser class
    JSONParser jsonParser = new JSONParser();
    /*CADENAS DE TEXTO PRIVADAS ESTATICAS FINALES CUYOS VALORES SON LAS URLs DE LOS PHP PARA REGISTRAR LOS DATOS*/
    private static final String REGISTER_URL = "http://estacionamientoesan.pe.hu/cas/register.php";
    private static final String REGISTER_URL2 = "http://estacionamientoesan.pe.hu/cas/register2.php";
    private static final String REGISTER_URL3 = "http://estacionamientoesan.pe.hu/cas/register3.php";
    //Cadenas de texto privadas cuyos valores son los textos obtenidos para resultados satisfactorios
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    //Cadena de texto cuyo valor es la URL del php para obtener los datos
    private static String url_all_empresas = "http://estacionamientoesan.pe.hu/esconnect/get_all_empresas.php";
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

        //Semaforo amarillo
        sem2=(ImageView)findViewById(R.id.sema2);

        //Semaforo verde
        sem3=(ImageView)findViewById(R.id.sema3);
        sem4=(ImageView)findViewById(R.id.sema4);
        sem5=(ImageView)findViewById(R.id.sema6);
        sem6=(ImageView)findViewById(R.id.sema5);
        sem7=(ImageView)findViewById(R.id.sema7);
        sem8=(ImageView)findViewById(R.id.sema9);
        sem9=(ImageView)findViewById(R.id.sema8);



        new LoadAllProducts().execute();// Este metodo busca el estado actual del estacionamiento en la base de datos






        sem1.setOnClickListener(new View.OnClickListener() {//se detecta si se hizo click a este boton(bombilla roja del semaforo)
            @Override
            public void onClick(View v) {

                showPopup(MainActivity.this,1);

            }
        });

        sem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showPopup(MainActivity.this,2);
                //Cuando el semaforo amarillo es clickeado
                //Si es que existe conexion a internet



            }
        });

        sem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(MainActivity.this,3);
                //Cuando el semaforo verde es clickeado
                //Si es que existe conexion a internet



            }
        });

        sem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(MainActivity.this,4);
                //Cuando el semaforo verde es clickeado
                //Si es que existe conexion a internet



            }
        });

        sem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(MainActivity.this,5);
                //Cuando el semaforo verde es clickeado
                //Si es que existe conexion a internet


            }
        });

        sem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(MainActivity.this,6);
                //Cuando el semaforo verde es clickeado
                //Si es que existe conexion a internet



            }
        });


        sem7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(MainActivity.this,7);
                //Cuando el semaforo verde es clickeado
                //Si es que existe conexion a internet



            }
        });

        sem8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(MainActivity.this,8);
                //Cuando el semaforo verde es clickeado
                //Si es que existe conexion a internet



            }
        });

        sem9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(MainActivity.this,9);
                //Cuando el semaforo verde es clickeado
                //Si es que existe conexion a internet



            }
        });


    }


    class CreateUser extends AsyncTask<String, String, String> {//Metodo que guarda el estado en la base de datos


        @Override
        protected void onPreExecute() {
            //Metodo antes de ser ejecutada la accion


            super.onPreExecute();
            //Se le da valor a la variable con un nuevo dialogo de progreso en esta actividad
            pDialog = new ProgressDialog(MainActivity.this);
            //Se le da el mensaje a mostrar
            pDialog.setMessage("Creating User...");
            //Se le da falso a su indeterminado
            pDialog.setIndeterminate(false);
            //Se le permite ser cancelado
            pDialog.setCancelable(true);
            //Se muiestra el dialogo de progreso
            pDialog.show();

        }

        @Override
        protected String doInBackground(String... args) {
            //Metodo que se hace en segundo plano

            // TODO Auto-generated method stub
            // Check for success tag
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
                    return json.getString(TAG_MESSAGE);
                }else{
                    Log.d("Registering Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String file_url) {
            //Metodo que se hace terminada la ejecucion de la accion

            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null){
                Toast.makeText(MainActivity.this, file_url, Toast.LENGTH_LONG).show();
            }



        }
    }

    class CreateUser2 extends AsyncTask<String, String, String> {//Metodo que guarda el estado en la base de datos


        @Override
        protected void onPreExecute() {
            //Metodo antes de ser ejecutada la accion


            super.onPreExecute();
            //Se da valor a la variable con un nuevo dialogo de progreso en la actividad
            pDialog = new ProgressDialog(MainActivity.this);
            //Se le asigna el mensaje a mostrar
            pDialog.setMessage("Creating User...");
            //Se le da falso al indeterminado
            pDialog.setIndeterminate(false);
            //Se permite cancelar
            pDialog.setCancelable(true);
            //Se muestra el dialogo de progreso
            pDialog.show();

        }

        @Override
        protected String doInBackground(String... args) {
            //Metodo que se hace en segundo plano

            // TODO Auto-generated method stub
            // Check for success tag
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
                    return json.getString(TAG_MESSAGE);
                }else{
                    Log.d("Registering Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String file_url) {
            //Metodo que se hace terminada la ejecucion de la accion

            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null){
                //Se muestra un mensaje en pantalla durante 3.5 segundos
                Toast.makeText(MainActivity.this, file_url, Toast.LENGTH_LONG).show();
            }



        }
    }


    class CreateUser3 extends AsyncTask<String, String, String> {//Metodo que guarda el estado en la base de datos


        @Override
        protected void onPreExecute() {
            //Metodo antes de ser ejecutada la accion


            super.onPreExecute();
            //Se da valor al dialogo de progreso con uno nuevo en la actividad
            pDialog = new ProgressDialog(MainActivity.this);
            //Se le asigna el mensaje a mostrar
            pDialog.setMessage("Creating User...");
            //Se le da valor falso indeterminado
            pDialog.setIndeterminate(false);
            //Se da valor verdadero al cancelable
            pDialog.setCancelable(true);
            //Se muestra el dialogo de progreso
            pDialog.show();

        }

        @Override
        protected String doInBackground(String... args) {
            //Metodo que se hace en segundo plano

            // TODO Auto-generated method stub
            // Check for success tag
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
                    return json.getString(TAG_MESSAGE);
                }else{
                    Log.d("Registering Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String file_url) {
            //Metodo que se hace terminada la ejecucion de la accion

            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null){
                //Se muestra un mensaje en pantalla durante 3.5 segundos
                Toast.makeText(MainActivity.this, file_url, Toast.LENGTH_LONG).show();
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
            pDialog = new ProgressDialog(MainActivity.this);
            //Se le asigna el mensaje a mostrar
            pDialog.setMessage("Creating User...");
            //Se le da valor falso
            pDialog.setIndeterminate(false);
            //Se le da valor cancelable verdadero
            pDialog.setCancelable(true);
            //Se muestra el dialogo de progreso
            pDialog.show();

        }

        protected String doInBackground(String... args) {
            //Segundo plano
            //Se crea una nueva lista
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
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
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
        MainActivity.this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
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
        Typeface TPP = Typeface.createFromAsset(MainActivity.this.getAssets(),font_pathPP);//llamanos a la CLASS TYPEFACE y la definimos
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
                tv1.setText("¿Es seguro que desea cambiar el estado del estacionamiento de Alonso de la Molina a rojo?");
                popim.setImageResource(R.drawable.rojoprendido);

                break;

            case 5:
                tv1.setText("¿Está seguro que desea cambiar el estado del estacionamiento de Alonso de la Molina a amarillo?");
                popim.setImageResource(R.drawable.amarilloprendido);

                break;

            case 6:
                tv1.setText("¿Está seguro que desea cambiar el estado del estacionamiento de Alonso de la Molina a verde?");
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
                            sem1.setImageResource(R.drawable.rojoprendido);//Se activa la imagen del rojo predido
                            sem2.setImageResource(R.drawable.amarilloapagado);//Se apaga la luz amarilla
                            sem3.setImageResource(R.drawable.verdeapagado);//Se apaga la luz verde
                            libres = "rojo";
                            new CreateUser().execute();// se ejecuta este metodo, que guarda el estado "rojo" en la base de datos


                            //Si no existe conexion a internet
                        } else {
                            Toast.makeText(MainActivity.this, "No hay conexio,n a internet", Toast.LENGTH_LONG).show();
                            Log.d("internet", "no hay");
                        }

                        break;

                    case 2:

                        if (isNetworkAvailable() == true) {
                            sem1.setImageResource(R.drawable.rojoapagado);
                            sem2.setImageResource(R.drawable.amarilloprendido);
                            sem3.setImageResource(R.drawable.verdeapagado);
                            libres = "amarillo";
                            new CreateUser().execute();

                            //Si no existe conexion a internet
                        } else {
                            Toast.makeText(MainActivity.this, "No hay coneccion a internet", Toast.LENGTH_LONG).show();
                            Log.d("internet", "no hay");
                        }

                        break;

                    case 3:

                        if (isNetworkAvailable() == true) {
                            sem1.setImageResource(R.drawable.rojoapagado);
                            sem2.setImageResource(R.drawable.amarilloapagado);
                            sem3.setImageResource(R.drawable.verdeprendido);
                            libres = "verde";
                            new CreateUser().execute();

                            //Si no existe conexion a internet
                        } else {
                            Toast.makeText(MainActivity.this, "No hay coneccion a internet", Toast.LENGTH_LONG).show();
                            Log.d("internet", "no hay");
                        }



                        break;

                    case 4:
                        if(isNetworkAvailable()==true){
                            sem4.setImageResource(R.drawable.rojoprendido);
                            sem5.setImageResource(R.drawable.amarilloapagado);
                            sem6.setImageResource(R.drawable.verdeapagado);
                            libres2="rojo";
                            new CreateUser2().execute();

                            //Si no existe conexion a internet
                        }else{
                            Toast.makeText(MainActivity.this, "No hay coneccion a internet", Toast.LENGTH_LONG).show();
                            Log.d("internet", "no hay");
                        }



                        break;

                    case 5:
                        if(isNetworkAvailable()==true){
                            sem4.setImageResource(R.drawable.rojoapagado);
                            sem5.setImageResource(R.drawable.amarilloprendido);
                            sem6.setImageResource(R.drawable.verdeapagado);
                            libres2="amarillo";
                            new CreateUser2().execute();

                            //Si no existe conexion a internet
                        }else{
                            Toast.makeText(MainActivity.this, "No hay coneccion a internet", Toast.LENGTH_LONG).show();
                            Log.d("internet", "no hay");
                        }



                        break;

                    case 6:

                        if(isNetworkAvailable()==true){
                            sem4.setImageResource(R.drawable.rojoapagado);
                            sem5.setImageResource(R.drawable.amarilloapagado);
                            sem6.setImageResource(R.drawable.verdeprendido);
                            libres2="verde";
                            new CreateUser2().execute();

                            //Si no existe conexion a internet
                        }else{
                            Toast.makeText(MainActivity.this, "No hay coneccion a internet", Toast.LENGTH_LONG).show();
                            Log.d("internet", "no hay");
                        }



                        break;

                    case 7:

                        if(isNetworkAvailable()==true){
                            sem7.setImageResource(R.drawable.rojoprendido);
                            sem8.setImageResource(R.drawable.amarilloapagado);
                            sem9.setImageResource(R.drawable.verdeapagado);
                            libres3="rojo";
                            new CreateUser3().execute();

                            //Si no existe conexion a internet
                        }else{
                            Toast.makeText(MainActivity.this, "No hay coneccion a internet", Toast.LENGTH_LONG).show();
                            Log.d("internet", "no hay");
                        }



                        break;

                    case 8:

                        if(isNetworkAvailable()==true){
                            sem7.setImageResource(R.drawable.rojoapagado);
                            sem8.setImageResource(R.drawable.amarilloprendido);
                            sem9.setImageResource(R.drawable.verdeapagado);
                            libres3="amarillo";
                            new CreateUser3().execute();

                            //Si no existe conexion a internet
                        }else{
                            Toast.makeText(MainActivity.this, "No hay coneccion a internet", Toast.LENGTH_LONG).show();
                            Log.d("internet", "no hay");
                        }



                        break;

                    case 9:




                        if(isNetworkAvailable()==true){
                            sem7.setImageResource(R.drawable.rojoapagado);
                            sem8.setImageResource(R.drawable.amarilloapagado);
                            sem9.setImageResource(R.drawable.verdeprendido);
                            libres3="verde";
                            new CreateUser3().execute();

                            //Si no existe conexion a internet
                        }else{
                            Toast.makeText(MainActivity.this, "No hay coneccion a internet", Toast.LENGTH_LONG).show();
                            Log.d("internet", "no hay");
                        }



                        break;


                }
            }
        });


    }

    private boolean isNetworkAvailable() {
        //Obtiene verdadero o falso segun la verificaion de conexion a internet

        ConnectivityManager cm = (ConnectivityManager) MainActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}
