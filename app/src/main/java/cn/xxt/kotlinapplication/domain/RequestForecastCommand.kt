package cn.xxt.kotlinapplication.domain

import cn.xxt.kotlinapplication.ForecastRequest

class RequestForecastCommand(private val zipCode: String): Command<ForecastList> {

    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(
                forecastRequest.execute()
        )
    }
}