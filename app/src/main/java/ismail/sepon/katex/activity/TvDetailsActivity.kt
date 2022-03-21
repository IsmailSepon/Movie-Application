package ismail.sepon.katex.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import ismail.sepon.katex.R
import ismail.sepon.katex.factory.TvDetailsFactory
import ismail.sepon.katex.pojo.TvResultsItem
import ismail.sepon.katex.viewmodel.TvDetailsViewModel
import kotlinx.android.synthetic.main.activity_tv_details.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class TvDetailsActivity : AppCompatActivity()  , KodeinAware {

    override val kodein by kodein()
    private lateinit var viewmodel: TvDetailsViewModel
    private  val factory : TvDetailsFactory by  instance()

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

//        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
//        val api = ApiService(networkConnectionInterceptor)
//        val repository = MovieRepository(api)
//        factory = TvDetailsFactory(repository)
//+++++++++++++++++++++++Depedency Injection handle this


        viewmodel = ViewModelProviders.of(this, factory).get(TvDetailsViewModel::class.java)
        viewmodel.getTvDetails("tv/"+tv?.id)

        viewmodel.tvDetails.observe(this, Observer {jobs ->
            Log.e("tv", jobs.overview.toString())

            if (jobs?.posterPath != null){
                val imageUrl : String =  imageBaseUrl+ jobs?.posterPath!!
                Glide.with(this)
                    .load(imageUrl)
                    .into(tv_details_image);
                tv_details.text = jobs.overview
                tv_title_txt.text = jobs.originalName

            }


        })

    }



}