package es.uji.al405084.drinkorchallenge.MVPName

import android.content.Context
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class PresenterName(private var view : ViewName) {
    private val model : ModelName = ModelName(this)
    // VIEW ---> MODEL
    fun onBtnAddPressed(name : EditText, recyclerView: RecyclerView, context: Context) {
        model.getName(name, recyclerView, context)
    }
    fun onDisplayReclycler(recyclerView  : RecyclerView, context: Context){
        model.getRecyclerView(recyclerView, context)
    }
    fun onDeletePlayer(nameDelete : String, recyclerView  : RecyclerView, context: Context){
        model.getDeleteName(nameDelete, recyclerView, context)
    }
    fun onDisplayLang(recyclerView: RecyclerView,context: Context) {
        model.getLang(recyclerView, context)
    }

    fun onCloseDialogue(context: Context, lang : String, nameDeletes: String, recyclerView: RecyclerView){
        model.closeDialogue(context, lang, nameDeletes, recyclerView)
    }

    // VIEW <--- MODEL
    fun onDisplayListEmpty(){
        view.displayListEmpty()
    }
    fun onDisplayListNotEmpty(){
        view.displayListNotEmpty()
    }
}
