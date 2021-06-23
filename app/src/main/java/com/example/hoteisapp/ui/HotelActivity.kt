package com.example.hoteisapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import com.example.hoteisapp.R
import com.example.hoteisapp.model.Hotel

class HotelActivity : AppCompatActivity(), HotelListFragment.OnHotelClickListener,
    SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener,
    HotelFormFragment.OnHotelSavedListener {

    private var lastSearchTerm: String = ""
    private var searchView: SearchView? = null

    private val listFragment: HotelListFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.fragmentList) as HotelListFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(EXTRA_SEARCH_TERM, lastSearchTerm)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        lastSearchTerm = savedInstanceState.getString(EXTRA_SEARCH_TERM) ?: ""
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.hotel, menu)
        val searchItem = menu.findItem(R.id.action_search)
        searchItem.setOnActionExpandListener(this)
        searchView = searchItem.actionView as SearchView
        searchView?.queryHint = getString(R.string.hint_search)
        searchView?.setOnQueryTextListener(this)
        if (lastSearchTerm.isNotEmpty()) {
            Handler().post {
                val query = lastSearchTerm
                searchItem.expandActionView()
                searchView?.setQuery(query, true)
                searchView?.clearFocus()
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.action_new ->
                HotelFormFragment.newInstance().open(supportFragmentManager)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onHotelClick(hotel: Hotel) {
        showDetailsActivity(hotel.id)
    }

    private fun showDetailsActivity(hotelId: Long) {
        searchView?.setOnQueryTextListener(null)
        HotelDetailsActivity.open(this, hotelId)
    }

    override fun onQueryTextSubmit(query: String?) = true


    override fun onQueryTextChange(newText: String): Boolean {
        lastSearchTerm = newText ?: ""
        listFragment.search(lastSearchTerm)
        return true
    }

    override fun onMenuItemActionExpand(item: MenuItem?) = true

    override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
        lastSearchTerm = ""
        listFragment.clearSearch()
        return true
    }

    companion object {
        const val EXTRA_SEARCH_TERM = "lastSearch"
    }

    override fun onHotelSaved(hotel: Hotel) {
        listFragment.search(lastSearchTerm)
    }
}