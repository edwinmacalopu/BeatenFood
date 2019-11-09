package ww.utp.beatenfood.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ww.utp.beatenfood.R;
import ww.utp.beatenfood.adapters.Adaptaprodal;
import ww.utp.beatenfood.models.Producto;

/**
 * A simple {@link Fragment} subclass.
 */
public class vencidos extends Fragment {

    JsonObjectRequest jsobj;
    public JSONArray lista2;
    private String url2="http://nf.achkam.com/BeatenFood/Controlador.php";
    List<Producto> listalu2;
    RecyclerView lw2;
    public vencidos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_vencidos, container, false);

        lw2=view.findViewById(R.id.reciprovenci);
        lw2.setLayoutManager(new LinearLayoutManager(getContext()));
        consulta();
        return view;
    }

    private void consulta(){
        listalu2= new ArrayList<>();
        String enlaces = url2+"?tag=listadoporuservencidos&txtiduser=1";
        jsobj=new JsonObjectRequest(Request.Method.GET, enlaces, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            lista2 = response.getJSONArray("user");
                            Log.w("res", lista2.toString());
                            for(int f=0;f<lista2.length();f++){
                                JSONObject ah=(JSONObject)lista2.get(f);
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


                                listalu2.add(n);
                            }

                            Adaptaprodal dp=new Adaptaprodal(listalu2);
                            lw2.setAdapter(dp);
                            //dp.setOnItemClickListener(home.this);
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
