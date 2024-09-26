package es.uji.al405084.drinkorchallenge.MVPGame

import android.content.Context
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView

interface IViewOnAppCard {
    // Interfaz del VIEW
    fun displayDeckImage(context: Context, numberDecks : Int)
    fun displayCardImage(context: Context)
    fun displayNameText(context: Context, randomNames : Switch, setYESNO : TextView)
    fun displaySaveChallenge(context: Context)
    fun displayChallengeGame(context: Context, lang : String)

    fun displayMakeToastEndDecksSP()
    fun displayMakeToastEndDecksEN()
    fun displayRenameName(randomName : String)
    fun displayRenameNameOrder(noRandomName : String)
    fun displayChallenge(challenge : String)
    fun displayImage(cardPNG: String)
    fun displayRemainingCards(cardRemaining: String)
    fun displayNameLang(nameLang : String)
}