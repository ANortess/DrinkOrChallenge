package es.uji.al405084.drinkorchallenge.MVPAbout

import android.content.Context
import android.content.Intent
import android.widget.Button
import es.uji.al405084.drinkorchallenge.MVPMain.ViewMain

class ModelAbout : IModelAbout {
    // Función al apretar el botón de volver
    override fun getButton(context : Context, btn : Button) {
        val intent = Intent(context, ViewMain::class.java)
        context.startActivity(intent)
    }
}