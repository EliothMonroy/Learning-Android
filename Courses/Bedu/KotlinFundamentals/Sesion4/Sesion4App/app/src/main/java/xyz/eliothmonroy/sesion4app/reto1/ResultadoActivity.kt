package xyz.eliothmonroy.sesion4app.reto1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_resultado.*
import xyz.eliothmonroy.sesion4app.R

class ResultadoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        NumMayor.text=getString(R.string.placeholder_num_mayor,Resultados.numMayor)
        NumMenor.text=getString(R.string.placeholder_num_menor,Resultados.numMenor)
        SumaDiagonal.text=getString(R.string.placeholder_suma_diagonal,Resultados.sumaDiagonal)
    }

}
