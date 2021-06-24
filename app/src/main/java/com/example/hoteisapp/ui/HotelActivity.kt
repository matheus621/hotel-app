package com.example.hoteisapp.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import com.example.hoteisapp.R
import com.example.hoteisapp.model.Hotel
import com.example.hoteisapp.presenter.HotelDetailsPresenter
import kotlinx.android.synthetic.main.activity_hotel.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HotelActivity : AppCompatActivity(), HotelListFragment.OnHotelClickListener,
    SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener,
    HotelFormFragment.OnHotelSavedListener {

    private var lastSearchTerm: String = ""
    private var searchView: SearchView? = null
    private var hotelIdSelected: Long = -1
    private var hotel: Hotel? = null

    private val listFragment: HotelListFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.fragmentList) as HotelListFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel)

        buttonAdd.setOnClickListener {
            HotelFormFragment.newInstance().open(supportFragmentManager)
        }
    }

    override fun onHotelClick(hotel: Hotel) {
        hotelIdSelected = hotel.id
        showDetailsActivity(hotelIdSelected)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(EXTRA_HOTEL_ID_SELECTED, hotelIdSelected)
        outState.putString(EXTRA_SEARCH_TERM, lastSearchTerm)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        hotelIdSelected = savedInstanceState?.getLong(EXTRA_HOTEL_ID_SELECTED) ?: 0
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
        if (item.itemId == R.id.action_edit) {
            HotelFormFragment
                .newInstance(hotel?.id ?: 0)
                .open(supportFragmentManager)
        }
        return super.onOptionsItemSelected(item)
    }


    private fun showDetailsActivity(hotelId: Long) {
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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == RESULT_OK){
            listFragment.search(lastSearchTerm)
        }
    }

    override fun onHotelSaved(hotel: Hotel) {
        listFragment.search(lastSearchTerm)
        val detailsFragment = supportFragmentManager
            .findFragmentByTag(HotelDetailsFragment.TAG_DETAILS) as? HotelDetailsFragment
        if (detailsFragment != null && hotel.id == hotelIdSelected ){
            showDetailsActivity(hotelIdSelected)
        }
    }

    companion object {
        const val EXTRA_SEARCH_TERM = "lastSearch"
        const val EXTRA_HOTEL_ID_SELECTED = "lastSelectedId"
    }


}