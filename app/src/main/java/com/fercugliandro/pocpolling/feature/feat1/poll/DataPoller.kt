package com.fercugliandro.pocpolling.feature.feat1.poll

import android.util.Log
import com.fercugliandro.pocpolling.feature.feat1.repository.FeatRepository
import com.fercugliandro.pocpolling.feature.feat1.viewmodel.FeatViewModel
import com.fercugliandro.pocpolling.feature.feat1.viewmodel.states.FeatState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn

class DataPoller(
    private val viewModel: FeatViewModel,
    private val repository: FeatRepository,
    private val dispacher: CoroutineDispatcher
): Poller {
    @ExperimentalCoroutinesApi
    override fun poll(delay: Long, query: String): Flow<String> {
        return channelFlow {
            while (!isClosedForSend) {
                if (viewModel.viewState.value == FeatState.PollerState.INACTIVE) {
                    Log.i("POLL-STOP", "Poller is stopping")
                    close()
                    return@channelFlow
                }
                Log.i("POLL", "Poller is running")
                send(repository.callApi())
                delay(delay)
            }
        }.flowOn(dispacher)
    }
}