package com.example.platzigram.view;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.platzigram.R;
import com.example.platzigram.post.view.HomeFragment;
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
