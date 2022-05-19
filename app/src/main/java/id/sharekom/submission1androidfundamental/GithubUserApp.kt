package id.sharekom.submission1androidfundamental

import android.app.Application
import id.sharekom.submission1androidfundamental.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GithubUserApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger(Level.NONE)
            androidContext(this@GithubUserApp)
            modules(appModules)
        }
    }
}