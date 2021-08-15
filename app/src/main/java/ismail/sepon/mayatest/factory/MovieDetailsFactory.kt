package ismail.sepon.mayatest.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ismail.sepon.mayatest.repository.MovieRepository
import ismail.sepon.mayatest.viewmodel.DetailsViewModel
import ismail.sepon.mayatest.viewmodel.ListViewModel


/**
 * Created by MD ISMAIL HOSSAIN SEPON on 13-Aug-21.
 * ismailhossainsepon@gmail.com
 */
class MovieDetailsFactory(private val repository: MovieRepository)  : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailsViewModel(repository) as T
    }
}