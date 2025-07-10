package io.iskopasi.somedemo.managers

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import io.iskopasi.somedemo.SampleDataHolder
import io.iskopasi.somedemo.managers.room.SampleDao
import io.iskopasi.somedemo.managers.room.SampleEntity
import io.iskopasi.somedemo.sampleDataMap
import io.iskopasi.somedemo.unknownSampleData
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class SomeSampleRequest(
    @SerializedName("path")
    @SerialName("path")
    val path: String
)

@Keep
@Serializable
data class SomeSampleResponse(
    @SerializedName("data")
    @SerialName("data")
    val data: String,
)


class DataSource(
    val dao: SampleDao,
    val restApi: RestApi,
) {
    val dataFlow: Flow<List<SampleEntity>> = dao.getFlow()

    suspend fun fetchData(): Boolean {
        try {
            val resultList = sampleDataMap.keys.mapNotNull { path ->
                // Let's pretend the reply is parsed here
                restApi.getData<SomeSampleResponse>(path)
                    .takeIf { it.code == 200 }
                    ?.data
                //------------------

                sampleDataMap[path]?.toSampleEntity()
            }

            dao.upsert(resultList)

            return true
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return false
    }

    suspend fun getData(id: Int) = dao.getById(id)
}

private fun SampleDataHolder?.toSampleEntity() = SampleEntity(
    imageRes = this?.imageRes ?: 0,
    name = this?.name ?: "",
    description = this?.description ?: "",
    link = this?.link ?: "",
    videoList = this?.videoLinks ?: emptyList()
)

private fun SomeSampleResponse.parseData(): SampleDataHolder {
    return sampleDataMap[this.data] ?: unknownSampleData
}
