package com.serhankhan.legocatalog.legotheme.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.serhankhan.legocatalog.testing.OpenForTesting

@Dao
@OpenForTesting
interface LegoThemeDao {

    @Query("SELECT * FROM themes ORDER BY id DESC")
    fun getLegoThemes():LiveData<List<LegoTheme>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(themes:List<LegoTheme>)

    @Query("DELETE FROM themes")
    fun deleteAllThemes()
}