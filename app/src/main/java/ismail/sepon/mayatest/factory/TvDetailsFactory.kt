package ismail.sepon.mayatest.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ismail.sepon.mayatest.pojo.TvDetailsResponse
import ismail.sepon.mayatest.repository.MovieRepository
import ismail.sepon.mayatest.viewmodel.TvDetailsViewModel


/**
 * Created by MD ISMAIL HOSSAIN SEPON on 13-Aug-21.
 * ismailhossainsepon@gmail.com
 */
class TvDetailsFactory(private val repository: MovieRepository)  : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TvDetailsViewModel(repository) as T
    }
}