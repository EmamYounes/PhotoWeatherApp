package com.example.photoweatherapp.data_base.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class WeatherEntity(
    @ColumnInfo(name = "image_Byte_Array") var imageByteArray: String,
    @ColumnInfo(name = "add_place_name") var addPlaceName: String,
    @ColumnInfo(name = "temperature") var temperature: String,
    @ColumnInfo(name = "weather_condition") var weatherCondition: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}