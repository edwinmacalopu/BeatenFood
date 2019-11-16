package ww.utp.beatenfood.fragments;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ww.utp.beatenfood.AddProducts;
import ww.utp.beatenfood.Detalleproducto;
import ww.utp.beatenfood.R;
import ww.utp.beatenfood.adapters.Adaptaprodal;
import ww.utp.beatenfood.models.Producto;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class home extends Fragment implements Adaptaprodal.OnItemClickListener {
    public static final String INT_IDPROD = "idproducto";
    public static final String INT_URL = "fotoproducto";
    public static final String INT_NOM = "nombreproducto";
    public static final String INT_TIPOPRO = "tipoproducto";
    public static final String INT_CANT = "cantidad";
    public static final String INT_UNID = "medidaunidad";
    public static final String INT_FECHINI= "fechainicio";
    public static final String INT_FECHIVENC= "fechacaducidad";
    public static final String INT_CONSUM= "consumido";
    private int mYear, mMonth, mDay;
    JsonObjectRequest jsobj;
    public JSONArray lista;
    String fechactual;
    private String url="http://nf.achkam.com/BeatenFood/Controlador.php";
    List<Producto> listalu;
    RecyclerView lw;
    String iduser;
    TextView fechaescogi;
    Button botontodos,botonfeches;
    String fechaes;
    String enlaces;
    public home() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPref", MODE_PRIVATE);
        //  String name = sharedPreferences.getString(“signature”, "");
        // SharedPreferences prefs = this.getActivity().getSharedPreferences("pref",0);
       iduser=sharedPreferences.getString("iduser","SIN DATOS");
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        lw=view.findViewById(R.id.reciproall);
        lw.setLayoutManager(new LinearLayoutManager(getContext()));
        fechaescogi=view.findViewById(R.id.Textfechaescog);
        fechactual=new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        fechaes=new SimpleDateFormat("yyyyMMdd").format(new Date());
        fechaescogi.setText(fechactual);
        fechaescogi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                escfecha();
            }
        });
        botontodos=view.findViewById(R.id.btntodos);
        botonfeches=view.findViewById(R.id.btnporfecha);
        botonfeches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consulta("porfecha");

            }
        });
        botontodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                consulta("");
            }
        });

        FloatingActionButton floatingActionButton=(FloatingActionButton)view.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                /*Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                                                        Intent intent = new Intent(getActivity(), AddProducts.class);
                                                        startActivity(intent);
                                                    }
                                                }



        );
        consulta("");
        return view;

    }
    public void escfecha(){
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        fechaescogi.setText(String.valueOf(dayOfMonth)+"/"+String.valueOf(monthOfYear+1)+"/"+String.valueOf(year));
                        int month= monthOfYear+1;
                        String fm=""+month;
                        String fd=""+dayOfMonth;
                        if(month<10){
                            fm ="0"+month;
                        }
                        if (dayOfMonth<10){
                            fd="0"+dayOfMonth;
                        }
                        fechaes=(year+""+fm+""+fd);



                    }

                }, mYear, mMonth, mDay);

        datePickerDialog.show();

    }


    private void consulta(String tipo){


        listalu= new ArrayList<>();
        if (tipo.equals("porfecha")){
            /*String fecha=fechaescogi.getText().toString();
            String d=fecha.substring(0,2);
            String m=fecha.substring(3,5);
            String a=fecha.substring(6,10);
             fecha=a+""+m+""+d;*/

            enlaces = url+"?tag=listadoporuserfecha&txtiduser="+iduser+"&txtfechaprod="+fechaes;
        }else{

            enlaces = url+"?tag=listadoporuser&txtiduser="+iduser;
        }

        jsobj=new JsonObjectRequest(Request.Method.GET, enlaces, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            lista = response.getJSONArray("user");
                            Log.w("res", lista.toString());
                            for(int f=0;f<lista.length();f++){
                                JSONObject ah=(JSONObject)lista.get(f);
                                Producto n=new Producto();
                                n.setIdprod(ah.getInt("idprod"));
                                n.setIduser(ah.getInt("iduser"));
                                n.setNombreproducto(ah.getString("nombreprod"));
                                n.setTipoproducto(ah.getString("tipoprod"));
                                n.setCantidad(ah.getInt("cantidad"));
                                n.setMedidaunidad(ah.getString("medidadunid"));
                                n.setFechainicio(ah.getString("fechaini"));
                                n.setFechacaducidad(ah.getString("fechaven"));
                                n.setFotoproducto(ah.getString("fotoprod"));
                                n.setConsumido(ah.getString("consumido"));
                                // n.setId(ah.getInt("coda"));
                                // n.setNombre(ah.getString("noma"));
                              /*  byte[] decodedString = Base64.decode(ah.getString("fotoprod"), Base64.DEFAULT);
                                final Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                                n.setFotoproducto(decodedByte);*/


                                listalu.add(n);
                            }

                            Adaptaprodal dp=new Adaptaprodal(listalu);
                            lw.setAdapter(dp);
                            dp.setOnItemClickListener(home.this);
                            //    Toast.makeText(getApplicationContext(),"mensaje "+respuesta,Toast.LENGTH_SHORT).show();
                        } catch (JSONException ex) {
                            Log.w("PISTAAAAA",ex.getMessage());
                            //  res.setText("respuesta "+ex.getMessage());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.w("erxx",""+error.getMessage().toString());
            }
        });

        RequestQueue r2 = Volley.newRequestQueue(getContext());
        r2.add(jsobj);
    }
    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(getActivity(), Detalleproducto.class);
        Producto clickedItem = listalu.get(position);

        detailIntent.putExtra(INT_URL, clickedItem.getFotoproducto());
        detailIntent.putExtra(INT_NOM, clickedItem.getNombreproducto());
        detailIntent.putExtra(INT_TIPOPRO, clickedItem.getTipoproducto());
        detailIntent.putExtra(INT_CANT, clickedItem.getCantidad());
        detailIntent.putExtra(INT_UNID, clickedItem.getMedidaunidad());
        detailIntent.putExtra(INT_FECHINI, clickedItem.getFechainicio());
        detailIntent.putExtra(INT_FECHIVENC, clickedItem.getFechacaducidad());
        detailIntent.putExtra(INT_IDPROD, clickedItem.getIdprod());
        detailIntent.putExtra(INT_CONSUM, clickedItem.getConsumido());
        // detailIntent.putExtra(EXTRA_LIKES, clickedItem.getLikeCount());

        startActivity(detailIntent);
    }

}
