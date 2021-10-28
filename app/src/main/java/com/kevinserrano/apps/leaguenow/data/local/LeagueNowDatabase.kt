package com.kevinserrano.apps.leaguenow.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kevinserrano.apps.leaguenow.data.models.Team

/**
 * Created by Kevin Serrano 28/08/21
 */
@Database(entities = [Team::class], version = 1, exportSchema = false)
abstract class LeagueNowDatabase : RoomDatabase() {

    abstract fun favoritesDao(): FavoritesDao

    companion object {
        const val DATABASE_NAME = "db_league_now_app"
        @Volatile
        private var INSTANCE: LeagueNowDatabase? = null
        fun getInstance(context: Context): LeagueNowDatabase {
            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext, LeagueNowDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }
    }

}