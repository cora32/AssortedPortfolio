package io.iskopasi.somedemo.managers.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import kotlin.jvm.java
import kotlin.let

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromListOfStrings(data: List<String>?): String? {
        return data?.let { gson.toJson(data) }
    }

    @TypeConverter
    fun stringToListOfStrings(string: String?): List<String> {
        return string?.let { gson.fromJson<List<String>>(string, List::class.java) } ?: emptyList()
    }
}