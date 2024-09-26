package es.uji.al405084.drinkorchallenge.DataBase

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AllNames_Table")
data class GetAllNames(
    @PrimaryKey val name: String
)

@Entity(tableName = "AllChallengesEnglish_Table")
data class GetAllChallengesEnglish(
    @PrimaryKey val id: Int,
    val challenges: String
)

@Entity(tableName = "AllChallengesSpanish_Table")
data class GetAllChallengesSpanish(
    @PrimaryKey val id: Int,
    val challenges: String
)




