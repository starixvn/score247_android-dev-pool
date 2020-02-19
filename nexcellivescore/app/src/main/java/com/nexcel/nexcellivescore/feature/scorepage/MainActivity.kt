package com.nexcel.nexcellivescore.feature.scorepage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.nexcel.nexcellivescore.R
import kotlinx.android.synthetic.main.activity_main.mainFeatureList

class MainActivity : AppCompatActivity(), OnPageChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpScorePage()
    }

    override fun onDestroy() {
        mainFeatureList.clearOnPageChangeListeners()
        super.onDestroy()
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {

    }

    private fun setUpScorePage() {
        mainFeatureList.apply {
            adapter = MainPagerAdapter(supportFragmentManager)
            addOnPageChangeListener(this@MainActivity)
        }
    }
}
