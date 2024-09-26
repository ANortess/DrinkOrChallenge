package es.uji.al405084.drinkorchallenge.MVPMain

import android.content.Context
import android.widget.Button

interface IModelMain {
    // Interfaz del MODEL
    fun getPressButtons(context: Context, buttonGoNames : Button, buttonAbout : Button)
    fun getStartGame(context: Context, buttonStart : Button)
}