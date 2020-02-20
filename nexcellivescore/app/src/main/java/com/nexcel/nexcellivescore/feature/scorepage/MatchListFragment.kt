package com.nexcel.nexcellivescore.feature.scorepage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nexcel.nexcellivescore.Constant.Companion.TIME
import com.nexcel.nexcellivescore.R
import com.nexcel.nexcellivescore.viewmodel.FootballMatchViewModel
import kotlinx.android.synthetic.main.fragment_match_list.matchList
import timber.log.Timber
import java.util.Date

class MatchListFragment : Fragment(R.layout.fragment_match_list) {

    lateinit var footballMatchViewModel: FootballMatchViewModel
    private lateinit var footballMatchAdapter: FootballMatchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        matchList.apply {
            footballMatchAdapter = FootballMatchAdapter(mutableListOf())
            adapter = footballMatchAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        activity?.let { activity ->
            footballMatchViewModel =
                ViewModelProviders.of(activity).get(FootballMatchViewModel::class.java)
            footballMatchViewModel.addFootballMatchListObserver(
                this,
                onMatchListChanged = { footballMatchList ->
                    Timber.d("Football matches: ${footballMatchList.size}")
                    footballMatchAdapter.updateMatchList(footballMatchList)
                })
            arguments?.getLong(TIME, 0L)?.takeIf { it != 0L }?.let { time ->
                footballMatchViewModel.currentDate = Date(time)
            }
        }
    }
}