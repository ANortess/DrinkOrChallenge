package es.uji.al405084.drinkorchallenge.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import es.uji.al405084.drinkorchallenge.Adapter.AdapterLang

@Database(entities = [Names::class, Challenges::class, Languages::class], version = 2)
abstract class DataBase : RoomDatabase(){
    abstract fun dao() : DAO

    companion object{

        @Volatile
        private var INSTANCE : DataBase? = null

        fun getDataBase(context: Context): DataBase {
            val tempInstace = INSTANCE
            if (tempInstace != null){
                return tempInstace
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            // Code
                        }
                    }).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

