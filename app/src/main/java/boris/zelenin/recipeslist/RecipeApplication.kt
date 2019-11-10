package boris.zelenin.recipeslist

import android.app.Application
import boris.zelenin.recipeslist.di.ApplicationComponent
import boris.zelenin.recipeslist.di.DaggerApplicationComponent
import boris.zelenin.recipeslist.di.DaggerComponentProvider
import com.facebook.drawee.backends.pipeline.Fresco
import timber.log.Timber

class RecipeApplication : Application(), DaggerComponentProvider {
    override val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()

        setupTimber()
        setupFresco()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    private fun setupFresco() {
        Fresco.initialize(getApplicationContext())
    }
}