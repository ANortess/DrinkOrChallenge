package es.uji.al405084.drinkorchallenge.MVPAbout

import android.content.Context
import android.widget.Button

interface IViewAbout {
    // Interfaz del VIEW
    fun onButton(context : Context, btnReturn : Button)
}