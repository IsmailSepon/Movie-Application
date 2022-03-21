package ismail.sepon.katex.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ismail.sepon.katex.R
import ismail.sepon.katex.databinding.SearchIttemBinding
import ismail.sepon.katex.pojo.SearchResultsItem

/**
 * Created by MD ISMAIL HOSSAIN SEPON on 13-Aug-21.
 * ismailhossainsepon@gmail.com
 */
class SearchAdapter (
    private val movies: List<SearchResultsItem?>?,
    mContext: Context
) : RecyclerView.Adapter<SearchAdapter.JobsViewHolder>(){

    private val mContext = mContext
    override fun getItemCount() = movies!!.size
    val imageBaseUrl : String = "https://image.tmdb.org/t/p/w500"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        JobsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.search_ittem,
                parent,
                false
            )
        )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: JobsViewHolder, position: Int) {

        holder.recyclerviewJobBinding.movie = movies!![position]

        if (movies[position]?.posterPath != null){
            val imageUrl : String =  imageBaseUrl+ movies[position]!!.posterPath!!
            Glide.with(mContext)
                .load(imageUrl)
                .into(holder.recyclerviewJobBinding.imageView);
        }


    }



    inner class JobsViewHolder(
        val recyclerviewJobBinding: SearchIttemBinding
    ) : RecyclerView.ViewHolder(recyclerviewJobBinding.root)

}