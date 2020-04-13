package com.avaj.base.machins;

import com.avaj.base.WeatherTower;

public interface Flyable {
	void updateConditions();
	void registerTower(WeatherTower weatherTower);
}