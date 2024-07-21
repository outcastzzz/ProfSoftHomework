package com.example.lesson6.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.lesson6.App
import com.example.lesson6.R
import com.example.lesson6.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as App).applicationComponent.inject(this)
        enableEdgeToEdge()
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        observeViewModel()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun observeViewModel() {
        viewModel.screenState.observe(this) { state ->
            with(binding) {
                layoutTopBar.visibility = View.GONE
                layoutTempInfo.visibility = View.GONE
                layoutWindInfo.visibility = View.GONE
                layoutConditionInfo.visibility = View.GONE
                textViewProfileHeader.visibility = View.GONE
                textViewError.visibility = View.GONE

                when (state) {
                    MainScreenState.Error -> {
                        textViewError.visibility = View.VISIBLE
                        layoutTopBar.visibility = View.GONE
                        layoutTempInfo.visibility = View.GONE
                        layoutWindInfo.visibility = View.GONE
                        layoutConditionInfo.visibility = View.GONE
                        progressBarLoading.visibility = View.GONE
                        textViewProfileHeader.visibility = View.GONE
                    }

                    MainScreenState.Loading -> {
                        progressBarLoading.visibility = View.VISIBLE
                        layoutTopBar.visibility = View.GONE
                        layoutTempInfo.visibility = View.GONE
                        layoutWindInfo.visibility = View.GONE
                        layoutConditionInfo.visibility = View.GONE
                        textViewProfileHeader.visibility = View.GONE
                        textViewError.visibility = View.GONE
                    }

                    is MainScreenState.SuccessfulLoaded -> {

                        val weather = state.weather

                        val tempC = (weather.main.temp - TEMP_KELVIN).toInt().toString()
                        val feelsLikeTempC =
                            (weather.main.feelsLike - TEMP_KELVIN).toInt().toString()
                        val weatherDescription = weather.weather[0].description
                        val minTemp = weather.main.tempMin.toString()
                        val maxTemp = weather.main.tempMax.toString()
                        val pressure = weather.main.pressure.toString()
                        val humidity = weather.main.humidity.toString()
                        val visibility = weather.visibility.toString()
                        val windSpeed = weather.wind.speed.toString()
                        val gust = weather.wind.gust.toString()
                        val direction = calculateWindDirection(weather.wind.deg)

                        textViewCityName.text = weather.name
                        textViewTemperature.text = getString(R.string.temperature, tempC)
                        textViewFeelsLikeTemperature.text = getString(
                            R.string.feels_like_and_description,
                            feelsLikeTempC,
                            weatherDescription
                        )
                        textViewMinAndMaxTemperature.text =
                            getString(R.string.temp_min_and_max, minTemp, maxTemp)
                        textViewPressure.text = getString(R.string.pressure, pressure)
                        textViewHumidity.text = getString(R.string.humidity, humidity)
                        textViewVisibility.text = getString(R.string.visibility, visibility)
                        textViewWindSpeed.text = getString(R.string.wind_speed_m_s, windSpeed)
                        textViewGust.text = getString(R.string.gust_m_s, gust)
                        textViewWindDirection.text = getString(R.string.direction, direction)

                        layoutTopBar.visibility = View.VISIBLE
                        layoutTempInfo.visibility = View.VISIBLE
                        layoutWindInfo.visibility = View.VISIBLE
                        layoutConditionInfo.visibility = View.VISIBLE
                        textViewProfileHeader.visibility = View.VISIBLE
                        textViewError.visibility = View.GONE
                        progressBarLoading.visibility = View.GONE

                    }
                }
            }
        }
    }

    private fun calculateWindDirection(deg: Int): String {
        return when (deg) {
            in 0..22 -> "N"
            in 23..67 -> "NE"
            in 68..112 -> "E"
            in 113..157 -> "SE"
            in 158..202 -> "S"
            in 203..247 -> "SW"
            in 248..292 -> "W"
            in 293..337 -> "NW"
            in 338..360 -> "N"
            else -> "?"
        }
    }

    companion object {

        private const val TEMP_KELVIN = 273.15

    }

}