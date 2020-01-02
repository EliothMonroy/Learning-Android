package xyz.eliothmonroy.realmfirsttest.data

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Person (@PrimaryKey var id:Int=0, var name:String="", var listaPerros:RealmList<Dog> = RealmList()): RealmObject()