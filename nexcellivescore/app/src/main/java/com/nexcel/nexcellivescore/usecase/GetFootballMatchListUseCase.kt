package com.nexcel.nexcellivescore.usecase

import com.nexcel.nexcellivescore.datasource.MatchRepository
import com.nexcel.nexcellivescore.datasource.MatchRepositoryImpl
import com.nexcel.nexcellivescore.entity.FootballMatch
import io.reactivex.Single

interface GetFootballMatchListUseCase {
    fun execute(): Single<List<FootballMatch>>
}

class GetFootballMatchListUseCaseImpl : GetFootballMatchListUseCase {
    private val matchRepository: MatchRepository = MatchRepositoryImpl()

    override fun execute(): Single<List<FootballMatch>> {
        return matchRepository.getFootballMatchList()
    }

}