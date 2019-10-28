package ww.utp.beatenfood;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddProducts extends AppCompatActivity {
    Button btnDatePicker,guardaprod;
    private int mYear, mMonth, mDay;
    TextView nameprod,tipoprod,mediuni,cant;
    ImageView imagefoto;
    RadioButton est1,est2,est3,radioButton;
    RadioGroup Grup_est;

    JsonObjectRequest jsobj;
    private String url="http://nf.achkam.com/BeatenFood/Controlador.php";
    String respuesta;
    String fechactual;
    String fechavenci;
    String encodedImage;
    String salida;
    JSONArray lista;
    LinearLayout layoutprodregistro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nameprod=findViewById(R.id.txtnomprod);
        tipoprod=findViewById(R.id.txttipoprod);
        mediuni=findViewById(R.id.txtunidadmed);
        cant=findViewById(R.id.txtpcantidad);
        guardaprod=findViewById(R.id.enviaregitroproducto);
        btnDatePicker=(Button)findViewById(R.id.btnfecha);
        imagefoto=findViewById(R.id.imagenprod);
        layoutprodregistro=findViewById(R.id.layoutprodregistro);
        fechactual = new SimpleDateFormat("yyyyMMdd").format(new Date());
        //System.out.print("FECHAAAAAAAAAA"+fechactual);

        imagefoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        });
        guardaprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tag=adicionprod&txtiduser=1&txtnomprod=manzana&txttipoprod=fruta&txtcantidad=2&txtmedunidad=2kg&txtfechaini=20191026&txtfechafin=20191026&txtfotopro=ddfndldskjfewjfionvdvndgni
                String enlace = url + "?tag=adicionprod&txtiduser=1"+"&txtnomprod=" + nameprod.getText().toString() + "&txttipoprod=" + tipoprod.getText().toString()
                        + "&txtcantidad=" + cant.getText().toString()+ "&txtmedunidad=" + mediuni.getText().toString()+ "&txtfechaini=" + fechactual
                        + "&txtfechafin=" + fechactual+ "&txtfotopro=" + encodedImage;
                jsobj = new JsonObjectRequest(Request.Method.GET, enlace, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    respuesta = response.getString("estado").toString();

                                    if (respuesta=="error"){
                                        Snackbar snackbar = Snackbar.make(layoutprodregistro, "error en el registro", Snackbar.LENGTH_SHORT);
                                        View snackBarView = snackbar.getView();
                                        snackBarView.setBackgroundColor(getResources().getColor(R.color.coloraceptado));
                                        snackbar.show();
                                    }else{
                                        Snackbar snackbar = Snackbar.make(layoutprodregistro, respuesta, Snackbar.LENGTH_SHORT);
                                        View snackBarView = snackbar.getView();
                                        snackBarView.setBackgroundColor(getResources().getColor(R.color.coloraceptado));
                                        snackbar.show();

                                        Intent intent = new Intent(AddProducts.this, Navbar.class);
                                        startActivity(intent);
                                    }

                                    // limpia();
                                } catch (JSONException ex) {
                                    Snackbar snackbar = Snackbar.make(layoutprodregistro, ex.getMessage(), Snackbar.LENGTH_SHORT);
                                    View snackBarView = snackbar.getView();
                                    snackBarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                                    snackbar.show();
                                }//catch
                            }//onresponse
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Snackbar snackbar = Snackbar.make(layoutprodregistro, "error en el registro", Snackbar.LENGTH_SHORT);
                        View snackBarView = snackbar.getView();
                        snackBarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        snackbar.show();
                        Log.w("erxx", "" + error.getMessage().toString());
                    }
                }//errorlistener
                );//jsonObjectRequest
                RequestQueue r2 = Volley.newRequestQueue(AddProducts.this);
                r2.add(jsobj);
            }

        });
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

                        fechavenci=String.valueOf(dayOfMonth+1);
                        System.out.print(fechavenci);
                       // fechesc.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        btnDatePicker.setBackgroundResource(R.drawable.ic_calendarcheck);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


       Bitmap foto=(Bitmap) data.getExtras().get("data");
        //imagefoto.setImageBitmap(bitmap);
        imagefoto.setImageBitmap(foto);
        //Bitmap fotocompr= Bitmap.createScaledBitmap(foto,alto,ancho,true);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        foto.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        byte[] byteArrayImage = baos.toByteArray();

         encodedImage = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
        //System.out.print(encodedImage);
    }
}
