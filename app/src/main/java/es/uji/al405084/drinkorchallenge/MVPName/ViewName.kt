package es.uji.al405084.drinkorchallenge.MVPName

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.uji.al405084.drinkorchallenge.Adapter.AdapterLang
import es.uji.al405084.drinkorchallenge.Adapter.AdapterNames
import es.uji.al405084.drinkorchallenge.DataBase.DataBase
import es.uji.al405084.drinkorchallenge.DataBase.Names
import es.uji.al405084.drinkorchallenge.MVPMain.ViewMain
import es.uji.al405084.drinkorchallenge.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewName : AppCompatActivity(), IViewName{
    private lateinit var textName : EditText
    private lateinit var anyNames : TextView
    private lateinit var btnAdd : Button
    private lateinit var btnReturn : Button
    private lateinit var recyclerView : RecyclerView
    private lateinit var dialog: Dialog
    lateinit var presenter : PresenterName
    var nameDeletes = ""
    @SuppressLint("WrongViewCast", "MissingInflatedId", "InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_names)
        presenter = PresenterName(this)

        saveItem()
        changeIdiome()
        buttonPressed()

        displayRecyclerView(recyclerView, this)
    }

    // Guarda las IDs de los items
    private fun saveItem(){
        recyclerView = findViewById(R.id.recyclerView)
        val myLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = myLayoutManager
        btnAdd = findViewById(R.id.id_button)
        btnReturn = findViewById(R.id.buttonExitNames)
        anyNames = findViewById(R.id.anyNamesTextView)
        textName = findViewById(R.id.id_personName)
    }

    fun closeDialog(lang : String) {

        displayDialogue(this, lang, nameDeletes, recyclerView)
        dialog.dismiss()
    }
    fun nameFilter(nameDelete : String){
        nameDeletes = nameDelete
    }
    // Custom Dialog
    fun customDialog(nameDelete : String){
        dialog = Dialog(this@ViewName)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.customdialogname)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val recyclerViewLanguajes : RecyclerView = dialog.findViewById(R.id.recyclerViewLanguajes)
        val nameDeleted : TextView = dialog.findViewById(R.id.nameDeleted)
        val btnDelete : Button = dialog.findViewById(R.id.btnDelete)
        val btnReturnDelete : Button = dialog.findViewById(R.id.btnReturn)

        nameDeleted.setText(nameDelete)
        displayLang(recyclerViewLanguajes, this)

        btnDelete.text = "Delete"
        btnReturnDelete.text = "Return"

        btnReturnDelete.setOnClickListener{
            dialog.dismiss()
        }

        btnDelete.setOnClickListener {
            displayDeleteName(nameDelete, recyclerView, this)
            dialog.dismiss()
        }

        dialog.show()
    }

    // Aplica el idioma
    private fun changeIdiome() {
        btnReturn.text = "Return"
        btnAdd.text = "Add"
        textName.hint = "name"
        anyNames.text = "Any Names"
        title = "Drink or challenge"

    }
    //Apretar botones
    private fun buttonPressed(){
        btnReturn.setOnClickListener {
            val intent= Intent(this, ViewMain::class.java).apply {}
            startActivity(intent)
        }
        btnAdd.setOnClickListener {
            if (textName.text.length < 16){
                displayName(textName, recyclerView, this)
            }
            else{
                Toast.makeText(this, "The name you have entered exceeds 16 characters", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun displayName(name : EditText, recyclerView: RecyclerView, context: Context) {
        if(name.text.toString().isNotEmpty()){
            presenter.onBtnAddPressed(name, recyclerView, context)
            if(name.text.isNotEmpty()){
                name.setText("")
                anyNames.text = ""
                name.hint = "name"
            }
        }
        else{
            Toast.makeText(context, "You didn't write anything", Toast.LENGTH_LONG).show()
        }
    }
    override fun displayRecyclerView(recyclerView  : RecyclerView, context: Context) { presenter.onDisplayReclycler(recyclerView, context) }
    override fun displayDeleteName(nameDelete : String, recyclerView: RecyclerView, context: Context) { presenter.onDeletePlayer(nameDelete, recyclerView, context) }
    override fun displayLang(recyclerView: RecyclerView,context: Context) { presenter.onDisplayLang(recyclerView, context) }
    override fun displayDialogue(context: Context, lang: String, nameDeletes: String, recyclerView: RecyclerView) { presenter.onCloseDialogue(context, lang, nameDeletes, recyclerView) }

    override fun displayListEmpty() {
        anyNames.text = "Any Names"
    }
    override fun displayListNotEmpty() {
        anyNames.text = ""
    }
}