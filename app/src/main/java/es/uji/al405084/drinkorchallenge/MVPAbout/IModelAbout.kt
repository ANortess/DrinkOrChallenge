package es.uji.al405084.drinkorchallenge.MVPAbout

import android.content.Context
import android.widget.Button

interface IModelAbout {
    // Interfaz del MODEL
    fun getButton(context : Context, btnReturn : Button)
}