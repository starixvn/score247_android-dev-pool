package com.nexcel.nexcellivescore.feature.scorepage

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexcel.nexcellivescore.NexcelTextView
import com.nexcel.nexcellivescore.R
import com.nexcel.nexcellivescore.entity.FootballMatch
import com.nexcel.nexcellivescore.utils.getDisplayTime
import kotlinx.android.synthetic.main.item_match.view.awayTeamName
import kotlinx.android.synthetic.main.item_match.view.fullTimeScore
import kotlinx.android.synthetic.main.item_match.view.halfTimeScore
import kotlinx.android.synthetic.main.item_match.view.homeTeamName
import kotlinx.android.synthetic.main.item_match.view.leagueCode
import kotlinx.android.synthetic.main.item_match.view.startTime

class FootballMatchAdapter(
    private val matchList: MutableList<FootballMatch>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MatchViewHolder(layoutInflater.inflate(R.layout.item_match, parent, false))
    }

    override fun getItemCount(): Int = matchList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val match = matchList[position]
        (holder as MatchViewHolder).setData(match)
    }

    fun updateMatchList(matches: List<FootballMatch>) {
        this.matchList.clear()
        this.matchList.addAll(matches)
        notifyDataSetChanged()
    }

    private inner class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val leagueCode: NexcelTextView = itemView.leagueCode
        private val startTime: NexcelTextView = itemView.startTime
        private val homeTeamName: NexcelTextView = itemView.homeTeamName
        private val awayTeamName: NexcelTextView = itemView.awayTeamName
        private val fullTimeScore: NexcelTextView = itemView.fullTimeScore
        private val halfTimeScore: NexcelTextView = itemView.halfTimeScore

        @SuppressLint("SetTextI18n")
        fun setData(footballMatch: FootballMatch) {
            leagueCode.text = footballMatch.leagueCode
            startTime.text = footballMatch.startDateTime.getDisplayTime()
            homeTeamName.text = footballMatch.homeTeam
            awayTeamName.text = footballMatch.awayTeam
            fullTimeScore.text =
                "${footballMatch.homeTeamScore()} - ${footballMatch.awayTeamScore()}"
            halfTimeScore.text =
                "(${footballMatch.homeTeamScore()} - ${footballMatch.awayTeamScore()})"
        }
    }
}