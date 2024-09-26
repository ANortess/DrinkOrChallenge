package es.uji.al405084.drinkorchallenge.MVPMain

import android.content.Context
import android.widget.Button

class PresenterMain(private var view: ViewMain) {
    private val modelMain : ModelMain = ModelMain(this)
    // VIEW ---> MODEL
    fun onPressButtons(context: Context, buttonGoNames : Button, buttonAbout : Button){
        modelMain.getPressButtons(context, buttonGoNames, buttonAbout)
    }
    fun onPlayGame(context: Context, buttonStart : Button){
        modelMain.getStartGame(context, buttonStart)
    }
    // VIEW <--- MODEL
    fun onDisplayMessageListEmpty(){
        view.displayMessageListEmpty()
    }
    fun onDisplayMessageInternet(){
        view.displayMessageInternet()
    }
    fun AddLanguages(context: Context){
        modelMain.setLanguages(context)
    }
}