package br.ufpe.cin.if710.rss

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.itemlista.view.*

class RssAdapter(private val listRss: List<ItemRSS>, private val c: Context) : RecyclerView.Adapter<RssAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(c).inflate(R.layout.itemlista, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listRss.size
    }

    override fun onBindViewHolder(holder: RssAdapter.ViewHolder, position: Int) {
        val r = listRss[position]
        holder?.date?.text = r.pubDate
        holder?.title?.text = r.title
    }

    class ViewHolder (item : View) : RecyclerView.ViewHolder(item){
        val title  = item.item_titulo
        val date = item.item_data

    }

}