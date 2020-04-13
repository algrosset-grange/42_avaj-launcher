package com.avaj.base;

import com.avaj.base.tower.Tower;
import com.avaj.base.weather.WeatherProvider;

public class WeatherTower extends Tower {

	public String getWeather(Coordinates coordinates)
	{
		return WeatherProvider.getProvider().getCurrentWeather(coordinates);
	}

	void changeWeather()
	{
		this.conditionChanged();
	}

}