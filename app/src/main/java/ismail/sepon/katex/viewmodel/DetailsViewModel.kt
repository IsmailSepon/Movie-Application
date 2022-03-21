package ismail.sepon.katex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ismail.sepon.katex.network.Coroutines
import ismail.sepon.katex.pojo.MovieDetailsResponse
import ismail.sepon.katex.repository.MovieRepository
import kotlinx.coroutines.Job


/**
 * Created by MD ISMAIL HOSSAIN SEPON on 13-Aug-21.
 * ismailhossainsepon@gmail.com
 */
class DetailsViewModel (private val repository: MovieRepository) : ViewModel() {

    private lateinit var job: Job

    private val response = MutableLiveData<MovieDetailsResponse>()
    val movieDetails : LiveData<MovieDetailsResponse>
        get() = response

    fun getMovieDetails(url : String) {
        job = Coroutines.ioThenMain(
            { repository.getMovieDetails(url) },
            { response.value = it }
        )

    }
}