package ismail.sepon.katex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ismail.sepon.katex.network.Coroutines
import ismail.sepon.katex.pojo.MovieResponse
import ismail.sepon.katex.repository.MovieRepository
import kotlinx.coroutines.Job


/**
 * Created by MD ISMAIL HOSSAIN SEPON on 13-Aug-21.
 * ismailhossainsepon@gmail.com
 */
class ListViewModel (private val repository: MovieRepository) : ViewModel() {

    private lateinit var job: Job

    private val response = MutableLiveData<MovieResponse>()
    val movielist : LiveData<MovieResponse>
        get() = response

    fun getJObList() {

        job = Coroutines.ioThenMain(
            { repository.getMovieLisst() },
            { response.value = it }
        )

    }
}