package pe.edu.esan.appestacionamientoregistro;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class MainActivity2Activity extends ActionBarActivity {

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
    private static final String REGISTER_URL = "http://www.estacionamientoesan.site88.net/cas/register.php";
    private static final String REGISTER_URL2 = "http://www.estacionamientoesan.site88.net/cas/register2.php";
    private static final String REGISTER_URL3 = "http://www.estacionamientoesan.site88.net/cas/register3.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    private static String url_all_empresas = "http://www.estacionamientoesan.site88.net/esconnect/get_all_empresas.php";
    private static final String TAG_PRODUCTS = "users";
    private static final String TAG_NOMBRE = "username";
    private static final String TAG_NOMBRE2 = "username2";
    private static final String TAG_NOMBRE3 = "username3";
    private String estado="waa";
    JSONArray products = null;
    JSONParser jParser = new JSONParser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_registroesta);

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

                showPopup(MainActivity2Activity.this,1);

            }
        });

        sem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showPopup(MainActivity2Activity.this,2);
                //Cuando el semaforo amarillo es clickeado
                //Si es que existe conexion a internet



            }
        });

        sem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(MainActivity2Activity.this,3);
                //Cuando el semaforo verde es clickeado
                //Si es que existe conexion a internet



            }
        });

        sem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(MainActivity2Activity.this,4);
                //Cuando el semaforo verde es clickeado
                //Si es que existe conexion a internet



            }
        });

        sem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(MainActivity2Activity.this,5);
                //Cuando el semaforo verde es clickeado
                //Si es que existe conexion a internet


            }
        });

        sem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(MainActivity2Activity.this,6);
                //Cuando el semaforo verde es clickeado
                //Si es que existe conexion a internet



            }
        });


        sem7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(MainActivity2Activity.this,7);
                //Cuando el semaforo verde es clickeado
                //Si es que existe conexion a internet



            }
        });

        sem8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(MainActivity2Activity.this,8);
                //Cuando el semaforo verde es clickeado
                //Si es que existe conexion a internet



            }
        });

        sem9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(MainActivity2Activity.this,9);
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
            pDialog = new ProgressDialog(MainActivity2Activity.this);
            pDialog.setMessage("Creating User...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
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
                Toast.makeText(MainActivity2Activity.this, file_url, Toast.LENGTH_LONG).show();
            }



        }
    }

    class CreateUser2 extends AsyncTask<String, String, String> {//Metodo que guarda el estado en la base de datos


        @Override
        protected void onPreExecute() {
            //Metodo antes de ser ejecutada la accion


            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity2Activity.this);
            pDialog.setMessage("Creating User...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
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
                Toast.makeText(MainActivity2Activity.this, file_url, Toast.LENGTH_LONG).show();
            }



        }
    }


    class CreateUser3 extends AsyncTask<String, String, String> {//Metodo que guarda el estado en la base de datos


        @Override
        protected void onPreExecute() {
            //Metodo antes de ser ejecutada la accion


            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity2Activity.this);
            pDialog.setMessage("Creating User...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
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
                Toast.makeText(MainActivity2Activity.this, file_url, Toast.LENGTH_LONG).show();
            }



        }
    }



    class LoadAllProducts extends AsyncTask<String, String, String> {

        /**
         * Antes de empezar el background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity2Activity.this);
            pDialog.setMessage("Creating User...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        protected String doInBackground(String... args) {
            List params = new ArrayList();
            JSONObject json = jParser.makeHttpRequest(url_all_empresas, "GET", params);
            try {
                int success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    products = json.getJSONArray(TAG_PRODUCTS);
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);
                        estado=c.getString(TAG_NOMBRE);
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

        }
    }


    private void showPopup(final Activity context, final int opc) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        MainActivity2Activity.this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        double width = displaymetrics.widthPixels;

        Log.v("tamano",String.valueOf(height));
        Log.v("tamano",String.valueOf(width));

        double popupHeight = height*0.52;
        double popupWidth = width*0.625;


        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.popup3);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup3, viewGroup);

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setWidth((int) Math.round(popupHeight));
        popup.setHeight((int) Math.round(popupWidth));
        popup.setFocusable(true);
        popup.setOutsideTouchable(false);

        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(layout, Gravity.CENTER, 0, 0);

        // FUENTE PARA TEXTO EN POPUP Y BOTONES:
        String font_pathPP = "font/HelveticaNeue-Light.ttf"; //ruta de la fuente
        Typeface TPP = Typeface.createFromAsset(MainActivity2Activity.this.getAssets(),font_pathPP);//llamanos a la CLASS TYPEFACE y la definimos
        // con un CREATE desde ASSETS con la ruta STRING

        // Getting a reference to Close button, and close the popup when clicked.
        Button close = (Button) layout.findViewById(R.id.call3);
        Button call = (Button) layout.findViewById(R.id.close3);
        final ImageView popim=(ImageView)layout.findViewById(R.id.pop3im);
        call.setTypeface(TPP);
        close.setTypeface(TPP);


        final TextView tv1 = (TextView) layout.findViewById(R.id.pop3tv1);
        tv1.setTypeface(TPP);



        switch(opc) {
            case 1:
                tv1.setText("Esta seguro que desea cambiar el estado del estacionamiento de ESAN a rojo?");
                popim.setImageResource(R.drawable.rojoprendido);

                break;

            case 2:
                tv1.setText("Esta seguro que desea cambiar el estado del estacionamiento de ESAN a amarillo?");
                popim.setImageResource(R.drawable.amarilloprendido);

                break;

            case 3:
                tv1.setText("Esta seguro que desea cambiar el estado del estacionamiento de ESAN a verde?");
                popim.setImageResource(R.drawable.verdeprendido);

                break;

            case 4:
                tv1.setText("Esta seguro que desea cambiar el estado del estacionamiento de Alonso de la Molina a rojo?");
                popim.setImageResource(R.drawable.rojoprendido);

                break;

            case 5:
                tv1.setText("Esta seguro que desea cambiar el estado del estacionamiento de Alonso de la Molina a amarillo?");
                popim.setImageResource(R.drawable.amarilloprendido);

                break;

            case 6:
                tv1.setText("Esta seguro que desea cambiar el estado del estacionamiento de Alonso de la Molina a verde?");
                popim.setImageResource(R.drawable.verdeprendido);
                break;

            case 7:
                tv1.setText("Esta seguro que desea cambiar el estado del estacionamiento de El Polo a rojo?");
                popim.setImageResource(R.drawable.rojoprendido);

                break;

            case 8:
                tv1.setText("Esta seguro que desea cambiar el estado del estacionamiento de El Polo a amarillo?");
                popim.setImageResource(R.drawable.amarilloprendido);

                break;

            case 9:
                tv1.setText("Esta seguro que desea cambiar el estado del estacionamiento de El Polo a verde?");
                popim.setImageResource(R.drawable.verdeprendido);

                break;
        }



        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();


                switch(opc) {
                    case 1:

                        if (isNetworkAvailable() == true) {
                            sem1.setImageResource(R.drawable.rojoprendido);//Se activa la imagen del rojo predido
                            sem2.setImageResource(R.drawable.amarilloapagado);//Se apaga la luz amarilla
                            sem3.setImageResource(R.drawable.verdeapagado);//Se apaga la luz verde
                            libres = "rojo";
                            new CreateUser().execute();// se ejecuta este metodo, que guarda el estado "rojo" en la base de datos


                            //Si no existe conexion a internet
                        } else {
                            Toast.makeText(MainActivity2Activity.this, "No hay conexio,n a internet", Toast.LENGTH_LONG).show();
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
                            Toast.makeText(MainActivity2Activity.this, "No hay coneccion a internet", Toast.LENGTH_LONG).show();
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
                            Toast.makeText(MainActivity2Activity.this, "No hay coneccion a internet", Toast.LENGTH_LONG).show();
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
                            Toast.makeText(MainActivity2Activity.this, "No hay coneccion a internet", Toast.LENGTH_LONG).show();
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
                            Toast.makeText(MainActivity2Activity.this, "No hay coneccion a internet", Toast.LENGTH_LONG).show();
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
                            Toast.makeText(MainActivity2Activity.this, "No hay coneccion a internet", Toast.LENGTH_LONG).show();
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
                            Toast.makeText(MainActivity2Activity.this, "No hay coneccion a internet", Toast.LENGTH_LONG).show();
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
                            Toast.makeText(MainActivity2Activity.this, "No hay coneccion a internet", Toast.LENGTH_LONG).show();
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
                            Toast.makeText(MainActivity2Activity.this, "No hay coneccion a internet", Toast.LENGTH_LONG).show();
                            Log.d("internet", "no hay");
                        }



                        break;


                }
            }
        });


    }

    private boolean isNetworkAvailable() {
        //Obtiene verdadero o falso segun la verificaion de conexion a internet

        ConnectivityManager cm = (ConnectivityManager) MainActivity2Activity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}
