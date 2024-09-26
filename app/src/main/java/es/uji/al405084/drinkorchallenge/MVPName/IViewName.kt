package es.uji.al405084.drinkorchallenge.MVPName

import android.content.Context
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

interface IViewName {
    // Interfaz del VIEW
    fun displayName(name : EditText, recyclerView: RecyclerView, context: Context)
    fun displayRecyclerView(recyclerView  : RecyclerView, context: Context)
    fun displayDeleteName(nameDelete : String, recyclerView: RecyclerView, context: Context)
    fun displayLang(recyclerView: RecyclerView,context: Context)
    fun displayDialogue(context: Context, lang : String, nameDeletes : String, recyclerView: RecyclerView)

    fun displayListEmpty()
    fun displayListNotEmpty()

}