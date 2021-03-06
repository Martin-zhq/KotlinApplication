package cn.xxt.kotlinapplication.data.db

import cn.xxt.kotlinapplication.domain.datasource.ForecastDataSource
import cn.xxt.kotlinapplication.domain.model.Forecast
import cn.xxt.kotlinapplication.domain.model.ForecastList
import cn.xxt.kotlinapplication.extensions.clear
import cn.xxt.kotlinapplication.extensions.parseList
import cn.xxt.kotlinapplication.extensions.parseOpt
import cn.xxt.kotlinapplication.extensions.toVarargArray
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.coroutines.experimental.selects.select
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class ForecastDb(
        val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
        val dataMapper: DbDataMapper = DbDataMapper()): ForecastDataSource {

    override fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {

        val dailyRequest = "${DayForecastTable.CITY_ID} = ?" +
                "AND ${DayForecastTable.DATE} >= ?"

        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple(dailyRequest, zipCode.toString(), date.toString())
                .parseList{ DayForecast(HashMap(it)) }

        val city = select(CityForecastTable.NAME)
                .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
                .parseOpt { CityForecast(HashMap(it), dailyForecast) }

        if (null != city) dataMapper.convertToDomain(city) else null
    }

    override fun requestDayForecast(id: Long): Forecast? {
        val forecast = select(DayForecastTable.NAME).byId(id).
                parseOpt{ DayForecast(HashMap(it))}
        if (forecast != null )dataMapper.convertDayToDomain(forecast) else null
    }


    fun  saveForecast(forecast: ForecastList) = forecastDbHelper.use {
        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)

        with(dataMapper.convertFromDomain(forecast)) {
            insert(CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach {
                insert(DayForecastTable.NAME, *it.map.toVarargArray())
            }
        }
    }
}