package ismail.sepon.mayatest

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.bumptech.glide.load.engine.Resource
import io.reactivex.Single
import ismail.sepon.mayatest.network.ApiService
import ismail.sepon.mayatest.network.NetworkConnectionInterceptor
import ismail.sepon.mayatest.network.SafeApiRequest
import ismail.sepon.mayatest.pojo.MovieResponse
import ismail.sepon.mayatest.repository.MovieRepository
import ismail.sepon.mayatest.viewmodel.ListViewModel
import ismail.sepon.mayatest.viewmodel.TestCorotine
import junit.framework.Assert.assertEquals
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner
import retrofit2.Response.success
import kotlin.Result.Companion.success
import org.mockito.MockitoAnnotations

import org.junit.Before
import org.junit.runner.manipulation.Ordering
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.Response
import java.lang.Exception


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