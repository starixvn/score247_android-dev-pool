package com.nexcel.nexcellivescore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nexcel.nexcellivescore.utils.increase
import com.nexcel.nexcellivescore.utils.decrease
import com.nexcel.nexcellivescore.utils.getDisplayDateOfMonthText
import com.nexcel.nexcellivescore.utils.getShortDayOfWeekText
import kotlinx.android.synthetic.main.item_calendar.view.calendarIcon
import kotlinx.android.synthetic.main.item_date.view.dateOfMonth
import kotlinx.android.synthetic.main.item_date.view.dayOfWeek
import kotlinx.android.synthetic.main.item_live.view.liveIcon
import java.util.Date

class CalendarBarAdapter(
    private val today: Date,
    private val itemSelectedListener: ItemSelectedListener
) : RecyclerView.Adapter<ViewHolder>() {
    var currentFocusedPosition: Int = TODAY_INDEX
        set(value) {
            if (value in 0 until TOTAL_ITEMS) {
                val lastFocusedPosition = field
                field = value
                notifyItemChanged(lastFocusedPosition)
                notifyItemChanged(value)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            LIVE_NOW_ITEM -> LiveViewHolder(
                layoutInflater.inflate(
                    R.layout.item_live,
                    parent,
                    false
                )
            )
            CALENDAR_ITEM -> CalendarViewHolder(
                layoutInflater.inflate(
                    R.layout.item_calendar,
                    parent,
                    false
                )
            )
            else -> DateViewHolder(layoutInflater.inflate(R.layout.item_date, parent, false))
        }
    }

    override fun getItemCount(): Int = TOTAL_ITEMS

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> LIVE_NOW_ITEM
            TOTAL_ITEMS - 1 -> CALENDAR_ITEM
            else -> DATE_ITEM
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val focused = position == currentFocusedPosition
        when (getItemViewType(position)) {
            DATE_ITEM -> {
                val itemDate = when {
                    position > TODAY_INDEX -> today.increase(position - TODAY_INDEX)
                    position < TODAY_INDEX -> today.decrease(TODAY_INDEX - position)
                    else -> today
                }
                (holder as DateViewHolder).setData(itemDate, focused, position)
            }

            LIVE_NOW_ITEM -> (holder as LiveViewHolder).setFocused(focused)

            CALENDAR_ITEM -> (holder as CalendarViewHolder).setFocused(focused)
        }
    }

    private inner class LiveViewHolder(itemView: View) : ViewHolder(itemView) {
        private val liveIcon: ImageView = itemView.liveIcon

        init {
            itemView.setOnClickListener {
                itemSelectedListener.selectLiveItem()
                currentFocusedPosition = 0
            }
        }

        fun setFocused(focused: Boolean) {
            liveIcon.isActivated = focused
        }
    }

    private inner class DateViewHolder(itemView: View) : ViewHolder(itemView) {
        private val dayOfWeek: TextView = itemView.dayOfWeek
        private val dateOfMonth: TextView = itemView.dateOfMonth

        fun setData(date: Date, focused: Boolean, position: Int) {
            dayOfWeek.text = if (date == today) {
                dayOfWeek.context.getString(R.string.today)
            } else {
                date.getShortDayOfWeekText()
            }
            dayOfWeek.isEnabled = focused
            dateOfMonth.text = date.getDisplayDateOfMonthText()
            dateOfMonth.isEnabled = focused

            itemView.setOnClickListener {
                itemSelectedListener.selectDate(date)
                currentFocusedPosition = position
            }
        }
    }

    private inner class CalendarViewHolder(itemView: View) : ViewHolder(itemView) {
        private val calendarIcon: ImageView = itemView.calendarIcon

        init {
            itemView.setOnClickListener {
                itemSelectedListener.selectCalendar()
                currentFocusedPosition = TOTAL_ITEMS - 1
            }
        }

        fun setFocused(focused: Boolean) {
            calendarIcon.isActivated = focused
        }
    }

    interface ItemSelectedListener {
        fun selectLiveItem()
        fun selectCalendar()
        fun selectDate(date: Date)
    }

    companion object {
        private const val LIVE_NOW_ITEM = 1
        private const val DATE_ITEM = 2
        private const val CALENDAR_ITEM = 3

        private const val TOTAL_ITEMS = 7
        private const val TODAY_INDEX = TOTAL_ITEMS / 2
    }
}