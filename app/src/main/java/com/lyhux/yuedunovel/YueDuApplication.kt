package com.lyhux.yuedunovel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.multidex.MultiDex
import com.lyhux.yuedunovel.api.HttpConfig
import com.lyhux.yuedunovel.data.http.KcHttp
import com.lyhux.yuedunovel.koin.Injector
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class YueDuApplication : Application() {
    /**
     * https://developer.android.com/studio/build/multidex
     */
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        initHttp()
        initKoin()
        registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ) {
                    val w: Window = activity.window
                    w.setFlags(
                            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                    )
                }
                // activity.setTheme(ThemeUtils.getSelectTheme(SpSource.appTheme))
                val appCompatActivity= activity as AppCompatActivity

                appCompatActivity.supportActionBar?.setDisplayShowTitleEnabled(false);


            }

            override fun onActivityPaused(activity: Activity) {

            }

            override fun onActivityResumed(activity: Activity) {
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityDestroyed(activity: Activity) {
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityStopped(activity: Activity) {

            }

        })
    }

    private fun initHttp() {
        KcHttp.okHttpClientBuilder
            .addInterceptor(
                HttpLoggingInterceptor()
                   .apply { level = HttpLoggingInterceptor.Level.BODY }
            )
            .addInterceptor(HttpConfig.tokenInterceptor())
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@YueDuApplication)
            modules(
                Injector.serviceModule,
                Injector.viewModels
            )
        }
    }


}