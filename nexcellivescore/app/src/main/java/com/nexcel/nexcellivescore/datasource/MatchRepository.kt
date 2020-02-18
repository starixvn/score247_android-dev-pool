package com.nexcel.nexcellivescore.datasource

import com.nexcel.nexcellivescore.entity.FootballMatch
import io.reactivex.Single

interface MatchRepository {
    fun getFootballMatchList(): Single<List<FootballMatch>>
}