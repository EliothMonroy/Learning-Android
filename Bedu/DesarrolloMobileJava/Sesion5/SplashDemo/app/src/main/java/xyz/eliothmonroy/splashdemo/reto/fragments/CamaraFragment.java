package xyz.eliothmonroy.splashdemo.reto.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.eliothmonroy.splashdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CamaraFragment extends Fragment {


    public CamaraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_camara, container, false);
    }

}
