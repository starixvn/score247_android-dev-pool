package com.nexcel.nexcellivescore.usecase

import com.nexcel.nexcellivescore.datasource.FootballRepository
import com.nexcel.nexcellivescore.datasource.FootballRepositoryImpl
import com.nexcel.nexcellivescore.entity.FootballMatch
import io.reactivex.Single

interface GetFootballMatchListUseCase {
    fun execute(): Single<List<FootballMatch>>
}

class GetFootballMatchListUseCaseImpl : GetFootballMatchListUseCase {
    private val footballRepository: FootballRepository = FootballRepositoryImpl()

    override fun execute(): Single<List<FootballMatch>> {
        return footballRepository.getMatchList()
    }

}