package android.kotlin.slidervideoviewpager2.views

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.kotlin.slidervideoviewpager2.R
import android.kotlin.slidervideoviewpager2.databinding.ActivitySplashBinding
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySplashBinding.inflate(layoutInflater) }
    private lateinit var player: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(binding.root)

        player = ExoPlayer.Builder(this).build().apply {
            binding.splashPlayerView.player = this
            val mediaItem = MediaItem.fromUri("android.resource://android.kotlin.slidervideoviewpager2/${R.raw.splash}")
            setMediaItem(mediaItem)
            prepare()
            play()
        }

        player.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == ExoPlayer.STATE_READY) {
                    Handler(Looper.getMainLooper()).postDelayed({
                        val anim = ActivityOptions.makeCustomAnimation(
                            applicationContext,
                            R.anim.slide_in_right,
                            R.anim.slide_in_right
                        ).toBundle()
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java), anim)
                        finish()
                    }, 3000)
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}
