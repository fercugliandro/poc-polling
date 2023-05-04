package com.fercugliandro.pocpolling.feature.feat1.viewmodel.states

class FeatState {

    sealed class PollerState {
        object INACTIVE: PollerState()
        object ACTIVE: PollerState()
    }
}