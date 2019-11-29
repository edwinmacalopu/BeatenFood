package ww.utp.beatenfood;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import ww.utp.beatenfood.interfaces.Navbar;
import ww.utp.beatenfood.notificaciones.NotificationPublisher;

import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddProducts extends AppCompatActivity {

    Button btnDatePicker, guardaprod,btnfechahora ;
    private int mYear, mMonth, mDay;
    String fechanotifi,horanotific,fechavencinotif;
    private int mYear2, mMonth2, mDay2, mHour2, mMinute2;
    private int ano,mes,dia,horas,minutos;
    TextView nameprod, tipoprod, mediuni, cant,fechavenci;
    ImageView imagefoto;
    String utf8;
    JsonObjectRequest jsobj;
    private String url = "http://nf.achkam.com/BeatenFood/Controlador.php";
    String respuesta;
    String fechactual;
    String tipoproducto;
    String Unidades;
    String encodedImage;
    String salida;
    public  JSONArray lista;
    LinearLayout layoutprodregistro;
Spinner sptipo,sunidad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nameprod = findViewById(R.id.txtnomprod);
        //tipoprod = findViewById(R.id.txttipoprod);
        sptipo= findViewById(R.id.spinertipo);
        btnfechahora=findViewById(R.id.btnescogefechahora);
        sunidad=findViewById(R.id.spineunidades);
        //mediuni = findViewById(R.id.txtunidadmed);
        cant = findViewById(R.id.txtpcantidad);

        guardaprod = findViewById(R.id.enviaregitroproducto);
        btnDatePicker = (Button) findViewById(R.id.btnfecha);
        imagefoto = findViewById(R.id.imagenprod);
        fechavenci=findViewById(R.id.capturafechavenc);
        layoutprodregistro = findViewById(R.id.layoutprodregistro);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplication(),
                R.array.tipoali, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sptipo.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getApplication(),
                R.array.unidades, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sunidad.setAdapter(adapter1);
        sunidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                              @Override
                                              public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                  Unidades= adapterView.getItemAtPosition(i).toString();
                                              }

                                              @Override
                                              public void onNothingSelected(AdapterView<?> adapterView) {

                                              }
                                          });
                sptipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        tipoproducto = adapterView.getItemAtPosition(i).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
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
                //guardanotificacion();
                sendWorkPostRequest();
            }

        });

        btnfechahora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickerdate();
               // pickertimer();
            }
        });





    }



    private void sendWorkPostRequest() {

        SharedPreferences sharedPreferences =getApplication().getSharedPreferences("MyPref", MODE_PRIVATE);
        //  String name = sharedPreferences.getString(“signature”, "");
        // SharedPreferences prefs = this.getActivity().getSharedPreferences("pref",0);
        String iduser=sharedPreferences.getString("iduser","SIN DATOS");

        try {
            String URL = "http://nf.achkam.com/BeatenFood/insertaimage.php";
            JSONObject jsonBody = new JSONObject();

            jsonBody.put("txtiduser", iduser);
            jsonBody.put("txtnomprod", nameprod.getText().toString());
            jsonBody.put("txttipoprod", tipoproducto);
            jsonBody.put("txtcantidad", cant.getText().toString());
            jsonBody.put("txtmedunidad", Unidades);
            jsonBody.put("txtfechaini", fechactual);
            jsonBody.put("txtfechafin", fechavenci.getText().toString());
            jsonBody.put("txtfotopro", encodedImage);

            jsobj= new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    lista = response.optJSONArray("dato");
                    guardanotificacion();
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
        final Calendar es = Calendar.getInstance();
        mYear = es.get(Calendar.YEAR);
        mMonth = es.get(Calendar.MONTH);
        mDay = es.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        int month= monthOfYear+1;
                        String fm=""+month;
                        String fd=""+dayOfMonth;
                        if(month<10){
                            fm ="0"+month;
                        }
                        if (dayOfMonth<10){
                            fd="0"+dayOfMonth;
                        }
                        fechavenci.setText(String.valueOf(year)+fm+fd);
                        fechavencinotif=String.valueOf(year)+"/"+fm+"/"+fd;
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

    public void estadocheck(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBox:
                if (checked){
                  btnfechahora.setVisibility(view.VISIBLE);

                }               // Put some meat on the sandwich
            else{
                    btnfechahora.setVisibility(view.INVISIBLE);
                    Log.w("res",  fechanotifi+" "+horanotific);
                }
                // Remove the meat


                break;
            // TODO: Veggie sandwich
        }
    }

    private void pickertimer(){
        final Calendar c = Calendar.getInstance();
        mHour2 = c.get(Calendar.HOUR_OF_DAY);
        mMinute2 = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                                c.set(hourOfDay,minute);
                               horas=hourOfDay;
                               minutos=minute;
                                horanotific=(String.valueOf(hourOfDay)+" "+String.valueOf(minute));

                        btnfechahora.setText(fechanotifi+" "+horanotific);
                      //  txtTime.setText(hourOfDay + ":" + minute);
                    }
                }, mHour2, mMinute2, false);
        timePickerDialog.show();
    }

    private void pickerdate(){
        final Calendar d = Calendar.getInstance();
        mYear2 = d.get(Calendar.YEAR);
        mMonth2 = d.get(Calendar.MONTH);
        mDay2 = d.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                                d.set(year,monthOfYear,dayOfMonth);
                                ano=year;
                                mes=monthOfYear;
                                dia=dayOfMonth;
                        String myFormat = "MM/dd/yyyy"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                       // txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        fechanotifi=(sdf.format(d.getTime()));

                            pickertimer();
                    }
                }, mYear2, mMonth2, mDay2);
        datePickerDialog.show();
    }

    private void guardanotificacion(){
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        String hora = sdf.format(new Date());
        int idnotif=Integer.parseInt(hora);

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,ano);
        c.set(Calendar.MONTH,mes);
        c.set(Calendar.DAY_OF_MONTH,dia);
        c.set(Calendar.HOUR_OF_DAY, horas);
        c.set(Calendar.MINUTE, minutos);
        c.set(Calendar.SECOND, 0);

        Intent intent = new Intent(getApplicationContext(), NotificationPublisher.class);
        intent.putExtra("TITULO", nameprod.getText().toString());
        intent.putExtra("DETALLE", "Tu producto vence el : "+fechavencinotif);
        PendingIntent p1 = PendingIntent.getBroadcast(getApplicationContext(), idnotif, intent, 0);
        AlarmManager a = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //a.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+1*1000, p1);
        a.set(AlarmManager.RTC_WAKEUP,  c.getTimeInMillis() , p1);
    }

}

