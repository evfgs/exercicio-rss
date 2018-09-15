package br.ufpe.cin.if710.rss

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.itemlista.view.*

//https://github.com/if710/2018.2-codigo/blob/master/2018-08-24/Adapters/app/src/main/java/br/ufpe/cin/if710/adapters/PessoaAdapter.kt
class RssAdapter(private val listRss: List<ItemRSS>, private val c: Context) : RecyclerView.Adapter<RssAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(c).inflate(R.layout.itemlista, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listRss.size
    }

    override fun onBindViewHolder(holder: RssAdapter.ViewHolder, position: Int) {
        holder.bindModel(listRss?.get(position))


    }

    inner class ViewHolder (item : View) : RecyclerView.ViewHolder(item), View.OnClickListener{
        var data: TextView? = null
        var titulo: TextView? = null
        var site: Uri? = null


        init {
            data = item.item_data
            titulo = item.item_titulo

            titulo!!.setOnClickListener(this)
        }

        fun bindModel(itemRss: ItemRSS){
            data?.text = itemRss?.pubDate
            titulo?.text = itemRss?.title
            site = Uri.parse(itemRss?.link)
        }


        override fun onClick(v: View?) {
            //https://stackoverflow.com/questions/50304974/how-to-start-activity-from-adapter-in-kotlin-android
            val intent = Intent(Intent.ACTION_VIEW, site)
            c.startActivity(intent)

        }


    }

}