package ismail.sepon.mayatest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ismail.sepon.mayatest.network.Coroutines
import ismail.sepon.mayatest.pojo.TvDetailsResponse
import ismail.sepon.mayatest.repository.MovieRepository
import kotlinx.coroutines.Job


/**
 * Created by MD ISMAIL HOSSAIN SEPON on 13-Aug-21.
 * ismailhossainsepon@gmail.com
 */
class TvDetailsViewModel(private val repository: MovieRepository) : ViewModel() {

    private lateinit var job: Job

    private val response = MutableLiveData<TvDetailsResponse>()
    val tvDetails : LiveData<TvDetailsResponse>
        get() = response

    fun getTvDetails(url : String) {
        job = Coroutines.ioThenMain(
            { repository.getTvDetails(url) },
            { response.value = it }
        )

    }
}