package ww.utp.beatenfood.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import ww.utp.beatenfood.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ajustes extends Fragment {

 TextView nameuser,mailuser;
    public ajustes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String nombrewq ="";
        String mailusershare="";
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
      //  String name = sharedPreferences.getString(“signature”, "");
       // SharedPreferences prefs = this.getActivity().getSharedPreferences("pref",0);
        nombrewq=sharedPreferences.getString("nameuser","SIN DATOS");
        mailusershare=sharedPreferences.getString("mailuser","SIN DATOS");
        View view= inflater.inflate(R.layout.fragment_ajustes, container, false);
        nameuser=view.findViewById(R.id.txtnombreuser);
        mailuser=view.findViewById(R.id.txtmailuserset);
       // Toast.makeText(this.getActivity(),"Bienvenido "+nombrewq, Toast.LENGTH_SHORT).show();
        mailuser.setText(mailusershare);
        nameuser.setText(nombrewq);

        return view;
    }

}
