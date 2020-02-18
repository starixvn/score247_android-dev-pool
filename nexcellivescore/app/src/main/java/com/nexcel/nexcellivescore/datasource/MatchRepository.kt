package com.nexcel.nexcellivescore.datasource

import com.nexcel.nexcellivescore.entity.FootballMatch
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

interface MatchRepository {
    fun getFootballMatchList(): Single<List<FootballMatch>>
}

class MatchRepositoryImpl @Inject constructor(

) : MatchRepository {
    override fun getFootballMatchList(): Single<List<FootballMatch>> {
        return Single.just(listOf(FootballMatch(Date(), "Juventus FC", "Barcelona FC")))
    }
}