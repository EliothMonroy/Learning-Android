package xyz.eliothmonroy.realmfirsttest.data

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

data class Person (@PrimaryKey val id:Int, var name:String, var listaPerros:RealmList<Dog>): RealmObject()