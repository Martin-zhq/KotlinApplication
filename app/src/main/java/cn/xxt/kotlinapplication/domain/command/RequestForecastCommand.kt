package cn.xxt.kotlinapplication.domain.command

import cn.xxt.kotlinapplication.domain.datasource.ForecastProvider
import cn.xxt.kotlinapplication.domain.model.ForecastList

class RequestForecastCommand(val zipCode: Long, val forecastProvider: ForecastProvider =
        ForecastProvider()): Command<ForecastList> {

    companion object {
        val DAYS = 7
    }

    override fun execute(): ForecastList {
        return forecastProvider.requestByZipCode(zipCode, DAYS)
    }
}