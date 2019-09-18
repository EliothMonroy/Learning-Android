package xyz.eliothmonroy.platzistore

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DbOpenHelper (ctx:Context): ManagedSQLiteOpenHelper(ctx,"PlatziStore",null,1){

    companion object{
        private var instance:DbOpenHelper?=null
        fun getInstance(ctx: Context):DbOpenHelper? = if (instance==null){
            instance= DbOpenHelper(ctx)
            instance
        }else{
            instance
        }
    }

    //Definimos las tablas que vamos a usar
    override fun onCreate(db: SQLiteDatabase?) {
        //Definimos los campos de la tabla producto
        val id="id" to INTEGER + PRIMARY_KEY //Automaticamente lo hace incremental
        val name="name" to TEXT
        val desc="desc" to TEXT
        val price="price" to REAL

        //Ahora creamos la tabla
        db?.createTable("Productos",true,id,name,desc,price)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable("Productos",true)
    }

    val Context.database:DbOpenHelper?
    get() = getInstance(applicationContext)

}