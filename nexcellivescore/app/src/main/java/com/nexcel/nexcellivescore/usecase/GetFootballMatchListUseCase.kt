package com.nexcel.nexcellivescore.usecase

import com.nexcel.nexcellivescore.entity.FootballMatch
import io.reactivex.Single

interface GetFootballMatchListUseCase {
    fun execute(): Single<List<FootballMatch>>
}