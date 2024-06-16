package ru.androidheroes.kamchatka

import android.app.Application
import androidx.core.app.NotificationManagerCompat
import com.yandex.mapkit.MapKitFactory
import ru.androidheroes.kamchatka.ui.feed.NotificationHelper
import timber.log.Timber

class KamchatkaHackatonApp : Application() {

    override fun onCreate() {
        super.onCreate()
        ru.androidheroes.kamchatka.KamchatkaHackatonApp.Companion.instance = this
        initDebugTools()

        NotificationHelper.createNotificationChannel(
            this,
            NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            getString(ru.androidheroes.kamchatka.R.string.app_name), "Лидеры цифровой трансформации."
        )

        MapKitFactory.setApiKey(BuildConfig.MAP_API_KEY);
    }

    private fun initDebugTools() {
        if (ru.androidheroes.kamchatka.BuildConfig.DEBUG) {
            initTimber()
        }
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        var instance: ru.androidheroes.kamchatka.KamchatkaHackatonApp? = null
            private set
    }
}
