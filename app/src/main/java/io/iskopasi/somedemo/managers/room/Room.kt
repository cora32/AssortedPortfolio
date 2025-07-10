package io.iskopasi.somedemo.managers.room

import androidx.annotation.Keep
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName


@Entity
@Keep
data class SampleEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @SerializedName("image_res") val imageRes: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("link") val link: String,
    @SerializedName("video_list") val videoList: List<String>,
)

@Database(
    entities = [
        SampleEntity::class,
    ], version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): SampleDao
}