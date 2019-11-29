package ww.utp.beatenfood;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import ww.utp.beatenfood.fragments.home;
import ww.utp.beatenfood.interfaces.Navbar;
import ww.utp.beatenfood.models.Producto;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ww.utp.beatenfood.fragments.home.INT_CANT;
import static ww.utp.beatenfood.fragments.home.INT_CONSUM;
import static ww.utp.beatenfood.fragments.home.INT_FECHINI;
import static ww.utp.beatenfood.fragments.home.INT_FECHIVENC;
import static ww.utp.beatenfood.fragments.home.INT_IDPROD;
import static ww.utp.beatenfood.fragments.home.INT_NOM;
import static ww.utp.beatenfood.fragments.home.INT_TIPOPRO;
import static ww.utp.beatenfood.fragments.home.INT_UNID;
import static ww.utp.beatenfood.fragments.home.INT_URL;

public class Detalleproducto extends AppCompatActivity {
    TextView name,tipo,cantid,unida,fechai,fechav;
    Switch aSwitch;
    Integer iddelproducto;
    String consumido;
    String nameprod;
    Button buttondetalle,recetas;
    Boolean chekactivo;
    JsonObjectRequest jsobj;
    public JSONArray lista;
    private String url="http://nf.achkam.com/BeatenFood/Controlador.php";
    LinearLayout linerlayoutdetalle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalleproducto);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent=getIntent();
        String imageurl=intent.getStringExtra(INT_URL);
         nameprod=intent.getStringExtra(INT_NOM);
        String tipoprod=intent.getStringExtra(INT_TIPOPRO);
        int canti=intent.getIntExtra(INT_CANT,0);
        String fechain=intent.getStringExtra(INT_FECHINI);
        String unid=intent.getStringExtra(INT_UNID);
        String fechavenc=intent.getStringExtra(INT_FECHIVENC);
        iddelproducto=intent.getIntExtra(INT_IDPROD,0);
        consumido=intent.getStringExtra(INT_CONSUM);
        linerlayoutdetalle=findViewById(R.id.layoutdetallepro);
        aSwitch=findViewById(R.id.switchcosumido);
        buttondetalle=findViewById(R.id.btndetalle);
        ImageView imageView=findViewById(R.id.imagendetalle);
        name=findViewById(R.id.detallenombre);
        tipo=findViewById(R.id.detalleTipo);
        cantid=findViewById(R.id.detalleCantidad);
        fechai=findViewById(R.id.detalleFechacomp);
        fechav=findViewById(R.id.detalleFechacaduc);
        unida=findViewById(R.id.detalleUnidad);
        name.setText(": "+nameprod);
        tipo.setText(": "+tipoprod);
        cantid.setText(": "+String.valueOf(canti));
        //Toast.makeText(this,"Bienvenido "+canti, Toast.LENGTH_SHORT).show();
        fechai.setText(": "+fechain);
        fechav.setText(": "+fechavenc);
        unida.setText(": "+unid);

        recetas=findViewById(R.id.btnrecetas);

        recetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detalleproducto.this, recetas.class);
                intent.putExtra("BUSCAR",nameprod );
                startActivity(intent);
            }
        });




        chekactivo=false;
        if(consumido.equals("1")){
            aSwitch.setChecked(true);
            aSwitch.setClickable(false);

        }



        Picasso.get().load(imageurl).fit().centerInside().into(imageView);
        buttondetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chekactivo){
                    sendUpdate();
                }else{
                    Intent intent = new Intent(Detalleproducto.this, Navbar.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void swichevento(View view) {
        if (view.getId()==R.id.switchcosumido){
            if(aSwitch.isChecked()){
                //buttondetalle.setBackgroundResource(R.color.coloraceptado);
                buttondetalle.setBackgroundResource(R.drawable.roundedbuttonactualizar);
                buttondetalle.setText("Actualizar");
                chekactivo=true;
            }else{
                buttondetalle.setBackgroundResource(R.drawable.roundedbutton);
                buttondetalle.setText("Cerrar");
                chekactivo=false;
            }
        }
    }


    private void sendUpdate() {

        String URL = url+"?tag=actualizarprod&txtidprod="+iddelproducto;
        //Log.w("SEENVIOOOOOOO", URL);
        jsobj= new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Snackbar snackbar = Snackbar.make(linerlayoutdetalle, "Producto Actualizado", Snackbar.LENGTH_SHORT);
                View snackBarView = snackbar.getView();
                snackBarView.setBackgroundColor(getResources().getColor(R.color.coloraceptado));
                snackbar.show();
                //Toast.makeText(getApplicationContext(), "Response:  " + response.toString(), Toast.LENGTH_SHORT).show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        Intent intent = new Intent(Detalleproducto.this, Navbar.class);
                        startActivity(intent);
                        //que hacer despues de 2 segundos
                    }
                }, 2000);
                //Toast.makeText(getApplicationContext(), "Response:  " + response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar snackbar = Snackbar.make(linerlayoutdetalle, "Error en Actualizar", Snackbar.LENGTH_SHORT);
                View snackBarView = snackbar.getView();
                snackBarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                snackbar.show();
                //Toast.makeText(getApplicationContext(), "Response:  " + error.toString(), Toast.LENGTH_SHORT).show();
                //onBackPressed();

            }
        }) {

        };
        RequestQueue r2 = Volley.newRequestQueue(Detalleproducto.this);
        r2.add(jsobj);

        // Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_LONG).show();

    }
}
