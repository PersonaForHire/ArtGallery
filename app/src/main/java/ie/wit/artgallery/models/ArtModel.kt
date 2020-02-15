package ie.wit.artgallery.models

import android.os.Parcelable
import android.text.Editable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
@RawValue
@Parcelize
data class ArtModel(var comment: Editable, var id: Long = 0, var image: String=""): Parcelable