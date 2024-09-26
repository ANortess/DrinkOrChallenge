package es.uji.al405084.drinkorchallenge.MVPAbout

import android.content.Context
import android.widget.Button

class PresenterAbout {
    private val modelAbout : ModelAbout = ModelAbout()

    // VIEW ---> MODEL
    fun onbtnReturn(context: Context, btnReturn : Button){
        modelAbout.getButton(context, btnReturn)
    }
}