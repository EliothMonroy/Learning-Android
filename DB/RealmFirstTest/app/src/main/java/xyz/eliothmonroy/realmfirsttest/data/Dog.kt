package xyz.eliothmonroy.realmfirsttest.data

import io.realm.RealmObject

open class Dog(var name:String="", var age:Int=0): RealmObject()