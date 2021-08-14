package ismail.sepon.mayatest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ismail.sepon.mayatest.network.Coroutines
import ismail.sepon.mayatest.pojo.MovieResponse
import ismail.sepon.mayatest.pojo.TvShowResponse
import ismail.sepon.mayatest.repository.MovieRepository
import kotlinx.coroutines.Job
import okhttp3.ResponseBody


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