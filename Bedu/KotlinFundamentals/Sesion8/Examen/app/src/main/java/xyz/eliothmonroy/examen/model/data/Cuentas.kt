package xyz.eliothmonroy.examen.model.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Cuentas (
	@SerializedName("numero") val numero : String,
	@SerializedName("saldo") val saldo : String
):Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString().toString(),
		parcel.readString().toString()
	)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(numero)
		parcel.writeString(saldo)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Cuentas> {
		override fun createFromParcel(parcel: Parcel): Cuentas {
			return Cuentas(parcel)
		}

		override fun newArray(size: Int): Array<Cuentas?> {
			return arrayOfNulls(size)
		}
	}
}