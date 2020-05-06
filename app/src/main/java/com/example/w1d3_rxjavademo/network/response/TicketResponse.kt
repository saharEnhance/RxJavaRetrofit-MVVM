package com.example.w1d3_rxjavademo.network.response

import com.example.w1d3_rxjavademo.network.model.Price
import com.example.w1d3_rxjavademo.network.model.Ticket

data class TicketResponse(val ticketsList: MutableList<Ticket>)

data class PriceResponse(val priceList: Price)