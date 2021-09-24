package com.example.android.pdp.shoha.db

import androidx.room.Room
import androidx.room.Database
import android.content.Context
import androidx.room.RoomDatabase
import com.example.android.pdp.shoha.db.dao.CharacterDao
import com.example.android.pdp.shoha.db.entity.CharacterE

@Database(entities = [CharacterE::class], version = 1)
abstract class AppDb : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    companion object {
        private var instance: AppDb? = null

        @Synchronized
        fun getInstance(context: Context): AppDb {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(context, AppDb::class.java, "appDatabase")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
            }
            return instance!!
        }
    }

}