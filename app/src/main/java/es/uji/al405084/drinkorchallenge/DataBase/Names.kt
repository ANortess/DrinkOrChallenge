package es.uji.al405084.drinkorchallenge.DataBase

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "names_table",
    foreignKeys=[ForeignKey(
        entity=Languages::class,
        parentColumns = ["lang"],
        childColumns = ["lang"],
        onDelete = ForeignKey.CASCADE
    )])
data class Names(
    @PrimaryKey val name: String,
    val lang:String
)