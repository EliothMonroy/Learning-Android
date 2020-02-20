package xyz.eliothmonroy.customviewtest

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize //Nos ahorra el código biolerplate de parcelable
data class UserInfo(
    val userAccount:String="",
    val userPass:String=""
):Parcelable