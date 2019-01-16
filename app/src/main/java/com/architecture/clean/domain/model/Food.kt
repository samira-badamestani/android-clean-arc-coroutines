package com.architecture.clean.domain.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


@Entity
data class Food(
        @PrimaryKey(autoGenerate = true)
        @SerializedName("id") var id: Int,
        @SerializedName("title") var title: String,
        @SerializedName("href") var href: String,
        @SerializedName("ingredients") var ingredients: String,
        @SerializedName("thumbnail") var thumbnail: String
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(title)
        writeString(href)
        writeString(ingredients)
        writeString(thumbnail)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Food> = object : Parcelable.Creator<Food> {
            override fun createFromParcel(source: Parcel): Food = Food(source)
            override fun newArray(size: Int): Array<Food?> = arrayOfNulls(size)
        }
    }
}