package ismail.sepon.mayatest.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ismail.sepon.mayatest.R
import ismail.sepon.mayatest.databinding.MovieIttemBinding
import ismail.sepon.mayatest.pojo.ResultsItem

/**
 * Created by MD ISMAIL HOSSAIN SEPON on 13-Aug-21.
 * ismailhossainsepon@gmail.com
 */
class MoviesAdapter (
    private val movies: List<ResultsItem?>?,
    mContext: Context,
    private val listener: RecyclerViewClickListener
) : RecyclerView.Adapter<MoviesAdapter.JobsViewHolder>(){

    private val mContext = mContext
    override fun getItemCount() = movies!!.size
    val imageBaseUrl : String = "https://image.tmdb.org/t/p/w500"



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        JobsViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.movie_ittem, parent, false)
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


        holder.itemView.setOnClickListener{
            listener.onRecyclerViewItemClick(it, movies[position]!!)
        }


    }




    inner class JobsViewHolder(
        val recyclerviewJobBinding: MovieIttemBinding
    ) : RecyclerView.ViewHolder(recyclerviewJobBinding.root)



    interface RecyclerViewClickListener {
        fun onRecyclerViewItemClick(view: View, movie: ResultsItem)
    }
}