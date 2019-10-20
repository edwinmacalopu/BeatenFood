package ww.utp.beatenfood.fragments;


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

 TextView nameuser;
    public ajustes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String nombrewq ="";
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
      //  String name = sharedPreferences.getString(“signature”, "");
       // SharedPreferences prefs = this.getActivity().getSharedPreferences("pref",0);
        nombrewq=sharedPreferences.getString("nameuser","");
        View view= inflater.inflate(R.layout.fragment_ajustes, container, false);
        nameuser=view.findViewById(R.id.txtnombreuser);
        Toast.makeText(this.getActivity(),"Bienvenido "+nombrewq, Toast.LENGTH_SHORT).show();

        nameuser.setText(nombrewq);

        return view;
    }

}
