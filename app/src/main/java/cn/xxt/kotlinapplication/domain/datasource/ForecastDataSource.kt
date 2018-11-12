package cn.xxt.kotlinapplication.domain.datasource

import cn.xxt.kotlinapplication.domain.ForecastList

interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?
}