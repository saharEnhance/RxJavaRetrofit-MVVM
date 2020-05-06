package com.example.w1d3_rxjavademo.view

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.w1d3_rxjavademo.R
import com.example.w1d3_rxjavademo.inject.Injection
import com.example.w1d3_rxjavademo.network.model.Ticket
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import androidx.lifecycle.Observer
import io.reactivex.schedulers.Schedulers
import kotlin.math.roundToInt
import com.example.w1d3_rxjavademo.network.remote.ApiService
import com.example.w1d3_rxjavademo.viewmodel.TicketViewModel
import com.example.w1d3_rxjavademo.viewmodel.TicketViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: TicketViewModel
    lateinit var mAdapter: TicketsAdapter
    val injection = Injection()


    private val from = "DEL"
    private val to = "HYD"
    // CompositeDisposable is used to dispose the subscriptions in onDestroy() method.
    private val disposable = CompositeDisposable()
    lateinit var apiService: ApiService
    //lateinit var mAdapter: TicketsAdapter
    private var ticketsList: MutableList<Ticket> = mutableListOf()
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "$from > $to"

        viewModel = ViewModelProvider(
            this,
            TicketViewModelFactory(injection.provideUserRepo())
        ).get(TicketViewModel::class.java)

        viewModel.stateLiveData.observe(this, Observer { appState ->
            when (appState) {
                is TicketViewModel.AppState.LOADING -> displayLoading()
                is TicketViewModel.AppState.SUCCESS -> displayWords(appState.ticketList)
                is TicketViewModel.AppState.ERROR -> displayMessage(appState.message)
                else -> displayMessage("Something Went Wrong... Try Again.")
            }
        })
        initRecyclerView()
    }
    private fun displayWords(ticketsList: MutableList<Ticket>) {
        // set recycler to eliminate flicker
        mAdapter.updateTickets(ticketsList)

        // set correct visible element
     /*   progressBar.visibility = View.GONE
        rvNews.visibility = View.VISIBLE
        messageText.visibility = View.GONE*/
    }

    private fun displayLoading() {
        // set correct visible element
    /*    progressBar.visibility = View.VISIBLE
        rvNews.visibility = View.GONE
        messageText.visibility = View.GONE*/
    }

    private fun displayMessage(message: String) {
        // set correct visible element
    /*    progressBar.visibility = View.GONE
        rvNews.visibility = View.GONE
        messageText.visibility = View.VISIBLE
        //set message
        messageText.text = message*/
    }
    private fun initRecyclerView() {
        mAdapter = TicketsAdapter(applicationContext, ticketsList){ ticket : Ticket -> onTicketSelected(ticket) }
        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 1)
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.addItemDecoration(GridSpacingItemDecoration(1, dpToPx(5), true))
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = mAdapter
    }

    private fun dpToPx(dp: Int): Int {
        val r: Resources = resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            r.displayMetrics
        ).roundToInt()
    }
    private fun onTicketSelected(ticket: Ticket?) {

        Toast.makeText(this, "Clicked: ${ticket?.flightNumber}", Toast.LENGTH_LONG).show()

    }
  /*  private fun ticketSearch() {
        viewModel.searchTickets(word_search)
    }*/
}