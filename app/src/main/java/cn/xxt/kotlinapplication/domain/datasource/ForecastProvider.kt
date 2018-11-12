package cn.xxt.kotlinapplication.domain.datasource

import cn.xxt.kotlinapplication.data.server.ForecastServer
import cn.xxt.kotlinapplication.data.db.ForecastDb
import cn.xxt.kotlinapplication.domain.ForecastList
import cn.xxt.kotlinapplication.extensions.firstResult

class ForecastProvider(val sources: List<ForecastDataSource> =
                               SOURCES) {
    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES = listOf(ForecastDb(), ForecastServer())
    }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList
            = sources.firstResult{ requestSource(it, days, zipCode)}

    private fun requestSource(source: ForecastDataSource, days: Int, zipCode: Long): ForecastList? {
        val res = source.requestForecastByZipCode(zipCode, todayTimeSpan())
        return if (null != res && res.size() >= days) res else null
    }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS
}