package ww.utp.beatenfood.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import ww.utp.beatenfood.R;
import ww.utp.beatenfood.interfaces.Login;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ajustes extends Fragment {
    Button cerrars;
    TextView nameuser,mailuser;
    LinearLayout layoutajust;
    public ajustes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String nombrewq ="";
        String mailusershare="";
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPref", MODE_PRIVATE);
        //  String name = sharedPreferences.getString(“signature”, "");
        // SharedPreferences prefs = this.getActivity().getSharedPreferences("pref",0);
        nombrewq=sharedPreferences.getString("nameuser","SIN DATOS");
        mailusershare=sharedPreferences.getString("mailuser","SIN DATOS");
        View view= inflater.inflate(R.layout.fragment_ajustes, container, false);
        nameuser=view.findViewById(R.id.txtnombreuser);
        mailuser=view.findViewById(R.id.txtmailuserset);
        cerrars=view.findViewById(R.id.btncerrars);
        layoutajust=view.findViewById(R.id.layoutajustes);
        // Toast.makeText(this.getActivity(),"Bienvenido "+nombrewq, Toast.LENGTH_SHORT).show();
        mailuser.setText(mailusershare);
        nameuser.setText(nombrewq);
        cerrars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getActivity().getSharedPreferences("MyPref", MODE_PRIVATE); // 0 - for private mode
                pref.edit().clear().commit();
                Snackbar snackbar = Snackbar.make(layoutajust, "Sesión Cerrada", Snackbar.LENGTH_SHORT);
                View snackBarView = snackbar.getView();
                snackBarView.setBackgroundColor(getResources().getColor(R.color.coloraceptado));
                snackbar.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        Intent intent = new Intent(getActivity(), Login.class);
                        startActivity(intent);
                        //que hacer despues de 2 segundos
                    }
                }, 2000);
            }
        });

        return view;
    }

}

