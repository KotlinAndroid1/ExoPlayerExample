package android.kotlin.slidervideoviewpager2.views

import android.content.Intent
import android.kotlin.slidervideoviewpager2.databinding.ActivityMainBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initEvents()
    }

    private fun initEvents() {
        binding.buttonConnection.setOnClickListener { startConnectionStepActivity() }
    }

    private fun startConnectionStepActivity() {
        val intent = Intent(this, ConnectionStepActivity::class.java)
        startActivity(intent)
    }
}