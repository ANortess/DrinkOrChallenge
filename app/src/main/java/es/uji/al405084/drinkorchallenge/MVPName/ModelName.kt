package es.uji.al405084.drinkorchallenge.MVPName

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.uji.al405084.drinkorchallenge.Adapter.AdapterLang
import es.uji.al405084.drinkorchallenge.Adapter.AdapterNames
import es.uji.al405084.drinkorchallenge.DataBase.DataBase
import es.uji.al405084.drinkorchallenge.DataBase.Names
import es.uji.al405084.drinkorchallenge.DataBase.Languages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ModelName(private var presenterName: PresenterName) : IModelName {
    lateinit var appDb: DataBase
    private var allNames = arrayListOf<Names>()

    // Función para coger el nombre e insertarlo en el recycler view con el adapter
    override fun getName(name : EditText, recyclerView: RecyclerView, context: Context) {
        appDb = DataBase.getDataBase(context)
        val onName = Names(name.text.toString(), "EN")

        GlobalScope.launch(Dispatchers.IO) {
            appDb.dao().insert(onName)
        }
        allNames.add(onName)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = AdapterNames(allNames, context as ViewName)
    }

    // Función para poner el texto de si esta vacio el recycler view
    override fun getRecyclerView(recyclerView  : RecyclerView, context: Context){
        appDb = DataBase.getDataBase(context)
        GlobalScope.launch(Dispatchers.IO) {
            allNames = appDb.dao().getAllNames() as ArrayList<Names>
            if(allNames.isEmpty()){
                presenterName.onDisplayListEmpty()
            }
            else{
                presenterName.onDisplayListNotEmpty()
                withContext(Dispatchers.Main) {
                    recyclerView.adapter = AdapterNames(allNames, context as ViewName)
                }
            }
        }
    }

    //Función para eliminar un nombre del recycler view
    override fun getDeleteName(nameDelete: String, recyclerView: RecyclerView, context: Context) {
        GlobalScope.launch(Dispatchers.IO) {
            appDb.dao().getDeleteName(nameDelete)
            if (appDb.dao().getAllNames().isEmpty()) {
                Handler(Looper.getMainLooper()).post {
                    presenterName.onDisplayListEmpty()
                }
            } else {
                Handler(Looper.getMainLooper()).post {
                    presenterName.onDisplayListNotEmpty()
                }
            }

            // Eliminar el elemento del ArrayList
            val iterator = allNames.iterator()
            while (iterator.hasNext()) {
                val name = iterator.next()
                if (name.name == nameDelete) {
                    iterator.remove()
                }
            }

            allNames = appDb.dao().getAllNames() as ArrayList<Names>
            Handler(Looper.getMainLooper()).post {
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = AdapterNames(allNames, context as ViewName)
            }
        }
    }

    //Obtener el lenguaje
    override fun getLang(recyclerView: RecyclerView,context: Context) {
        appDb = DataBase.getDataBase(context)
        GlobalScope.launch(Dispatchers.IO) {
            var allLang = appDb.dao().getLang() as ArrayList<Languages>
            withContext(Dispatchers.Main) {
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = AdapterLang(allLang, context as ViewName)
            }
        }

    }

    override fun closeDialogue(context: Context, lang : String, nameDeletes: String, recyclerView: RecyclerView) {
        GlobalScope.launch(Dispatchers.IO){
            appDb.dao().updateLanguage(nameDeletes, lang)
            allNames = appDb.dao().getAllNames() as ArrayList<Names>
            withContext(Dispatchers.Main) {
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = AdapterNames(allNames, context as ViewName)
            }
        }
    }
}