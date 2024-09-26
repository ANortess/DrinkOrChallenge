package es.uji.al405084.drinkorchallenge.MVPMain

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Button
import android.widget.Toast
import es.uji.al405084.drinkorchallenge.DataBase.DataBase
import es.uji.al405084.drinkorchallenge.DataBase.Languages
import es.uji.al405084.drinkorchallenge.MVPGame.ViewOnAppCard
import es.uji.al405084.drinkorchallenge.MVPName.ViewName
import es.uji.al405084.drinkorchallenge.MVPAbout.ViewAbout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ModelMain(private var presenterMain: PresenterMain) : IModelMain {
    lateinit var appDb: DataBase
    override fun getPressButtons(context: Context, buttonGoNames : Button, buttonAbout : Button) {
        buttonGoNames.setOnClickListener {
            val intent= Intent(context , ViewName::class.java).apply {}
            context.startActivity(intent)
        }
        buttonAbout.setOnClickListener {
            val intent= Intent(context , ViewAbout::class.java).apply {}
            context.startActivity(intent)
        }
    }
    override fun getStartGame(context: Context, buttonStart: Button) {
        appDb = DataBase.getDataBase(context)
        val connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        GlobalScope.launch(Dispatchers.IO) {
            if (appDb.dao().getAllNames().isEmpty()) {
                buttonStart.setOnClickListener {
                    presenterMain.onDisplayMessageListEmpty()
                }
            } else {
                buttonStart.setOnClickListener {
                    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
                    val isConnected : Boolean = activeNetwork?.isConnectedOrConnecting == true
                    if(isConnected){
                        val intent = Intent(context, ViewOnAppCard::class.java).apply {}
                        context.startActivity(intent)
                    }
                    else{
                        presenterMain.onDisplayMessageInternet()
                    }
                }
            }
        }
    }
    fun setLanguages(context:Context){
        appDb=DataBase.getDataBase(context)
        GlobalScope.launch(Dispatchers.IO) {
            val english=Languages( "EN")
            val spanish=Languages( "ES")

            appDb.dao().insertLanguage(english)
            appDb.dao().insertLanguage(spanish)
        }
    }
}