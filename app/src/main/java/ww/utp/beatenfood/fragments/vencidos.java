package ww.utp.beatenfood.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ww.utp.beatenfood.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class vencidos extends Fragment {


    public vencidos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vencidos, container, false);
    }

}
