package es.uji.al405084.drinkorchallenge.MVPGame

import android.content.Context
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView

interface IModelOnAppCard {
    // Interfaz del MODEL
    fun getDeckImage(context: Context, numberDecks : Int)
    fun getCardImage(context: Context)
    fun getNameText(context: Context, randomNames : Switch, setYESNO : TextView)
    fun getSaveChallenge(context: Context)
    fun getChallengeGame(context: Context, lang : String)
}