package com.jg.greencode.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jg.greencode.data.local.entity.ConversionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ConversionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConversion(conversion: ConversionEntity)

    @Query("SELECT * FROM conversion_history ORDER BY date DESC")
    fun getAllConversions(): Flow<List<ConversionEntity>>

    @Query("SELECT * FROM conversion_history WHERE id = :id")
    suspend fun getConversionById(id: Int): ConversionEntity
}