package ww.utp.beatenfood;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import ww.utp.beatenfood.interfaces.Login;
import ww.utp.beatenfood.interfaces.Navbar;
import ww.utp.beatenfood.interfaces.Registrarse;

import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddProducts extends AppCompatActivity {
    Button btnDatePicker, guardaprod;
    private int mYear, mMonth, mDay;
    TextView nameprod, tipoprod, mediuni, cant,fechavenci;
    ImageView imagefoto;
    String utf8;
    JsonObjectRequest jsobj;
    private String url = "http://nf.achkam.com/BeatenFood/Controlador.php";
    String respuesta;
    String fechactual;

    String encodedImage;
    String salida;
    public  JSONArray lista;
    LinearLayout layoutprodregistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nameprod = findViewById(R.id.txtnomprod);
        tipoprod = findViewById(R.id.txttipoprod);
        mediuni = findViewById(R.id.txtunidadmed);
        cant = findViewById(R.id.txtpcantidad);
        guardaprod = findViewById(R.id.enviaregitroproducto);
        btnDatePicker = (Button) findViewById(R.id.btnfecha);
        imagefoto = findViewById(R.id.imagenprod);
        fechavenci=findViewById(R.id.capturafechavenc);
        layoutprodregistro = findViewById(R.id.layoutprodregistro);
        fechactual = new SimpleDateFormat("yyyyMMdd").format(new Date());
        //System.out.print("FECHAAAAAAAAAA"+fechactual);

        imagefoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });
        guardaprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendWorkPostRequest();
            }

        });
    }


    private void sendWorkPostRequest() {

        try {
            String URL = "http://nf.achkam.com/BeatenFood/insertaimage.php";
            JSONObject jsonBody = new JSONObject();

            jsonBody.put("txtiduser", "1");
            jsonBody.put("txtnomprod", nameprod.getText().toString());
            jsonBody.put("txttipoprod", tipoprod.getText().toString());
            jsonBody.put("txtcantidad", cant.getText().toString());
            jsonBody.put("txtmedunidad", mediuni.getText().toString());
            jsonBody.put("txtfechaini", fechactual);
            jsonBody.put("txtfechafin", fechavenci.getText().toString());
            jsonBody.put("txtfotopro", encodedImage);

            jsobj= new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    lista = response.optJSONArray("dato");
                    Snackbar snackbar = Snackbar.make(layoutprodregistro, "Producto Agregado", Snackbar.LENGTH_SHORT);
                    View snackBarView = snackbar.getView();
                    snackBarView.setBackgroundColor(getResources().getColor(R.color.coloraceptado));
                    snackbar.show();
                    //Toast.makeText(getApplicationContext(), "Response:  " + response.toString(), Toast.LENGTH_SHORT).show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            Intent intent = new Intent(AddProducts.this, Navbar.class);
                            startActivity(intent);
                            //que hacer despues de 2 segundos
                        }
                    }, 2000);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Response:  " + error.toString(), Toast.LENGTH_SHORT).show();
                    //onBackPressed();

                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    final Map<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "text/html; charset=utf-8");
                    headers.put("Access-Control-Allow-Methods", "POST");//put your token here
                    return headers;
                }
            };
            RequestQueue r2 = Volley.newRequestQueue(AddProducts.this);
            r2.add(jsobj);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_LONG).show();

    }



    public void escfecha(View v){
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        fechavenci.setText(String.valueOf(year)+String.valueOf(monthOfYear+1)+String.valueOf(dayOfMonth));
                        //fechavenci =(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        // fechesc.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        btnDatePicker.setBackgroundResource(R.drawable.ic_calendarcheck);

                    }

                }, mYear, mMonth, mDay);
        System.out.print(fechavenci);
        datePickerDialog.show();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        Bitmap foto=(Bitmap) data.getExtras().get("data");
        imagefoto.setImageBitmap(foto);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        foto.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] byteArrayImage = baos.toByteArray();
        encodedImage = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);



        //System.out.print("HOLAAAAAAAAAAAAAAAA"+hola);

        //System.out.print(encodedImage);
    }
}

