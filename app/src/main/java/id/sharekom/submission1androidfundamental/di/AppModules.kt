package id.sharekom.submission1androidfundamental.di

import id.sharekom.submission1androidfundamental.BuildConfig
import id.sharekom.submission1androidfundamental.data.GithubRepository
import id.sharekom.submission1androidfundamental.data.IGithubRepository
import id.sharekom.submission1androidfundamental.data.remote.ApiService
import id.sharekom.submission1androidfundamental.data.remote.RemoteDataSource
import id.sharekom.submission1androidfundamental.ui.GithubViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networking = module {
    single<ApiService> {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }

    single {
        RemoteDataSource(get())
    }
}

val repository = module {
    factory<IGithubRepository> {
        GithubRepository(get())
    }
}

val viewModel = module {
    viewModel { GithubViewModel(get()) }
}

val appModules = listOf(networking, repository, viewModel)