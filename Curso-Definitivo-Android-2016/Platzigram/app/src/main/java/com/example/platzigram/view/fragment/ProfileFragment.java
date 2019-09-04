package com.example.platzigram.view.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.platzigram.R;
import com.example.platzigram.adapter.PictureAdapterRecyclerView;
import com.example.platzigram.model.Picture;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        showToolbar("",false,view);

        RecyclerView picturesRecycler = view.findViewById(R.id.pictureProfileRecycler);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        picturesRecycler.setLayoutManager(linearLayoutManager);//Asignamos nuestro layout
        PictureAdapterRecyclerView pictureAdapterRecyclerView=new PictureAdapterRecyclerView(buildPictures(), R.layout.cardview_picture,getActivity());

        picturesRecycler.setAdapter(pictureAdapterRecyclerView);

        return view;
    }

    public ArrayList<Picture> buildPictures(){
        ArrayList<Picture> pictures=new ArrayList<>();
        pictures.add(new Picture("https://i.ibb.co/rkSywDf/test1.jpg","Elioth Monroy","4 días","1 Me gusta"));
        pictures.add(new Picture("https://i.ibb.co/6NMDz53/test2.jpg","Elioth Monroy","4 días","2 Me gusta"));
        pictures.add(new Picture("https://i.ibb.co/F8CZkDc/test3.jpg","Elioth Monroy","4 días","3 Me gusta"));
        pictures.add(new Picture("https://i.ibb.co/ThZYPFc/test4.jpg","Elioth Monroy","4 días","4 Me gusta"));
        pictures.add(new Picture("https://i.ibb.co/r2SyY7H/test5.jpg","Elioth Monroy","4 días","5 Me gusta"));
        pictures.add(new Picture("https://i.ibb.co/YBg2kzf/test6.jpg","Elioth Monroy","4 días","6 Me gusta"));
        pictures.add(new Picture("https://i.ibb.co/HN9p4xS/test7.jpg","Elioth Monroy","4 días","7 Me gusta"));
        pictures.add(new Picture("https://i.ibb.co/cYYR6Bt/test8.jpg","Elioth Monroy","4 días","8 Me gusta"));
        pictures.add(new Picture("https://i.ibb.co/Fb4kDGV/test9.jpg","Elioth Monroy","4 días","9 Me gusta"));
        pictures.add(new Picture("https://i.ibb.co/YQ0KDqJ/test10.jpg","Elioth Monroy","4 días","10 Me gusta"));

       /*
        https://i.ibb.co/rkSywDf/test1.jpg
        https://i.ibb.co/6NMDz53/test2.jpg
        https://i.ibb.co/F8CZkDc/test3.jpg
        https://i.ibb.co/ThZYPFc/test4.jpg
        https://i.ibb.co/r2SyY7H/test5.jpg
        https://i.ibb.co/YBg2kzf/test6.jpg
        https://i.ibb.co/HN9p4xS/test7.jpg
        https://i.ibb.co/cYYR6Bt/test8.jpg
        https://i.ibb.co/Fb4kDGV/test9.jpg
        https://i.ibb.co/YQ0KDqJ/test10.jpg
        */

        return pictures;

    }

    public void showToolbar(String title, boolean upButton, View view){
        Toolbar toolbar=view.findViewById(R.id.toolbarProfile);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

}
