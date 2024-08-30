package com.jakovsaric.footballleaguesapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CountryAdapter
    private val competitions = mutableListOf<Competition>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = CountryAdapter(competitions) { competition ->
            val intent = Intent(this, DetailsActivity::class.java).apply {
                putExtra("name", competition.area.name)
                putExtra("flag", competition.area.flag)
                putExtra("leagueName", competition.name)
                putExtra("emblem", competition.emblemUrl)
            }
            startActivity(intent)
        }

        recyclerView.adapter = adapter

        getDataFromApi()
    }

    private fun getDataFromApi() {
        val apiUrl = "https://api.football-data.org/v4/competitions"

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val url = URL(apiUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.setRequestProperty("X-Auth-Token", "22be09555d3f49548868924d027a39d9")
                connection.requestMethod = "GET"

                val responseCode = connection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val reader = connection.inputStream.bufferedReader()
                    val response = reader.readText()

                    val jsonData = JSONObject(response)
                    val competitionsArray = jsonData.getJSONArray("competitions")

                    for (i in 0 until competitionsArray.length()) {
                        val compJson = competitionsArray.getJSONObject(i)
                        val areaJson = compJson.getJSONObject("area")

                        val competition = Competition(
                            id = compJson.getInt("id"),
                            name = compJson.getString("name"),
                            area = Area(
                                name = areaJson.getString("name"),
                                flag = areaJson.optString("flag", null)
                            ),
                            emblemUrl = compJson.optString("emblem", null)
                        )

                        if (areaJson.getString("name") in listOf(
                                "England", "Spain", "Germany", "Italy", "France", "Brazil", "Portugal", "World", "Netherlands" /* Add more countries */
                            )
                        ) {
                            competitions.add(competition)
                        }
                    }

                    launch(Dispatchers.Main) {
                        adapter.notifyDataSetChanged()
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
