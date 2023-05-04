package com.fercugliandro.pocpolling.feature.feat1.poll

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

interface Poller {
    @ExperimentalCoroutinesApi
    fun poll(delay: Long, query: String): Flow<String>
}