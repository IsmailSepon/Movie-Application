package ismail.sepon.mayatest.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import ismail.sepon.mayatest.R
import ismail.sepon.mayatest.factory.TvDetailsFactory
import ismail.sepon.mayatest.network.ApiService
import ismail.sepon.mayatest.network.NetworkConnectionInterceptor
import ismail.sepon.mayatest.pojo.TvResultsItem
import ismail.sepon.mayatest.repository.MovieRepository
import ismail.sepon.mayatest.viewmodel.TvDetailsViewModel
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_tv_details.*

class TvDetailsActivity : AppCompatActivity() {

    private lateinit var viewmodel: TvDetailsViewModel
    private lateinit var factory : TvDetailsFactory

    val imageBaseUrl : String = "https://image.tmdb.org/t/p/w500"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_details)

        val i = intent
        val bundle = i.extras
        val movie : TvResultsItem? = bundle!!.getSerializable("tv") as TvResultsItem?
        loadimage(movie)

    }



    private fun loadimage(tv: TvResultsItem?) {

        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = ApiService(networkConnectionInterceptor)
        val repository = MovieRepository(api)


        factory = TvDetailsFactory(repository)
        viewmodel = ViewModelProviders.of(this, factory).get(TvDetailsViewModel::class.java)
        viewmodel.getTvDetails("tv/"+tv?.id)

        viewmodel.tvDetails.observe(this, Observer {jobs ->
            Log.e("tv", jobs.overview.toString())

            val imageUrl : String =  imageBaseUrl+ jobs?.posterPath!!
            Glide.with(this)
                .load(imageUrl)
                .into(tv_details_image);
            tv_details.text = jobs.overview
            tv_title_txt.text = jobs.originalName


        })

    }



}