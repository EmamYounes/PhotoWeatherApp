package com.example.photoweatherapp.data_base.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class WeatherEntity(
    @ColumnInfo(
        name = "image_Byte_Array",
        typeAffinity = ColumnInfo.BLOB
    ) var imageByteArray: ByteArray,
    @ColumnInfo(name = "add_place_name") var addPlaceName: String,
    @ColumnInfo(name = "temperature") var temperature: String,
    @ColumnInfo(name = "weather_condition") var weatherCondition: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WeatherEntity

        if (!imageByteArray.contentEquals(other.imageByteArray)) return false

        return true
    }

    override fun hashCode(): Int {
        return imageByteArray.contentHashCode()
    }
}