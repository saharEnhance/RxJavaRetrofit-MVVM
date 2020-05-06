package com.example.w1d3_rxjavademo.network.model

import com.example.w1d3_rxjavademo.network.response.PriceResponse
import com.example.w1d3_rxjavademo.network.response.TicketResponse
import io.reactivex.Single

interface TicketRepository {

    fun getTicketsList(from: String, to: String): Single<TicketResponse>

    fun getPriceList(flightNumber: String, from: String, to: String): Single<PriceResponse>
}