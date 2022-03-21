package ismail.sepon.katex.controller

import android.app.Application
import ismail.sepon.katex.factory.MovieDetailsFactory
import ismail.sepon.katex.factory.MovieFactory
import ismail.sepon.katex.factory.TvDetailsFactory
import ismail.sepon.katex.network.ApiService
import ismail.sepon.katex.network.NetworkConnectionInterceptor
import ismail.sepon.katex.repository.MovieRepository
import ismail.sepon.katex.viewmodel.DetailsViewModel
import ismail.sepon.katex.viewmodel.ListViewModel
import ismail.sepon.katex.viewmodel.TvDetailsViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


/**
 * Created by MD ISMAIL HOSSAIN SEPON on 14-Aug-21.
 * ismailhossainsepon@gmail.com
 */
@SuppressWarnings("ALL")
class AppController: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@AppController))

        bind() from singleton {String()}

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { ApiService(instance()) }
        bind() from singleton { MovieRepository(instance()) }


        bind() from provider { MovieFactory(instance()) }
        bind() from provider { ListViewModel(instance()) }



        bind() from provider { MovieDetailsFactory(instance()) }
        bind() from provider { DetailsViewModel(instance()) }


        bind() from provider { TvDetailsFactory(instance()) }
        bind() from provider { TvDetailsViewModel(instance()) }


    }

}