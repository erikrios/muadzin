package com.warokdroid.muadzin.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ProgressBar
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.warokdroid.muadzin.R
import com.warokdroid.muadzin.adapters.CityAdapter
import com.warokdroid.muadzin.apis.ApiRepository
import com.warokdroid.muadzin.models.City
import com.warokdroid.muadzin.presenters.CityMainPresenter
import com.warokdroid.muadzin.views.CityMainView

class MainActivity : AppCompatActivity(), CityMainView {

    private lateinit var rvCities: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: CityMainPresenter
    private val cities: MutableList<City> = mutableListOf()
    private lateinit var cityAdapter: CityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        val gson = Gson()
        val request = ApiRepository()
        presenter = CityMainPresenter(this, request, gson)
        presenter.getCityList()
        setActionBar(getString(R.string.choose_city))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchFilter: MenuItem? = menu?.findItem(R.id.item_search_filter)
        val searchView: SearchView = searchFilter?.actionView as SearchView
        searchView.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView.queryHint = resources.getString(R.string.city_search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText?.isEmpty()!!)
                    presenter.getCityList()
                cityAdapter.filter.filter(newText)
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.item_about_developers) {
            val aboutDevelopersIntent = Intent(this, BiodataActivity::class.java)
            startActivity(aboutDevelopersIntent)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initViews() {
        rvCities = findViewById(R.id.rv_cities)
        progressBar = findViewById(R.id.progress_bar)
    }

    private fun setRecyclerList() {
        cityAdapter = CityAdapter(this, cities)
        rvCities.setHasFixedSize(true)
        rvCities.layoutManager = LinearLayoutManager(this)
        rvCities.adapter = cityAdapter
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showCityList(data: List<City>?) {
        cities.clear()
        data?.let { cities.addAll(it) }
        setRecyclerList()
        cityAdapter.notifyDataSetChanged()
    }

    private fun setActionBar(title: String?) {
        if (supportActionBar != null)
            (supportActionBar as ActionBar).title = title
    }
}
