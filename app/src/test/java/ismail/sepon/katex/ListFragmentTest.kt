package ismail.sepon.katex

import android.content.Context
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import ismail.sepon.katex.network.ApiService
import ismail.sepon.katex.network.NetworkConnectionInterceptor
import ismail.sepon.katex.pojo.MovieResponse
import ismail.sepon.katex.repository.MovieRepository
import ismail.sepon.katex.viewmodel.ListViewModel
import ismail.sepon.katex.viewmodel.TestCorotine
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito

import org.junit.Before
import org.mockito.Mockito.*


/**
 * Created by MD ISMAIL HOSSAIN SEPON on 15-Aug-21.
 * ismailhossainsepon@gmail.com
 */
@RunWith(JUnit4::class)

class ListFragmentTest : TestCase() {

    @ExperimentalCoroutinesApi
    @get:Rule
    var testCoroutineRule: TestCorotine = TestCorotine()

    @Mock
    private lateinit  var intercepter: NetworkConnectionInterceptor //= NetworkConnectionInterceptor(context)

    @Mock
    private lateinit  var apiHelper: ApiService // = ApiService(intercepter)

    @Mock
    private lateinit var repo: MovieRepository //= MovieRepository(apiHelper)

    @Mock
    private lateinit var apiUsersObserver: Observer<MovieResponse>



    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        intercepter = NetworkConnectionInterceptor(context)
        apiHelper = ApiService(intercepter)
        repo = MovieRepository(apiHelper)


    }



    @ExperimentalCoroutinesApi
    @Test
    fun givenServerResponse_whenFetch_Success() {

        testCoroutineRule.test {

            doReturn(emptyList<MovieResponse>())
                .`when`(apiHelper)
                .getMovieList("eb8aa6f914f794f711fb1841fb141f12")
            val viewmodel = ListViewModel(repo)
            viewmodel.movielist.observeForever(apiUsersObserver)
            Mockito.verify(apiHelper).getMovieList("eb8aa6f914f794f711fb1841fb141f12")
          //  Mockito.verify(apiUsersObserver).onChanged("Success")
            viewmodel.movielist.removeObserver(apiUsersObserver)

        }

    }


}