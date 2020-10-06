package com.debdut.bulkuploadroom.DatabaseRoom

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.debdut.bulkuploadroom.Dao.DataDao
import com.debdut.bulkuploadroom.model.AttributeTable
import com.debdut.bulkuploadroom.model.RoomModel

@Database(entities = [RoomModel::class,AttributeTable::class],version = 2)
abstract class DataDatabase:RoomDatabase(){

    abstract fun dataDao():DataDao
    companion object {
        var instance:DataDatabase?=null
        @Synchronized
        fun getInstance(context: Context): DataDatabase? {
            if (instance == null) {
                instance = Room
                    .databaseBuilder(
                        context.applicationContext,
                        DataDatabase::class.java,
                        "data_database"
                    )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }
}