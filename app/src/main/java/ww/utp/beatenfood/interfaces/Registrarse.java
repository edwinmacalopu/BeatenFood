package ww.utp.beatenfood.interfaces;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

import ww.utp.beatenfood.R;

public class Registrarse extends AppCompatActivity {
    EditText edname,edmail,edpassword;
    Button btnregistro;
    JsonObjectRequest jsobj;
    private String url="http://nf.achkam.com/BeatenFood/Controlador.php";
    String respuesta;
    public JSONArray lista;
    LinearLayout layoutregistro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edname=findViewById(R.id.regisnombre);
        edmail=findViewById(R.id.regisCorreo);
        edpassword=findViewById(R.id.regispassword);
        btnregistro=findViewById(R.id.btnenviaregistro);
        layoutregistro = findViewById(R.id.layoutregistro);



        btnregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edname.getText().toString())){
                    //Toast.makeText(Login.this,"Ingrese Correo",Toast.LENGTH_LONG).show();

                    Snackbar snackbar = Snackbar.make(layoutregistro,"Ingrese nombre",Snackbar.LENGTH_SHORT);
                    View snackBarView = snackbar.getView();
                    snackBarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    snackbar.show();
                }else if(TextUtils.isEmpty(edmail.getText().toString())) {
                    Snackbar snackbar = Snackbar.make(layoutregistro, "Ingrese correo", Snackbar.LENGTH_SHORT);
                    View snackBarView = snackbar.getView();
                    snackBarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    snackbar.show();
                }else if (TextUtils.isEmpty(edpassword.getText().toString())) {
                    Snackbar snackbar = Snackbar.make(layoutregistro, "Ingrese contraseña", Snackbar.LENGTH_SHORT);
                    View snackBarView = snackbar.getView();
                    snackBarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    snackbar.show();
                }else if (edpassword.getText().toString().trim().length()<7) {
                    Snackbar snackbar = Snackbar.make(layoutregistro, "Ingrese contraseña con mas caracteres", Snackbar.LENGTH_SHORT);
                    View snackBarView = snackbar.getView();
                    snackBarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    snackbar.show();

                }else{
                    String enlace = url + "?tag=adicion&txtnom=" + edname.getText().toString().trim() +
                            "&txtmail=" + edmail.getText().toString() + "&txtpass=" + edpassword.getText().toString();
                    jsobj = new JsonObjectRequest(Request.Method.GET, enlace, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        respuesta = response.getString("estado").toString();

                                        if (respuesta=="error"){
                                            Snackbar snackbar = Snackbar.make(layoutregistro, "error en el registro", Snackbar.LENGTH_SHORT);
                                            View snackBarView = snackbar.getView();
                                            snackBarView.setBackgroundColor(getResources().getColor(R.color.coloraceptado));
                                            snackbar.show();
                                        }else{
                                            Snackbar snackbar = Snackbar.make(layoutregistro, respuesta, Snackbar.LENGTH_SHORT);
                                            View snackBarView = snackbar.getView();
                                            snackBarView.setBackgroundColor(getResources().getColor(R.color.coloraceptado));
                                            snackbar.show();

                                        Intent intent = new Intent(Registrarse.this, Login.class);
                                            startActivity(intent);
                                        }

                                        // limpia();
                                    } catch (JSONException ex) {
                                        Snackbar snackbar = Snackbar.make(layoutregistro, ex.getMessage(), Snackbar.LENGTH_SHORT);
                                        View snackBarView = snackbar.getView();
                                        snackBarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                                        snackbar.show();
                                    }//catch
                                }//onresponse
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Snackbar snackbar = Snackbar.make(layoutregistro, "error en el registro", Snackbar.LENGTH_SHORT);
                            View snackBarView = snackbar.getView();
                            snackBarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                            snackbar.show();
                            Log.w("erxx", "" + error.getMessage().toString());
                        }
                    }//errorlistener
                    );//jsonObjectRequest
                    RequestQueue r2 = Volley.newRequestQueue(Registrarse.this);
                    r2.add(jsobj);
                }

            }
        });


    }



}
