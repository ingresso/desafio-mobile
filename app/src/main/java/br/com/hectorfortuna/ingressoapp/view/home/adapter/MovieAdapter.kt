package br.com.hectorfortuna.ingressoapp.view.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.hectorfortuna.ingressoapp.R
import br.com.hectorfortuna.ingressoapp.data.model.Item
import br.com.hectorfortuna.ingressoapp.databinding.MovieItemBinding
import com.bumptech.glide.Glide

class MovieAdapter(
    private val results: List<Item>,
    private val itemClick: ((item: Item) -> Unit)
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding, itemClick)
    }

    override fun getItemCount() = results.count()

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindView(results[position])
    }

    class MovieViewHolder(
        private val binding: MovieItemBinding,
        private val itemClick: ((item: Item) -> Unit)
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(movieItem: Item) {
            binding.run {
                txtNameCharacterItem.text = movieItem.title

                Glide.with(itemView)
                    .load(
                        movieItem.images?.firstOrNull { it.type == "PosterPortrait" }?.url
                            ?: R.drawable.ic_movie_clapperboard
                    )
                    .centerCrop()
                    .into(imgItem)

                movieItem.premiereDate?.let {
                    val formattedDate = "${it.dayAndMonth}/${it.year}"
                    txtPremiereDate.text = formattedDate
                } ?: run {
                    txtPremiereDate.visibility = View.INVISIBLE
                }
                itemView.setOnClickListener {
                    itemClick.invoke(movieItem)
                }

                if (movieItem.inPreSale == true) {
                    binding.txtInPreSale.apply {
                        visibility = View.VISIBLE
                    }
                    val preSaleColor = ContextCompat.getColor(binding.cardViewItem.context, R.color.pre_sale);
                    binding.cardViewItem.setCardBackgroundColor(preSaleColor);
                } else {
                    binding.txtInPreSale.visibility = View.INVISIBLE
                    val cardColor = ContextCompat.getColor(binding.cardViewItem.context, R.color.white);
                    binding.cardViewItem.setCardBackgroundColor(cardColor);
                }
            }
        }
    }


}