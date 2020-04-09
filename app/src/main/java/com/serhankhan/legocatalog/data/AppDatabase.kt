package com.serhankhan.legocatalog.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.serhankhan.legocatalog.legotheme.data.LegoTheme
import com.serhankhan.legocatalog.legotheme.data.LegoThemeDao


@Database(entities = [LegoTheme::class],version = 1,exportSchema = false)
abstract class AppDatabase:RoomDatabase() {

    abstract fun legoThemeDao(): LegoThemeDao

    companion object{
        @Volatile
        private var instance:AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "lego_db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { instance = it }
            }
        }
    }
}