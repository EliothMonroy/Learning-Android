package xyz.eliothmonroy.sesion8.reto2

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_home.*
import xyz.eliothmonroy.sesion8.R

class HomeActivity : AppCompatActivity() {

    private val codigo: Int=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.M){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)){
                    Toast.makeText(this,"Es necesario que habilite los permisos",Toast.LENGTH_LONG).show()
                }
                ActivityCompat.requestPermissions(this,
                    arrayOf(
                        Manifest.permission.CAMERA
                    ),
                    codigo
                )
            }
        }

        fab.setOnClickListener {
            val intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
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
