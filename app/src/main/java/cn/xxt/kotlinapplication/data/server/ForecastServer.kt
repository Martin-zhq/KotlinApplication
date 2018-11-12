package cn.xxt.kotlinapplication.data.server

import cn.xxt.kotlinapplication.data.db.ForecastDb
import cn.xxt.kotlinapplication.domain.model.ForecastList
import cn.xxt.kotlinapplication.domain.datasource.ForecastDataSource

class ForecastServer(val dataMapper: ServerDataMapper = ServerDataMapper(),
                     val forecastDb: ForecastDb = ForecastDb()): ForecastDataSource {

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }
}