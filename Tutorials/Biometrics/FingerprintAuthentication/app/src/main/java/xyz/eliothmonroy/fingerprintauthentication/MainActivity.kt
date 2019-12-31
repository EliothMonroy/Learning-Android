package xyz.eliothmonroy.fingerprintauthentication

import android.Manifest
import android.app.KeyguardManager
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var cancellationSignal: CancellationSignal

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkBiometricSupport()
        button.setOnClickListener {
            authenticateUser()
        }
    }

    private fun checkBiometricSupport():Boolean {
        val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        val packageManager = packageManager
        if(!keyguardManager.isKeyguardSecure){
            notifyUser("Lock screen security not enabled in settings")
            return false
        }
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.USE_BIOMETRIC)!=PackageManager.PERMISSION_GRANTED){
            notifyUser("Fingerprint permission not granted")
            return false
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)){
            return true
        }
        return true
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun getAuthenticationCallback():BiometricPrompt.AuthenticationCallback{
        return object : BiometricPrompt.AuthenticationCallback() {

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                super.onAuthenticationError(errorCode, errString)
                notifyUser("Authentication error $errString")
            }

            override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
                super.onAuthenticationHelp(helpCode, helpString)
                notifyUser("Authentication help")
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                notifyUser("Authentication failed")
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                super.onAuthenticationSucceeded(result)
                notifyUser("Authentication succeded")
            }

        }
    }

    private fun getCancellationSignalTest():CancellationSignal{
        val cancellationSignal=CancellationSignal()
        cancellationSignal.setOnCancelListener {
            notifyUser("Cancelled via signal")
        }
        return cancellationSignal
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun authenticateUser(){
        val biometricPrompt=BiometricPrompt.Builder(this)
            .setTitle(getString(R.string.title_biometric_prompt))
            .setSubtitle(getString(R.string.subtitle_biometric_prompt))
            .setDescription(getString(R.string.description_biometric_prompt))
            .setNegativeButton(getString(R.string.negative_biometric_prompt),mainExecutor,DialogInterface.OnClickListener { dialog, which ->
                notifyUser("Authentication cancelled")
            }).build()
        biometricPrompt.authenticate(getCancellationSignalTest(),mainExecutor,getAuthenticationCallback())
    }

    private fun notifyUser(message:String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

}
