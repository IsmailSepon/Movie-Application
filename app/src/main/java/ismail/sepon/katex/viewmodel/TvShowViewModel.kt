package ismail.sepon.katex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ismail.sepon.katex.network.Coroutines
import ismail.sepon.katex.pojo.TvShowResponse
import ismail.sepon.katex.repository.MovieRepository
import kotlinx.coroutines.Job


/**
 * Created by MD ISMAIL HOSSAIN SEPON on 13-Aug-21.
 * ismailhossainsepon@gmail.com
 */
class TvShowViewModel (private val repository: MovieRepository) : ViewModel() {

    private lateinit var job: Job

    private val response = MutableLiveData<TvShowResponse>()
    val tvShowlist : LiveData<TvShowResponse>
        get() = response

    fun getTvshowList() {
        //coroutines
        job = Coroutines.ioThenMain(
            { repository.getTvshowList() },
            { response.value = it }
        )

    }
}