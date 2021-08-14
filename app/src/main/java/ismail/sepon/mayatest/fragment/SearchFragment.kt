package ismail.sepon.mayatest.fragment

import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import ismail.sepon.mayatest.R
import ismail.sepon.mayatest.adapter.MoviesAdapter
import ismail.sepon.mayatest.adapter.SearchAdapter
import ismail.sepon.mayatest.factory.MovieFactory
import ismail.sepon.mayatest.factory.SearchFactory
import ismail.sepon.mayatest.network.ApiService
import ismail.sepon.mayatest.network.NetworkConnectionInterceptor
import ismail.sepon.mayatest.repository.MovieRepository
import ismail.sepon.mayatest.viewmodel.ListViewModel
import ismail.sepon.mayatest.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.search_fragment.*
import kotlinx.android.synthetic.main.search_fragment.view.*


/**
 * Created by MD ISMAIL HOSSAIN SEPON on 13-Aug-21.
 * ismailhossainsepon@gmail.com
 */
class SearchFragment : Fragment() {

    private lateinit var viewmodel: SearchViewModel
    private lateinit var factory : SearchFactory

    var showList = arrayOf("Movie", "Tv Show")
    var showType  : String = "Movie"

    var progressDialog: ProgressDialog? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.search_fragment, container, false)



        generateSpinner(view)



        view.button.setOnClickListener(View.OnClickListener {

            if (view.search.getText().toString().equals("")){
                view.search.setError("Required")
            }else{
                if (showType.equals("Movie")){
                    getMovies( view.search.getText().toString())
                }else{
                    getTvshow( view.search.getText().toString())
                }


                view.search.setText("")
                hiddenSoftKeyboard()
            }



        })

        return view
    }

    private fun generateSpinner( view: View) {

        view.type_spinner.onItemSelectedListener
        val aa: ArrayAdapter<*> = ArrayAdapter<Any?>(requireContext(), android.R.layout.simple_spinner_item, showList)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        view.type_spinner.adapter = aa
        view.type_spinner.setSelection(0)



        view.type_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {


                showType = showList.get(position)
                Toast.makeText(requireContext(), showType, Toast.LENGTH_SHORT).show()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

    }


    private  fun getMovies(query : String) {


        // Without Dependency Injection
        val networkConnectionInterceptor = NetworkConnectionInterceptor(requireActivity().applicationContext)
        val api = ApiService(networkConnectionInterceptor)
        val repository = MovieRepository(api)

        factory = SearchFactory(repository)
        viewmodel = ViewModelProviders.of(this, factory).get(SearchViewModel::class.java)
        viewmodel.getMovieSearch(query)


        viewmodel.movielist.observe(requireActivity(), Observer { jobs ->
            Log.e("response", jobs.results!!.size.toString())
            search_recyclerview.also {
                it.layoutManager  = LinearLayoutManager(requireActivity().applicationContext, LinearLayoutManager.VERTICAL, false)
                it.setHasFixedSize(true)
                it.adapter = SearchAdapter(jobs.results, requireActivity().applicationContext)
            }
        })


    }


    private  fun getTvshow(query : String) {


        // Without Dependency Injection
        val networkConnectionInterceptor = NetworkConnectionInterceptor(requireActivity().applicationContext)
        val api = ApiService(networkConnectionInterceptor)
        val repository = MovieRepository(api)

        factory = SearchFactory(repository)
        viewmodel = ViewModelProviders.of(this, factory).get(SearchViewModel::class.java)
        viewmodel.getTvSearch(query)


        viewmodel.movielist.observe(requireActivity(), Observer { jobs ->
            Log.e("response", jobs.results!!.size.toString())
            search_recyclerview.also {
                it.layoutManager  = LinearLayoutManager(requireActivity().applicationContext, LinearLayoutManager.VERTICAL, false)
                it.setHasFixedSize(true)
                it.adapter = SearchAdapter(jobs.results, requireActivity().applicationContext)
            }
        })


    }



    fun hiddenSoftKeyboard() {
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }
}