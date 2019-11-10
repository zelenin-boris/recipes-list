package boris.zelenin.recipeslist

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import timber.log.Timber

class RecipeApplication : Application() {

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