package es.uji.al405084.drinkorchallenge.DataBase

import androidx.room.*
import java.util.jar.Attributes.Name


@Dao
interface DAO {

    // Names
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(card: Names)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertLanguage(language: Languages)

    @Query("SELECT * FROM names_table")
    fun getAllNames() : List<Names>

    @Query("DELETE FROM names_table WHERE name = :name")
    fun getDeleteName(name: String)

    @Query("SELECT * FROM names_table ORDER BY RANDOM() LIMIT 1")
    fun getRandomName(): List<Names>

    @Query("SELECT * FROM names_table")
    fun getOrderName(): List<Names>

    @Query("SELECT * FROM languages_table")
    fun getLang() : List<Languages>

    @Query("UPDATE names_table SET lang = :lang WHERE name = :name")
    fun updateLanguage(name: String, lang: String)

    @Query("SELECT lang FROM names_table WHERE name = :name")
    fun getNameLang(name: String) : String
    /*
    // Challenges English
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertChallengeEnglish(challengeList: List<Challenges>)
*/
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertChallenge(challengeList: List<Challenges>)
/*
    @Query("SELECT * FROM challenges_table WHERE id = :id")
    fun getChallengeByIdEnglish(id: Int): Challenges

    // Challenges Spanish
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertChallengeSpanish(challengeList: List<Challenges>)

    @Query("SELECT * FROM challenges_table WHERE id = :id")
    fun getChallengeByIdSpanish(id: Int): Challenges
*/

    @Query("SELECT * FROM challenges_table WHERE lang = :lang AND card=:card")
    fun getChallenge(lang:String, card:Int):Challenges
}

