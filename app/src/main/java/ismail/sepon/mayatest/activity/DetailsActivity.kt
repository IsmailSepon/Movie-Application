package ismail.sepon.mayatest.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import ismail.sepon.mayatest.R
import ismail.sepon.mayatest.factory.MovieDetailsFactory
import ismail.sepon.mayatest.pojo.ResultsItem
import ismail.sepon.mayatest.pojo.TvResultsItem
import ismail.sepon.mayatest.viewmodel.DetailsViewModel
import kotlinx.android.synthetic.main.activity_details.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class DetailsActivity : AppCompatActivity() , KodeinAware {

    override val kodein by kodein()
    private lateinit var viewmodel: DetailsViewModel
    private  val factory : MovieDetailsFactory by  instance()

    val imageBaseUrl : String = "https://image.tmdb.org/t/p/w500"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val i = intent
        val bundle = i.extras
        val movie : ResultsItem? = bundle!!.getSerializable("movie") as ResultsItem?
        loadimage(movie)



    }

    private fun loadimage(movie: ResultsItem?) {

//        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
//        val api = ApiService(networkConnectionInterceptor)
//        val repository = MovieRepository(api)
//        factory = MovieDetailsFactory(repository)
//+++++++++++++++++++++++Depedency Injection handle this

        viewmodel = ViewModelProviders.of(this, factory).get(DetailsViewModel::class.java)
        viewmodel.getMovieDetails("movie/"+movie?.id)

        viewmodel.movieDetails.observe(this, Observer {jobs ->
            Log.e("response", jobs.overview.toString())

            if (jobs?.posterPath != null){
                val imageUrl : String =  imageBaseUrl+ jobs?.posterPath!!
                Glide.with(this)
                    .load(imageUrl)
                    .into(details_image);
                details.text = jobs.overview
                title_txt.text = jobs.title

            }


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