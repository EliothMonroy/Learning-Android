package xyz.eliothmonroy.platzistore

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

//Toast
fun Activity.toast(mensaje:String, duracion:Int=Toast.LENGTH_SHORT){
    Toast.makeText(this,mensaje,duracion).show()
}
//Inflar vistas
fun ViewGroup.inflate(layoutId:Int):View{
    //LayoutInflater.from(viewGroup.getContext()).inflate(resource,viewGroup,false);
    return LayoutInflater.from(context).inflate(layoutId,this,false)
}