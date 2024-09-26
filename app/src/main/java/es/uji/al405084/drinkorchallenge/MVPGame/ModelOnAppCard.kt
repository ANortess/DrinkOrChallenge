package es.uji.al405084.drinkorchallenge.MVPGame

import android.content.Context
import android.content.Intent
import android.widget.Switch
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import es.uji.al405084.drinkorchallenge.DataBase.Challenges
import es.uji.al405084.drinkorchallenge.DataBase.DataBase
import es.uji.al405084.drinkorchallenge.MVPMain.ViewMain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ModelOnAppCard(private var presenterOnAppCard: PresenterOnAppCard) : IModelOnAppCard {
    companion object {
        const val FIRST_CALL = 0
    }

    lateinit var appDb: DataBase
    private var deckArray = ""
    var cardPNG = ""
    var valueCard = 0
    var valueCardNone = ""
    var currentIndex = 0 // Inicializar el índice en 0

    // Función que saca una baraja de internet dependiendo del número de barajas introducidas por el usuario
    override fun getDeckImage(context: Context, numberDecks : Int) {
        val url = "https://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=$numberDecks"

        val reQueueRC : RequestQueue = Volley.newRequestQueue(context)
        val requestRC = JsonObjectRequest(
            Request.Method.GET, url, null,
            {response ->
                deckArray = response.getString("deck_id")
                getCardImage(context)
            },
            {  })
        reQueueRC.add(requestRC)

    }

    // Función que saca una carta aleatoria de todas las barajas
    override fun getCardImage(context: Context) {
        val url = "https://deckofcardsapi.com/api/deck/" + deckArray + "/draw/?count=1"
        val reQueueRC : RequestQueue = Volley.newRequestQueue(context)
        val requestRC = JsonObjectRequest(
            Request.Method.GET, url, null,
            {response ->
                val cardArray = response.getJSONArray("cards")
                val cardRemaining = response.getString("remaining")
                var displayNumberOfDecks = cardRemaining.toString()
                presenterOnAppCard.onDisplayCardRemaining(displayNumberOfDecks)
                if(cardRemaining.toInt() == FIRST_CALL ){
                    val intent= Intent(context , ViewMain::class.java).apply {}
                    context.startActivity(intent)
                    presenterOnAppCard.onDisplayMakeToastEndDecksEN()
                }
                val firtsCall = cardArray.getJSONObject(FIRST_CALL)
                cardPNG = firtsCall.getString("image")
                valueCardNone = firtsCall.getString("value").toString()
                when(valueCardNone){
                    "JACK" -> valueCard = 11
                    "QUEEN" -> valueCard = 12
                    "KING" -> valueCard = 13
                    "ACE" -> valueCard = 1
                    else -> valueCard = valueCardNone.toInt()
                }
            },
            {  })
        reQueueRC.add(requestRC)

        presenterOnAppCard.onDisplayImage(cardPNG)
    }

    // Función que saca el nombre de la base de datos para plasmarla en el TextView
    override fun getNameText(context: Context, randomNames : Switch, setYESNO : TextView) {
        appDb = DataBase.getDataBase(context)
        if(!randomNames.isChecked){
            GlobalScope.launch(Dispatchers.IO) {
                val namesList = withContext(Dispatchers.IO) {
                    appDb.dao().getAllNames()
                }

                if (currentIndex < namesList.size) {
                    val noRandomName = namesList[currentIndex].name
                    presenterOnAppCard.onDisplayRenameNameOrder(noRandomName)

                    val nameLang = withContext(Dispatchers.IO) {
                        appDb.dao().getNameLang(noRandomName)
                    }

                    presenterOnAppCard.OnDisplayNameLang(nameLang)

                    currentIndex++
                    if (currentIndex == namesList.size) {
                        currentIndex = FIRST_CALL
                    }
                }
            }
        }
        else{
            GlobalScope.launch(Dispatchers.IO) {
                val randomName = appDb.dao().getRandomName()[0].name
                withContext(Dispatchers.Main) {
                    presenterOnAppCard.onDisplayRenameName(randomName)
                }
                val nameLang = appDb.dao().getNameLang(randomName)
                presenterOnAppCard.OnDisplayNameLang(nameLang)
            }
        }
    }

    //Función que guarda la lista de retos en inglés
    override fun getSaveChallenge(context: Context) {
        appDb = DataBase.getDataBase(context)

        val challengeList = listOf(
            Challenges(1, "All players take a drink.", "EN",1),
            Challenges(2, "The player can choose another player to take two drinks or to challenge someone.", "EN",2),
            Challenges(3, "The player takes a drink.","EN",3),
            Challenges(4, "The player to the left of the current player takes a drink.", "EN",4),
            Challenges(5, "The player to the right of the current player takes a drink.", "EN",5),
            Challenges(6, "The player can choose to take a drink or to challenge someone.", "EN",6),
            Challenges(7, "The player chooses another player to take a drink or to challenge someone.", "EN",7),
            Challenges(8, "All players must take a drink.","EN",8),
            Challenges(9, "The player can choose to take a drink or to challenge someone.","EN",9),
            Challenges(10, "The player can choose another player to take three drinks or to challenge someone.","EN",10),
            Challenges(11, "The player must ask a question to another player. If the player does not answer correctly, they must take a drink.","EN",11),
            Challenges(12, "The player chooses another player to take two drinks or to challenge someone.","EN",12),
            Challenges(13, "All players take a drink.","EN",13),
            Challenges(14,"Todos los jugadores toman un trago.","ES",1),
            Challenges(15,"El jugador puede elegir a otro jugador para tomar dos tragos o para retar a alguien.","ES",2),
            Challenges(16,"El jugador toma un trago.","ES",3),
            Challenges(17,"El jugador a la izquierda del jugador actual toma un trago.","ES",4),
            Challenges(18,"El jugador a la derecha del jugador actual toma un trago.","ES",5),
            Challenges(19,"El jugador puede elegir si toma un trago o si reta a alguien.","ES",6),
            Challenges(20,"El jugador elige a otro jugador para tomar un trago o para retar a alguien.","ES",7),
            Challenges(21,"Todos los jugadores deben tomar un trago.","ES",8),
            Challenges(22,"El jugador puede elegir si toma un trago o si reta a alguien.","ES",9),
            Challenges(23,"El jugador puede elegir a otro jugador para tomar tres tragos o para retar a alguien.","ES",10),
            Challenges(24,"El jugador debe hacer una pregunta a otro jugador. Si el jugador no responde correctamente, debe tomar un trago.","ES",11),
            Challenges(25,"El jugador elige a otro jugador para tomar dos tragos o para retar a alguien.","ES",12),
            Challenges(26,"Todos los jugadores toman un trago.","ES",13)

        )
        GlobalScope.launch(Dispatchers.IO) {
            appDb.dao().insertChallenge(challengeList)
        }
    }

    //Función que carga la lista de retos en inglés
    override fun getChallengeGame(context: Context, lang : String) {
        appDb = DataBase.getDataBase(context)
        GlobalScope.launch(Dispatchers.IO) {
            val challengeItem = appDb.dao().getChallenge(lang,valueCard)
            if (challengeItem != null) {
                val challenge = challengeItem.challenges
                presenterOnAppCard.onDisplayChallenge(challenge)
            }
        }
    }

    //Función que carga la lista de retos en español
    //override fun getChallengeSpanish(context: Context) {
    //    appDb = DataBase.getDataBase(context)
    //    GlobalScope.launch(Dispatchers.IO) {
    //        val challengeItem = appDb.dao().getChallenge("ES",valueCard)
    //        if (challengeItem != null) {
    //            val challenge = challengeItem.challenges
    //            presenterOnAppCard.onDisplayChallenge(challenge)
    //        }
    //    }
    //}

}