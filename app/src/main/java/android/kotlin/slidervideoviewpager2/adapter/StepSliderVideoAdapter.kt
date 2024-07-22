package android.kotlin.slidervideoviewpager2.adapter

import android.kotlin.slidervideoviewpager2.data.model.Step
import android.kotlin.slidervideoviewpager2.views.StepFragment
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class StepSliderVideoAdapter(fragmentActivity: FragmentActivity, private val steps: List<Step>) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = steps.size

    override fun createFragment(position: Int): Fragment {
        Log.d("TAG", "createFragment: ${steps[position]}")
        return StepFragment.newInstance(steps[position])
    }
}