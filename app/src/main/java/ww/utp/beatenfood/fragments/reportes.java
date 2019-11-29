package ww.utp.beatenfood.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import ww.utp.beatenfood.R;
import ww.utp.beatenfood.adapters.Adaptaprodal;
import ww.utp.beatenfood.models.Producto;

/**
 * A simple {@link Fragment} subclass.
 */
public class reportes extends Fragment {
    Button mes;
    BarChart barChart;
    PieChart pieChart;
    JsonObjectRequest jsobj;
    public JSONArray lista2;
    private String url2="http://nf.achkam.com/BeatenFood/Controlador.php";
    List<Producto> listalu2;
    RecyclerView lw2;
    int consumido,vencido;
    int  carne,fruta,lacteo,vegetal,bebida,otros;

    Spinner spimes;
    String mesconsulta;
    private int mYear, mMonth, mDay;
    public reportes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_reportes, container, false);
        barChart=view.findViewById(R.id.graficopastel);
        mes=view.findViewById(R.id.btnreporte);
        spimes= view.findViewById(R.id.spinner);
        pieChart = view.findViewById(R.id.piechart);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.mes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spimes.setAdapter(adapter);
        spimes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
               mesconsulta=parentView.getItemAtPosition(position).toString();

                switch (mesconsulta){
                    case "Enero":
                        mesconsulta="01";
                        break;
                    case "Febrero":
                        mesconsulta="02";
                        break;
                    case "Marzo":
                        mesconsulta="03";
                        break;
                    case "Abril":
                        mesconsulta="04";
                        break;
                    case "Mayo":
                        mesconsulta="05";
                        break;
                    case "Junio":
                        mesconsulta="06";
                        break;
                    case "Julio":
                        mesconsulta="07";
                        break;
                    case "Agosto":
                        mesconsulta="08";
                        break;
                    case "Septiembre":
                        mesconsulta="09";
                        break;
                    case "Octubre":
                        mesconsulta="10";
                        break;
                    case "Noviembre":
                        mesconsulta="11";
                        break;
                    case "Diciembre":
                        mesconsulta="12";
                        break;

                        default:
                            mesconsulta=" ";


                }
                //Toast.makeText(getContext(),"Bienvenido "+mesconsulta, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        mes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consulta();

            }
        });
        return view;
    }


    private void  creargraficobarras(){
        String cons=String.valueOf(consumido);
        String venc=String.valueOf(vencido);
        //  barChart.animateY(5000);
        float consumid= Float.parseFloat(cons);
        float vencid= Float.parseFloat(venc);
        //  Toast.makeText(getContext(),"Bienvenido "+consumido, Toast.LENGTH_SHORT).show();
        ArrayList  entries = new ArrayList ();
        entries.add(new BarEntry(consumid, 0));
        entries.add(new BarEntry(vencid, 1));

        ArrayList labelss = new ArrayList();
        labelss.add("Consumidos");
        labelss.add("Malogrados");


        BarDataSet bardataset = new BarDataSet(entries, "Estados");
        barChart.animateY(1500);
        BarData data = new BarData(labelss, bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.setData(data);
    }
    private void  creargraficopastel(){
            float carn=Float.parseFloat(String.valueOf(carne));
            float frut=Float.parseFloat(String.valueOf(fruta));
             float lact=Float.parseFloat(String.valueOf(lacteo));
        float vege=Float.parseFloat(String.valueOf(vegetal));
        float bebid=Float.parseFloat(String.valueOf(bebida));
        float otro=Float.parseFloat(String.valueOf(otros));

        ArrayList NoOfEmp = new ArrayList();

        NoOfEmp.add(new Entry(carn, 0));
        NoOfEmp.add(new Entry(frut, 1));
        NoOfEmp.add(new Entry(lact, 2));
        NoOfEmp.add(new Entry(vege, 3));
        NoOfEmp.add(new Entry(bebid, 4));
        NoOfEmp.add(new Entry(otro, 5));



        ArrayList year = new ArrayList();

        year.add("CARNE");
        year.add("FRUTA");
        year.add("LACTEO");
        year.add("VEGETAL");
        year.add("BEBIDA");
        year.add("OTROS");
        PieDataSet dataSet = new PieDataSet(NoOfEmp, "Tipos");
        pieChart.animateXY(1500, 1500);
        PieData data = new PieData(year, dataSet);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.setData(data);


    }
    private void consulta(){
        consumido=0;
        vencido=0;
        listalu2= new ArrayList<>();
        String enlaces = url2+"?tag=reportemes&txtiduser=1&txtfechaprod="+mesconsulta;
        jsobj=new JsonObjectRequest(Request.Method.GET, enlaces, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            lista2 = response.getJSONArray("user");
                            Log.w("res", lista2.toString());
                            for(int f=0;f<lista2.length();f++){
                                JSONObject ah=(JSONObject)lista2.get(f);
                                String cantidad=ah.getString("consumido");
                                String tipoprod=ah.getString("tipoprod");
                                if (cantidad.equals("1")){
                                    //  Toast.makeText(getContext(),"+1", Toast.LENGTH_SHORT).show();
                                    consumido++;
                                }else{
                                    vencido++;
                                }


                                if(tipoprod.equals("CARNE")){
                                    carne++;
                                }else if(tipoprod.equals("FRUTA"))
                                 {
                                    fruta++;
                                }else if(tipoprod.equals("LACTEO")){
                                    lacteo++;
                                }else if(tipoprod.equals("VEGETAL")){
                                    vegetal++;

                                }else if (tipoprod.equals("BEBIDA")){
                                    bebida++;
                                }else if (tipoprod.equals("OTROS")){
                                    otros++;
                                }

                                // n.setId(ah.getInt("coda"));
                                // n.setNombre(ah.getString("noma"));
                              /*  byte[] decodedString = Base64.decode(ah.getString("fotoprod"), Base64.DEFAULT);
                                final Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                                n.setFotoproducto(decodedByte);*/


                                //  listalu2.add(n);
                            }
                            creargraficobarras();
                            creargraficopastel();
                            // Adaptaprodal dp=new Adaptaprodal(listalu2);
                            //lw2.setAdapter(dp);
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
