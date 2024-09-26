package es.uji.al405084.drinkorchallenge.DataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "languages_table")
data class Languages (
    @PrimaryKey val lang:String
)

