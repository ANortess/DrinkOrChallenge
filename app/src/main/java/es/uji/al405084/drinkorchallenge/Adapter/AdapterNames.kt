package es.uji.al405084.drinkorchallenge.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.uji.al405084.drinkorchallenge.DataBase.Names
import es.uji.al405084.drinkorchallenge.MVPName.ViewName
import es.uji.al405084.drinkorchallenge.R

class AdapterNames(private val namesList: ArrayList<Names>, private val mainActivity: ViewName) :
    RecyclerView.Adapter<AdapterNames.NamesViewHolder>() {
    var txtName = ""
    var txtLang = ""

    class NamesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.name_post)
        val language : TextView = itemView.findViewById(R.id.textLanguage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NamesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.customname, parent, false)
        return NamesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return namesList.size
    }

    override fun onBindViewHolder(holder: NamesViewHolder, position: Int) {
        val name = namesList[position]
        holder.name.text = name.name
        holder.language.text = name.lang

        holder.itemView.setOnClickListener {
            // val root = (holder.itemView.context as Activity).window.decorView.rootView
            // val txtName = root.findViewById<Button>(R.id.nameDeleted)
            txtLang = name.lang
            txtName = name.name
            mainActivity.nameFilter(txtName)
            mainActivity.customDialog(txtName)
        }
    }
}