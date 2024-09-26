package es.uji.al405084.drinkorchallenge.MVPGame

import android.content.Context
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView

class PresenterOnAppCard(private var view: ViewOnAppCard) {
    private val modelOnGameCard : ModelOnAppCard = ModelOnAppCard(this)

    // VIEW ---> MODEL
    fun onDisplayDeckImage(context: Context, numberDecks : Int){
        modelOnGameCard.getDeckImage(context, numberDecks)
    }
    fun onDisplayCardImage(context: Context){
        modelOnGameCard.getCardImage(context)
    }
    fun onDisplayNameText(context: Context, randomNames : Switch, setYESNO : TextView){
        modelOnGameCard.getNameText(context, randomNames, setYESNO)
    }
    fun onDisplaySaveChallenge(context: Context){
        modelOnGameCard.getSaveChallenge(context)
    }
    fun onDisplayChallengeGame(context: Context, lang : String){
        modelOnGameCard.getChallengeGame(context, lang)
    }

    // VIEW <--- MODEL
    fun onDisplayMakeToastEndDecksEN(){
        view.displayMakeToastEndDecksEN()
    }
    fun onDisplayRenameName(randomName : String){
        view.displayRenameName(randomName)
    }
    fun onDisplayRenameNameOrder(noRandomName : String){
        view.displayRenameNameOrder(noRandomName)
    }
    fun onDisplayChallenge(challenge : String){
        view.displayChallenge(challenge)
    }
    fun onDisplayImage(cardPNG: String){
        view.displayImage(cardPNG)
    }
    fun onDisplayCardRemaining(cardRemaining: String){
        view.displayRemainingCards(cardRemaining)
    }

    fun OnDisplayNameLang(nameLang : String) {
        view.displayNameLang(nameLang)
    }
}