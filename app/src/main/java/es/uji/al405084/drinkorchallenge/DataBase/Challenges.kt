package es.uji.al405084.drinkorchallenge.DataBase

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "challenges_table",
    foreignKeys=[ForeignKey(
        entity=Languages::class,
        parentColumns = ["lang"],
        childColumns = ["lang"],
        onDelete = ForeignKey.CASCADE
    )])

data class Challenges (
    @PrimaryKey val id: Int,
    val challenges: String,
    val lang:String,
    val card:Int
    )