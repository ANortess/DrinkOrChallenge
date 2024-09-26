package es.uji.al405084.drinkorchallenge.MVPMain

import android.content.Context
import android.widget.Button

interface IViewMain {
    // Interfaz del VIEW
    fun pressButtons(context: Context, buttonGoNames : Button, buttonAbout : Button)
    fun pressStart(context: Context, buttonStart : Button)
    fun displayMessageListEmpty()
    fun displayMessageInternet()
}