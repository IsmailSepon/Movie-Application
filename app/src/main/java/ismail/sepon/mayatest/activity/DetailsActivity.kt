package ismail.sepon.mayatest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ismail.sepon.mayatest.R
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import ismail.sepon.mayatest.adapter.MoviesAdapter
import ismail.sepon.mayatest.factory.MovieDetailsFactory
import ismail.sepon.mayatest.factory.MovieFactory
import ismail.sepon.mayatest.network.ApiService
import ismail.sepon.mayatest.network.NetworkConnectionInterceptor
import ismail.sepon.mayatest.pojo.ResultsItem
import ismail.sepon.mayatest.pojo.TvResultsItem
import ismail.sepon.mayatest.repository.MovieRepository
import ismail.sepon.mayatest.viewmodel.DetailsViewModel
import ismail.sepon.mayatest.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.fragment_list.*


class DetailsActivity : AppCompatActivity() {

    private lateinit var viewmodel: DetailsViewModel
    private lateinit var factory : MovieDetailsFactory

    val imageBaseUrl : String = "https://image.tmdb.org/t/p/w500"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val i = intent
        val bundle = i.extras

//        val type : Int = intent.getIntExtra("type",0)
//        if (type == 1){

            val movie : ResultsItem? = bundle!!.getSerializable("movie") as ResultsItem?
            loadimage(movie)

//        }else if (type == 2){
//
//            val movie : TvResultsItem? = bundle!!.getSerializable("tv") as TvResultsItem?
//            loadTv(movie)
//        }else{
//            this.finish()
//        }



    }

    private fun loadimage(movie: ResultsItem?) {

        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = ApiService(networkConnectionInterceptor)
        val repository = MovieRepository(api)


        factory = MovieDetailsFactory(repository)
        viewmodel = ViewModelProviders.of(this, factory).get(DetailsViewModel::class.java)
        viewmodel.getMovieDetails("movie/"+movie?.id)

        viewmodel.movieDetails.observe(this, Observer {jobs ->
            Log.e("response", jobs.overview.toString())

            val imageUrl : String =  imageBaseUrl+ jobs?.posterPath!!
        Glide.with(this)
            .load(imageUrl)
            .into(details_image);
        details.text = jobs.overview
        title_txt.text = jobs.title


        })

    }

    private fun loadTv(movie: TvResultsItem?) {
//        val imageUrl : String =  imageBaseUrl+ movie?.posterPath!!
//        Glide.with(this)
//            .load(imageUrl)
//            .into(details_image);
//        details.text = movie.overview
//        title_txt.text = movie.name

    }

}