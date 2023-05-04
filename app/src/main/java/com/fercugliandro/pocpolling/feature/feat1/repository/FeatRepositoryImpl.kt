package com.fercugliandro.pocpolling.feature.feat1.repository

import kotlin.random.Random.Default.nextInt

class FeatRepositoryImpl: FeatRepository {
    override fun callApi(): String {
        return nextInt(0, 10).toString()
    }
}