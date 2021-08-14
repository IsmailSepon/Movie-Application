package ismail.sepon.mayatest.fragment

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import ismail.sepon.mayatest.R
import ismail.sepon.mayatest.activity.DetailsActivity
import ismail.sepon.mayatest.adapter.TvShowAdapter
import ismail.sepon.mayatest.base.ProgressDialogFrament
import ismail.sepon.mayatest.factory.TvShowFactory
import ismail.sepon.mayatest.network.ApiService
import ismail.sepon.mayatest.network.NetworkConnectionInterceptor
import ismail.sepon.mayatest.pojo.TvResultsItem
import ismail.sepon.mayatest.repository.MovieRepository
import ismail.sepon.mayatest.viewmodel.TvShowViewModel
import kotlinx.android.synthetic.main.tvshow_fragment.*


class TvShowFragment : Fragment() , TvShowAdapter.TvRecyclerViewClickListener {

    private lateinit var viewmodel: TvShowViewModel

    private lateinit var factory : TvShowFactory

    var progressDialog: ProgressDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.tvshow_fragment, container, false)






        getMovies()



        return view
    }



    private  fun getMovies() {


        // Without Dependency Injection
        val networkConnectionInterceptor = NetworkConnectionInterceptor(requireActivity().applicationContext)
        val api = ApiService(networkConnectionInterceptor)
        val repository = MovieRepository(api)

        factory = TvShowFactory(repository)
        viewmodel = ViewModelProviders.of(this, factory).get(TvShowViewModel::class.java)
        viewmodel.getTvshowList()


        viewmodel.tvShowlist.observe(requireActivity(), Observer { jobs ->
            Log.e("tv_response", jobs.results!!.size.toString())
            tv_recyclerview.also {
                it.layoutManager  = LinearLayoutManager(requireActivity().applicationContext, LinearLayoutManager.VERTICAL, false)
                it.setHasFixedSize(true)
                it.adapter = TvShowAdapter(jobs.results, requireActivity().applicationContext, this)
            }
        })


    }

    override fun onRecyclerViewItemClick(view: View, movie: TvResultsItem) {

        val intent = Intent(requireContext(), DetailsActivity::class.java)
        val b = Bundle()
        b.putSerializable("tv", movie)
        intent.putExtras(b) //pass bundle to your intent
        intent.putExtra("type", 2)
        startActivity(intent)
    }


}