package com.example.platzigram.view;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.platzigram.R;
import com.example.platzigram.view.fragment.HomeFragment;
import com.example.platzigram.view.fragment.ProfileFragment;
import com.example.platzigram.view.fragment.SearchFragment;

public class ContainerActivity extends AppCompatActivity {
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottombar);
        bottomNavigationView.setSelectedItemId(R.id.home);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new HomeFragment())
                    .commit();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(

                new BottomNavigationView.OnNavigationItemSelectedListener(){

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.home:
                                fragment=new HomeFragment();
                                break;

                            case R.id.profile:
                                fragment=new ProfileFragment();
                                break;

                            case R.id.search:
                                fragment=new SearchFragment();
                                break;

                        }

                        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .disallowAddToBackStack().commit();

                        return true;
                    }
                }
        );
    }
}
