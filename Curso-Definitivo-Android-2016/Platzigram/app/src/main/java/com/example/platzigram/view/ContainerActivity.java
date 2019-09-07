package com.example.platzigram.view;

import androidx.annotation.NonNull;

import com.example.platzigram.login.view.LoginActivity;
import com.facebook.login.LoginManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.platzigram.R;
import com.example.platzigram.post.view.HomeFragment;
import com.example.platzigram.view.fragment.ProfileFragment;
import com.example.platzigram.view.fragment.SearchFragment;
import com.google.firebase.auth.FirebaseAuth;

public class ContainerActivity extends AppCompatActivity {
    private static final String TAG = "ContainerActivity";
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
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones,menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mSignOut:
                FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
                firebaseAuth.signOut();
                if (LoginManager.getInstance()!=null){
                    LoginManager.getInstance().logOut();
                    Log.w(TAG,"Sesión cerrada exitosamente de Facebook");
                }
                Log.w(TAG,"Sesión cerrada exitosamente");
                Intent intent=new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                this.finish();
                break;
            case R.id.mAbout:
                Toast.makeText(this, "Platzigramm by Elioth Monroy", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
