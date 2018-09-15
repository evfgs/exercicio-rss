package br.ufpe.cin.if710.rss

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : Activity() {

    //ao fazer envio da resolucao, use este link no seu codigo!

   // private val RSS_FEED = "http://leopoldomt.com/if1001/g1brasil.xml"

    //OUTROS LINKS PARA TESTAR...
    //http://rss.cnn.com/rss/edition.rss
    //http://pox.globo.com/rss/g1/brasil/
    //http://pox.globo.com/rss/g1/ciencia-e-saude/
    //http://pox.globo.com/rss/g1/tecnologia/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Definindo o layoutManager do conteudoRSS
        conteudoRSS.layoutManager = LinearLayoutManager(this)

    }

    override fun onStart() {
        super.onStart()
        try {
            //Usando tarefa assincrona por meio de doAsync
            //https://antonioleiva.com/anko-background-kotlin-android/
            //Utilizando o RssAdapter e o Resource que está em strings.xml
            doAsync { val feedXML = ParserRSS.parse(getRssFeed(getString(R.string.rssfeed)))
            uiThread { conteudoRSS.adapter = RssAdapter(feedXML, this@MainActivity)
                conteudoRSS.addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))

            }}
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    //Opcional - pesquise outros meios de obter arquivos da internet - bibliotecas, etc.
    //Quando passou para Kotlin ocorreram alguns bugs que precisaram ser corrigidos
    @Throws(IOException::class)
    private fun getRssFeed(feed: String): String {
        var inputStream: InputStream? = null
        var rssFeed = ""
        try {
            val url = URL(feed)
            val conn = url.openConnection() as HttpURLConnection
            inputStream = conn.inputStream
            val out = ByteArrayOutputStream()
            val buffer = ByteArray(1024)
            var count = inputStream.read(buffer)
            while (count != -1) {
                out.write(buffer, 0, count)
                count = inputStream.read(buffer)
            }
            val response = out.toByteArray()
            rssFeed = response.toString(charset("UTF-8"))
        } finally {
            inputStream?.close()
        }
        return rssFeed
    }
}
