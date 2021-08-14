package ismail.sepon.mayatest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ismail.sepon.mayatest.network.Coroutines
import ismail.sepon.mayatest.pojo.MovieSearchResponse
import ismail.sepon.mayatest.repository.MovieRepository
import kotlinx.coroutines.Job
import okhttp3.ResponseBody


/**
 * Created by MD ISMAIL HOSSAIN SEPON on 13-Aug-21.
 * ismailhossainsepon@gmail.com
 */
class SearchViewModel (private val repository: MovieRepository) : ViewModel() {

    private lateinit var job: Job

    private val response = MutableLiveData<MovieSearchResponse>()
    val movielist : LiveData<MovieSearchResponse>
        get() = response

    fun getMovieSearch(query : String) {

        job = Coroutines.ioThenMain(
            { repository.getsearchList(query) },
            { response.value = it }
        )

    }

    fun getTvSearch(query : String) {

        job = Coroutines.ioThenMain(
            { repository.getsearchtvList(query) },
            { response.value = it }
        )

    }
}