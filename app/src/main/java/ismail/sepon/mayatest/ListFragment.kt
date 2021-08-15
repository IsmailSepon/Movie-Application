package ismail.sepon.mayatest

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
import ismail.sepon.mayatest.adapter.MoviesAdapter
import ismail.sepon.mayatest.factory.MovieFactory
import ismail.sepon.mayatest.network.ApiService
import ismail.sepon.mayatest.network.NetworkConnectionInterceptor
import ismail.sepon.mayatest.pojo.ResultsItem
import ismail.sepon.mayatest.repository.MovieRepository
import ismail.sepon.mayatest.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import android.content.Intent
import ismail.sepon.mayatest.activity.DetailsActivity


class ListFragment : Fragment() , MoviesAdapter.RecyclerViewClickListener {

    private lateinit var viewmodel: ListViewModel

    private lateinit var factory : MovieFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)






        getMovies()



        return view
    }



    private  fun getMovies() {
        // Without Dependency Injection
        val networkConnectionInterceptor = NetworkConnectionInterceptor(requireActivity().applicationContext)
        val api = ApiService(networkConnectionInterceptor)
        val repository = MovieRepository(api)

        factory = MovieFactory(repository)
        viewmodel = ViewModelProviders.of(this, factory).get(ListViewModel::class.java)
        viewmodel.getJObList()


        viewmodel.movielist.observe(requireActivity(), Observer { jobs ->
            Log.e("response", jobs.results!!.size.toString())
            recyclerview.also {
                it.layoutManager  = LinearLayoutManager(requireActivity().applicationContext, LinearLayoutManager.VERTICAL, false)
                it.setHasFixedSize(true)
                it.adapter = MoviesAdapter(jobs.results, requireActivity().applicationContext, this)
            }
        })

    }

    override fun onRecyclerViewItemClick(view: View, movie: ResultsItem) {

        val intent = Intent(requireContext(), DetailsActivity::class.java)
        val b = Bundle()
        b.putSerializable("movie", movie)
        intent.putExtras(b) //pass bundle to your intent
//        intent.putExtra("type", 1)
        startActivity(intent)

    }



}