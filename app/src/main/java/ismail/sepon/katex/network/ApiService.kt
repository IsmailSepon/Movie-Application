package ismail.sepon.katex.network

import com.google.gson.GsonBuilder
import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import ismail.sepon.katex.BuildConfig
import ismail.sepon.katex.pojo.*
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by MD ISMAIL HOSSAIN SEPON on 13-Aug-21.
 * ismailhossainsepon@gmail.com
 */
interface ApiService {



    @GET("discover/movie")
    suspend fun getMovieList(@Query("api_key") username: String?) : Response<MovieResponse>




    @GET("discover/tv")
    suspend fun gettvShowList(@Query("api_key") username: String?) : Response<TvShowResponse>



    @GET("search/movie")
    suspend fun getSearchList(@Query("api_key") username: String?,
                              @Query("query") query: String?) : Response<MovieSearchResponse>




    @GET("search/tv")
    suspend fun getSearchtvList(@Query("api_key") username: String?,
                              @Query("query") query: String?) : Response<MovieSearchResponse>





    @GET()
    suspend fun getMovieDetails(@Url  url : String,  @Query("api_key") username: String?) : Response<MovieDetailsResponse>




    @GET()
    suspend fun getTvDetails(@Url  url : String,  @Query("api_key") username: String?) : Response<TvDetailsResponse>











    companion object{
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ) : ApiService {

            val builder = OkHttpClient.Builder()
            if (BuildConfig.DEBUG) {
                builder.addInterceptor(OkHttpProfilerInterceptor())
            }
            val client = builder.build()


            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            val gson = GsonBuilder()
                .setLenient()
                .create()


            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
                .create(ApiService::class.java)
        }
    }
}