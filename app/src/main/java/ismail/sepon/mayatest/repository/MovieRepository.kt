package ismail.sepon.mayatest.repository

import ismail.sepon.mayatest.network.ApiService
import ismail.sepon.mayatest.network.SafeApiRequest
import ismail.sepon.mayatest.pojo.*
import okhttp3.ResponseBody
import retrofit2.Response


/**
 * Created by MD ISMAIL HOSSAIN SEPON on 13-Aug-21.
 * ismailhossainsepon@gmail.com
 */
class MovieRepository  (private val api: ApiService) : SafeApiRequest() {

    private val API_KEY = "eb8aa6f914f794f711fb1841fb141f12"

    suspend fun getMovieLisst() : MovieResponse {
        return apiRequest { api.getMovieList(API_KEY) }
    }

    suspend fun getTvshowList() : TvShowResponse {
        return apiRequest { api.gettvShowList(API_KEY) }
    }

    suspend fun getsearchList(query : String) : MovieSearchResponse {
        return apiRequest { api.getSearchList(API_KEY, query) }
    }


    suspend fun getsearchtvList(query : String) : MovieSearchResponse {
        return apiRequest { api.getSearchtvList(API_KEY, query) }
    }

    suspend fun getMovieDetails( url : String) : MovieDetailsResponse {
        return apiRequest {api.getMovieDetails(url, API_KEY) }
    }
    suspend fun getTvDetails( url : String) : TvDetailsResponse {
        return apiRequest {api.getTvDetails(url, API_KEY) }
    }


}