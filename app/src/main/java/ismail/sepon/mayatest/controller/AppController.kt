package ismail.sepon.mayatest.controller

import android.app.Application
import ismail.sepon.mayatest.factory.MovieFactory
import ismail.sepon.mayatest.network.ApiService
import ismail.sepon.mayatest.network.NetworkConnectionInterceptor
import ismail.sepon.mayatest.repository.MovieRepository
import ismail.sepon.mayatest.viewmodel.ListViewModel
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


//        bind() from provider { DoodleViewModelFactory(instance()) }
//        bind() from provider { DoodleViewModel(instance()) }


    }

}