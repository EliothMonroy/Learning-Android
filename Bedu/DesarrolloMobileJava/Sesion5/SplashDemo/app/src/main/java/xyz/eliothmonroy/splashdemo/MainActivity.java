package xyz.eliothmonroy.splashdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private static final int MY_PERMISSIONS_REQUEST_READ_CALENDAR = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int version= Build.VERSION.SDK_INT;
        Log.d("MainActivity",""+version);
        if(version> Build.VERSION_CODES.M){
            Toast.makeText(this,"Es necesario pedir permisos",Toast.LENGTH_LONG).show();

            // Here, thisActivity is the current activity
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED
                                                    ||ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED ) {

                // Permission is not granted
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)
                    ||ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CALENDAR)) {
                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                    Toast.makeText(this,"Es necesario que habilite los permisos",Toast.LENGTH_LONG).show();
                } //else {
                    // No explanation needed; request the permission

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                //}
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS,Manifest.permission.READ_CALENDAR}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            } else {
                // Permission has already been granted
                Toast.makeText(this,"Permiso ya concedido",Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(this,"No es necesario pedir permisos",Toast.LENGTH_LONG).show();
        }

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        UnoFragment unoFragment=new UnoFragment();
        fragmentTransaction.add(R.id.contenedor,unoFragment,"f1");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if(grantResults.length>0){
                    for(int i=0;i<grantResults.length;i++){
                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                            // permission was granted, yay! Do the
                            // contacts-related task you+permissions[i] need to do.
                            Toast.makeText(this,"Si nos dieron el permiso de:" +permissions[i],Toast.LENGTH_SHORT).show();
                        } else {
                            // permission denied, boo! Disable the
                            // functionality that depends on this permission.
                            Toast.makeText(this,"Nos negaron el permiso contactos :("+permissions[i],Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    Toast.makeText(this,"No nos dieron ningún permiso",Toast.LENGTH_SHORT).show();
                }


            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }


    public void MostrarFragment2(View view) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        DosFragment dosFragment=new DosFragment();
        fragmentTransaction.add(R.id.contenedor,dosFragment,"f2");
        fragmentTransaction.addToBackStack("f1");
        fragmentTransaction.commit();
    }
}
