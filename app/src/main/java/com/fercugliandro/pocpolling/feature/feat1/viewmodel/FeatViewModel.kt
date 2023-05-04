package com.fercugliandro.pocpolling.feature.feat1.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fercugliandro.pocpolling.commons.viewmodel.BaseViewModel
import com.fercugliandro.pocpolling.feature.feat1.poll.DataPoller
import com.fercugliandro.pocpolling.feature.feat1.repository.FeatRepositoryImpl
import com.fercugliandro.pocpolling.feature.feat1.viewmodel.states.FeatState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeatViewModel: BaseViewModel() {

    private val state = MutableLiveData<FeatState.PollerState>()
    open val viewState: LiveData<FeatState.PollerState> = state

    private val repository = FeatRepositoryImpl()

    fun initPoll() {
        state.value = FeatState.PollerState.ACTIVE
        val dataPoller = DataPoller(this, repository, Dispatchers.IO)
        val data = dataPoller.poll(2000, "Teste")
        launch {
            data.collect {
                Log.i("COLLECT", it)
                if (it == "2") {
                    state.value = FeatState.PollerState.INACTIVE
                }
            }
        }

    }



}