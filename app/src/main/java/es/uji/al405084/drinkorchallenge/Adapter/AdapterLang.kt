package es.uji.al405084.drinkorchallenge.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.uji.al405084.drinkorchallenge.DataBase.DataBase
import es.uji.al405084.drinkorchallenge.DataBase.Languages
import es.uji.al405084.drinkorchallenge.MVPName.ViewName
import es.uji.al405084.drinkorchallenge.R

class AdapterLang (private val namesList:ArrayList<Languages>, private val mainActivity: ViewName) :
    RecyclerView.Adapter<AdapterLang.LangViewHolder>()  {

    class LangViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.langpost)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterLang.LangViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.customlang, parent, false)
        return AdapterLang.LangViewHolder(view)
    }

    override fun getItemCount(): Int {
        return namesList.size
    }

    override fun onBindViewHolder(holder: AdapterLang.LangViewHolder, position: Int) {
        val name = namesList[position]
        holder.name.text = name.lang

        holder.itemView.setOnClickListener {
            mainActivity.closeDialog(name.lang)
        }
    }

}