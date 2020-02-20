package com.nexcel.nexcellivescore.datasource

import com.nexcel.nexcellivescore.entity.FootballMatch
import io.reactivex.Single
import java.util.Date
import javax.inject.Inject
import kotlin.random.Random

interface FootballRepository {
    fun getMatchList(): Single<List<FootballMatch>>
}

// Todo: update this to provide real data later
class FootballRepositoryImpl @Inject constructor(

) : FootballRepository {
    private val today = Date()
    private val footballMatches = arrayListOf<FootballMatch>().apply {
        add(FootballMatch("Juventus FC", "Barcelona FC", today, "CPL C1"))
        add(FootballMatch("Liverpool FC", "Real Madrid FC", today, "CPL C1"))
        add(FootballMatch("Chelsea FC", "Atletico Madrid FC", today, "CPL C1"))
        add(FootballMatch("Totenham Hospur FC", "Bayemunich FC", today, "CPL C1"))
        add(FootballMatch("Manchester City FC", "Dortmun FC", today, "CPL C1"))
        add(FootballMatch("AS Roma Hospur FC", "Ajax Amsterdam FC", today, "CPL C1"))
    }

    override fun getMatchList(): Single<List<FootballMatch>> {
        val randomMatchCounts = Random.nextInt(footballMatches.size, footballMatches.size * 2)
        val matchList = arrayListOf<FootballMatch>()
        for (i in 0 until randomMatchCounts) {
            val randomMatchIndex = Random.nextInt(0, footballMatches.size)
            matchList.add(footballMatches[randomMatchIndex])
        }
        return Single.just(matchList)
    }
}