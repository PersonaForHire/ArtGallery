package ie.wit.artgallery.models

import android.os.Parcelable
import android.text.Editable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class ArtModel(var id: Long = 0, var image: String=""): Parcelable