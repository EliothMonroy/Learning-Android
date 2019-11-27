package xyz.eliothmonroy.sesion2.reto2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*
import xyz.eliothmonroy.sesion2.R
import xyz.eliothmonroy.sesion2.reto2.data.Alumno
import xyz.eliothmonroy.sesion2.reto2.data.Grupo
import xyz.eliothmonroy.sesion2.reto2.data.Materia
import xyz.eliothmonroy.sesion2.showMessage

class HomeActivity : AppCompatActivity() {

    private lateinit var materia1:Materia
    private lateinit var materia2:Materia
    private lateinit var materia3:Materia
    private lateinit var materia4:Materia
    private lateinit var materia5:Materia
    private lateinit var materia6:Materia

    private lateinit var alumno1:Alumno
    private lateinit var alumno2:Alumno
    private lateinit var alumno3:Alumno
    private lateinit var alumno4:Alumno
    private lateinit var grupo1:Grupo
    private lateinit var grupo2:Grupo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //Creamos objetos
        inicializarDatos()

        radioGroupGrupo.check(radioButtonGrupo1.id)

        mostrarGrupo1()

        radioGroupGrupo.setOnCheckedChangeListener { _, checkedId -> if(radioButtonGrupo1.id==checkedId) mostrarGrupo1() else mostrarGrupo2()}

    }

    private fun mostrarGrupo2() {
        //showMessage(this, "Grupo2")

        headerMateria1.text=alumno1.materias[0].nombre
        headerMateria2.text=alumno1.materias[1].nombre
        headerMateria3.text=alumno1.materias[2].nombre

        alumno1Nombre.text=alumno3.nombre
        alumno2Nombre.text=alumno4.nombre
        alumno1Materia1.text=alumno3.materias[0].cal.toString()
        alumno1Materia2.text=alumno3.materias[1].cal.toString()
        alumno1Materia3.text=alumno3.materias[2].cal.toString()
        alumno2Materia1.text=alumno4.materias[0].cal.toString()
        alumno2Materia2.text=alumno4.materias[1].cal.toString()
        alumno2Materia3.text=alumno4.materias[2].cal.toString()

        alumno1Promedio.text=calcularPromedio(alumno3).toString()
        alumno2Promedio.text=calcularPromedio(alumno4).toString()
    }

    private fun mostrarGrupo1() {
        //showMessage(this, "Grupo1")

        headerMateria1.text=alumno3.materias[0].nombre
        headerMateria2.text=alumno3.materias[1].nombre
        headerMateria3.text=alumno3.materias[2].nombre

        alumno1Nombre.text=alumno1.nombre
        alumno2Nombre.text=alumno2.nombre
        alumno1Materia1.text=alumno1.materias[0].cal.toString()
        alumno1Materia2.text=alumno1.materias[1].cal.toString()
        alumno1Materia3.text=alumno1.materias[2].cal.toString()
        alumno2Materia1.text=alumno2.materias[0].cal.toString()
        alumno2Materia2.text=alumno2.materias[1].cal.toString()
        alumno2Materia3.text=alumno2.materias[2].cal.toString()

        alumno1Promedio.text=calcularPromedio(alumno1).toString()
        alumno2Promedio.text=calcularPromedio(alumno2).toString()

    }

    private fun calcularPromedio(alumno: Alumno): Float {
        var promedio=0.0f
        alumno.materias.forEach {
            promedio+=it.cal
        }
        promedio/=alumno.materias.size

        return promedio
    }

    private fun inicializarDatos() {
        materia1=Materia("Español",6.0f)
        materia2=Materia("Inglés",7.0f)
        materia3=Materia("Matemáticas",8.0f)
        materia4=Materia("Biologia",9.0f)
        materia5=Materia("Civismo",6.0f)
        materia6=Materia("Geografía",7.0f)
        alumno1= Alumno("Elioth", listOf(materia1,materia2,materia3))
        alumno2= Alumno("Julio", listOf(materia1,materia2,materia3))
        alumno3= Alumno("Ximena", listOf(materia4,materia5,materia6))
        alumno4= Alumno("Samuel", listOf(materia4,materia5,materia6))

        grupo1= Grupo("Grupo 1", listOf(alumno1,alumno2))
        grupo2= Grupo("Grupo 2", listOf(alumno3,alumno4))
    }
}
