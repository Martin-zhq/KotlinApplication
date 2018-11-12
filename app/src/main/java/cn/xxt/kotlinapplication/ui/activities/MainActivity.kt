package cn.xxt.kotlinapplication.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import cn.xxt.kotlinapplication.R
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.uiThread


class MainActivity : AppCompatActivity() {


    private val items = listOf(
            "周一 - 晴 - 31/17",
            "周二 - 多云 - 30/15",
            "周一 - 晴 - 29/17",
            "周一 - 雨 - 30/18",
            "周一 - 晴 - 31/17",
            "周一 - 晴 - 31/17",
            "周一 - 晴 - 31/17"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvForecast = findViewById<RecyclerView>(R.id.rv_forecast)
        rvForecast.layoutManager = LinearLayoutManager(this)

        //获取数据
        async() {
//            val result = RequestForecastCommand("94043").execute()
            uiThread {
//                rvForecast.adapter = ForecastListAdapter(result) { toast(it.description) }
            }
        }
    }
}
