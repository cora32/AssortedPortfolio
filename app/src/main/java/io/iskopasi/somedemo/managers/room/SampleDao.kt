package io.iskopasi.somedemo.managers.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface SampleDao {
    @Query("SELECT * FROM sampleentity")
    fun getFlow(): Flow<List<SampleEntity>>

    @Query("SELECT * FROM sampleentity")
    fun getAll(): List<SampleEntity>

    @Query("SELECT * FROM sampleentity WHERE uid = :uid")
    suspend fun getById(uid: Int): SampleEntity

    @Query("DELETE FROM sampleentity")
    suspend fun deleteAll()

    @Insert
    suspend fun insertAll(list: List<SampleEntity>)

    @Transaction
    suspend fun upsert(list: List<SampleEntity>) {
        deleteAll()
        insertAll(list)
    }
}