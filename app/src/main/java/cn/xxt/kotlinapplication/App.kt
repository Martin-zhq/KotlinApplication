package cn.xxt.kotlinapplication

import android.app.Application
import cn.xxt.kotlinapplication.delegate.NotNullSinglValueVar

class App: Application() {

    companion object {
        var instance: App by NotNullSinglValueVar()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}