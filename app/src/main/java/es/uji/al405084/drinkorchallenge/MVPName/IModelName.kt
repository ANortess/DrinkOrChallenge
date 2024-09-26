package es.uji.al405084.drinkorchallenge.MVPName

import android.content.Context
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

interface IModelName {
    // Interfaz del MODEL
    fun getName(name : EditText, recyclerView: RecyclerView, context: Context)
    fun getRecyclerView(recyclerView  : RecyclerView, context: Context)
    fun getDeleteName(nameDelete : String, recyclerView: RecyclerView, context: Context)
    fun getLang(recyclerView: RecyclerView,context: Context)
    fun closeDialogue(context : Context, lang : String, nameDeletes: String, recyclerView: RecyclerView)
}