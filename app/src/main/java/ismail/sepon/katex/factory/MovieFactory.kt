package ismail.sepon.katex.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ismail.sepon.katex.repository.MovieRepository
import ismail.sepon.katex.viewmodel.ListViewModel


/**
 * Created by MD ISMAIL HOSSAIN SEPON on 13-Aug-21.
 * ismailhossainsepon@gmail.com
 */
class MovieFactory(private val repository: MovieRepository)  : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListViewModel(repository) as T
    }
}