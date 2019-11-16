package ww.utp.beatenfood.interfaces;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ww.utp.beatenfood.R;

public class Login extends AppCompatActivity {
    Button registrar,Ingresar;
    EditText ecorreo,epassword;
    JsonObjectRequest jsobj;
    private String url="http://nf.achkam.com/BeatenFood/Controlador.php";
    String respuesta;
    String salida;
    JSONArray lista;
    LinearLayout layoutlogin;
    ProgressDialog progressDialog;
    @Override
    public void onBackPressed() {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        registrar=findViewById(R.id.btnregistrar);
        Ingresar=findViewById(R.id.btningresar);
        ecorreo=findViewById(R.id.editcorreo);
        epassword=findViewById(R.id.editpassword);
        layoutlogin=findViewById(R.id.layoutlogin);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Login.this,Registrarse.class);
                startActivity(intent);
            }
        });

        Ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(ecorreo.getText().toString())){
                    //Toast.makeText(Login.this,"Ingrese Correo",Toast.LENGTH_LONG).show();

                    Snackbar snackbar = Snackbar.make(layoutlogin,"Ingrese Correo",Snackbar.LENGTH_SHORT);
                    View snackBarView = snackbar.getView();
                    snackBarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    snackbar.show();
                }else if(TextUtils.isEmpty(epassword.getText().toString())) {
                    Snackbar snackbar = Snackbar.make(layoutlogin, "Ingrese Contraseña", Snackbar.LENGTH_SHORT);
                    View snackBarView = snackbar.getView();
                    snackBarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    snackbar.show();
                }else {

                    String enlace = url + "?tag=consulta&txtmail=" + ecorreo.getText().toString() + "&txtpass=" + epassword.getText().toString();
                    //String enlace = url+"?tag=listado";
                    jsobj = new JsonObjectRequest(Request.Method.GET, enlace, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        lista = response.getJSONArray("dato");
                                        Log.w("res", lista.toString());
                                        String cadena = "";
                                        //condicion si existe usuario registrado o no
                                        if (lista.toString().equals("[]")) {
                                            //alerta si usuario no existe
                                            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                            builder.setMessage(R.string.respuesta_error_logeo)
                                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {

                                                        }
                                                    });


                                            AlertDialog alertDialog = builder.create();
                                            alertDialog.show();
                                            // Log.w("res", "NO REGISTRADO");
                                        } else {
                                            //si usuario existe guarda el id y pasa a la pantalla principal
                                            for (int f = 0; f < lista.length(); f++) {
                                                JSONObject ah = (JSONObject) lista.get(f);
                                                String iduser=ah.getString("id");
                                                String nombreuser=ah.getString("name");
                                                String mailuser=ah.getString("mail");
                                                SharedPreferences pref = getSharedPreferences("MyPref", MODE_PRIVATE); // 0 - for private mode
                                                SharedPreferences.Editor editor = pref.edit();
                                                editor.putString("iduser", iduser);
                                                editor.putString("nameuser", nombreuser);
                                                editor.putString("mailuser",mailuser);// Storing string
                                                editor.putString("session","activo");/// Storing string
                                                editor.apply();
                                                //editor.commit();
                                                Toast.makeText(getApplicationContext(),"Bienvenido "+nombreuser, Toast.LENGTH_SHORT).show();
                                           /* cadena+="cuenta:"+ah.getString("name")+"\n";
                                            cadena+="nombre:"+ah.getString("mail")+"\n";
                                            cadena+="saldo:"+ah.getString("saldo")+"\n";
                                            cadena+="==============\n";*/
                                                progressDialog.dismiss();
                                            }
                                            Intent intent = new Intent(Login.this, Navbar.class);
                                            startActivity(intent);

                                        }


                                        // salida.setText(cadena);

                                        //    Toast.makeText(getApplicationContext(),"mensaje "+respuesta,Toast.LENGTH_SHORT).show();
                                    } catch (JSONException ex) {
                                        //  res.setText("respuesta "+ex.getMessage());
                                    }

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Log.w("erxx", "" + error.getMessage().toString());
                        }
                    });

                    RequestQueue r2 = Volley.newRequestQueue(Login.this);
                    r2.add(jsobj);
                    progressDialog = new ProgressDialog(Login.this);
                    progressDialog.setMessage("Cargando....");
                    progressDialog.show();


                }


    }
        });
    }

}
