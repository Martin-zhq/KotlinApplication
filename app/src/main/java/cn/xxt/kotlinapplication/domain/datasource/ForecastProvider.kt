package cn.xxt.kotlinapplication.domain.datasource

import cn.xxt.kotlinapplication.data.db.ForecastDb

import cn.xxt.kotlinapplication.data.server.ForecastServer
import cn.xxt.kotlinapplication.domain.model.Forecast
import cn.xxt.kotlinapplication.domain.model.ForecastList
import cn.xxt.kotlinapplication.extensions.firstResult

class ForecastProvider(val sources: List<ForecastDataSource> =
                               SOURCES) {
    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES = listOf(ForecastDb(), ForecastServer())
    }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList = requestToSources {
        val res = it.requestForecastByZipCode(zipCode, todayTimeSpan())
        if (null != res && res.size() >= days) res else null
    }

    fun requestForecast(id: Long): Forecast = requestToSources {
        it.requestDayForecast(id)
    }


    private fun requestSource(source: ForecastDataSource, days: Int, zipCode: Long): ForecastList? {
        val res = source.requestForecastByZipCode(zipCode, todayTimeSpan())
        return if (null != res && res.size() >= days) res else null
    }

    private fun <T: Any> requestToSources(f: (ForecastDataSource) -> T?): T
            = sources.firstResult{f(it)}


    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS
}