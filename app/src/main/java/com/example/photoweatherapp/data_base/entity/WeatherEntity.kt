package com.example.photoweatherapp.data_base.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int= 0,
    @ColumnInfo(name = "image_Byte_Array") val imageByteArray: ByteArray,
    @ColumnInfo(name = "add_place_name") val addPlaceName: String,
    @ColumnInfo(name = "temperature") val temperature: String,
    @ColumnInfo(name = "weather_condition") val weatherCondition: String
)