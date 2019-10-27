package ww.utp.beatenfood.fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ww.utp.beatenfood.AddProducts;
import ww.utp.beatenfood.R;
import ww.utp.beatenfood.adapters.Adaptaprodal;
import ww.utp.beatenfood.models.Producto;

/**
 * A simple {@link Fragment} subclass.
 */
public class home extends Fragment {

    JsonObjectRequest jsobj;
    public JSONArray lista;
    private String url="http://nf.achkam.com/BeatenFood/Controlador.php";
    List<Producto> listalu;
    RecyclerView lw;

    public home() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        lw=view.findViewById(R.id.reciproall);
        lw.setLayoutManager(new LinearLayoutManager(getContext()));


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
        consulta();
        return view;

    }
    private void consulta(){
        listalu= new ArrayList<>();
        String enlaces = url+"?tag=listadoporuser&txtiduser=1";
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
                                n.setIduser(ah.getInt("iduser"));
                                n.setNombreproducto(ah.getString("nombreprod"));
                                n.setTipoproducto(ah.getString("tipoprod"));
                                n.setCantidad(ah.getInt("cantidad"));
                                n.setMedidaunidad(ah.getString("medidadunid"));
                                //  n.setFechainicio(ah.getString("fechaini"));
                                //  n.setFechacaducidad(ah.getString("fechaven"));

                                // n.setId(ah.getInt("coda"));
                                // n.setNombre(ah.getString("noma"));
                               /* byte[] decodedString = Base64.decode(ah.getString("foto"), Base64.DEFAULT);
                                final Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                                n.setFoto(decodedByte);*/


                                listalu.add(n);
                            }

                            Adaptaprodal dp=new Adaptaprodal(listalu);
                            lw.setAdapter(dp);
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

}
