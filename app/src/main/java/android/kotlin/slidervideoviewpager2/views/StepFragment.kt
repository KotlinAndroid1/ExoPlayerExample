package android.kotlin.slidervideoviewpager2.views

import android.kotlin.slidervideoviewpager2.data.model.Step
import android.kotlin.slidervideoviewpager2.databinding.FragmentStepBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer

private const val ARG_STEP = "step"

@Suppress("DEPRECATION")
class StepFragment : Fragment() {
    private val binding by lazy { FragmentStepBinding.inflate(layoutInflater) }
    private var player: Player? = null
    private var step: Step? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            step = it.getParcelable(ARG_STEP)
        }
        player = ExoPlayer.Builder(requireContext()).build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillViews()
        loadVideoStep()
        initEvents()
    }

    private fun fillViews() {
        step?.let { step ->
            binding.run {
                title.text = step.title
                subtitle.text = step.subtitle
            }
        }
    }

    private fun loadVideoStep() {
        binding.playerView.player = player
        val mediaItem = MediaItem.fromUri(step?.videoUrl ?: "")
        player?.setMediaItem(mediaItem)
        player?.prepare()
        player?.play()
        binding.buttonPlay.visibility = View.GONE
    }

    private fun initEvents() {
        binding.buttonPlay.setOnClickListener {
            player?.isPlaying?.let { isPlaying ->
                if (!isPlaying) {
                    player?.play()
                    binding.buttonPlay.visibility = View.GONE
                }
            }
        }

        player?.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == Player.STATE_ENDED) {
                    player?.seekTo(0)
                    player?.pause()
                    binding.buttonPlay.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onPause() {
        super.onPause()
        player?.pause()
        binding.buttonPlay.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
        player = null
    }

    companion object {
        fun newInstance(step: Step) = StepFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_STEP, step)
            }
        }
    }
}