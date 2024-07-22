package android.kotlin.slidervideoviewpager2.data.model

import android.os.Parcel
import android.os.Parcelable

data class Step(
    val title: String,
    val subtitle: String,
    val videoUrl: String
) : Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeString(subtitle)
        dest.writeString(videoUrl)
    }

    companion object CREATOR : Parcelable.Creator<Step> {
        override fun createFromParcel(parcel: Parcel): Step {
            return Step(
                title = parcel.readString() ?: "",
                subtitle = parcel.readString() ?: "",
                videoUrl = parcel.readString() ?: ""
            )
        }

        override fun newArray(size: Int): Array<Step?> {
            return arrayOfNulls(size)
        }
    }
}