package xyz.eliothmonroy.splashdemo.reto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import xyz.eliothmonroy.splashdemo.R;
import xyz.eliothmonroy.splashdemo.reto.fragments.BluetoothFragment;
import xyz.eliothmonroy.splashdemo.reto.fragments.CalendarFragment;
import xyz.eliothmonroy.splashdemo.reto.fragments.CamaraFragment;
import xyz.eliothmonroy.splashdemo.reto.fragments.ContactsFragment;
import xyz.eliothmonroy.splashdemo.reto.fragments.InicioFragment;

public class HomeActivity extends AppCompatActivity {

    final String CONSTANTE_CAMARA=Manifest.permission.CAMERA;
    final String CONSTANTE_CALENDARIO=Manifest.permission.READ_CALENDAR;
    final String CONSTANTE_CONTACTOS=Manifest.permission.READ_CONTACTS;
    final String CONSTANTE_BLUETOOTH=Manifest.permission.BLUETOOTH;
    final int VERSION= Build.VERSION.SDK_INT;
    final int REQUEST_CAMARA= 1;
    final int REQUEST_CALENDARIO= 2;
    final int REQUEST_CONTACTOS= 3;
    final int REQUEST_BLUETOOTH= 4;
    Fragment fragment;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().
                replace(R.id.contenedor,new InicioFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    public void mostrarFragment(View view) {
        switch (view.getId()){
            case R.id.botonCamara:
                if(tienePermiso(CONSTANTE_CAMARA)){
                    remplazarFragment(new CamaraFragment());
                }else{
                    solicitarPermiso(CONSTANTE_CAMARA,REQUEST_CAMARA);
                }
                break;
            case R.id.botonCalendario:
                if(tienePermiso(CONSTANTE_CALENDARIO)){
                    remplazarFragment(new CalendarFragment());
                }else{
                    solicitarPermiso(CONSTANTE_CALENDARIO,REQUEST_CALENDARIO);
                }
                break;
            case R.id.botonContactos:
                if(tienePermiso(CONSTANTE_CONTACTOS)){
                    remplazarFragment(new ContactsFragment());
                }else{
                    solicitarPermiso(CONSTANTE_CONTACTOS,REQUEST_CONTACTOS);
                }
                break;
            case R.id.botonBluetooth:
                if(tienePermiso(CONSTANTE_BLUETOOTH)){
                    remplazarFragment(new BluetoothFragment());
                }else{
                    solicitarPermiso(CONSTANTE_BLUETOOTH,REQUEST_BLUETOOTH);
                }
                break;
            default: break;
        }
    }

    private void remplazarFragment(Fragment fragmento) {
        fragment=fragmento;
        fragmentManager.beginTransaction().
                replace(R.id.contenedor,fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();
    }

    private void solicitarPermiso(String permiso, int request_code) {
        ActivityCompat.requestPermissions(this, new String[]{permiso}, request_code);
    }

    private boolean tienePermiso(String permiso) {
        boolean retorno=false;
        if(VERSION> Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(this, permiso) != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permiso)) {
                    Toast.makeText(this,"Es necesario que habilite el permiso: "+permiso,Toast.LENGTH_SHORT).show();
                }
            } else {
                // Permission has already been granted
                retorno= true;
            }
        }else{
            retorno= true;
        }
        return retorno;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMARA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    remplazarFragment(new CamaraFragment());
                } else {
                    Toast.makeText(this, "No nos diste el permiso: "+permissions[0]+ " por lo cual no podemos mostrar el fragment", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case REQUEST_CALENDARIO:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    remplazarFragment(new CalendarFragment());
                } else {
                    Toast.makeText(this, "No nos diste el permiso: "+permissions[0]+ " por lo cual no podemos mostrar el fragment", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case REQUEST_CONTACTOS:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    remplazarFragment(new ContactsFragment());
                } else {
                    Toast.makeText(this, "No nos diste el permiso: "+permissions[0]+ " por lo cual no podemos mostrar el fragment", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case REQUEST_BLUETOOTH:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    remplazarFragment(new BluetoothFragment());
                } else {
                    Toast.makeText(this, "No nos diste el permiso: "+permissions[0]+ " por lo cual no podemos mostrar el fragment", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
}

