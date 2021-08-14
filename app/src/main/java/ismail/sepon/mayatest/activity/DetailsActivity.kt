package ismail.sepon.mayatest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ismail.sepon.mayatest.R
import android.content.Intent
import android.widget.Toast
import com.bumptech.glide.Glide
import ismail.sepon.mayatest.pojo.ResultsItem
import ismail.sepon.mayatest.pojo.TvResultsItem
import kotlinx.android.synthetic.main.activity_details.*


class DetailsActivity : AppCompatActivity() {


    val imageBaseUrl : String = "https://image.tmdb.org/t/p/w500"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val i = intent
        val bundle = i.extras
        val type : Int = intent.getIntExtra("type",0)


        if (type == 1){

            val movie : ResultsItem? = bundle!!.getSerializable("movie") as ResultsItem?
            loadimage(movie)

        }else if (type == 2){

            val movie : TvResultsItem? = bundle!!.getSerializable("tv") as TvResultsItem?
            loadTv(movie)
        }else{
            this.finish()
        }



    }

    private fun loadimage(movie: ResultsItem?) {
        val imageUrl : String =  imageBaseUrl+ movie?.posterPath!!
        Glide.with(this)
            .load(imageUrl)
            .into(details_image);
        details.text = movie.overview
        title_txt.text = movie.title

    }

    private fun loadTv(movie: TvResultsItem?) {
        val imageUrl : String =  imageBaseUrl+ movie?.posterPath!!
        Glide.with(this)
            .load(imageUrl)
            .into(details_image);
        details.text = movie.overview
        title_txt.text = movie.name

    }
}