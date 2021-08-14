package ismail.sepon.mayatest.repository

import ismail.sepon.mayatest.network.ApiService
import ismail.sepon.mayatest.network.SafeApiRequest
import ismail.sepon.mayatest.pojo.MovieResponse
import ismail.sepon.mayatest.pojo.MovieSearchResponse
import ismail.sepon.mayatest.pojo.TvShowResponse
import okhttp3.ResponseBody
import retrofit2.Response


/**
 * Created by MD ISMAIL HOSSAIN SEPON on 13-Aug-21.
 * ismailhossainsepon@gmail.com
 */
class MovieRepository  (private val api: ApiService) : SafeApiRequest() {

    suspend fun getMovieLisst() : MovieResponse {
        return apiRequest { api.getMovieList("eb8aa6f914f794f711fb1841fb141f12") }
    }

    suspend fun getTvshowList() : TvShowResponse {
        return apiRequest { api.gettvShowList("eb8aa6f914f794f711fb1841fb141f12") }
    }

    suspend fun getsearchList(query : String) : MovieSearchResponse {
        return apiRequest { api.getSearchList("eb8aa6f914f794f711fb1841fb141f12", query) }
    }


    suspend fun getsearchtvList(query : String) : MovieSearchResponse {
        return apiRequest { api.getSearchtvList("eb8aa6f914f794f711fb1841fb141f12", query) }
    }


}