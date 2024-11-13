package br.com.hectorfortuna.ingressoapp.view.detail.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.hectorfortuna.ingressoapp.R
import br.com.hectorfortuna.ingressoapp.data.model.Item
import br.com.hectorfortuna.ingressoapp.databinding.FragmentDetailBinding
import com.bumptech.glide.Glide
import timber.log.Timber

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var movieDetail: Item

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieDetail = arguments?.getParcelable<Item>("MOVIE")
            ?: throw IllegalArgumentException("Movie not found in arguments")

        Timber.tag("Data").e(movieDetail.toString())
        setDetailTexts()
        setupShareButton()
        setImage()
    }

    private fun setDetailTexts() {
        premiereDate()
        binding.apply {
            txtTitle.text = movieDetail.title ?: ""
            txtGenre.text = movieDetail.genres?.joinToString (", ") ?: ""
            txtCast.text = movieDetail.cast
            txtDuration.text = "${movieDetail.duration}m "?: ""
            txtDescription.text = movieDetail.synopsis ?: ""
        }
    }

    private fun premiereDate(){
        movieDetail.premiereDate?.let {
            val formattedDate = "${it.dayAndMonth}/${it.year}"
            binding.txtPremiereDateDetail.text = formattedDate
        } ?: run {
            binding.txtPremiereDateDetail.visibility = View.INVISIBLE
        }
    }
    private fun setImage() {
        binding.apply {
            Glide.with(this@DetailFragment)
                .load(movieDetail.images?.firstOrNull { it.type == "PosterPortrait" }?.url
                    ?: R.drawable.ic_movie_clapperboard)
                .centerCrop()
                .into(imgPoster)
        }
    }

    private fun setupShareButton() {
        binding.btnShare.setOnClickListener {
            shareMovie()
        }
    }

    private fun shareMovie() {
        val urlToShare = movieDetail.nationalSiteURL?: movieDetail.siteURL ?: "URL não disponível"

        val shareText = "Confira o filme '${movieDetail.title}'! $urlToShare"

        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }

        startActivity(Intent.createChooser(shareIntent, "Compartilhar filme"))
}
}