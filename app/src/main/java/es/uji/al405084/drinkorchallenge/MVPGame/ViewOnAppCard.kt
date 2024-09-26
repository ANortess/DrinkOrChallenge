package es.uji.al405084.drinkorchallenge.MVPGame

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import es.uji.al405084.drinkorchallenge.R

@SuppressLint("UseSwitchCompatOrMaterialCode")
class ViewOnAppCard: AppCompatActivity(), IViewOnAppCard {
    lateinit var btnRerollCard : Button
    lateinit var imageCard : ImageView
    lateinit var nameTextView : TextView
    lateinit var challengeTextView : TextView
    lateinit var presenterOnAppCard: PresenterOnAppCard
    lateinit var rndNames : TextView
    lateinit var setYESNO : TextView
    lateinit var randomNames : Switch
    lateinit var textClickToStart : TextView
    lateinit var displayNumberOfDecks : TextView
    lateinit var nameCardsOfDecks : TextView
    private var numberDecks = 1
    private var sharedPreferences: SharedPreferences? = null

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_displaycard)
        presenterOnAppCard = PresenterOnAppCard(this)

        loadSharedPreferences()
        saveItems()
        changeLaguaje()
        btnPress()
        displaySaveChallenge(this)

        displayDeckImage(this, numberDecks)
    }
    // Guardo las IDs de los items
    private fun saveItems(){
        btnRerollCard = findViewById(R.id.buttonRerollCard)
        imageCard = findViewById(R.id.imageCard)
        nameTextView = findViewById(R.id.nameTextView)
        challengeTextView = findViewById(R.id.challengeTextView)
        randomNames = findViewById(R.id.switchRandomNames)
        setYESNO = findViewById(R.id.checkRandomNames)
        textClickToStart = findViewById(R.id.clickToStartAGame)
        rndNames = findViewById(R.id.rndNames)
        displayNumberOfDecks = findViewById(R.id.displayNumberOfDecks)
        nameCardsOfDecks = findViewById(R.id.nameCardsOfDecks)

    }
    // Cargo el lenguaje
    private fun loadSharedPreferences(){
        sharedPreferences = getSharedPreferences("LanguagePreferences", MODE_PRIVATE)
        numberDecks = sharedPreferences?.getInt("numberOfDecks", 1) ?: 1

    }
    // Aplico el lenguaje
    private fun changeLaguaje(){
        textClickToStart.alpha = 1F
        nameTextView.setText("")
        challengeTextView.setText("")
        setYESNO.text = "YES"
        btnRerollCard.text = "Draw"
        rndNames.text = "Random names: "
        textClickToStart.text = "CLICK DRAW TO START A GAME"
        title = "Drink or challenge"
        nameCardsOfDecks.text = "Cards"
    }
    // Apretar botón de sacar una carta
    private fun btnPress(){
        btnRerollCard.setOnClickListener {
            displayCardImage(this)

            displayNameText(this, randomNames, setYESNO)

            textClickToStart.alpha = 0F
        }

        randomNames.setOnClickListener {
            if(!randomNames.isChecked){
                setYESNO.text = "NO"
            }
            else{
                setYESNO.text = "YES"
            }
        }
    }

    override fun displayDeckImage(context: Context, numberDecks : Int) { presenterOnAppCard.onDisplayDeckImage(context, numberDecks) }
    override fun displayCardImage(context: Context) { presenterOnAppCard.onDisplayCardImage(context) }
    override fun displayNameText(context: Context, randomNames : Switch, setYESNO : TextView) { presenterOnAppCard.onDisplayNameText(context, randomNames, setYESNO) }
    override fun displaySaveChallenge(context: Context) { presenterOnAppCard.onDisplaySaveChallenge(context) }
    override fun displayChallengeGame(context: Context, lang : String) { presenterOnAppCard.onDisplayChallengeGame(context, lang) }

    override fun displayMakeToastEndDecksSP() {
        Toast.makeText(this, "Has terminado la(s) baraja(s), puedes empezar otra(s)", Toast.LENGTH_LONG).show()
    }
    override fun displayMakeToastEndDecksEN() {
        Toast.makeText(this, "You have finished the deck(s), you can start another one(s)", Toast.LENGTH_LONG).show()
    }
    override fun displayRenameName(randomName : String) {
        nameTextView.text = randomName
    }
    override fun displayRenameNameOrder(noRandomName: String) {
        nameTextView.text = noRandomName
    }
    override fun displayChallenge(challenge: String) {
        challengeTextView.text = challenge
    }
    override fun displayImage(cardPNG: String) {
        Glide.with(this)
            .load(cardPNG)
            .into(imageCard)
    }
    override fun displayRemainingCards(cardRemaining: String) {
        displayNumberOfDecks.text = cardRemaining
    }

    override fun displayNameLang(nameLang : String) {
        displayChallengeGame(this, nameLang)
    }


}