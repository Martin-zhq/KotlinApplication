package cn.xxt.kotlinapplication.domain.datasource

import cn.xxt.kotlinapplication.domain.model.Forecast
import cn.xxt.kotlinapplication.domain.model.ForecastList

interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?

    fun requestDayForecast(id: Long): Forecast?
}