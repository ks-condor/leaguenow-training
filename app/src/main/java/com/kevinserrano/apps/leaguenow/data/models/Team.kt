package com.kevinserrano.apps.leaguenow.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

/**
 * Created by Kevin Serrano 28/08/21
 */
@Entity(tableName = Team.TABLE_NAME)
data class Team(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "idTeam") @NotNull
    val idTeam:String = "",
    @ColumnInfo(name = "teamName") @NotNull
    val teamName:String = "",
    @ColumnInfo(name = "banner") @NotNull
    val banner:String = "",
    @ColumnInfo(name = "badge") @NotNull
    val badge:String = "",
    @ColumnInfo(name = "leagueName") @NotNull
    val leagueName:String = "",
    @ColumnInfo(name = "stadium") @NotNull
    val stadium:String = "",
    @ColumnInfo(name = "teamJersey") @NotNull
    val teamJersey:String = "",
    @ColumnInfo(name = "facebook") @NotNull
    val facebook:String = "",
    @ColumnInfo(name = "youtube") @NotNull
    val youtube:String = "",
    @ColumnInfo(name = "description") @NotNull
    val description:String = "",
    @ColumnInfo(name = "formedYear") @NotNull
    val formedYear:String = ""
){
    companion object {
        const val TABLE_NAME = "team"
    }
}