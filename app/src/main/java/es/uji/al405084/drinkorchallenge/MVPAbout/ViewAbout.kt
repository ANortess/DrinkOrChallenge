package es.uji.al405084.drinkorchallenge.MVPAbout

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import es.uji.al405084.drinkorchallenge.R

class ViewAbout : AppCompatActivity(), IViewAbout {
    private lateinit var textAboutTitle: TextView
    private lateinit var textAboutApp: TextView
    private lateinit var buttonReturn: Button
    private lateinit var decksTitle: TextView
    private lateinit var numberOfDecks: EditText

    lateinit var presenterAbout : PresenterAbout

    private var sharedPreferences: SharedPreferences? = null
    private var numberDecks = 1


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        sharedPreferences = getSharedPreferences("LanguagePreferences", MODE_PRIVATE)
        presenterAbout = PresenterAbout()

        saveItems()
        btnReturn()
        loadLanguagePreference()
        updateLanguage()
    }

    // Guardo las IDs de los items
    private fun saveItems(){
        textAboutTitle = findViewById(R.id.aboutTitle)
        textAboutApp = findViewById(R.id.textAboutApp)
        buttonReturn = findViewById(R.id.buttonReturn)
        decksTitle = findViewById(R.id.decksTitle)
        numberOfDecks = findViewById(R.id.numberOfDecks)
    }

    // Botón de volver al menu principal pero con condiciones
    private fun btnReturn(){
        buttonReturn.setOnClickListener {
            if(numberOfDecks.text.toString().isEmpty()){
                    Toast.makeText(this, "You need to enter a number between 1 and 9 both included", Toast.LENGTH_LONG).show()
            }
            else{
                if (numberOfDecks.text.toString().toInt() in 1..9){
                    numberDecks = numberOfDecks.text.toString().toInt()
                    saveLanguagePreference()
                    onButton(this, buttonReturn)
                }
                else{
                    Toast.makeText(this, "You need to enter a number between 1 and 9 both included", Toast.LENGTH_LONG).show()
                }
            }
        }

    }
    // Cargo el lenguaje
    private fun loadLanguagePreference(){
        numberDecks = sharedPreferences?.getInt("numberOfDecks", 1) ?: 1
        numberOfDecks.setText(numberDecks.toString())
    }
    // Guardo el lenguaje
    private fun saveLanguagePreference() {
        val editor = sharedPreferences?.edit()
        editor?.putInt("numberOfDecks", numberDecks)
        editor?.apply()
    }
    // Aplico el lenguaje
    private fun updateLanguage() {
        numberOfDecks.hint = "number"
    }

    override fun onButton(context : Context, btnReturn: Button) { presenterAbout.onbtnReturn(context, btnReturn) }
}