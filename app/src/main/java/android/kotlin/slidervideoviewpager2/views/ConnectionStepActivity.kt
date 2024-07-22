package android.kotlin.slidervideoviewpager2.views

import android.kotlin.slidervideoviewpager2.adapter.StepSliderVideoAdapter
import android.kotlin.slidervideoviewpager2.data.ExamData
import android.kotlin.slidervideoviewpager2.databinding.ActivityConnectionStepBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ConnectionStepActivity : AppCompatActivity() {
    private val binding by lazy { ActivityConnectionStepBinding.inflate(layoutInflater) }
    private lateinit var stepSliderVideoAdapter: StepSliderVideoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupSliderVideo()
        initEvents()
    }

    private fun setupSliderVideo() {
        stepSliderVideoAdapter = StepSliderVideoAdapter(this, ExamData.steps)
        binding.sliderVideo.adapter = stepSliderVideoAdapter
        binding.dotsIndicator.attachTo(binding.sliderVideo)
    }

    private fun initEvents() {
        binding.buttonNextStep.setOnClickListener {
            val currentItem = binding.sliderVideo.currentItem
            if (currentItem < stepSliderVideoAdapter.itemCount - 1) {
                binding.sliderVideo.currentItem = currentItem + 1
            }
        }
    }
}