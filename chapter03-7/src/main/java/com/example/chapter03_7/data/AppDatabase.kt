package com.example.chapter03_7.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.chapter03_7.data.dao.ContentDao
import com.example.chapter03_7.model.ContentEntity

@Database(entities = [ContentEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contentDao(): ContentDao
}