package com.nexcel.nexcellivescore.feature.scorepage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.nexcel.nexcellivescore.Constant.Companion.TIME
import com.nexcel.nexcellivescore.R
import com.nexcel.nexcellivescore.viewmodel.FootballMatchViewModel
import kotlinx.android.synthetic.main.fragment_match_list.dateTitle
import java.util.Date

class MatchListFragment : Fragment(R.layout.fragment_match_list) {

    lateinit var footballMatchViewModel: FootballMatchViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getLong(TIME, 0L)?.takeIf { it != 0L }?.let { time ->
            val date = Date(time)
            dateTitle.text = date.toString()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let { activity ->
            footballMatchViewModel =
                ViewModelProviders.of(activity).get(FootballMatchViewModel::class.java)
            footballMatchViewModel.addFootballMatchListObserver(
                this,
                onMatchListChanged = { footballMatchList ->

                })
            footballMatchViewModel.updateCurrentMatchList()
        }
    }
}