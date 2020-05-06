package com.example.w1d3_rxjavademo.inject

import com.example.w1d3_rxjavademo.network.model.TicketRepository
import com.example.w1d3_rxjavademo.network.model.TicketRepositoryImpl
import com.example.w1d3_rxjavademo.network.remote.TicketRestService

class Injection {

    private var userRestService: TicketRestService? = null

    fun provideUserRepo(): TicketRepository {
        return TicketRepositoryImpl(provideTicketRestService())
    }

    private fun provideTicketRestService(): TicketRestService {
        if (userRestService == null) {
            userRestService = TicketRestService.instance
        }
        return userRestService as TicketRestService
    }
}