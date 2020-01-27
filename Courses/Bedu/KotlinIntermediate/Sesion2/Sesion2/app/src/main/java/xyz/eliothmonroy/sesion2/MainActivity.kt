package xyz.eliothmonroy.sesion2

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {

    private val codigo: Int = 1

    private val READ_CONTACTS=Manifest.permission.READ_CONTACTS
    private val READ_CALENDAR=Manifest.permission.READ_CALENDAR
    private val CAMERA=Manifest.permission.CAMERA
    private val RECORD_AUDIO=Manifest.permission.RECORD_AUDIO
    private val READ_CALL_LOG=Manifest.permission.READ_CALL_LOG

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Verificamos si el dispositivo tiene android mayor a 6
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.M){
            //Verificamos si tenemos todos los permisos
            if(ContextCompat.checkSelfPermission(this, READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED||
                ContextCompat.checkSelfPermission(this, READ_CALENDAR)!=PackageManager.PERMISSION_GRANTED||
                ContextCompat.checkSelfPermission(this, CAMERA)!=PackageManager.PERMISSION_GRANTED||
                ContextCompat.checkSelfPermission(this, RECORD_AUDIO)!=PackageManager.PERMISSION_GRANTED||
                ContextCompat.checkSelfPermission(this, READ_CALL_LOG)!=PackageManager.PERMISSION_GRANTED){

                //Ahora verificamos si es necesario mostrar una explicación sobre el porque usamos el permiso
                if(ActivityCompat.shouldShowRequestPermissionRationale(this,READ_CONTACTS)||
                    ActivityCompat.shouldShowRequestPermissionRationale(this,READ_CALENDAR)||
                    ActivityCompat.shouldShowRequestPermissionRationale(this,CAMERA)||
                    ActivityCompat.shouldShowRequestPermissionRationale(this,RECORD_AUDIO)||
                    ActivityCompat.shouldShowRequestPermissionRationale(this,READ_CALL_LOG)){
                    Toast.makeText(this,"Es necesario que habilite los permisos",Toast.LENGTH_LONG).show()
                }

                //Ahora solicitamos los permisos
                ActivityCompat.requestPermissions(this,
                    arrayOf(
                        READ_CONTACTS,
                        READ_CALENDAR,
                        CAMERA,
                        RECORD_AUDIO,
                        READ_CALL_LOG
                    ),
                    codigo
                )
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            codigo->{
                if(grantResults.isNotEmpty()){
                    grantResults.forEach {
                        if(it==PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this,"Si tenemos el permiso: ${permissions[it]}",Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this,"No nos dieron el permiso: ${permissions[it]}",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

}
