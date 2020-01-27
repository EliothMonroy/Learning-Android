package xyz.eliothmonroy.sesion4app.reto1

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.forEach
import kotlinx.android.synthetic.main.activity_home.*
import xyz.eliothmonroy.sesion4app.R

class HomeActivity : AppCompatActivity() {

    private lateinit var listaNumeros:List<List<Double>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        createNotificationChannel()

        BotonCalcular.setOnClickListener {
            obtenerResultados()
        }

    }

    private fun obtenerResultados() {
        if(validarCampos()){
            inicializarList()
            Resultados.list=listaNumeros
            Resultados.obtenerNumMayor()
            Resultados.obtenerNumMenor()
            Resultados.obtenerSumaDiagonal()
            mostrarNofificacion()

        }else{
            Toast.makeText(this,"Por favor llene todos los campos", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inicializarList() {
        val fila1= listOf(num1.text.toString().toDouble(), num2.text.toString().toDouble(), num3.text.toString().toDouble())
        val fila2= listOf(num4.text.toString().toDouble(), num5.text.toString().toDouble(), num6.text.toString().toDouble())
        val fila3= listOf(num7.text.toString().toDouble(), num8.text.toString().toDouble(), num9.text.toString().toDouble())
        listaNumeros= listOf(fila1,fila2,fila3)
    }

    private fun validarCampos(): Boolean {

       /* var camposValidos=true

        contenedor.forEach {
            when(it){
                is EditText -> {
                    if (it.text.toString()==""){
                        camposValidos=camposValidos.not()
                    }
                }
            }
        }*/

        //return camposValidos

        return !(num1.text.toString() == "" || num2.text.toString()=="" || num3.text.toString()==""|| num4.text.toString()==""||
                num5.text.toString()==""||num6.text.toString()==""|| num7.text.toString()==""||num8.text.toString()==""||num9.text.toString()=="")
    }


    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

    }

    private fun mostrarNofificacion() {

        val sonido= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notifyIntent = Intent(this, ResultadoActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val notifyPendingIntent = PendingIntent.getActivity(
            this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Listos los resultados")
            .setContentText("Presiona esta notificación para ver los resultados")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setSound(sonido)
            .setContentIntent(notifyPendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(NOTIFICATION_ID, builder.build())
        }
    }

    companion object {
        const val CHANNEL_ID="Matriz"
        const val NOTIFICATION_ID=2
    }

}
