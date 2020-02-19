package com.nexcel.nexcellivescore.datasource

import com.nexcel.nexcellivescore.entity.FootballMatch
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

interface FootballRepository {
    fun getMatchList(): Single<List<FootballMatch>>
}

class FootballRepositoryImpl @Inject constructor(

) : FootballRepository {
    override fun getMatchList(): Single<List<FootballMatch>> {
        return Single.just(listOf(FootballMatch(Date(), "Juventus FC", "Barcelona FC")))
    }
}