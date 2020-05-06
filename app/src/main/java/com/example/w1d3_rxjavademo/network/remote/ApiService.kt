package com.example.w1d3_rxjavademo.network.remote

import io.reactivex.Single
import retrofit2.http.GET
import com.example.w1d3_rxjavademo.network.model.Price
import com.example.w1d3_rxjavademo.network.model.Ticket
import retrofit2.http.Query

interface ApiService {

    @GET("airline-tickets.php")
    fun searchTickets(@Query("from") from: String, @Query("to") to: String): Single<List<Ticket>>

    @GET("airline-tickets-price.php")
    fun getPrice(@Query("flight_number") flightNumber: String, @Query("from") from: String, @Query("to") to: String): Single<Price>
}