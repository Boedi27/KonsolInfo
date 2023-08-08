package com.example.mysubmissionkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvThings: RecyclerView
    private val list = ArrayList<Thing>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvThings = findViewById(R.id.rv_things)
        rvThings.setHasFixedSize(true)

        list.addAll(getListThings())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.about_page -> {
                val intent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListThings(): ArrayList<Thing> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataCompany = resources.getStringArray(R.array.data_company)
        val dataConsoleType = resources.getStringArray(R.array.data_console_type)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listThing = ArrayList<Thing>()

        for (i in dataName.indices) {
            val thing = Thing(dataName[i], dataCompany[i],dataConsoleType[i] ,dataDescription[i], dataPhoto.getResourceId(i, -1))
            listThing.add(thing)
        }
        return listThing
    }

    private fun showRecyclerList() {
        rvThings.layoutManager = LinearLayoutManager(this)
        val listThingAdapter = ListThingAdapter(list)
        rvThings.adapter = listThingAdapter

        listThingAdapter.setOnItemClickCallback(object :ListThingAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Thing) {
                val thing = Thing(
                    data.name,
                    data.company,
                    data.consoleType,
                    data.description,
                    data.photo
                )
                val moveWithObjectIntent = Intent(this@MainActivity, DetailActivity::class.java)
                moveWithObjectIntent.putExtra(DetailActivity.EXTRA_THING, thing)
                startActivity(moveWithObjectIntent)
            }
        })
    }

    private fun showSelectedThing(thing: Thing) {
        //Toast.makeText(this, "Kamu memilih " + thing.name, Toast.LENGTH_SHORT).show()
    }
}