package es.uji.al405084.drinkorchallenge.MVPMain

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import es.uji.al405084.drinkorchallenge.R

class ViewMain : AppCompatActivity(), IViewMain{
    private lateinit var buttonGoNames : Button
    private lateinit var buttonStart : Button
    private lateinit var buttonAbout : Button
    private lateinit var infoTitleGame : TextView
    lateinit var presenterMain : PresenterMain

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenterMain = PresenterMain(this)

        SaveId()
        ChangeIdiome()

        pressButtons(this, buttonGoNames, buttonAbout)

        presenterMain.AddLanguages(this)
    }
    // Guarda las IDs de los items
    private fun SaveId(){
        buttonGoNames = findViewById(R.id.buttonGoNames)
        buttonStart = findViewById(R.id.buttonStart)
        buttonAbout = findViewById(R.id.buttonAbout)
        infoTitleGame = findViewById(R.id.titleOfGame)
    }
    // Carga el idioma y lo aplico
    private fun ChangeIdiome(){
        buttonAbout.text = "About"
        buttonGoNames.text = "Names"
        buttonStart.text = "Start"
        infoTitleGame.text = "Drink Or Challenge"
        title = "Drink or challenge"
    }

    override fun onResume() {
        super.onResume()
        pressStart(this, buttonStart)

    }

    override fun pressButtons(context: Context, buttonGoNames : Button, buttonAbout : Button) { presenterMain.onPressButtons(context, buttonGoNames, buttonAbout) }
    override fun pressStart(context: Context, buttonStart: Button) { presenterMain.onPlayGame(context, buttonStart) }

    // Mostrar mensaje si la lista está o no vacia
    override fun displayMessageListEmpty() {
        Toast.makeText( this, "Dont have any names in list", Toast.LENGTH_LONG ).show()
    }
    // Mostrar mensaje si hay o no internet
    override fun displayMessageInternet() {
        Toast.makeText( this, "There is no Internet connection", Toast.LENGTH_LONG ).show()
    }
}